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
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import main.Presentation.MainUI.MainFrame;
import main.Presentation.MainUI.MainUIController;
import main.Presentation.StockManagerUI.GoodsManageFrame;
import main.Presentation.StockManagerUI.StockManagerHomeFrame;
import main.RMI.RemoteHelper;
import main.VO.CommodityReciptVO;
import main.VO.GoodsVO;
import main.utility.ResultMessage;

/**
 * 库存管理人员填写报警单界面的控制类
 * @author 周正伟
 *
 */
public class WarningListFrame extends MainUIController{
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
	TableColumn<Goods, String> warningamountColumn;
	
	static ArrayList<GoodsVO> warned;
	
	ObservableList<Goods> warnedlist;
	
	ArrayList<CommodityReciptVO> submitwaningvo = new ArrayList<>();
	
	/**
	 * 库存管理人员主界面的初始化方法
	 * @return 返回加载好的界面
	 * @param goods 预填写单据绑定商品vo的list
	 */
	public static Parent init(ArrayList<GoodsVO> goods){
		warned = goods;
		try {
			GridPane warninglist = FXMLLoader.load(WarningListFrame.class.getResource("WarningListFrameFXML.fxml"));
			return warninglist;
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
		warningamountColumn.setCellFactory(TextFieldTableCell.<Goods>forTableColumn());
		warningamountColumn.setOnEditCommit(
			    (CellEditEvent<Goods, String> t) -> {
			        ((Goods) t.getTableView().getItems().get(
			            t.getTablePosition().getRow())
			            ).setWanringamount(t.getNewValue());
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
		for (int i = 0; i < warned.size(); i++) {
			System.out.println(warned.get(i).getName()+warned.get(i).getAlertAmounts()+"      2");
		}
		warnedlist= FXCollections.observableArrayList();
		
        idColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));      
        versionColumn.setCellValueFactory(new PropertyValueFactory<>("version"));      
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));      
        warningamountColumn.setCellValueFactory(new PropertyValueFactory<>("wanringamount"));
        for (int i = 0; i < warned.size(); i++) {
			warnedlist.add(new Goods(
					warned.get(i).getName(),
					warned.get(i).getID(),
					warned.get(i).getVersion(),
					warned.get(i).getAmounts()+"",
					warned.get(i).getAlertAmounts()+""));
		}
        
        goodstable.setItems(warnedlist);
	}
	
	/**
	 * 确认提交报损单按钮的监听
	 */
	public void submitwarninglist(){
		ResultMessage resultMessage = null;
		
		for (int i = 0; i < warnedlist.size(); i++) {
			System.out.println(warnedlist.get(i).getName()+" "+warnedlist.get(i).getWanringamount());
//			resultMessage = RemoteHelper.getCommodityReciptBLService().setWarningList(vo);
			submitwaningvo.add(new CommodityReciptVO("BJ",
					warnedlist.get(i).getName(),
					warnedlist.get(i).getID(),
					Integer.parseInt(warnedlist.get(i).getWanringamount()),
					"会被覆盖",
					"Unchecked",
					user.getName()));
		}
		for (int i = 0; i < submitwaningvo.size(); i++) {
			try {
				resultMessage = RemoteHelper.getCommodityReciptBLService().setWarningList(submitwaningvo.get(i));
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
	 * 用于商品table绑定的数据类
	 * @author 周正伟
	 */
	public class Goods{
		
		private String name;
		private String ID;
		private String version;
		private String amount;
		private String wanringamount;
		
		


		public Goods(String name, String iD, String version, String amount, String wanringnumber) {
			super();
			this.name = name;
			ID = iD;
			this.version = version;
			this.amount = amount;
			this.setWanringamount(wanringnumber);
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

		public String getWanringamount() {
			return wanringamount;
		}

		public void setWanringamount(String wanringamount) {
			this.wanringamount = wanringamount;
		}


		
		
		
	}
}
