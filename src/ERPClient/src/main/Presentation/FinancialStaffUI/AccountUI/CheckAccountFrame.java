package main.Presentation.FinancialStaffUI.AccountUI;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import main.BussinessLogicService.AccountBLService.AccountBLService;
import main.Presentation.FinancialStaffUI.AccountFrame;
import main.Presentation.FinancialStaffUI.FinancialStaffHomeFrame;
import main.Presentation.MainUI.MainFrame;
import main.Presentation.MainUI.MainUIController;
import main.RMI.RemoteHelper;
import main.VO.AccountVO;
import main.VO.BankAccountVO;
import main.VO.CustomerVO;
import main.VO.ReciptGoodsVO;

/**
 * 查看期初信息界面的controller
 * @author 49869
 *
 */
public class CheckAccountFrame extends MainUIController{
	AccountBLService accountBL;
	
	@FXML
	ImageView head;
		
	@FXML
	TableView<GoodsInfo> goodsTable;
	@FXML
	TableColumn<GoodsInfo, String> goodsFenLeiColumn;
	@FXML
	TableColumn<GoodsInfo, String> goodsNameColumn;
	@FXML
	TableColumn<GoodsInfo, String> goodsXingHaoColumn;
	@FXML
	TableColumn<GoodsInfo, String> goodsBuyPriceColumn;
	@FXML
	TableColumn<GoodsInfo, String> goodsSellPriceColumn;
	@FXML
	TableColumn<GoodsInfo, String> goodsRecentBuyPriceColumn;
	@FXML
	TableColumn<GoodsInfo, String> goodsRecentSellPriceColumn;

	@FXML
	TableView<CustomerInfo> customerTable;
	@FXML
	TableColumn<CustomerInfo, String> customerCategoryColumn;
	@FXML
	TableColumn<CustomerInfo, String> customerNameColumn;
	@FXML
	TableColumn<CustomerInfo, String> customerContactColumn;
	@FXML
	TableColumn<CustomerInfo, String> customerReceivableColumn;
	@FXML
	TableColumn<CustomerInfo, String> customerPayableColumn;
	
	@FXML
	TableView<BankAccountInfo> bankAccountTable;
	@FXML
	TableColumn<BankAccountInfo, String> bankAccountNameColumn;
	@FXML
	TableColumn<BankAccountInfo, Double> bankAccountAmountColumn;
	
	
	/**
	 * 期初建账界面的初始化方法
	 * @return 返回加载好的界面
	 */
	public static Parent init() {
		try {
			GridPane checkAccountFrame = FXMLLoader.load(CheckAccountFrame.class.getResource("CheckAccountFrameFXML.fxml"));
			return checkAccountFrame;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new GridPane();
	}
	
	/**
	 * 加载FXML时初始化table，comboBox等
	 */
	@FXML
	private void initialize() {
		accountBL = RemoteHelper.getAccountBLService();
		
		goodsFenLeiColumn.setCellValueFactory(new PropertyValueFactory<GoodsInfo, String>("fenLei"));
		goodsNameColumn.setCellValueFactory(new PropertyValueFactory<GoodsInfo, String>("name"));
		goodsXingHaoColumn.setCellValueFactory(new PropertyValueFactory<GoodsInfo, String>("xingHao"));
		goodsBuyPriceColumn.setCellValueFactory(new PropertyValueFactory<GoodsInfo, String>("buyPrice"));
		goodsSellPriceColumn.setCellValueFactory(new PropertyValueFactory<GoodsInfo, String>("sellPrice"));
		goodsRecentBuyPriceColumn.setCellValueFactory(new PropertyValueFactory<GoodsInfo, String>("recentBuyPrice"));
		goodsRecentSellPriceColumn.setCellValueFactory(new PropertyValueFactory<GoodsInfo, String>("recentSellPrice"));

		customerCategoryColumn.setCellValueFactory(new PropertyValueFactory<CustomerInfo, String>("category"));
		customerNameColumn.setCellValueFactory(new PropertyValueFactory<CustomerInfo, String>("name"));
		customerContactColumn.setCellValueFactory(new PropertyValueFactory<CustomerInfo, String>("contact"));
		customerReceivableColumn.setCellValueFactory(new PropertyValueFactory<CustomerInfo, String>("receivable"));
		customerPayableColumn.setCellValueFactory(new PropertyValueFactory<CustomerInfo, String>("payable"));
		
		bankAccountNameColumn.setCellValueFactory(new PropertyValueFactory<BankAccountInfo, String>("name"));
		bankAccountAmountColumn.setCellValueFactory(new PropertyValueFactory<BankAccountInfo, Double>("amount"));
		
		loadInfo();
	}
	
	private void loadInfo() {
		try {
			AccountVO accountVO = accountBL.get();
//			ArrayList<GoodsVO> goodsVOList = accountVO.getGoodsList();
//			for(int i = 0;i < goodsVOList.size();i++) {
//				GoodsVO vo = goodsVOList.get(i);
//				goodsTable.getItems().add(new GoodsInfo(vo.getCatagory(), vo.getName(), vo.getVersion(), ""+vo.getBid(), ""+vo.getRecentBid(), "", ""));
//			}
			ArrayList<ReciptGoodsVO> goodsVOList = accountVO.getGoodsList();
			for(int i = 0;i < goodsVOList.size();i++) {
				ReciptGoodsVO vo = goodsVOList.get(i);
				goodsTable.getItems().add(new GoodsInfo(vo.getComment(), vo.getName(), vo.getVersion(), ""+vo.getBid(), ""+vo.getSum(), "", ""));
			}
			ArrayList<CustomerVO> customerVOList = accountVO.getCustomerList();
			for(int i = 0;i < customerVOList.size();i++) {
				CustomerVO vo = customerVOList.get(i);
				customerTable.getItems().add(new CustomerInfo(vo.getCategory(), vo.getName(), vo.getNumber(), vo.getReceive(), vo.getPayment()));
			}
			ArrayList<BankAccountVO> bankAccountVOList = accountVO.getBankAccountList();
			for(int i = 0;i < bankAccountVOList.size();i++) {
				BankAccountVO vo = bankAccountVOList.get(i);
				bankAccountTable.getItems().add(new BankAccountInfo(vo.getId(), vo.getAmount()));
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 主页按钮的监听
	 */
	@FXML
	private void home(){
		Parent home = FinancialStaffHomeFrame.init();
		MainFrame.setSceneRoot(home);
	}
	
	/**
	 * 返回按钮的监听
	 */
	@FXML
	private void back(){
		Parent back = AccountFrame.init();
		MainFrame.setSceneRoot(back);
	}
	
		
	/**
	 * 确认按钮的监听
	 */
	@FXML
	private void sure() {
		
		back();
	}
	
	
	/**
	 * 用作商品信息列表数据绑定的类
	 * @author 杨袁瑞
	 *
	 */
	public class GoodsInfo{
		private SimpleStringProperty fenLei;
		private SimpleStringProperty name;
		private SimpleStringProperty xingHao;
		private SimpleStringProperty buyPrice;
		private SimpleStringProperty sellPrice;
		private SimpleStringProperty recentBuyPrice;
		private SimpleStringProperty recentSellPrice;
		
		public GoodsInfo(String fenLei, String name, String xingHao, String buyPrice, 
				String sellPrice, String recentBuyPrice, String recentSellPrice) {
			this.fenLei = new SimpleStringProperty(fenLei);
			this.name = new SimpleStringProperty(name);
			this.xingHao = new SimpleStringProperty(xingHao);
			this.buyPrice = new SimpleStringProperty(buyPrice);
			this.sellPrice = new SimpleStringProperty(sellPrice);
			this.recentBuyPrice = new SimpleStringProperty(recentBuyPrice);
			this.recentSellPrice = new SimpleStringProperty(recentSellPrice);
		}
		
		public void setFenLei(String fl) {
			fenLei.set(fl);
		}
		public String getFenLei() {
			return fenLei.get();
		}
		
		public void setName(String n) {
			name.set(n);
		}
		public String getName() {
			return name.get();
		}
		
		public void setXingHao(String xh) {
			xingHao.set(xh);
		}
		public String getXingHao() {
			return xingHao.get();
		}

		public void setBuyPrice(String bp) {
			buyPrice.set(bp);
		}
		public String getBuyPrice() {
			return buyPrice.get();
		}

		public void setSellPrice(String sp) {
			sellPrice.set(sp);
		}
		public String getSellPrice() {
			return sellPrice.get();
		}

		public void setRecentBuyPrice(String rbp) {
			recentBuyPrice.set(rbp);
		}
		public String getRecentBuyPrice() {
			return recentBuyPrice.get();
		}

		public void setRecentSellPrice(String rsp) {
			recentSellPrice.set(rsp);
		}
		public String getRecentSellPrice() {
			return recentSellPrice.get();
		}
	}
	
	/**
	 * 用作客户信息列表数据绑定的类
	 * @author 杨袁瑞
	 *
	 */
	public class CustomerInfo{
		private SimpleStringProperty category;
		private SimpleStringProperty name;
		private SimpleStringProperty contact;
		private SimpleDoubleProperty receivable;
		private SimpleDoubleProperty payable;
		
		public CustomerInfo(String category, String name, String contact, double receivable, double payable) {
			this.category = new SimpleStringProperty(category);
			this.name = new SimpleStringProperty(name);
			this.contact = new SimpleStringProperty(contact);
			this.receivable = new SimpleDoubleProperty(receivable);
			this.payable = new SimpleDoubleProperty(payable);
		}
		
		public void setCategory(String fl) {
			category.set(fl);
		}
		public String getCategory() {
			return category.get();
		}
		
		public void setName(String n) {
			name.set(n);
		}
		public String getName() {
			return name.get();
		}
		
		public void setContact(String lxfs) {
			contact.set(lxfs);
		}
		public String getContact() {
			return contact.get();
		}

		public void setReceivable(double ys) {
			receivable.set(ys);
		}
		public double getReceivable() {
			return receivable.get();
		}

		public void setPayable(double yf) {
			payable.set(yf);
		}
		public double getPayable() {
			return payable.get();
		}
	}

	/**
	 * 用于银行账户列表数据绑定的类
	 * @author 杨袁瑞
	 */
	public class BankAccountInfo{
		private SimpleStringProperty name;
		private SimpleDoubleProperty amount;
		
		/**
		 * 
		 * @param 银行账户的名称
		 */
		BankAccountInfo(String name, double amount){
			this.name = new SimpleStringProperty(name);
			this.amount = new SimpleDoubleProperty(amount);
		}

		public String getName() {
			return name.get();
		}

		public void setName(String name) {
			this.name.set(name);
		}
		
		public double getAmount() {
			return amount.get();
		}
		public void setAmount(double amount) {
			this.amount.set(amount);
		}
	}

}
