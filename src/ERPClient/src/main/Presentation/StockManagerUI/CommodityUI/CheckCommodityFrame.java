package main.Presentation.StockManagerUI.CommodityUI;

import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Stack;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import main.Presentation.MainUI.MainFrame;
import main.Presentation.MainUI.MainUIController;
import main.Presentation.StockManagerUI.CommodityFrame;
import main.Presentation.StockManagerUI.StockManagerHomeFrame;
import main.Presentation.StockManagerUI.GoodsManageFrame.Goods;
import main.RMI.RemoteHelper;
import main.VO.CategoryVO;
import main.VO.GoodsVO;
import main.utility.ResultMessage;

/**
 * 库存管理人员库存盘点界面的控制类
 * @author 周正伟
 *
 */
public class CheckCommodityFrame extends MainUIController{
	@FXML
	private ImageView head;
	
	String nowdate;
	@FXML
	TextField date;
	
	@FXML 
	TableView<Goods> table;
	
	@FXML
	TableColumn<Goods, String> rowCol;
	
	@FXML
	TableColumn<Goods, String> nameCol;
		
	@FXML
	TableColumn<Goods, String> versionCol;
	
	@FXML
	TableColumn<Goods, String> amountCol;
	
	@FXML
	TableColumn<Goods, String> averageCol;
	
	ArrayList<GoodsVO> allgoods = null;
	
	ObservableList<Goods> tablelist ;
	
	
	/**
	 * 库存管理人员主界面的初始化方法
	 * @return 返回加载好的界面
	 */
	public static Parent init(){
		
		try {
			GridPane checkcommodity = FXMLLoader.load(CheckCommodityFrame.class.getResource("CheckCommodityFrameFXML.fxml"));
			return checkcommodity;
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
		
	    Date now=new Date();
	    SimpleDateFormat matter1=new SimpleDateFormat("yyyy-MM-dd");
	    nowdate = matter1.format(now);
	    date.setText(nowdate);
	    
	    try {
			allgoods = RemoteHelper.getGoodsBLService().goodsFind("", "name");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    tablelist= FXCollections.observableArrayList();
	    
	    rowCol.setCellValueFactory(new PropertyValueFactory<>("row"));
	    nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
	    versionCol.setCellValueFactory(new PropertyValueFactory<>("version"));
	    amountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
	    averageCol.setCellValueFactory(new PropertyValueFactory<>("average"));
	    
	    for (int i = 0; i < allgoods.size(); i++) {
			tablelist.add(new Goods(i+1+"",
					allgoods.get(i).getName(),
					allgoods.get(i).getVersion(),
					allgoods.get(i).getAmounts()+"",
					allgoods.get(i).getAvgValue()+""));
		}
	  
	    
	    System.out.println(tablelist.size());
	    
	    table.setItems(tablelist);
	}

	/**
	 * 导出excel的方法
	 */
	@FXML
	private void exportExcel() {
		FileChooser fileChooser = new FileChooser();
		File file= fileChooser.showSaveDialog(new Stage());
		String name = file.getName()+".xls";
		String path = file.getParentFile().getAbsolutePath();
		System.out.println(name);
		System.out.println(path);
//		调导出excel的接口
		ResultMessage resultMessage = null;
		try {
			resultMessage = RemoteHelper.getCommodityBLService().checkCommodityInfoToExcel(name, path);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	protected void returnCommodityFrameHandle(ActionEvent event) {
        MainFrame.setSceneRoot(CommodityFrame.init());
    }
	

	/**
	 * 用于商品table绑定的数据类
	 * @author 周正伟
	 */
	public class Goods{
		
		private String row;
		private String name;
		private String version;
		private String amount;
		private String average;
		
		public Goods(String row,String name, String version, String amount, String average) {
			super();
			this.row = row;
			this.name = name;
			this.version = version;
			this.amount = amount;
			this.average = average;
		}
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
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
		public String getAverage() {
			return average;
		}
		public void setAverage(String average) {
			this.average = average;
		}

		public String getRow() {
			return row;
		}

		public void setRow(String row) {
			this.row = row;
		}
		
		
		
		
		
	}
}
