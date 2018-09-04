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
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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
import main.RMI.RemoteHelper;
import main.VO.CouponVO;
import main.VO.CustomerVO;
import main.VO.GoodsVO;
import main.VO.ManifestVO;
import main.VO.ReciptGoodsVO;
import main.utility.ResultMessage;

/**
 * 进货销售人员销售单填写界面的控制类
 * @author 周正伟
 *
 */
public class GoodsSaleFrame extends MainUIController{
	
	@FXML
	TextField id;
	
	@FXML
	AnchorPane submitmessage;
	
	@FXML
	ComboBox<String> seller;
	
	@FXML
	TextField operator;
	
	@FXML
	TextField defaultsalesman;
	
	@FXML
	TextField totalprice;
	
	double totalprize = 0;
	
	@FXML
	TextArea remarks;
	
	@FXML
	TextField infototalprice;
	
	@FXML
	TextField discount;
	
	@FXML
	ComboBox<String> coupon;
	
	@FXML
	TextField infototalpricefinal;

	@FXML
	ComboBox<String> warehouse;
	
	@FXML 
	TableView<Goodsout> goodstable;
	
	@FXML
	TableColumn<Goodsout, String> idCol;
	
	@FXML
	TableColumn<Goodsout, String> nameCol;
	
	@FXML
	TableColumn<Goodsout, String> modelCol;
	
	@FXML
	TableColumn<Goodsout, String> numberCol;
	
	@FXML
	TableColumn<Goodsout, String> unitpriceCol;
	
	@FXML
	TableColumn<Goodsout, String> amountCol;
	
	@FXML
	TableColumn<Goodsout, String> remarksCol;
	
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
	Label goodsnumlimit;
	
	ManifestVO manifestgoodsout;

	ObservableList<Goodsout> outlist = FXCollections.observableArrayList();
	
	ArrayList<GoodsVO> goodslist = new ArrayList<>();
	
	ArrayList<CustomerVO> sellerlist = new ArrayList<>();
	
	ArrayList<ReciptGoodsVO> reciptGoodsVOlist = new ArrayList<>();
	
	ArrayList<CouponVO> couponslist = new ArrayList<>();
	
	static ManifestVO draftvo;
	
	static String newsource;
	
	/**
	 * 进货销售人员填写销售单界面的初始化方法
	 *@return 返回加载好的界面
	 *@param source 是否打开草稿
	 *@param vo 草稿vo或者null
	 */
	public static Parent init(String source,ManifestVO vo){
		newsource = source;
		draftvo = vo;
		try {
			GridPane goodsinlist = FXMLLoader.load(GoodsInFrame.class.getResource("GoodsSaleFrameFXML.fxml"));
			return goodsinlist;
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
				ids = RemoteHelper.getManifestBLService().getNextManifestID("XSD");
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		System.out.println("2");	
		}else {//打开草稿
			
			ids = draftvo.getID();
			seller.setValue(draftvo.getCustomerName());
			warehouse.setValue(draftvo.getWarehouse());
			operator.setText(draftvo.getOperator());
			remarks.setText(draftvo.getComment());
			defaultsalesman.setText(draftvo.getSalesman());
			totalprice.setText(draftvo.getSum()+"");
			discount.setText(draftvo.getDiscount()+"");
//			coupon.setValue(draftvo.ge);
			infototalprice.setText(draftvo.getSum()+"");
			List<ReciptGoodsVO> draftgoodslist = draftvo.getGoodsList();
			for (int i = 0; i < draftgoodslist.size(); i++) {
				outlist.add(new Goodsout(
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
							coupon.setValue("");
							couponslist = sellerlist.get(newval.intValue()).getCouponList();
//							for (int i = 0; i < sellerlist.get(newval.intValue()).getCouponList().size(); i++) {
//								coupons.add(sellerlist.get(newval.intValue()).getCouponList().get(i).getAmount()+"");
//							}
							ObservableList<String> couponss = FXCollections.observableArrayList();
							for (int i = 0; i < couponslist.size(); i++) {
								couponss.add(couponslist.get(i).getAmount()+" "+couponslist.get(i).getId());
							}
							couponss.add("不使用代金券");
							coupon.setItems(couponss);
						});

		
//		coupon.getSelectionModel().selectedIndexProperty().addListener(
//				(ObservableValue<? extends Number> ov,
//						Number oldval,Number newval)->{
//							double finalprice = Double.parseDouble(infototalprice.getText()) - Double.parseDouble(discount.getText()) - Double.parseDouble(coupon.getSelectionModel().getSelectedItem()); 
//							infototalpricefinal.setText(finalprice+"");
//						});
//		
		discount.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            	double finalprice = Double.parseDouble(infototalprice.getText())-Double.parseDouble(discount.getText());
            	infototalpricefinal.setText(finalprice+"");
            }
        });
		
		
		
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
					double maxamount = 0;
				    for (int i = 0; i < goodslist.size(); i++) {
						if (goodslist.get(i).getID().equals(goodsid.getText())) {
							maxamount = goodslist.get(i).getAmounts();
							break;
						}
					}
				    if (maxamount>=Double.parseDouble(goodsnumber.getText())) {
						goodsnumlimit.setText("商品数量符合规定");
				    	goodstotalprice.setText(Double.parseDouble(goodsunitprice.getText()) * Double.parseDouble(goodsnumber.getText())+"");
					}else {
						goodsnumlimit.setText("商品数量超过库存");
				    	goodstotalprice.setText(Double.parseDouble(goodsunitprice.getText()) * Double.parseDouble(goodsnumber.getText())+"");
					}
					 
				}
            }
        });
		
		goodstable.setItems(outlist);
		
	}
	
	/**
	 * 确认提交货单的按钮监听
	 */
	@FXML
	public void submitsure(){
		ResultMessage resultMessage = null;
		//提交单据vo的信息设置
		for (int i = 0; i < outlist.size(); i++) {
			reciptGoodsVOlist.add(new ReciptGoodsVO(0,
					outlist.get(i).getName(),
					outlist.get(i).getModel(),
					outlist.get(i).getId(),
					Integer.parseInt(outlist.get(i).getNumber()),
					Double.parseDouble(outlist.get(i).getUnitprice()),
					Double.parseDouble(outlist.get(i).getTotalprices()), 
					outlist.get(i).getRemarks()));
		}
		
		int couponid = 0;
		double couponvalue = 0;
		if (coupon.getSelectionModel().getSelectedItem().equals("不使用代金券")||coupon.getSelectionModel().getSelectedItem()==null) {
			couponid = 0;
			couponvalue = 0;
		}else {
			couponid = couponslist.get(coupon.getSelectionModel().getSelectedIndex()).getId();
			couponvalue = couponslist.get(coupon.getSelectionModel().getSelectedIndex()).getAmount();
		}
					
		if (newsource.equals("")) { //新建货单并非编辑的草稿
				manifestgoodsout = new ManifestVO(id.getText(),
						seller.getSelectionModel().getSelectedItem(),
						warehouse.getSelectionModel().getSelectedItem(),
						operator.getText(),
						reciptGoodsVOlist,
						remarks.getText(), 
						Double.parseDouble(infototalprice.getText()),
						defaultsalesman.getText(), 
						Double.parseDouble(discount.getText()),
						"Unchecked",
						operator.getText(),
						couponid,
						couponvalue);
			try {
				resultMessage = RemoteHelper.getManifestBLService().setSaleList(manifestgoodsout);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}else {//编辑的草稿而非新建
				manifestgoodsout = new ManifestVO(id.getText(),
						seller.getSelectionModel().getSelectedItem(),
						warehouse.getSelectionModel().getSelectedItem(),
						operator.getText(),
						reciptGoodsVOlist,
						remarks.getText(), 
						Double.parseDouble(infototalprice.getText()),
						defaultsalesman.getText(), 
						Double.parseDouble(discount.getText()),
						"Unchecked",
						operator.getText(),
						couponid,
						couponvalue);

			try {
				resultMessage = RemoteHelper.getManifestBLService().modifyManifest(manifestgoodsout);
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
		
		for (int i = 0; i < outlist.size(); i++) {
			reciptGoodsVOlist.add(new ReciptGoodsVO(0,
					outlist.get(i).getName(),
					outlist.get(i).getModel(),
					outlist.get(i).getId(),
					Integer.parseInt(outlist.get(i).getNumber()),
					Double.parseDouble(outlist.get(i).getUnitprice()),
					Double.parseDouble(outlist.get(i).getTotalprices()), 
					outlist.get(i).getRemarks()));
		}
					
		int couponid = 0;
		double couponvalue = 0;
		if (coupon.getSelectionModel().getSelectedItem().equals("不使用代金券")||coupon.getSelectionModel().getSelectedItem()==null) {
			couponid = 0;
			couponvalue = 0;
		}else {
			couponid = couponslist.get(coupon.getSelectionModel().getSelectedIndex()).getId();
			couponvalue = couponslist.get(coupon.getSelectionModel().getSelectedIndex()).getAmount();
		}
		if (newsource.equals("")) {
				manifestgoodsout = new ManifestVO(id.getText(),
						seller.getSelectionModel().getSelectedItem()==null?"":seller.getSelectionModel().getSelectedItem(),
						warehouse.getSelectionModel().getSelectedItem()==null?"":warehouse.getSelectionModel().getSelectedItem(),
						operator.getText(),
						reciptGoodsVOlist,
						remarks.getText(), 
						Double.parseDouble(infototalpricefinal.getText()),
						defaultsalesman.getText(), 
						Double.parseDouble(discount.getText()),
						"Draft",
						operator.getText(),
						couponid,
						couponvalue);
			
			try {
				resultMessage = RemoteHelper.getManifestBLService().setSaleList(manifestgoodsout);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
				manifestgoodsout = new ManifestVO(id.getText(),
						seller.getSelectionModel().getSelectedItem()==null?"":seller.getSelectionModel().getSelectedItem(),
						warehouse.getSelectionModel().getSelectedItem()==null?"":warehouse.getSelectionModel().getSelectedItem(),
						operator.getText(),
						reciptGoodsVOlist,
						remarks.getText(), 
						Double.parseDouble(infototalpricefinal.getText()),
						defaultsalesman.getText(), 
						Double.parseDouble(discount.getText()),
						"Draft",
						operator.getText(),
						couponid,
						couponvalue);

			try {
				resultMessage = RemoteHelper.getManifestBLService().modifyManifest(manifestgoodsout);
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
		
		if (goodstotalprice.getText().equals("0")||goodsnumlimit.getText().equals("商品数量超过库存")) {
			//防数量为0的商品被添加
		}else {
			 outlist.add(new Goodsout(goodsid.getText(),
						goodsname.getSelectionModel().getSelectedItem().split(" ")[0],
						goodsmodel.getText(), 
						goodsnumber.getText(), 
						goodsunitprice.getText(), 
						goodstotalprice.getText(), 
						goodsremark.getText()));
					  
					    totalprize = totalprize + Double.parseDouble(goodstotalprice.getText());
					    totalprice.setText(totalprize+"");
					    infototalprice.setText(totalprize+"");
					 
					    goodsid.clear();
					    goodsnumlimit.setText("");
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
		for (int i = 0; i <outlist.size(); i++) {
			System.out.println(outlist.get(i).getTotalprices());
		}
	    System.out.println(goodstable.getSelectionModel().getSelectedIndex());

		totalprize = totalprize - Double.parseDouble(outlist.get(goodstable.getSelectionModel().getFocusedIndex()).getTotalprices());
		outlist.remove(goodstable.getSelectionModel().getSelectedIndex());
//		totalprize = totalprize - Double.parseDouble(inlist.get(goodstable.getSelectionModel().getFocusedIndex()).getTotalprices());
//	    System.out.println(goodstable.getSelectionModel().getSelectedIndex());
	    totalprice.setText(totalprize+"");
	    infototalprice.setText(totalprize+"");
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
	public class Goodsout{
		
		
		private String id;
		private String name;
		private String model;//型号
		private String number;
		private String unitprice;//单价
		private String totalprices;
		private String remarks;
		public Goodsout() {
			// TODO Auto-generated constructor stub
		}
		
		public Goodsout(String id,String name,String model,String number,String unitprice,String amount,String remarks) {
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
