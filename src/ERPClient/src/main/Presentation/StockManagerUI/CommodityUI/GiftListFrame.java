package main.Presentation.StockManagerUI.CommodityUI;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import main.Presentation.MainUI.MainFrame;
import main.Presentation.MainUI.MainUIController;
import main.Presentation.StockManagerUI.CommodityFrame;
import main.Presentation.StockManagerUI.GoodsManageFrame;
import main.Presentation.StockManagerUI.StockManagerHomeFrame;
import main.Presentation.StockManagerUI.CommodityUI.OverflowListFrame.Goods;
import main.RMI.RemoteHelper;
import main.VO.CommodityReciptVO;
import main.VO.GoodsVO;
import main.utility.ResultMessage;

/**
 * 库存管理人员填写赠送单界面的控制类
 * @author 周正伟
 *
 */
public class GiftListFrame extends MainUIController{
	@FXML
	private ImageView head;
	
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
	TableColumn<Goods, String> giftamountColumn;
	
	static ArrayList<GoodsVO> gifts;
	
	ObservableList<Goods> giftlist;
	
	ArrayList<CommodityReciptVO> submitgiftsvo = new ArrayList<>();
	
	/**
	 * 库存管理人员主界面的初始化方法
	 * @return 返回加载好的界面
	 * @param goods 预填写单据绑定商品vo的list
	 */
	public static Parent init(ArrayList<GoodsVO> goods){
		gifts = goods;
		try {
			GridPane giftlist = FXMLLoader.load(GiftListFrame.class.getResource("GiftListFrameFXML.fxml"));
			return giftlist;
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
		loadlist();
		goodstable.setEditable(true);
		giftamountColumn.setCellFactory(TextFieldTableCell.<Goods>forTableColumn());
		giftamountColumn.setOnEditCommit(
			    (CellEditEvent<Goods, String> t) -> {
			        ((Goods) t.getTableView().getItems().get(
			            t.getTablePosition().getRow())
			            ).setGiftamount(t.getNewValue());
//			        for (int i = 0; i < warnedlist.size(); i++) {
//						if (goodstable.getSelectionModel().getSelectedItem().getName().equals(warnedlist.get(i).getName())) {
//							warnedlist.get(i).setWanringamount(t.getNewValue());
//							System.out.println(warnedlist.get(i).getName()+warnedlist.get(i).getWanringamount());
//						}
//					}
			});
	}
	
	/**
	 * 加载商品呢列表的方法
	 */
	public void loadlist(){
		for (int i = 0; i < gifts.size(); i++) {
			System.out.println(gifts.get(i).getName()+gifts.get(i).getAlertAmounts()+"      2");
		}
		giftlist= FXCollections.observableArrayList();
		
        idColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));      
        versionColumn.setCellValueFactory(new PropertyValueFactory<>("version"));      
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));      
        giftamountColumn.setCellValueFactory(new PropertyValueFactory<>("giftamount"));
        for (int i = 0; i < gifts.size(); i++) {
			giftlist.add(new Goods(
					gifts.get(i).getName(),
					gifts.get(i).getID(),
					gifts.get(i).getVersion(),
					gifts.get(i).getAmounts()+"",
					"0"));
		}
        
        goodstable.setItems(giftlist);
	}
	
	/**
	 * 确认提赠送单按钮的监听
	 */
	public void submitgiftslist(){
		ResultMessage resultMessage = null;
		
		for (int i = 0; i < giftlist.size(); i++) {
			System.out.println(giftlist.get(i).getName()+" "+giftlist.get(i).getGiftamount());
//			resultMessage = RemoteHelper.getCommodityReciptBLService().setWarningList(vo);
			submitgiftsvo.add(new CommodityReciptVO("ZS",
					giftlist.get(i).getName(),
					giftlist.get(i).getID(),
					Integer.parseInt(giftlist.get(i).getGiftamount()),
					"会被覆盖",
					"Unchecked",
					user.getName()));
		}
		for (int i = 0; i < submitgiftsvo.size(); i++) {
			try {
				resultMessage = RemoteHelper.getCommodityReciptBLService().setGiftList(submitgiftsvo.get(i));
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (resultMessage.equals(ResultMessage.SUCCESS)) {
				System.out.println("提交成功");
				MainFrame.setSceneRoot(GoodsManageFrame.init());
			}
		}
	}
	
	/**
	 * 返回库存管理人员主界面按钮的监听
	 */
	@FXML
	protected void returnStockManagerHomeHandle(ActionEvent event) {
        MainFrame.setSceneRoot(StockManagerHomeFrame.init());
    }
	
	/**
	 * 返回库存管理人员主界面按钮的监听
	 */
	@FXML
	protected void returnGoodsManageFrameHandle(ActionEvent event) {
        MainFrame.setSceneRoot(GoodsManageFrame.init());
    }
	
	/**
	 * 用于商品table绑定的数据类
	 * @author 周正伟
	 */
	public class Goods{
		
		private String name;
		private String ID;
		private String version;
		private String amount;
		private String giftamount;
		
		

		public Goods(String name, String iD, String version, String amount, String giftamount) {
			super();
			this.name = name;
			ID = iD;
			this.version = version;
			this.amount = amount;
			this.giftamount = giftamount;
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

		public String getGiftamount() {
			return giftamount;
		}

		public void setGiftamount(String giftamount) {
			this.giftamount = giftamount;
		}



		
	}
}
