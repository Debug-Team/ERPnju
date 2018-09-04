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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import main.Presentation.MainUI.MainFrame;
import main.Presentation.MainUI.MainUIController;
import main.Presentation.StockManagerUI.CommodityFrame;
import main.Presentation.StockManagerUI.StockManagerHomeFrame;
import main.RMI.RemoteHelper;
import main.VO.CommodityInfoVO;
import main.VO.ReciptGoodsVO;

/**
 * 库存管理人员库存盘点界面的控制类
 * @author 周正伟
 *
 */
public class ViewCommodityFrame extends MainUIController{
	@FXML
	private ImageView head;
	
	@FXML
	DatePicker startdate;
	
	@FXML
	DatePicker enddate;
	
	@FXML
	TableView<CheckedGoods> comintable;
	
	@FXML
	TableColumn<CheckedGoods, String> cominnameCol;
	
	@FXML
	TableColumn<CheckedGoods, String> cominversionCol;
	
	@FXML
	TableColumn<CheckedGoods, String> cominidCol;
	
	@FXML
	TableColumn<CheckedGoods, String> cominamountCol;
	
	@FXML
	TableColumn<CheckedGoods, String> comintotalpriceCol;
	
	@FXML
	TextField cominTotalamount;
	
	@FXML
	TextField cominSum;
	
	@FXML
	TableView<CheckedGoods> comouttable;
	
	@FXML
	TableColumn<CheckedGoods, String> comoutnameCol;
	
	@FXML
	TableColumn<CheckedGoods, String> comoutversionCol;
	
	@FXML
	TableColumn<CheckedGoods, String> comoutidCol;
	
	@FXML
	TableColumn<CheckedGoods, String> comoutamountCol;
	
	@FXML
	TableColumn<CheckedGoods, String> comouttotalpriceCol;
	
	@FXML
	TextField comoutTotalamount;
	
	@FXML
	TextField comoutSum;
	
	@FXML
	TableView<CheckedGoods> listintable;
	
	@FXML
	TableColumn<CheckedGoods, String> listinnameCol;
	
	@FXML
	TableColumn<CheckedGoods, String> listinversionCol;
	
	@FXML
	TableColumn<CheckedGoods, String> listinidCol;
	
	@FXML
	TableColumn<CheckedGoods, String> listinamountCol;
	
	@FXML
	TableColumn<CheckedGoods, String> listintotalpriceCol;
	
	@FXML
	TextField listinTotalamount;
	
	@FXML
	TextField listinSum;
	
	@FXML
	TableView<CheckedGoods> listouttable;
	
	@FXML
	TableColumn<CheckedGoods, String> listoutnameCol;
	
	@FXML
	TableColumn<CheckedGoods, String> listoutversionCol;
	
	@FXML
	TableColumn<CheckedGoods, String> listoutidCol;
	
	@FXML
	TableColumn<CheckedGoods, String> listoutamountCol;
	
	@FXML
	TableColumn<CheckedGoods, String> listouttotalpriceCol;
	
	@FXML
	TextField listoutTotalamount;
	
	@FXML
	TextField listoutSum;
	
	CommodityInfoVO commodityInfoVO;
	
	
	/**
	 * 库存管理人员主界面的初始化方法
	 * @return 返回加载好的界面
	 */
	public static Parent init(){
		
		try {
			GridPane viewcommodity = FXMLLoader.load(ViewCommodityFrame.class.getResource("ViewCommodityFrameFXML.fxml"));
			return viewcommodity;
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
//		startdate.setValue(LocalDate.MIN);
//		enddate.setValue(LocalDate.MAX);
		startdate.getEditor().textProperty().addListener((
				observable, oldValue, newValue) -> {
					loadall();
				});
		enddate.getEditor().textProperty().addListener((
				observable, oldValue, newValue) -> {
					loadall();
				});
		
	}
	
	/**
	 * 加载所有商品入库信息table的方法
	 */
	@FXML
	public void loadall(){
		if (enddate.getValue()!=null&&startdate.getValue()!=null) {
			try {
				commodityInfoVO = RemoteHelper.getCommodityBLService().viewCommodity(startdate.getValue().toString().replaceAll("-", ""), enddate.getValue().toString().replaceAll("-", ""));
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(commodityInfoVO.getCommodityInList().size());
			loadcomintable();
			loadcomouttable();
			loadlistintable();
			loadlistouttable();
		}

		
	}
	
	/**
	 * 加载入库信息表
	 */
	public void loadcomintable(){
		ObservableList<CheckedGoods> comindata = FXCollections.observableArrayList();
		ArrayList<ReciptGoodsVO> comingoods = commodityInfoVO.getCommodityInList();
		for (int i = 0; i <comingoods.size(); i++) {
			comindata.add(new CheckedGoods(
					comingoods.get(i).getName(),
					comingoods.get(i).getVersion(),
					comingoods.get(i).getGoodsID(),
					comingoods.get(i).getAmounts()+"",
					comingoods.get(i).getSum()+""));
		}
		cominnameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		cominversionCol.setCellValueFactory(new PropertyValueFactory<>("version"));
		cominidCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		cominamountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
		comintotalpriceCol.setCellValueFactory(new PropertyValueFactory<>("totalprice"));
		comintable.setItems(comindata);
		cominTotalamount.setText(commodityInfoVO.getCommodityInAmounts()+"");
		cominSum.setText(commodityInfoVO.getCommodityInSumValue()+"");
	}
	
	/**
	 * 加载出库信息表
	 */
	public void loadcomouttable(){
		ObservableList<CheckedGoods> comoutdata = FXCollections.observableArrayList();
		ArrayList<ReciptGoodsVO> comoutgoods = commodityInfoVO.getCommodityOutList();
		for (int i = 0; i <comoutgoods.size(); i++) {
			comoutdata.add(new CheckedGoods(
					comoutgoods.get(i).getName(),
					comoutgoods.get(i).getVersion(),
					comoutgoods.get(i).getGoodsID(),
					comoutgoods.get(i).getAmounts()+"",
					comoutgoods.get(i).getSum()+""));
		}
		comoutnameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		comoutversionCol.setCellValueFactory(new PropertyValueFactory<>("version"));
		comoutidCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		comoutamountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
		comouttotalpriceCol.setCellValueFactory(new PropertyValueFactory<>("totalprice"));
		comouttable.setItems(comoutdata);
		comoutTotalamount.setText(commodityInfoVO.getCommodityOutAmouts()+"");
		comoutSum.setText(commodityInfoVO.getCommodityOutSumValue()+"");
	}
	
	/**
	 * 加载进货信息表
	 */
	public void loadlistintable(){
		ObservableList<CheckedGoods> listindata = FXCollections.observableArrayList();
		ArrayList<ReciptGoodsVO> listingoods = commodityInfoVO.getGoodsInList();
		for (int i = 0; i <listingoods.size(); i++) {
			listindata.add(new CheckedGoods(
					listingoods.get(i).getName(),
					listingoods.get(i).getVersion(),
					listingoods.get(i).getGoodsID(),
					listingoods.get(i).getAmounts()+"",
					listingoods.get(i).getSum()+""));
		}
		listinnameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		listinversionCol.setCellValueFactory(new PropertyValueFactory<>("version"));
		listinidCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		listinamountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
		listintotalpriceCol.setCellValueFactory(new PropertyValueFactory<>("totalprice"));
		listintable.setItems(listindata);
		listinTotalamount.setText(commodityInfoVO.getGoodsInAmounts()+"");
		listinSum.setText(commodityInfoVO.getGoodsInAmountsSumValue()+"");
	}
	
	/**
	 * 加载销售信息表
	 */
	public void loadlistouttable(){
		ObservableList<CheckedGoods> listoutdata = FXCollections.observableArrayList();
		ArrayList<ReciptGoodsVO> listoutgoods = commodityInfoVO.getSaleList();
		for (int i = 0; i <listoutgoods.size(); i++) {
			listoutdata.add(new CheckedGoods(
					listoutgoods.get(i).getName(),
					listoutgoods.get(i).getVersion(),
					listoutgoods.get(i).getGoodsID(),
					listoutgoods.get(i).getAmounts()+"",
					listoutgoods.get(i).getSum()+""));
		}
		listoutnameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		listoutversionCol.setCellValueFactory(new PropertyValueFactory<>("version"));
		listoutidCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		listoutamountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
		listouttotalpriceCol.setCellValueFactory(new PropertyValueFactory<>("totalprice"));
		listouttable.setItems(listoutdata);
		listoutTotalamount.setText(commodityInfoVO.getSaleAmounts()+"");
		listoutSum.setText(commodityInfoVO.getSaleAmountsSumValue()+"");
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
	 * 用于入出库、销售进货信息table绑定的数据类
	 * @author 周正伟
	 */
	public class CheckedGoods{
		private String name;
		private String version;
		private String id;
		private String amount;
		private String totalprice;


		
		public CheckedGoods(String name, String version, String id, String amount, String totalprice) {
			super();
			this.name = name;
			this.version = version;
			this.id = id;
			this.amount = amount;
			this.totalprice = totalprice;
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
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getAmount() {
			return amount;
		}
		public void setAmount(String amount) {
			this.amount = amount;
		}
		public String getTotalprice() {
			return totalprice;
		}
		public void setTotalprice(String totalprice) {
			this.totalprice = totalprice;
		}
		
		
		
	}
}
