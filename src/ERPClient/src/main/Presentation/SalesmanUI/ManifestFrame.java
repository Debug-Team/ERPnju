package main.Presentation.SalesmanUI;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.text.TabableView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import main.Presentation.MainUI.MainFrame;
import main.Presentation.MainUI.MainUIController;
import main.Presentation.SalesmanUI.CustomerUI.CustomerdetailInfoFrame;
import main.Presentation.SalesmanUI.ManifestUI.GoodsInFrame;
import main.Presentation.SalesmanUI.ManifestUI.GoodsInReturnFrame;
import main.Presentation.SalesmanUI.ManifestUI.GoodsSaleFrame;
import main.Presentation.SalesmanUI.ManifestUI.GoodsSaleReturnFrame;
import main.RMI.RemoteHelper;
import main.VO.CustomerVO;
import main.VO.ManifestVO;
import main.utility.ResultMessage;

/**
 * 负责进货销售人员新建货单部分,及该界面的部分监听
 * @author 周正伟
 *
 */
public class ManifestFrame extends MainUIController{
	
	@FXML
	ImageView head;
	
	//已提交货单列表UI控件
	@FXML
	TableView<Mainfests> submittedtable;
	
	@FXML
	TableColumn<Mainfests,String> submittedidCol;	
	
	@FXML 
	TableColumn<Mainfests,String> submittedtypeCol;
	
	@FXML 
	TableColumn<Mainfests,String> submittedcusnameCol;
	
	@FXML
	TableColumn<Mainfests,String> submittedtotalpriceCol;
	
	@FXML
	TableColumn<Mainfests,String> submittedoperatorCol;
	
	@FXML
	TableColumn<Mainfests,String> submitteddateCol;
	
	@FXML
	TableColumn<Mainfests,String> submittedstateCol;
	
	//草稿列表UI控件
	@FXML 
	TableView<Mainfests> drafttable;
	
	@FXML 
	TableColumn<Mainfests,String> draftidCol;
	
	@FXML
	TableColumn<Mainfests,String> drafttypeCol;
	
	@FXML
	TableColumn<Mainfests,String> draftcusnameCol;
	
	@FXML 
	TableColumn<Mainfests,String> draftdateCol;
	
	@FXML 
	TabPane tabpane;
	
	@FXML 
	Tab listtab;

	@FXML 
	Tab drafttab;
	
	@FXML 
	Tab newtab;
	
	@FXML 
	Label littletitle;
	
	ObservableList<Mainfests> submittedtablelist;//提交单据table绑定的数据
	
	ObservableList<Mainfests> drafttablelist;//草稿单据table绑定的数据
	
	ArrayList<ManifestVO> allmanifestvo = null;//所有的货单vo
	
	ArrayList<ManifestVO> submmittedvo = new ArrayList<>();//已提交的货单vo
	
	ArrayList<ManifestVO> draftvo = new ArrayList<>();//草稿vo
	
	@FXML
	TableView<Gifts> giftgoodstable;
	
	@FXML
	TableColumn<Gifts, String> giftgoodsnameCol;
	
	@FXML
	TableColumn<Gifts, String> giftgoodsamountCol;
 
	@FXML
	AnchorPane giftanchorpane;
	
	/**
	 * 进货销售人员货单管理主界面的初始化方法
	 *@return 返回加载好的界面
	 */
	public static Parent init(){
		try {
			GridPane manifest = FXMLLoader.load(CustomerManageFrame.class.getResource("ManifestManageFXML.fxml"));
			return manifest;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 *加载fxml时自动加载的方法，初始化相关货单列表
	 */
	public void initialize() {

		
		try {
				allmanifestvo = RemoteHelper.getManifestBLService().getOperatorManifests(user.getName());
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		for (int i = 0; i < allmanifestvo.size(); i++) {
			if (allmanifestvo.get(i).getState().equals("Draft")) {
				draftvo.add(allmanifestvo.get(i));
			}else {
				submmittedvo.add(allmanifestvo.get(i));
			}
		}	
		
		setsubmmittedtable();
		setdrafttable();
		
	}
	
	
	/**
	 *绑定已提交货单列表的数据类
	 */
	public void setsubmmittedtable(){
		
		submittedtablelist =FXCollections.observableArrayList();
		
		submittedidCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		submittedtypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
		submittedcusnameCol.setCellValueFactory(new PropertyValueFactory<>("customername"));
		submittedtotalpriceCol.setCellValueFactory(new PropertyValueFactory<>("totalprice"));
		submittedoperatorCol.setCellValueFactory(new PropertyValueFactory<>("operator"));
		submitteddateCol.setCellValueFactory(new PropertyValueFactory<>("subdate"));
		submittedstateCol.setCellValueFactory(new PropertyValueFactory<>("state"));
		
		
		
		for (int i = 0; i < submmittedvo.size(); i++) {
			submittedtablelist.add(new Mainfests(
					submmittedvo.get(i).getID(),
					submmittedvo.get(i).getType(),
					submmittedvo.get(i).getCustomerName(),
					submmittedvo.get(i).getSum()+"",
					submmittedvo.get(i).getOperator(),
					submmittedvo.get(i).getCreateDate(),
					submmittedvo.get(i).getState()));
		}
		submittedtable.setItems(submittedtablelist);
		
		


	}
	
	/**
	 *绑定草稿货单列表的数据类
	 */
	public void setdrafttable(){
		
		drafttablelist =FXCollections.observableArrayList();
		
		
		draftidCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		drafttypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
		draftcusnameCol.setCellValueFactory(new PropertyValueFactory<>("customername"));
		draftdateCol.setCellValueFactory(new PropertyValueFactory<>("subdate"));
		
		
		for (int i = 0; i < draftvo.size(); i++) {
			drafttablelist.add(new Mainfests(
					draftvo.get(i).getID(),
					draftvo.get(i).getType(),
					draftvo.get(i).getCustomerName(),
					draftvo.get(i).getCreateDate()));
		}
		
		drafttable.setItems(drafttablelist);
		
		

	}
	
	/**
	 * 删除草稿
	 */
	@FXML
	protected void deletedraft(ActionEvent event) {
		if (drafttable.getSelectionModel().getSelectedIndex()==-1) {
			//防未选定聚焦报错
		}else {
		ResultMessage resultMessage = null;
		try {
			resultMessage = RemoteHelper.getManifestBLService().deleteManifest(draftvo.get(drafttable.getSelectionModel().getSelectedIndex()));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (resultMessage.equals(ResultMessage.SUCCESS)) {
			
		}
		}
		MainFrame.setSceneRoot(ManifestFrame.init());
    }
	
	/**
	 * 打开草稿
	 */
	@FXML
	protected void opendraft(ActionEvent event) {
		ManifestVO vo = null;
		vo = draftvo.get(drafttable.getSelectionModel().getSelectedIndex());
		if (vo.getType().equals("JHD")) {
			MainFrame.setSceneRoot(GoodsInFrame.init("draft", vo));
		}else if (vo.getType().equals("JHTHD")) {
			MainFrame.setSceneRoot(GoodsInReturnFrame.init("draft", vo));
		}else if (vo.getType().equals("XSD")) {
			MainFrame.setSceneRoot(GoodsSaleFrame.init("draft",vo));
		}else if (vo.getType().equals("XSTHD")) {
			MainFrame.setSceneRoot(GoodsSaleReturnFrame.init("draft",vo));
		}

    }
	
	/**
	 * 返回进货销售人员主界面按钮的监听
	 */
	@FXML
	public void returnSalesmanhomeHandle(ActionEvent event) {
        MainFrame.setSceneRoot(SalesmanHomeFrame.init());
    }
	
	/**
	 * 进入新建进货单填写界面按钮的监听
	 */
	@FXML
	public void GoodsInListFrameHandle(ActionEvent event ) {
        MainFrame.setSceneRoot(GoodsInFrame.init("",null));
    }
	
	/**
	 * 进入新建进货退货单填写界面按钮的监听
	 */
	@FXML
	public void GoodsInReturnListFrameHandle(ActionEvent event) {
        MainFrame.setSceneRoot(GoodsInReturnFrame.init("",null));
    }

	/**
	 * 进入新建销售单填写界面按钮的监听
	 */
	@FXML
	public void GoodsSaleListFrameHandle(ActionEvent event) {
        MainFrame.setSceneRoot(GoodsSaleFrame.init("",null));
    }
	

	/**
	 * 进入新建销售退货单填写界面按钮的监听
	 */
	@FXML
	protected void GoodsSaleReturnListFrameHandle(ActionEvent event) {
        MainFrame.setSceneRoot(GoodsSaleReturnFrame.init("",null));
    }
	
	/**
	 * 进入货单列表界面按钮的监听
	 */
	@FXML
	protected void changelisttab(ActionEvent event) {
		tabpane.getSelectionModel().select(listtab);
		littletitle.setText("货单列表");
    }
	
	/**
	 * 进入草稿列表界面按钮的监听
	 */
	@FXML
	protected void changedrafttab(ActionEvent event) {
		tabpane.getSelectionModel().select(drafttab);
		littletitle.setText("草稿列表");
    }
	
	/**
	 * 进入新建货单界面按钮的监听
	 */
	@FXML
	protected void changenewtab(ActionEvent event) {
		tabpane.getSelectionModel().select(newtab);
		littletitle.setText("新建货单");
    }
	
	/**
	 * 双击行查看已审批单据赠送商品列表的监听
	 */
	@FXML
	protected void getgifts(MouseEvent event) {
		ManifestVO hasgiftsvo = null;
		for (int i = 0; i < submmittedvo.size(); i++) {
			if (submmittedvo.get(i).getID().equals(submittedtable.getSelectionModel().getSelectedItem().getId())) {
				hasgiftsvo = submmittedvo.get(i);
				break;
			}
		}
		
		if (event.getClickCount() == 2) {
//				CustomerdetailInfoFrame customerdetailInfoFrame = new CustomerdetailInfoFrame();
//				customerdetailInfoFrame.init(detailvo);
			if (hasgiftsvo.getState().equals("Checked")) {
				
				ObservableList<Gifts> giftsdata = FXCollections.observableArrayList();
				for (int i = 0; i < hasgiftsvo.getGiftGoodslist().size(); i++) {
					giftsdata.add(new Gifts(hasgiftsvo.getGiftGoodslist().get(i).getName(),
							hasgiftsvo.getGiftGoodslist().get(i).getAmounts()+""));
				}
				
				giftgoodsnameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
				giftgoodsamountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
				giftgoodstable.setItems(giftsdata);
				giftanchorpane.setVisible(true);
				
			}
		}
	}
	
	/**
	 * 确认查看赠送单的按钮监听
	 */
	@FXML
	protected void suregifts() {
		giftanchorpane.setVisible(false);
	}
	
	/**
	 * 用于货单tableview绑定的数据类
	 * @author 周正伟
	 */
	public class Mainfests{
		
		private String id;
		private String type;
		private String customername;
		private String totalprice;
		private String operator;
		private String subdate;
		private String state;
		
		
		
		public Mainfests(String id, String type, String customername, String subdate) {
			super();
			this.id = id;
			this.type = type;
			this.customername = customername;
			this.subdate = subdate;
		}

		public Mainfests(String id, String type, String customername, String totalprice, String operator,
				String subdate, String state) {
			super();
			this.id = id;
			this.type = type;
			this.customername = customername;
			this.totalprice = totalprice;
			this.operator = operator;
			this.subdate = subdate;
			this.state = state;
		}
		
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getCustomername() {
			return customername;
		}
		public void setCustomername(String customername) {
			this.customername = customername;
		}
		public String getTotalprice() {
			return totalprice;
		}
		public void setTotalprice(String totalprice) {
			this.totalprice = totalprice;
		}
		public String getOperator() {
			return operator;
		}
		public void setOperator(String operator) {
			this.operator = operator;
		}
		public String getSubdate() {
			return subdate;
		}
		public void setSubdate(String subdate) {
			this.subdate = subdate;
		}
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}
		
	}
	
	/**
	 * 用于赠送商品tableview绑定的数据类
	 * @author 周正伟
	 */
	public class Gifts{
		private String name;
		private String amount;
		
		
		
		public Gifts(String name, String amount) {
			super();
			this.name = name;
			this.amount = amount;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getAmount() {
			return amount;
		}
		public void setAmount(String amount) {
			this.amount = amount;
		}
	}
}
