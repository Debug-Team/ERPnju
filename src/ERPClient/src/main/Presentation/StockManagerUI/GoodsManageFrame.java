package main.Presentation.StockManagerUI;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import main.Presentation.MainUI.MainFrame;
import main.Presentation.MainUI.MainUIController;
import main.Presentation.StockManagerUI.CommodityUI.DamageListFrame;
import main.Presentation.StockManagerUI.CommodityUI.GiftListFrame;
import main.Presentation.StockManagerUI.CommodityUI.OverflowListFrame;
import main.Presentation.StockManagerUI.CommodityUI.WarningListFrame;
import main.Presentation.StockManagerUI.GoodsManageDetail.GoodsAddFrame;
import main.Presentation.StockManagerUI.GoodsManageDetail.GoodsModifyFrame;
import main.RMI.RemoteHelper;
import main.VO.CategoryVO;
import main.VO.GoodsVO;
import main.utility.ResultMessage;

/**
 * 库存管理人员商品管理界面的控制类
 * @author 周正伟
 *
 */
public class GoodsManageFrame extends MainUIController{
	
	@FXML
	private ImageView head;
	
	//商品树状图UI控件
	@FXML
	TreeTableView<Goods> goodstreetable;
	
	@FXML
	TreeTableColumn<Goods, String> nameCol;
	
	@FXML
	TreeTableColumn<Goods, String> idCol;
	
	@FXML
	TreeTableColumn<Goods, String> versionCol;
	
	@FXML
	TreeTableColumn<Goods, String> amountCol;
	
	@FXML
	TreeTableColumn<Goods, String> bidCol;
	
	@FXML
	TreeTableColumn<Goods, String> retailPriceCol;
	
	@FXML
	TreeTableColumn<Goods, String> recentBidCol;
	
	@FXML
	TreeTableColumn<Goods, String> recentretailPriceCol;
	
	@FXML
	TreeTableColumn<Goods, String> warningamountCol;
	
	@FXML
	TreeTableColumn<Goods, String> catagoryCol;
	
	@FXML
	TextField searchkeyword;
	
	ArrayList<Goodscategory> allcategorydata = new ArrayList<>();
	
	ArrayList<TreeItem<Goods>> categorytreeitems = new ArrayList<>();
	
	ArrayList<CategoryVO> allcategory = null;
	
	ArrayList<Goods> allgoodsdata = new ArrayList<>();
	
	ArrayList<TreeItem<Goods>> goodstreeitems = new ArrayList<>();
	
	ArrayList<GoodsVO> allgoods = null;
	
	//商品列表UI控件
	@FXML 
	TableView<Goods> goodstable;
	
	@FXML
	TableColumn<Goods, String> idColumn;
	
	@FXML
	TableColumn<Goods, String> nameColumn;
	
	@FXML
	TableColumn<Goods, String> versionColumn;
	
	@FXML
	TableColumn<Goods, String> amountColumn;
	
	@FXML
	TableColumn<Goods, String> bidColumn;
	
	@FXML
	TableColumn<Goods, String> retailpriceColumn;
	
	@FXML
	TableColumn<Goods, String> recentbidColumn;
	
	@FXML
	TableColumn<Goods, String> recentretailpriceColumn;
	
	@FXML
	TableColumn<Goods, String> catagoryColumn;
	
	ObservableList<Goods> tablelist;
	
	ArrayList<GoodsVO> allgoodssearch = null;
	
	@FXML
	Button modifygood;
	
	@FXML
	Button addgood;
	
	@FXML
	Button deletegoods;
	
	@FXML
	Button warngoods;
	
	@FXML
	Button overflowgoods;
	
	@FXML
	Button damagegoods;
	
	@FXML
	Button giftgoods;
	
	@FXML
	ComboBox<String> searchkeytype;
	
	@FXML
	TabPane tabpane;
	
	@FXML
	Tab manageinfo;
	
	@FXML
	Tab tableinfo;
	
	/**
	 * 库存管理人员主界面的初始化方法
	 * @return 返回加载好的界面
	 */
	public static Parent init(){
		
		try {
			GridPane goodsmanage = FXMLLoader.load(GoodsManageFrame.class.getResource("GoodsManageFrameFXML.fxml"));
			return goodsmanage;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new GridPane();
		
	}
	
	/**
	 * 加载fxml时默认加载的方法，会对所有的商品列表进行初始化
	 */
	@FXML
	public void initialize(){
		loadcategory();
		loadgoods();
		
		nameCol.setCellValueFactory(
	            (TreeTableColumn.CellDataFeatures<Goods, String> param) -> 
	            new ReadOnlyStringWrapper(param.getValue().getValue().getName())
	        );	

		idCol.setCellValueFactory(
	            (TreeTableColumn.CellDataFeatures<Goods, String> param) -> 
	            new ReadOnlyStringWrapper(param.getValue().getValue().getID())
	        );
		
		versionCol.setCellValueFactory(
	            (TreeTableColumn.CellDataFeatures<Goods, String> param) -> 
	            new ReadOnlyStringWrapper(param.getValue().getValue().getVersion())
	        );
		
		amountCol.setCellValueFactory(
	            (TreeTableColumn.CellDataFeatures<Goods, String> param) -> 
	            new ReadOnlyStringWrapper(param.getValue().getValue().getAmount())
	        );
		
		bidCol.setCellValueFactory(
	            (TreeTableColumn.CellDataFeatures<Goods, String> param) -> 
	            new ReadOnlyStringWrapper(param.getValue().getValue().getBid())
	        );
		
		retailPriceCol.setCellValueFactory(
	            (TreeTableColumn.CellDataFeatures<Goods, String> param) -> 
	            new ReadOnlyStringWrapper(param.getValue().getValue().getRetailPrice())
	        );
		
		recentBidCol.setCellValueFactory(
	            (TreeTableColumn.CellDataFeatures<Goods, String> param) -> 
	            new ReadOnlyStringWrapper(param.getValue().getValue().getRecentBid())
	        );
		
		recentretailPriceCol.setCellValueFactory(
	            (TreeTableColumn.CellDataFeatures<Goods, String> param) -> 
	            new ReadOnlyStringWrapper(param.getValue().getValue().getRecentRetailPrice())
	        );
		
		warningamountCol.setCellValueFactory(
	            (TreeTableColumn.CellDataFeatures<Goods, String> param) -> 
	            new ReadOnlyStringWrapper(param.getValue().getValue().getWarningamount())
	        );
		
		catagoryCol.setCellValueFactory(
	            (TreeTableColumn.CellDataFeatures<Goods, String> param) -> 
	            new ReadOnlyStringWrapper(param.getValue().getValue().getCatagory())
	        );
		loadtable();
		
		goodstreetable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		//树状列表的监听，选定某些商品后 根据逻辑判断部分按钮是否可操作
		goodstreetable.getSelectionModel().selectedItemProperty().addListener(
                  new ChangeListener<TreeItem <Goods>>() {
                  @Override
                  public void changed(ObservableValue<? extends TreeItem<Goods>> observableValue,
                  TreeItem<Goods> oldItem, TreeItem<Goods> newItem) {
                	if (goodstreetable.getSelectionModel().getSelectedItems().size()==0) {
						modifygood.setDisable(true);
						addgood.setDisable(true);
						deletegoods.setDisable(true);
						warngoods.setDisable(true);
						overflowgoods.setDisable(true);
						damagegoods.setDisable(true);
						giftgoods.setDisable(true);
					}
                	if (goodstreetable.getSelectionModel().getSelectedItems().size()>1) {
						modifygood.setDisable(true);
						addgood.setDisable(true);
						for (int i = 0; i < goodstreetable.getSelectionModel().getSelectedItems().size(); i++) {
							if (goodstreetable.getSelectionModel().getSelectedItems().get(i).getValue().getID().equals("")) {
								deletegoods.setDisable(true);
								warngoods.setDisable(true);
								overflowgoods.setDisable(true);
								damagegoods.setDisable(true);
								giftgoods.setDisable(true);
								break;
							}else {
								deletegoods.setDisable(false);
								warngoods.setDisable(false);
								overflowgoods.setDisable(false);
								damagegoods.setDisable(false);
								giftgoods.setDisable(false);
							}
						}
					}
                	if (goodstreetable.getSelectionModel().getSelectedItems().size()==1) {
                		if (goodstreetable.getSelectionModel().getSelectedItem().getValue().getID().equals("")) {
	                		modifygood.setDisable(true);
	                		addgood.setDisable(true);
	                		deletegoods.setDisable(true);
							warngoods.setDisable(true);
							overflowgoods.setDisable(true);
							damagegoods.setDisable(true);
							giftgoods.setDisable(true);
						}else {
							modifygood.setDisable(false);
							addgood.setDisable(false);
							deletegoods.setDisable(false);
							warngoods.setDisable(false);
							overflowgoods.setDisable(false);
							damagegoods.setDisable(false);
							giftgoods.setDisable(false);
						}
					}
                	if (!goodstreetable.getSelectionModel().getSelectedItem().getValue().getAmount().equals("0")) {
						deletegoods.setDisable(true);
					}else {
					   	giftgoods.setDisable(true);
	                	String idd = goodstreetable.getSelectionModel().getSelectedItem().getValue().getID();
	                	Boolean canbedelete = null;
	                	try {
							canbedelete = RemoteHelper.getGoodsBLService().couldBeDeleted(idd);
						} catch (RemoteException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	                	if (canbedelete == false) {
							deletegoods.setDisable(true);
						}else if(canbedelete == true){
							deletegoods.setDisable(false);
						}
					}

                	
                  }
              });
		

	}
	
	/**
	 * 加载分类树状列表
	 */
	public void loadcategory(){
		
		try {
			allcategory = RemoteHelper.getGoodsCatagoryBLService().categoryAll();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (int i = 0; i < allcategory.size(); i++) {
			allcategorydata.add(new Goodscategory(allcategory.get(i)));

		}
		
		for (int i = 0; i < allcategorydata.size(); i++) {

			categorytreeitems.add(new TreeItem<Goods>(new Goods(allcategorydata.get(i).getMyvalue())));
			categorytreeitems.get(i).setExpanded(true);

		}
		
		for (int i = 0; i < allcategorydata.size(); i++) {
			if (allcategorydata.get(i).getFatherStr().equals("Nofather")) {
				goodstreetable.setRoot(categorytreeitems.get(i));
			}
		}
		
		for (int i = 0; i < allcategorydata.size(); i++) {
			ArrayList<Goodscategory> temp = allcategorydata.get(i).getSonscategory();
			for (int j = 0; j < allcategorydata.size(); j++) {
					for (int j2 = 0; j2 < temp.size(); j2++) {
						if (allcategorydata.get(j).getMyvalue().equals(temp.get(j2).getMyvalue())) {
							categorytreeitems.get(i).getChildren().add(categorytreeitems.get(j));

						}
					}			

			}
			temp = null;
		}	
		
		
		
		
	}
	
	/**
	 * 加载商品树状列表
	 */
	public void loadgoods(){
		
		try {
			allgoods = RemoteHelper.getGoodsBLService().goodsFind("", "name");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (int i = 0; i < allgoods.size(); i++) {
			allgoodsdata.add(new Goods(
					allgoods.get(i).getName(),
					allgoods.get(i).getID(),
					allgoods.get(i).getVersion(),
					allgoods.get(i).getAmounts()+"", 
					allgoods.get(i).getBid()+"",
					allgoods.get(i).getRetailPrice()+"",
					allgoods.get(i).getRecentBid()+"",
					allgoods.get(i).getRecentRetailPrice()+"",
					allgoods.get(i).getCatagory(),
					allgoods.get(i).getAlertAmounts()+""));
		}
		
		for (int i = 0; i < allgoodsdata.size(); i++) {
			goodstreeitems.add(new TreeItem<Goods>(new Goods(
					allgoodsdata.get(i).getName(),
					allgoodsdata.get(i).getID(),
					allgoodsdata.get(i).getVersion(),
					allgoodsdata.get(i).getAmount(),
					allgoodsdata.get(i).getBid(),
					allgoodsdata.get(i).getRetailPrice(),
					allgoodsdata.get(i).getRecentBid(),
					allgoodsdata.get(i).getRecentRetailPrice(),
					allgoodsdata.get(i).getCatagory(),
					allgoodsdata.get(i).getWarningamount())));
			goodstreeitems.get(i).setExpanded(true);
		}
		
		for (int i = 0; i < allcategorydata.size(); i++) {
//			if (allcategorydata.get(i).getHasitems()==true) {
				if (allcategorydata.get(i).getSonscategory().size()==0) {
					for (int j = 0; j < allgoods.size(); j++) {
						if (allgoods.get(j).getCatagory().equals(allcategorydata.get(i).getMyvalue())) {
							categorytreeitems.get(i).getChildren().add(goodstreeitems.get(j));
						}
					}
				}
//			}
		}
		
	}
	
	/**
	 * 加载商品列表
	 */
	public void loadtable(){
		
		tablelist= FXCollections.observableArrayList();
		
        idColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));      
        versionColumn.setCellValueFactory(new PropertyValueFactory<>("version"));      
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));      
        bidColumn.setCellValueFactory(new PropertyValueFactory<>("bid"));      
        retailpriceColumn.setCellValueFactory(new PropertyValueFactory<>("retailPrice"));      
        recentbidColumn.setCellValueFactory(new PropertyValueFactory<>("recentBid"));      
        recentretailpriceColumn.setCellValueFactory(new PropertyValueFactory<>("recentRetailPrice"));      
        catagoryColumn.setCellValueFactory(new PropertyValueFactory<>("catagory"));      
        
        for (int i = 0; i < allgoods.size(); i++) {
			tablelist.add(new Goods(
					allgoods.get(i).getID(),
					allgoods.get(i).getName(),
					allgoods.get(i).getVersion(),
					allgoods.get(i).getAmounts()+"",
					allgoods.get(i).getBid()+"",
					allgoods.get(i).getRetailPrice()+"",
					allgoods.get(i).getRecentBid()+"",
					allgoods.get(i).getRecentRetailPrice()+"",
					allgoods.get(i).getCatagory()));
			
		}
        goodstable.setItems(tablelist);

	}
	
	/**
	 * 返回库存管理人员主界面按钮的监听
	 */
	@FXML
	public void returnStockManagerHomeHandle(ActionEvent event) {
        MainFrame.setSceneRoot(StockManagerHomeFrame.init());
    }
	
	/**
	 * 填写库存报警单界面按钮的监听
	 */
	@FXML
	public void warninglist(ActionEvent event) {
		ArrayList<GoodsVO> goodsselected = new ArrayList<>();
		ObservableList<TreeItem<Goods>> selectedd = goodstreetable.getSelectionModel().getSelectedItems();
		for (int i = 0; i < selectedd.size(); i++) {
			for (int j = 0; j < allgoods.size(); j++) {
				if (selectedd.get(i).getValue().getName().equals(allgoods.get(j).getName())) {
					goodsselected.add(allgoods.get(j));
				}
			}
		}
//		System.out.println(goodsselected.size());
//		for (int i = 0; i < goodsselected.size(); i++) {
//			System.out.println(goodsselected.get(i).getName()+goodsselected.get(i).getID());
//		}
        MainFrame.setSceneRoot(WarningListFrame.init(goodsselected));
    }
	
	/**
	 * 填写库存溢单界面按钮的监听
	 */
	@FXML
	public void overflowlist(ActionEvent event) {
		ArrayList<GoodsVO> goodsselected = new ArrayList<>();
		ObservableList<TreeItem<Goods>> selectedd = goodstreetable.getSelectionModel().getSelectedItems();
		for (int i = 0; i < selectedd.size(); i++) {
			for (int j = 0; j < allgoods.size(); j++) {
				if (selectedd.get(i).getValue().getName().equals(allgoods.get(j).getName())) {
					goodsselected.add(allgoods.get(j));
				}
			}
		}
//		System.out.println(goodsselected.size());
//		for (int i = 0; i < goodsselected.size(); i++) {
//			System.out.println(goodsselected.get(i).getName()+goodsselected.get(i).getID());
//		}
        MainFrame.setSceneRoot(OverflowListFrame.init(goodsselected));
    }
	
	/**
	 * 填写库存溢单界面按钮的监听
	 */
	@FXML
	public void damagelist(ActionEvent event) {
		ArrayList<GoodsVO> goodsselected = new ArrayList<>();
		ObservableList<TreeItem<Goods>> selectedd = goodstreetable.getSelectionModel().getSelectedItems();
		for (int i = 0; i < selectedd.size(); i++) {
			for (int j = 0; j < allgoods.size(); j++) {
				if (selectedd.get(i).getValue().getName().equals(allgoods.get(j).getName())) {
					goodsselected.add(allgoods.get(j));
				}
			}
		}
//		System.out.println(goodsselected.size());
//		for (int i = 0; i < goodsselected.size(); i++) {
//			System.out.println(goodsselected.get(i).getName()+goodsselected.get(i).getID());
//		}
        MainFrame.setSceneRoot(DamageListFrame.init(goodsselected));
    }
	
	/**
	 * 填写库存赠送界面按钮的监听
	 */
	@FXML
	public void giftlist(ActionEvent event) {
		ArrayList<GoodsVO> goodsselected = new ArrayList<>();
		ObservableList<TreeItem<Goods>> selectedd = goodstreetable.getSelectionModel().getSelectedItems();
		for (int i = 0; i < selectedd.size(); i++) {
			for (int j = 0; j < allgoods.size(); j++) {
				if (selectedd.get(i).getValue().getName().equals(allgoods.get(j).getName())) {
					goodsselected.add(allgoods.get(j));
				}
			}
		}
//		System.out.println(goodsselected.size());
//		for (int i = 0; i < goodsselected.size(); i++) {
//			System.out.println(goodsselected.get(i).getName()+goodsselected.get(i).getID());
//		}
        MainFrame.setSceneRoot(GiftListFrame.init(goodsselected));
    }
	
	
	/**
	 * 添加商品按钮的监听
	 */
	public void addgoodHandle(){
		GoodsAddFrame goodsAddFrame = new GoodsAddFrame();
		String father = goodstreetable.getSelectionModel().getSelectedItem().getValue().getCatagory();
//		System.out.println(father);
		goodsAddFrame.init(father);
	}
	
	/**
	 * 修改商品按钮的监听
	 */
	public void modifygoodHandle(){
		GoodsModifyFrame goodsModifyFrame = new GoodsModifyFrame();
		GoodsVO modifyvo = null;
		for (int i = 0; i < allgoods.size(); i++) {
			if (allgoods.get(i).getID().equals(goodstreetable.getSelectionModel().getSelectedItem().getValue().getID())) {
				modifyvo = allgoods.get(i);
			}
		}
		goodsModifyFrame.init(modifyvo);
	}
	
	/**
	 * 删除商品按钮的监听
	 */
	public void deletegoodsHandle(){
		ArrayList<GoodsVO> goodsselected = new ArrayList<>();
		ObservableList<TreeItem<Goods>> selectedd = goodstreetable.getSelectionModel().getSelectedItems();
		for (int i = 0; i < selectedd.size(); i++) {
			for (int j = 0; j < allgoods.size(); j++) {
				if (selectedd.get(i).getValue().getName().equals(allgoods.get(j).getName())) {
					goodsselected.add(allgoods.get(j));
				}
			}
		}
		ResultMessage resultMessage = null;
		for (int i = 0; i < goodsselected.size(); i++) {
			try {
				resultMessage = RemoteHelper.getGoodsBLService().goodsDelete(goodsselected.get(i));
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (resultMessage.equals(ResultMessage.SUCCESS)) {
			System.out.println("删除成功");
		}
		MainFrame.setSceneRoot(GoodsManageFrame.init());
		
	}
	
	/**
	 * 搜索商品textfied的监听
	 */
	public void search(KeyEvent event){
		if (event.getCode().equals(KeyCode.ENTER)) {
				if (searchkeyword.getText().equals("")) {
					MainFrame.setSceneRoot(GoodsManageFrame.init());
				}else {
					
					TreeItem<Goods> searchroot = new TreeItem<Goods>(new Goods("搜索结果"));
					searchroot.setExpanded(true);
					goodstreetable.setRoot(searchroot);
					ArrayList<Goods> allgoodsdatasearch = new ArrayList<>();
					ArrayList<TreeItem<Goods>> goodstreeitemssearch = new ArrayList<>();
//					ArrayList<TreeItem<Goods>> goodstreeitemssearch = new ArrayList<>();
					
					try {
						allgoodssearch = RemoteHelper.getGoodsBLService().goodsFind(searchkeyword.getText(), searchkeytype.getValue().equals("商品名称")?"name":"ID");
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					for (int i = 0; i < allgoodssearch.size(); i++) {
						allgoodsdatasearch.add(new Goods(
								allgoodssearch.get(i).getName(),
								allgoodssearch.get(i).getID(),
								allgoodssearch.get(i).getVersion(),
								allgoodssearch.get(i).getAmounts()+"", 
								allgoodssearch.get(i).getBid()+"",
								allgoodssearch.get(i).getRetailPrice()+"",
								allgoodssearch.get(i).getRecentBid()+"",
								allgoodssearch.get(i).getRecentRetailPrice()+"",
								allgoodssearch.get(i).getCatagory(),
								allgoodssearch.get(i).getAlertAmounts()+""));
					}
					
					for (int i = 0; i < allgoodsdatasearch.size(); i++) {
						goodstreeitemssearch.add(new TreeItem<Goods>(new Goods(
								allgoodsdatasearch.get(i).getName(),
								allgoodsdatasearch.get(i).getID(),
								allgoodsdatasearch.get(i).getVersion(),
								allgoodsdatasearch.get(i).getAmount(),
								allgoodsdatasearch.get(i).getBid(),
								allgoodsdatasearch.get(i).getRetailPrice(),
								allgoodsdatasearch.get(i).getRecentBid(),
								allgoodsdatasearch.get(i).getRecentRetailPrice(),
								allgoodsdatasearch.get(i).getCatagory(),
								allgoodsdatasearch.get(i).getWarningamount())));
						goodstreeitemssearch.get(i).setExpanded(true);
					}
					
					for (int i = 0; i < goodstreeitemssearch.size(); i++) {
						searchroot.getChildren().add(goodstreeitemssearch.get(i));
					}
					
					
				}

		}
	}
	
	/**
	 * 刷新商品列表
	 */
	public void refresh(){
		MainFrame.setSceneRoot(GoodsManageFrame.init());
	}
	
	/**
	 * 进入商品管理界面的按钮监听
	 */
	public void changetomanage(){
		tabpane.getSelectionModel().select(manageinfo);
	}
	
	/**
	 * 切进入商品列表按钮的监听
	 */
	public void changetotable(){
		tabpane.getSelectionModel().select(tableinfo);
	}
	
	
	/**
	 * 用于商品tableview绑定的数据类
	 * @author 周正伟
	 */
	class Goodscategory {
//		private goodscategory rootcategory;
		private ArrayList<Goodscategory> sonscategory = new ArrayList<>();
		private String myvalue;
//		private Boolean epanded;
		private String fatherStr;
		private Boolean hasitems;
		
		
		public Goodscategory(CategoryVO vo) {
			setMyvalue(vo.getMyvalue());
//			setEpanded(vo.getEpanded());
			setFatherStr(vo.getFatherStr());
			setHasitems(vo.getHasGoods());
			ArrayList<CategoryVO> list = vo.getSonscategory();
			ArrayList<Goodscategory> listchange = new ArrayList<>();

				for (int i = 0; i < list.size(); i++) {
					CategoryVO voson= list.get(i);
					Goodscategory changeson = new Goodscategory(voson);
					listchange.add(changeson);
				}
			setSonscategory(listchange);
		}
		
		public String getMyvalue() {
			return myvalue;
		}
		public void setMyvalue(String myvalue) {
			this.myvalue = myvalue;
		}
//		public Boolean getEpanded() {
//			return epanded;
//		}
//		public void setEpanded(Boolean epanded) {
//			this.epanded = epanded;
//		}
		public ArrayList<Goodscategory> getSonscategory() {
			return sonscategory;
		}
		public void setSonscategory(ArrayList<Goodscategory> sonscategory) {
			this.sonscategory = sonscategory;
		}
		
		public void addsons(Goodscategory s){
			this.sonscategory.add(s);
		}

		public String getFatherStr() {
			return fatherStr;
		}

		public void setFatherStr(String fatherStr) {
			this.fatherStr = fatherStr;
		}

		public Boolean getHasitems() {
			return hasitems;
		}

		public void setHasitems(Boolean hasitems) {
			this.hasitems = hasitems;
		}
	    	
	
}
	
	/**
	 * 用于商品tableview绑定的数据类
	 * @author 周正伟
	 */
	public class Goods{
		
		private String name;
		private String ID;
		private String version;
		private String amount;
		private String bid;
		private String retailPrice;
		private String recentBid;
		private String recentRetailPrice;
		private String catagory;
		private String warningamount;
		
		public Goods(String name, String iD, String version, String amount, String bid, String retailPrice,
				String recentBid, String recentRetailPrice, String catagory) {
			super();
			this.name = name;
			ID = iD;
			this.version = version;
			this.amount = amount;
			this.bid = bid;
			this.retailPrice = retailPrice;
			this.recentBid = recentBid;
			this.recentRetailPrice = recentRetailPrice;
			this.catagory = catagory;
		}

		public Goods(String name, String iD, String version, String amount, String bid, String retailPrice,
				String recentBid, String recentRetailPrice) {
			super();
			this.name = name;
			ID = iD;
			this.version = version;
			this.amount = amount;
			this.bid = bid;
			this.retailPrice = retailPrice;
			this.recentBid = recentBid;
			this.recentRetailPrice = recentRetailPrice;
		}

		
		
		public Goods(String name, String iD, String version, String amount, String bid, String retailPrice,
				String recentBid, String recentRetailPrice, String catagory, String warningamount) {
			super();
			this.name = name;
			ID = iD;
			this.version = version;
			this.amount = amount;
			this.bid = bid;
			this.retailPrice = retailPrice;
			this.recentBid = recentBid;
			this.recentRetailPrice = recentRetailPrice;
			this.catagory = catagory;
			this.warningamount = warningamount;
		}

		public Goods(String name) {
			super();
			this.name = name;
			ID= "";
			version= "";
			amount = "";
			bid ="";
			recentBid="";
			recentRetailPrice ="";
			retailPrice ="";
			catagory = "";
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getID() {
			return ID;
		}

		public void setID(String iD) {
			ID = iD;
		}

		public String getVersion() {
			return version;
		}

		public void setVersion(String version) {
			this.version = version;
		}

		public String getAmount() {
			return amount;
		}

		public void setAmount(String amount) {
			this.amount = amount;
		}

		public String getBid() {
			return bid;
		}

		public void setBid(String bid) {
			this.bid = bid;
		}

		public String getRetailPrice() {
			return retailPrice;
		}

		public void setRetailPrice(String retailPrice) {
			this.retailPrice = retailPrice;
		}

		public String getRecentBid() {
			return recentBid;
		}

		public void setRecentBid(String recentBid) {
			this.recentBid = recentBid;
		}

		public String getRecentRetailPrice() {
			return recentRetailPrice;
		}

		public void setRecentRetailPrice(String recentRetailPrice) {
			this.recentRetailPrice = recentRetailPrice;
		}

		public String getCatagory() {
			return catagory;
		}

		public void setCatagory(String catagory) {
			this.catagory = catagory;
		}

		public String getWarningamount() {
			return warningamount;
		}

		public void setWarningamount(String warningamount) {
			this.warningamount = warningamount;
		}
		
		
		
		
	}
}
