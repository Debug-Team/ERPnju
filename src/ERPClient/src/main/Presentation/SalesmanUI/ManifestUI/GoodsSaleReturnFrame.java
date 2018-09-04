package main.Presentation.SalesmanUI.ManifestUI;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import main.Presentation.MainUI.MainFrame;
import main.Presentation.MainUI.MainUIController;
import main.Presentation.SalesmanUI.ManifestFrame;
import main.Presentation.SalesmanUI.ManifestUI.GoodsInFrame.Goodsin;
import main.RMI.RemoteHelper;
import main.VO.CustomerVO;
import main.VO.GoodsVO;
import main.VO.ManifestVO;
import main.VO.ReciptGoodsVO;
import main.utility.ResultMessage;

/**
 * 进货销售人员销售单退货填写界面的控制类
 * @author 周正伟
 *
 */
public class GoodsSaleReturnFrame extends MainUIController{
	@FXML
	TextField id;
	
	@FXML
	AnchorPane submitmessage;
	
	@FXML
	ComboBox<String> seller;
	
	@FXML
	TextField operator;
	
	
	@FXML
	TextField totalprice;
	
	double totalprize = 0;
	
	@FXML
	TextArea remarks;
	

	@FXML
	ComboBox<String> warehouse;
	
	@FXML 
	TableView<Goodsin> goodstable;
	
	@FXML
	TableColumn<Goodsin, String> idCol;
	
	@FXML
	TableColumn<Goodsin, String> nameCol;
	
	@FXML
	TableColumn<Goodsin, String> modelCol;
	
	@FXML
	TableColumn<Goodsin, String> numberCol;
	
	@FXML
	TableColumn<Goodsin, String> unitpriceCol;
	
	@FXML
	TableColumn<Goodsin, String> amountCol;
	
	@FXML
	TableColumn<Goodsin, String> remarksCol;
	
	@FXML
	ComboBox<String> goodsname;
	
	@FXML
	TextField goodsid;
	
	@FXML
	TextField goodsmodel;
	
	@FXML
	TextField goodsnumber;
	
	@FXML
	TextField goodsunitprice;
	
	@FXML
	TextField goodstotalprice;
	
	@FXML
	TextArea goodsremark;
	
	@FXML
	TabPane tabpane;
	
	@FXML
	Tab listinfo;
	
	@FXML
	Tab goodsinfo;
	
	@FXML
	AnchorPane submitsucccess;
	
	@FXML
	Button successsure;
	
	@FXML 
	AnchorPane goodsaddpane;
	
	@FXML
	AnchorPane reminddraftanchor;
	
	@FXML
	TextField totalprice1;
	
	@FXML
	TextField defaultsalesman;
	
	
	ManifestVO manifestgoodsin;

	ObservableList<Goodsin> inlist = FXCollections.observableArrayList();
	
	ArrayList<GoodsVO> goodslist = new ArrayList<>();
	
	ArrayList<CustomerVO> sellerlist = new ArrayList<>();
	
	ArrayList<ReciptGoodsVO> reciptGoodsVOlist = new ArrayList<>();
	
	static ManifestVO draftvo;
	
	static String newsource;
	
	/**
	 * 进货销售人员填写销售退货单界面的初始化方法
	 *@return 返回加载好的界面
	 *@param source 是否打开草稿
	 *@param vo 草稿vo或者null
	 */
	public static Parent init(String source,ManifestVO vo){
		newsource = source;
		draftvo = vo;
		try {
			GridPane goodssalereturnlist = FXMLLoader.load(GoodsSaleReturnFrame.class.getResource("GoodsSaleReturnFrameFXML.fxml"));
			return goodssalereturnlist;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 *加载fxml时自动加载的方法，商品table的信息绑定
	 */
	public void initialize(){
		
		
		idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		modelCol.setCellValueFactory(new PropertyValueFactory<>("model"));
		numberCol.setCellValueFactory(new PropertyValueFactory<>("number"));
		unitpriceCol.setCellValueFactory(new PropertyValueFactory<>("unitprice"));
		amountCol.setCellValueFactory(new PropertyValueFactory<>("totalprices"));
		remarksCol.setCellValueFactory(new PropertyValueFactory<>("remarks"));
		
//		int a = 1;
		String ids = "" ;
		if (newsource.equals("")) {//新建货单
			try {
				ids = RemoteHelper.getManifestBLService().getNextManifestID("XSTHD");
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		System.out.println(ids);	
		}else {//打开草稿
			
			ids = draftvo.getID();
			seller.setValue(draftvo.getCustomerName());
			warehouse.setValue(draftvo.getWarehouse());
			operator.setText(draftvo.getOperator());
			remarks.setText(draftvo.getComment());
			defaultsalesman.setText(draftvo.getSalesman());
			totalprice.setText(draftvo.getSum()+"");
			totalprice1.setText(draftvo.getSum()+"");
//			coupon.setValue(draftvo.ge);
//			infototalpricefinal.setText(draftvo.getSum()+"");
			List<ReciptGoodsVO> draftgoodslist = draftvo.getGoodsList();
			for (int i = 0; i < draftgoodslist.size(); i++) {
				inlist.add(new Goodsin(
						draftgoodslist.get(i).getGoodsID(),
						draftgoodslist.get(i).getName(),
						draftgoodslist.get(i).getVersion(),
						draftgoodslist.get(i).getAmounts()+"",
						draftgoodslist.get(i).getBid()+"",
						draftgoodslist.get(i).getSum()+"",
						draftgoodslist.get(i).getComment()));
			}
			totalprize = draftvo.getSum();
		}
		
//		goodsinfo.setDisable(true);
		id.setText(ids);
		operator.setText(user.getName());
		//加载销售商
		try {
			sellerlist = RemoteHelper.getManifestBLService().getAllCustomerName("销售商");
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
		ObservableList<String> sellersnames = FXCollections.observableArrayList();
		for (int i = 0; i < sellerlist.size(); i++) {
			sellersnames.add(sellerlist.get(i).getName());
		}
		seller.setItems(sellersnames);
		seller.getSelectionModel().selectedIndexProperty().addListener(
				(ObservableValue<? extends Number> ov,
						Number oldval,Number newval)->{

							defaultsalesman.setText(sellerlist.get(newval.intValue()).getDefaultsalesman());

						});
//		ObservableList<String> couponss = FXCollections.observableArrayList();
//		for (int i = 0; i < coupons.size(); i++) {
//			couponss.add(coupons.get(i).getAmount()+" "+coupons.get(i).getId());
//		}
//		couponss.add("0"+" 这是在代码里注");
//		coupon.setItems(coupons);
//		coupon.setItems(couponss);
		
//		coupon.getSelectionModel().selectedIndexProperty().addListener(
//				(ObservableValue<? extends Number> ov,
//						Number oldval,Number newval)->{
//							double finalprice = Double.parseDouble(infototalprice.getText()) - Double.parseDouble(discount.getText()) - Double.parseDouble(coupon.getSelectionModel().getSelectedItem()); 
//							infototalpricefinal.setText(finalprice+"");
//						});
		
		//加载商品名称列表
		try {
			goodslist = RemoteHelper.getManifestBLService().getGoods();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ObservableList<String> goodsnamess = FXCollections.observableArrayList();
		for (int i = 0; i < goodslist.size(); i++) {
				 goodsnamess.add(goodslist.get(i).getName()+" "+goodslist.get(i).getVersion());
		}
		goodsname.setItems(goodsnamess);
		
		//选择商品后默认生成商品id 单价 型号的监听
		goodsname.getSelectionModel().selectedIndexProperty().addListener(
				(ObservableValue<? extends Number> ov,
						Number oldval,Number newval)->{
							goodsid.setText(goodslist.get(newval.intValue()).getID());
							goodsunitprice.setText(goodslist.get(newval.intValue()).getRetailPrice()+"");
							goodsmodel.setText(goodslist.get(newval.intValue()).getVersion());
						});

		//填写商品数量时会检测是超过库存数量
		goodsnumber.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            	if (goodsnumber.getText().equals("")) {
					//删商品数量出错
				}else{	  
				    	goodstotalprice.setText(Double.parseDouble(goodsunitprice.getText()) * Double.parseDouble(goodsnumber.getText())+"");
				}
            }
        });
		
		goodstable.setItems(inlist);
		
	}
	
	
	/**
	 * 确认提交货单的按钮监听
	 */
	@FXML
	public void submitsure(){
		ResultMessage resultMessage = null;
		
		for (int i = 0; i < inlist.size(); i++) {
			reciptGoodsVOlist.add(new ReciptGoodsVO(0,
					inlist.get(i).getName(),
					inlist.get(i).getModel(),
					inlist.get(i).getId(),
					Integer.parseInt(inlist.get(i).getNumber()),
					Double.parseDouble(inlist.get(i).getUnitprice()),
					Double.parseDouble(inlist.get(i).getTotalprices()), 
					inlist.get(i).getRemarks()));
		}
					
		if (newsource.equals("")) {
				manifestgoodsin = new ManifestVO(id.getText()+"",
						seller.getSelectionModel().getSelectedItem()+"",
						warehouse.getSelectionModel().getSelectedItem()+"",
						operator.getText()+"",
						reciptGoodsVOlist,
						remarks.getText()+"", 
						Double.parseDouble(totalprice.getText()+""),
						"Unchecked",
						operator.getText());
			try {
				resultMessage = RemoteHelper.getManifestBLService().setSaleReturnList(manifestgoodsin);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}else {
				manifestgoodsin = new ManifestVO(id.getText()+"",
						seller.getSelectionModel().getSelectedItem()+"",
						warehouse.getSelectionModel().getSelectedItem()+"",
						operator.getText()+"",
						reciptGoodsVOlist,
						remarks.getText()+"", 
						Double.parseDouble(totalprice.getText()+""),
						"Unchecked",
						operator.getText());

			try {
				resultMessage = RemoteHelper.getManifestBLService().modifyManifest(manifestgoodsin);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
			}
		if (resultMessage.equals(ResultMessage.SUCCESS)) {
			submitmessage.setVisible(false);
			submitsucccess.setVisible(true);
	        successsure.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent e) {
	               MainFrame.setSceneRoot(ManifestFrame.init());
	            }
	        });
		}
		
}
	
	/**
	 * 提示是否保存草稿
	 */
	@FXML
	public void remindsavedraft(){
		tabpane.getSelectionModel().selectFirst();
		reminddraftanchor.setVisible(true);
	}
	
	/**
	 * 不保存草按钮的监听
	 */
	@FXML
	public void notsavedraft(){
		MainFrame.setSceneRoot(ManifestFrame.init());
	}
	
	/**
	 * 保存草稿按钮的监听
	 */
	@FXML
	public void savedraft(){
		ResultMessage resultMessage = null;
		for (int i = 0; i < inlist.size(); i++) {
			reciptGoodsVOlist.add(new ReciptGoodsVO(0,
					inlist.get(i).getName(),
					inlist.get(i).getModel(),
					inlist.get(i).getId(),
					Integer.parseInt(inlist.get(i).getNumber()),
					Double.parseDouble(inlist.get(i).getUnitprice()),
					Double.parseDouble(inlist.get(i).getTotalprices()), 
					inlist.get(i).getRemarks()));
		}
					
		if (newsource.equals("")) {
				manifestgoodsin = new ManifestVO(id.getText()+"",
						seller.getSelectionModel().getSelectedItem()==null?"":seller.getSelectionModel().getSelectedItem(),
						warehouse.getSelectionModel().getSelectedItem()==null?"":warehouse.getSelectionModel().getSelectedItem(),
						operator.getText(),
						reciptGoodsVOlist,
						remarks.getText()+"", 
						Double.parseDouble(totalprice.getText()),
						"Draft",
						operator.getText());
			
			try {
				resultMessage = RemoteHelper.getManifestBLService().setSaleReturnList(manifestgoodsin);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
				manifestgoodsin = new ManifestVO(id.getText()+"",
						seller.getSelectionModel().getSelectedItem()==null?"":seller.getSelectionModel().getSelectedItem(),
						warehouse.getSelectionModel().getSelectedItem()==null?"":warehouse.getSelectionModel().getSelectedItem(),
						operator.getText(),
						reciptGoodsVOlist,
						remarks.getText()+"", 
						Double.parseDouble(totalprice.getText()),
						"Draft",
						operator.getText()+"");

			try {
				resultMessage = RemoteHelper.getManifestBLService().modifyManifest(manifestgoodsin);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (resultMessage.equals(ResultMessage.SUCCESS)) {
			MainFrame.setSceneRoot(ManifestFrame.init());
		}
	}
	
	/**
	 * 添加商品列表的按钮的监听
	 */
	@FXML
	public void showgoodsinfo(){
//		goodsinfo.setDisable(false);
		tabpane.getSelectionModel().selectNext();
	}
	
	/**
	 * 主要信息填写界面的按钮监听
	 */
	@FXML
	public void showlistinfo(){
		tabpane.getSelectionModel().selectFirst();

	}
	
	/**
	 * 初步提交按钮监听
	 */
	@FXML
	public void sure(){
		submitmessage.setVisible(true);
	}
	
	/**
	 * 添加商品的按钮监听
	 */
	@FXML
	public void goodsadd(){
		goodsaddpane.setVisible(true);
	}
	
	/**
	 * 取消添加商品的按钮的监听
	 */
	@FXML
	public void goodsaddcancel(){
	    goodsid.clear();
	    goodsnumber.setText("0");;
	    goodsmodel.clear();
	    goodsremark.clear();
	    goodstotalprice.setText("0");
	    goodsunitprice.clear();
	    goodsname.setValue("");
		goodsaddpane.setVisible(false);
	}
	
	/**
	 * 确认添加商品的按钮的监听
	 */
	@FXML
	public void goodsadddsure(){
		
		if (goodstotalprice.getText().equals("0")) {
			//防数量为0的商品被添加
		}else {
			 inlist.add(new Goodsin(goodsid.getText(),
						goodsname.getSelectionModel().getSelectedItem().split(" ")[0],
						goodsmodel.getText(), 
						goodsnumber.getText(), 
						goodsunitprice.getText(), 
						goodstotalprice.getText(), 
						goodsremark.getText()));
					  
					    totalprize = totalprize + Double.parseDouble(goodstotalprice.getText());
					    totalprice.setText(totalprize+"");
					    totalprice1.setText(totalprize+"");
					 
					    goodsid.clear();
					    goodsnumber.setText("0");;
					    goodsmodel.clear();
					    goodsremark.clear();
					    goodstotalprice.setText("0");
					    goodsunitprice.clear();
					    goodsname.setValue("");
					    goodsaddpane.setVisible(false);
		}
		
		    
	}
	
	/**
	 * 删除商品的按钮的监听
	 */
	@FXML
	public void goodsdelete(){
		for (int i = 0; i <inlist.size(); i++) {
			System.out.println(inlist.get(i).getTotalprices());
		}
	    System.out.println(goodstable.getSelectionModel().getSelectedIndex());

		totalprize = totalprize - Double.parseDouble(inlist.get(goodstable.getSelectionModel().getFocusedIndex()).getTotalprices());
		inlist.remove(goodstable.getSelectionModel().getSelectedIndex());
//		totalprize = totalprize - Double.parseDouble(inlist.get(goodstable.getSelectionModel().getFocusedIndex()).getTotalprices());
//	    System.out.println(goodstable.getSelectionModel().getSelectedIndex());
	    totalprice.setText(totalprize+"");
	    totalprice1.setText(totalprize+"");
//	    infototalprice.setText(totalprize+"");
	}
	
	/**
	 * 取消提交的按钮的监听
	 */
	@FXML
	public void cancelsubmit(){
		submitmessage.setVisible(false);
	}
	
	/**
	 * 用于商品tableview绑定的数据类
	 * @author 周正伟
	 */
	public class Goodsin{
		
		
		private String id;
		private String name;
		private String model;//型号
		private String number;
		private String unitprice;//单价
		private String totalprices;
		private String remarks;
		public Goodsin() {
			// TODO Auto-generated constructor stub
		}
		
		public Goodsin(String id,String name,String model,String number,String unitprice,String amount,String remarks) {
			// TODO Auto-generated constructor stub
			this.id = id;
			this.name = name;
			this.model = model;
			this.number = number;
			this.unitprice = unitprice;
			this.totalprices= amount;
			this.remarks = remarks;
			
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getModel() {
			return model;
		}
		public void setModel(String model) {
			this.model = model;
		}
		public String getNumber() {
			return number;
		}
		public void setNumber(String number) {
			this.number = number;
		}
		public String getUnitprice() {
			return unitprice;
		}
		public void setUnitprice(String unitprice) {
			this.unitprice = unitprice;
		}
		public String getTotalprices() {
			return totalprices;
		}
		public void setTotalprices(String amount) {
			this.totalprices = amount;
		}
		public String getRemarks() {
			return remarks;
		}
		public void setRemarks(String remarks) {
			this.remarks = remarks;
		}
		
	}
	
	
}
