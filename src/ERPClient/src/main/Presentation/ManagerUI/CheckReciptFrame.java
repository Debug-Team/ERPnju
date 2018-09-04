package main.Presentation.ManagerUI;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import javafx.util.converter.DoubleStringConverter;
import main.BussinessLogicService.CheckBLService.CheckBLService;
import main.Presentation.MainUI.MainFrame;
import main.Presentation.MainUI.MainUIController;
import main.RMI.RemoteHelper;
import main.VO.CashBillVO;
import main.VO.CashItemVO;
import main.VO.CollectionItemVO;
import main.VO.CollectionOrderVO;
import main.VO.CommodityReciptVO;
import main.VO.InfoVO;
import main.VO.ManifestVO;
import main.VO.PaymentItemVO;
import main.VO.PaymentOrderVO;
import main.VO.ReciptGoodsVO;

public class CheckReciptFrame extends MainUIController{

	@FXML
	private ImageView head;
	
	@FXML
	DatePicker startDatePicker;
	@FXML
	DatePicker endDatePicker;
	@FXML
	ComboBox<String> reciptTypeBox;
	@FXML
	ComboBox<String> operatorBox;

	@FXML
	TableView<Info> reciptTable;
	@FXML
	TableColumn<Info, String> reciptTypeColumn;
	@FXML
	TableColumn<Info, String> reciptIdColumn;
	@FXML
	TableColumn<Info, String> operatorColumn;
	@FXML
	TableColumn<Info, String> dateColumn;
	
	@FXML
	Button checkButton;
	@FXML
	Button modifyButton;

	@FXML
	TabPane tabPane;
	
	//收款单界面
	@FXML
	TextField idInCollection;
	@FXML
	TextField supplierInCollection;
	@FXML
	TextField sellerInCollection;
	@FXML
	TextField sumInCollection;
	@FXML
	TextField operatorInCollection;
	@FXML
	TableView<InfoInFinancialRecipt> tableInCollection;
	@FXML
	TableColumn<InfoInFinancialRecipt, String> nameColumnInCollection;
	@FXML
	TableColumn<InfoInFinancialRecipt, Double> amountColumnInCollection;
	@FXML
	TableColumn<InfoInFinancialRecipt, String> commentColumnInCollection;
	
	//付款单界面
	@FXML
	TextField idInPayment;
	@FXML
	TextField supplierInPayment;
	@FXML
	TextField sellerInPayment;
	@FXML
	TextField sumInPayment;
	@FXML
	TextField operatorInPayment;
	@FXML
	TableView<InfoInFinancialRecipt> tableInPayment;
	@FXML
	TableColumn<InfoInFinancialRecipt, String> nameColumnInPayment;
	@FXML
	TableColumn<InfoInFinancialRecipt, Double> amountColumnInPayment;
	@FXML
	TableColumn<InfoInFinancialRecipt, String> commentColumnInPayment;

	//现金费用单界面
	@FXML
	TextField idInCashBill;
	@FXML
	TextField bankAccountInCashBill;
	@FXML
	TextField sumInCashBill;
	@FXML
	TextField operatorInCashBill;
	@FXML
	TableView<InfoInFinancialRecipt> tableInCashBill;
	@FXML
	TableColumn<InfoInFinancialRecipt, String> nameColumnInCashBill;
	@FXML
	TableColumn<InfoInFinancialRecipt, Double> amountColumnInCashBill;
	@FXML
	TableColumn<InfoInFinancialRecipt, String> commentColumnInCashBill;
	
	//进货单界面
	@FXML
	TextField idInGoodsInList;
	@FXML
	TextField producerInGoodsInList;
	@FXML
	TextField totalpriceInGoodsInList;
	@FXML
	TextField operatorInGoodsInList;
	@FXML
	TextField commentInGoodsInList;
	@FXML
	TextField warehouseInGoodsInList;
	@FXML
	TableView<ListGoods> tableInGoodsInList;
	@FXML
	TableColumn<ListGoods, String> idColumnInGoodsInList;
	@FXML
	TableColumn<ListGoods, String> nameColumnInGoodsInList;
	@FXML
	TableColumn<ListGoods, String> versionColumnInGoodsInList;
	@FXML
	TableColumn<ListGoods, String> amountColumnInGoodsInList;
	@FXML
	TableColumn<ListGoods, String> bidColumnInGoodsInList;
	@FXML
	TableColumn<ListGoods, String> commentColumnInGoodsInList;
	
	//进货退货单界面
	@FXML
	TextField idInGoodsInReturnList;
	@FXML
	TextField producerInGoodsInReturnList;
	@FXML
	TextField totalpriceInGoodsInReturnList;
	@FXML
	TextField operatorInGoodsInReturnList;
	@FXML
	TextField commentInGoodsInReturnList;
	@FXML
	TextField warehouseInGoodsInReturnList;
	@FXML
	TableView<ListGoods> tableInGoodsInReturnList;
	@FXML
	TableColumn<ListGoods, String> idColumnInGoodsInReturnList;
	@FXML
	TableColumn<ListGoods, String> nameColumnInGoodsInReturnList;
	@FXML
	TableColumn<ListGoods, String> versionColumnInGoodsInReturnList;
	@FXML
	TableColumn<ListGoods, String> amountColumnInGoodsInReturnList;
	@FXML
	TableColumn<ListGoods, String> bidColumnInGoodsInReturnList;
	@FXML
	TableColumn<ListGoods, String> commentColumnInGoodsInReturnList;
	
	//销售单界面
	@FXML
	TextField idInSaleList;
	@FXML
	TextField sellerInSaleList;
	@FXML
	TextField totalpriceInSaleList;
	@FXML
	TextField operatorInSaleList;
	@FXML
	TextField commentInSaleList;
	@FXML
	TextField warehouseInSaleList;
	@FXML
	TableView<ListGoods> tableInSaleList;
	@FXML
	TableColumn<ListGoods, String> idColumnInSaleList;
	@FXML
	TableColumn<ListGoods, String> nameColumnInSaleList;
	@FXML
	TableColumn<ListGoods, String> versionColumnInSaleList;
	@FXML
	TableColumn<ListGoods, String> amountColumnInSaleList;
	@FXML
	TableColumn<ListGoods, String> bidColumnInSaleList;
	@FXML
	TableColumn<ListGoods, String> commentColumnInSaleList;
	
	//销售退货单界面
	@FXML
	TextField idInSaleReturnList;
	@FXML
	TextField sellerInSaleReturnList;
	@FXML
	TextField totalpriceInSaleReturnList;
	@FXML
	TextField operatorInSaleReturnList;
	@FXML
	TextField commentInSaleReturnList;
	@FXML
	TextField warehouseInSaleReturnList;
	@FXML
	TableView<ListGoods> tableInSaleReturnList;
	@FXML
	TableColumn<ListGoods, String> idColumnInSaleReturnList;
	@FXML
	TableColumn<ListGoods, String> nameColumnInSaleReturnList;
	@FXML
	TableColumn<ListGoods, String> versionColumnInSaleReturnList;
	@FXML
	TableColumn<ListGoods, String> amountColumnInSaleReturnList;
	@FXML
	TableColumn<ListGoods, String> bidColumnInSaleReturnList;
	@FXML
	TableColumn<ListGoods, String> commentColumnInSaleReturnList;
	
	//库存报溢单
	@FXML
	TextField idInOverflowList;
	@FXML
	TextField goodsnameInOverflowList;
	@FXML
	TextField goodsidInOverflowList;
	@FXML
	TextField amountInOverflowList;
	
	//库存报损单
	@FXML
	TextField idInDamageList;
	@FXML
	TextField goodsnameInDamageList;
	@FXML
	TextField goodsidInDamageList;
	@FXML
	TextField amountInDamageList;
	
	//库存报警单
	@FXML
	TextField idInWarningList;
	@FXML
	TextField goodsnameInWarningList;
	@FXML
	TextField goodsidInWarningList;
	@FXML
	TextField amountInWarningList;
	
	//库存赠送单
	@FXML
	TextField idIngiftList;
	@FXML
	TextField goodsnameIngiftList;
	@FXML
	TextField goodsidIngiftList;
	@FXML
	TextField amountIngiftList;
	
	private CheckBLService checkBL;
	private InfoVO infoVO;
	
	/**
	 * 加载审批单据界面
	 * @return 加载好的界面
	 */
	public static Parent init() {
		try {
			GridPane checkReciptFrame = FXMLLoader.load(CheckReciptFrame.class.getResource("CheckReciptFrameFXML.fxml"));
			return checkReciptFrame;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new GridPane();
	}

	/**
	 * 初始化组件
	 */
	public void initialize() {
		checkBL = RemoteHelper.getCheckBLService();
		
//		startDatePicker.setValue(LocalDate.now());
//		endDatePicker.setValue(LocalDate.now());
		reciptTypeBox.setItems(FXCollections.observableArrayList("销售类单据", "进货类单据", "财务类单据", "库存类单据"));
		
		reciptTypeColumn.setCellValueFactory(new PropertyValueFactory<Info, String>("reciptType"));
		reciptIdColumn.setCellValueFactory(new PropertyValueFactory<Info, String>("reciptId"));
		operatorColumn.setCellValueFactory(new PropertyValueFactory<Info, String>("operator"));
		dateColumn.setCellValueFactory(new PropertyValueFactory<Info, String>("date"));
		
		reciptTable.setRowFactory(new Callback<TableView<Info>, TableRow<Info>>(){
			@Override
			public TableRow<Info> call(TableView<Info> param) {
				return new TableRowControl();
			}
		});
		reciptTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		loadInfo();
		
		//轮询查询，太卡顿，舍弃
//		new Thread(()-> {
//			while(true) {
//				Platform.runLater(()->{
//					if(reciptTable.getSelectionModel().getSelectedIndices().size() <= 1) {
//						loadInfo();
//					}
//				});
//				try {
//					Thread.sleep(3000);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}).start();
		
		//收款单界面
		initCollectionOrder();
		//付款单界面
		initPaymentOrder();
		//现金费用单
		initCashBill();
		//进货单界面
		initGoodsInTable();
		//进货退货单
		initGoodsInReturnTable();
		//销售单
		initSaleTable();
		//销售退货单
		initSaleReturnTable();
	}
		
	/**
	 * 加载所有需要审批的单据
	 */
	private void loadInfo() {
		try {
			infoVO = checkBL.getInfo();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(infoVO == null)	return;
		
		//记录已选择项
		int selectedIndex = reciptTable.getSelectionModel().getSelectedIndex();
//		ObservableList<Info> list = reciptTable.getItems();
		ObservableList<Info> list = FXCollections.observableArrayList();
		ArrayList<ManifestVO> manifestList = infoVO.getManifestList();
		for(int i = 0;i < manifestList.size();i++) {
			ManifestVO vo = manifestList.get(i);
			String voType = vo.getType();
			switch(voType) {
			case "XSD":{
				voType = "销售单";
				break;
			}
			case "XSTHD":{
				voType = "销售退货单";
				break;
			}
			case "JHD":{
				voType = "进货单";
				break;
			}
			case "JHTHD":{
				voType = "进货退货单";
				break;
			}
			}
			list.add(new Info(voType, vo.getID(), vo.getOperator(), vo.getCreateDate()));
		}
		ArrayList<CommodityReciptVO> commodityReciptList = infoVO.getCommodityReciptList();
		for(int i = 0;i < commodityReciptList.size();i++){
			CommodityReciptVO vo = commodityReciptList.get(i);
			String voType = vo.getType();
			switch(voType) {
			case "BJ":{
				voType = "库存报警单";
				break;
			}
			case "ZS":{
				voType = "库存赠送单";
				break;
			}
			case "BY":{
				voType = "库存报溢单";
				break;
			}
			case "BS":{
				voType = "库存报损单";
				break;
			}
			}
			list.add(new Info(voType, ""+vo.getID(), "", vo.getCreateDate()));
		}
		ArrayList<CollectionOrderVO> collectionOrderList = infoVO.getCollectionOrderList();
		for(int i = 0; i < collectionOrderList.size(); i++) {
			CollectionOrderVO vo = collectionOrderList.get(i);
			String id = vo.getId();
			String date = id.substring(id.indexOf("-")+1, id.lastIndexOf("-"));
			list.add(new Info("收款单", id, vo.getOperator(), date));
		}
		ArrayList<PaymentOrderVO> paymentOrderList = infoVO.getPaymentOrderList();
		for(int i = 0; i < paymentOrderList.size(); i++) {
			PaymentOrderVO vo = paymentOrderList.get(i);
			String id = vo.getId();
			String date = id.substring(id.indexOf("-")+1, id.lastIndexOf("-"));
			list.add(new Info("付款单", id, vo.getOperator(), date));
		}
		ArrayList<CashBillVO> cashBillList = infoVO.getCashBillList();
		for(int i = 0; i < cashBillList.size(); i++) {
			CashBillVO vo = cashBillList.get(i);
			String id = vo.getId();
			String date = id.substring(id.indexOf("-")+1, id.lastIndexOf("-"));
			list.add(new Info("现金费用单", id, vo.getOperator(), date));
		}
		
		list.sort(new Comparator<Info>() {
			@Override
			public int compare(Info o1, Info o2) {
				return o1.getDate().compareTo(o2.getDate());
			}
		});
		
		reciptTable.setItems(list);
		reciptTable.getSelectionModel().select(selectedIndex);
	}
	
	/**
	 * 选择时间的监听
	 */
	@FXML
	private void setTime() {
		
	}

	/**
	 * 单据类型ComboBox的监听
	 */
	@FXML
	private void setReciptTypeBox() {
		
	}
	
	/**
	 * 操作员ComboBox的监听
	 */
	@FXML
	private void setOperatorBox() {
		
	}
	
	/**
	 * 审批按钮的监听
	 */
	@FXML
	private void check() {
		ObservableList<Info> checkedList = reciptTable.getSelectionModel().getSelectedItems();
		
		for(int i = 0;i < checkedList.size();i++) {
			Info info = checkedList.get(i);
			String type = info.getReciptType();
			switch(type) {
			case "收款单":{
				ArrayList<CollectionOrderVO> orderList = infoVO.getCollectionOrderList();
				for(int j = 0;j < orderList.size();j++) {
					CollectionOrderVO vo = orderList.get(j);
					if(vo.getId().equals(info.getReciptId())) {
						vo.setState("Checked");
					}
				}
				break;
			}
			case "付款单":{
				ArrayList<PaymentOrderVO> orderList = infoVO.getPaymentOrderList();
				for(int j = 0;j < orderList.size(); j++) {
					PaymentOrderVO vo = orderList.get(j);
					if(vo.getId().equals(info.getReciptId())) {
						vo.setState("Checked");
					}
				}
				break;
			}
			case "现金费用单":{
				ArrayList<CashBillVO> orderList = infoVO.getCashBillList();
				for(int j = 0;j < orderList.size(); j++) {
					CashBillVO vo = orderList.get(j);
					if(vo.getId().equals(info.getReciptId())) {
						vo.setState("Checked");
					}
				}
				break;	
			}
			default:{
				if(type.equals("BJ") || type.equals("ZS") || type.equals("BY") || type.equals("BS")) {
					ArrayList<CommodityReciptVO> commodityReciptList = infoVO.getCommodityReciptList();
					for(int j = 0;j < commodityReciptList.size();j++) {
						CommodityReciptVO vo = commodityReciptList.get(j);
						if((""+vo.getID()).equals(info.getReciptId())) {
							vo.setState("Checked");
						}
					}
				}
				else{
					ArrayList<ManifestVO> manifestList = infoVO.getManifestList();
					for(int j = 0;j < manifestList.size(); j++) {
						ManifestVO vo = manifestList.get(j);
						if(vo.getID().equals(info.getReciptId())) {
							vo.setState("Checked");
						}
					}
				}
				break;
			}
			}
		}
		try {
			checkBL.setSuggestion(infoVO, username);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		loadInfo();
	}

	/**
	 * 修改按钮的监听
	 */
	@FXML
	private void modify() {
		
	}

	/**
	 * 刷新按钮的监听
	 */
	@FXML
	private void refresh() {
		loadInfo();
	}

	/**
	 * 主页按钮的监听
	 */
	@FXML
	private void home() {
		Parent home = ManagerHomeFrame.init();
		MainFrame.setSceneRoot(home);
	}
	
	/**
	 * 返回按钮的监听
	 */
	@FXML
	private void back() {
		Parent back = ManagerHomeFrame.init();
		MainFrame.setSceneRoot(back);
	}

	/**
	 * 处理tableview的鼠标双击事件的类
	 * @author 杨袁瑞
	 */
	class TableRowControl extends TableRow<Info> {  
		  
		public TableRowControl() {  
			super();  
			this.setOnMouseClicked(new EventHandler<MouseEvent>(){
				@Override
				public void handle(MouseEvent event) {
					if(event.getButton() == MouseButton.PRIMARY
							&& event.getClickCount() == 1
							&& TableRowControl.this.getIndex() < reciptTable.getItems().size()){
						//设置按钮可用 
						checkButton.setDisable(false);
						modifyButton.setDisable(false);
						
						if(reciptTable.getSelectionModel().getSelectedItems().size() > 1){		//多选时不可以修改
							modifyButton.setDisable(true);
							checkButton.setDisable(false);
						}
					}
					if(event.getButton() == MouseButton.PRIMARY
							&& event.getClickCount() == 2
							&& TableRowControl.this.getIndex() < reciptTable.getItems().size()){
//						System.out.println("test");
//						check();
						showRecipt();
					}
				}
			});
		}
	}
	
	/**
	 * 初始化收款单界面
	 * @author yyr
	 */
	private void initCollectionOrder() {
		//初始化table
		nameColumnInCollection.setCellValueFactory(new PropertyValueFactory<InfoInFinancialRecipt, String>("name"));
		//总经理审批时应不能更改名称
		
		amountColumnInCollection.setCellValueFactory(new PropertyValueFactory<InfoInFinancialRecipt, Double>("amount"));
		amountColumnInCollection.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
//		amountColumnInCollection.setOnEditCommit((CellEditEvent<InfoInFinancialRecipt, Double> t) -> {
//			t.getTableView().getItems().get(t.getTablePosition().getRow()).setAmount(t.getNewValue());
//			calcSumInCollection();
//		});
		
		commentColumnInCollection.setCellValueFactory(new PropertyValueFactory<InfoInFinancialRecipt, String>("comment"));
//		commentColumnInCollection.setCellFactory(TextFieldTableCell.forTableColumn());
//		commentColumnInCollection.setOnEditCommit((CellEditEvent<InfoInFinancialRecipt, String> t) -> {
//			t.getTableView().getItems().get(t.getTablePosition().getRow()).setComment(t.getNewValue());
//		});
	}
	/**
	 * 初始化付款单界面
	 */
	private void initPaymentOrder() {
		//初始化table
		nameColumnInPayment.setCellValueFactory(new PropertyValueFactory<InfoInFinancialRecipt, String>("name"));
		
		amountColumnInPayment.setCellValueFactory(new PropertyValueFactory<InfoInFinancialRecipt, Double>("amount"));
//		amountColumnInPayment.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
//		amountColumnInPayment.setOnEditCommit((CellEditEvent<InfoInFinancialRecipt, Double> t) -> {
//			t.getTableView().getItems().get(t.getTablePosition().getRow()).setAmount(t.getNewValue());
//			calcSumInCollection();
//		});
		
		commentColumnInPayment.setCellValueFactory(new PropertyValueFactory<InfoInFinancialRecipt, String>("comment"));
//		commentColumnInPayment.setCellFactory(TextFieldTableCell.forTableColumn());
//		commentColumnInPayment.setOnEditCommit((CellEditEvent<InfoInFinancialRecipt, String> t) -> {
//			t.getTableView().getItems().get(t.getTablePosition().getRow()).setComment(t.getNewValue());
//		});
	}
	/**
	 * 初始化现金费用单界面
	 */
	private void initCashBill() {
		//初始化table
		nameColumnInCashBill.setCellValueFactory(new PropertyValueFactory<InfoInFinancialRecipt, String>("name"));
		
		amountColumnInCashBill.setCellValueFactory(new PropertyValueFactory<InfoInFinancialRecipt, Double>("amount"));
//		amountColumnInCashBill.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
//		amountColumnInCashBill.setOnEditCommit((CellEditEvent<InfoInFinancialRecipt, Double> t) -> {
//			t.getTableView().getItems().get(t.getTablePosition().getRow()).setAmount(t.getNewValue());
//			calcSumInCollection();
//		});
		
		commentColumnInCashBill.setCellValueFactory(new PropertyValueFactory<InfoInFinancialRecipt, String>("comment"));
//		commentColumnInCashBill.setCellFactory(TextFieldTableCell.forTableColumn());
//		commentColumnInCashBill.setOnEditCommit((CellEditEvent<InfoInFinancialRecipt, String> t) -> {
//			t.getTableView().getItems().get(t.getTablePosition().getRow()).setComment(t.getNewValue());
//		});
	}

	/**
	 * 绑定进货单数据类
	 * @author 周正伟
	 */
	private void initGoodsInTable(){
		
		idColumnInGoodsInList.setCellValueFactory(new PropertyValueFactory<>("id"));
		nameColumnInGoodsInList.setCellValueFactory(new PropertyValueFactory<>("name"));
		versionColumnInGoodsInList.setCellValueFactory(new PropertyValueFactory<>("version"));
		bidColumnInGoodsInList.setCellValueFactory(new PropertyValueFactory<>("bid"));
		amountColumnInGoodsInList.setCellValueFactory(new PropertyValueFactory<>("amount"));
		commentColumnInGoodsInList.setCellValueFactory(new PropertyValueFactory<>("comment"));
	}
	
	/**
	 * 绑定进货退货单数据类
	 * @author 周正伟
	 */
	private void initGoodsInReturnTable(){
		
		idColumnInGoodsInReturnList.setCellValueFactory(new PropertyValueFactory<>("id"));
		nameColumnInGoodsInReturnList.setCellValueFactory(new PropertyValueFactory<>("name"));
		versionColumnInGoodsInReturnList.setCellValueFactory(new PropertyValueFactory<>("version"));
		bidColumnInGoodsInReturnList.setCellValueFactory(new PropertyValueFactory<>("bid"));
		amountColumnInGoodsInReturnList.setCellValueFactory(new PropertyValueFactory<>("amount"));
		commentColumnInGoodsInReturnList.setCellValueFactory(new PropertyValueFactory<>("comment"));
	
	}
	
	/**
	 * 绑定销售单数据类
	 * @author 周正伟
	 */
	private void initSaleTable(){
		
		idColumnInSaleList.setCellValueFactory(new PropertyValueFactory<>("id"));
		nameColumnInSaleList.setCellValueFactory(new PropertyValueFactory<>("name"));
		versionColumnInSaleList.setCellValueFactory(new PropertyValueFactory<>("version"));
		bidColumnInSaleList.setCellValueFactory(new PropertyValueFactory<>("bid"));
		amountColumnInSaleList.setCellValueFactory(new PropertyValueFactory<>("amount"));
		commentColumnInSaleList.setCellValueFactory(new PropertyValueFactory<>("comment"));
	
	}
	
	/**
	 * 绑定销售退货单数据类
	 * @author 周正伟
	 */
	private void initSaleReturnTable(){
		
		idColumnInSaleReturnList.setCellValueFactory(new PropertyValueFactory<>("id"));
		nameColumnInSaleReturnList.setCellValueFactory(new PropertyValueFactory<>("name"));
		versionColumnInSaleReturnList.setCellValueFactory(new PropertyValueFactory<>("version"));
		bidColumnInSaleReturnList.setCellValueFactory(new PropertyValueFactory<>("bid"));
		amountColumnInSaleReturnList.setCellValueFactory(new PropertyValueFactory<>("amount"));
		commentColumnInSaleReturnList.setCellValueFactory(new PropertyValueFactory<>("comment"));
	}

	/**
	 * 显示一张单据的方法
	 */
	private void showRecipt() {
		Info info = reciptTable.getSelectionModel().getSelectedItem();
		String type = info.getReciptType();
		switch(type) {
		case "收款单":{
			for(CollectionOrderVO vo : infoVO.getCollectionOrderList()) {
				if(vo.getId().equals(info.getReciptId())) {
					showCollectionOrder(vo);
				}
			}
			break;
		}
		case "付款单":{
			for(PaymentOrderVO vo : infoVO.getPaymentOrderList()) {
				if(vo.getId().equals(info.getReciptId())) {
					showPaymentOrder(vo);
				}
			}
			break;
		}
		case "现金费用单":{
			for(CashBillVO vo : infoVO.getCashBillList()) {
				if(vo.getId().equals(info.getReciptId())) {
					showCashBill(vo);
				}
			}
			break;
		}
		case "进货单":{
			for(ManifestVO vo : infoVO.getManifestList()) {
				if(vo.getID().equals(info.getReciptId())) {
					showGoodsIn(vo);
				}
			}
			break;
		}
		case "进货退货单":{
			for(ManifestVO vo : infoVO.getManifestList()) {
				if(vo.getID().equals(info.getReciptId())) {
					showGoodsInReturn(vo);
				}
			}
			break;
		}
		case "销售单":{
			for(ManifestVO vo : infoVO.getManifestList()) {
				if(vo.getID().equals(info.getReciptId())) {
					showSaleList(vo);
				}
			}
			break;
		}
		case "销售退货单":{
			for(ManifestVO vo : infoVO.getManifestList()) {
				if(vo.getID().equals(info.getReciptId())) {
					showSaleReturnList(vo);
				}
			}
			break;
		}
		case "库存报溢单":{
			for(CommodityReciptVO vo : infoVO.getCommodityReciptList()) {
				if((""+vo.getID()).equals(info.getReciptId())) {
					showOverflowList(vo);
				}
			}
			break;
		}
		case "库存报损单":{
			for(CommodityReciptVO vo : infoVO.getCommodityReciptList()) {
				if((""+vo.getID()).equals(info.getReciptId())) {
					showDamageList(vo);
				}
			}
			break;
		}
		case "库存报警单":{
			for(CommodityReciptVO vo : infoVO.getCommodityReciptList()) {
				if((""+vo.getID()).equals(info.getReciptId())) {
					showWarningList(vo);
				}
			}
			break;
		}
		case "库存赠送单":{
			for(CommodityReciptVO vo : infoVO.getCommodityReciptList()) {
				if((""+vo.getID()).equals(info.getReciptId())) {
					showGiftList(vo);
				}
			}
			break;
		}
		}
	}
	
	/**
	 * 显示一张收款单
	 * @param vo 要显示的收款单VO
	 */
	private void showCollectionOrder(CollectionOrderVO vo) {
		idInCollection.setText(vo.getId());
//		System.out.println(vo.getSupplier());
//		System.out.println(vo.getRetailer());
		supplierInCollection.setText(vo.getSupplier());
		sellerInCollection.setText(vo.getRetailer());
		ArrayList<CollectionItemVO> itemList = vo.getItemList();
		for(CollectionItemVO item : itemList) {
			tableInCollection.getItems().add(new InfoInFinancialRecipt(
					item.getBankAccountID(), item.getAmount(), item.getComment()));
		}
//		calcSumInCollection();
		sumInCollection.setText(""+vo.getSum());
		operatorInCollection.setText(vo.getOperator());
		
		tabPane.getSelectionModel().select(1);
	}
	/**
	 * 显示一张付款单
	 * @param vo 要显示的付款单VO
	 */
	private void showPaymentOrder(PaymentOrderVO vo) {
		idInPayment.setText(vo.getId());
		supplierInPayment.setText(vo.getSupplier());
		sellerInPayment.setText(vo.getRetailer());
		ArrayList<PaymentItemVO> itemList = vo.getItemList();
		
		tableInPayment.getItems().clear();
		for(PaymentItemVO item : itemList) {
			tableInPayment.getItems().add(new InfoInFinancialRecipt(
					item.getBankAccountID(), item.getAmount(), item.getComment()));
		}
//		calcSumInPayment();
		sumInPayment.setText(""+vo.getSum());
		operatorInPayment.setText(vo.getOperator());
		
		tabPane.getSelectionModel().select(2);
	}

	/**
	 * 显示一张现金费用单
	 * @param vo 要显示的付款单VO
	 */
	private void showCashBill(CashBillVO vo) {
		idInCashBill.setText(vo.getId());
		bankAccountInCashBill.setText(vo.getBankAccountID());
		ArrayList<CashItemVO> itemList = vo.getItemList();
		
		tableInCashBill.getItems().clear();
		for(CashItemVO item : itemList) {
			tableInCashBill.getItems().add(new InfoInFinancialRecipt(
					item.getName(), item.getAmount(), item.getComment()));
		}
//		calcSumInCashBill();
		sumInCashBill.setText(""+vo.getSum());
		operatorInCashBill.setText(vo.getOperator());
		
		tabPane.getSelectionModel().select(3);
	}

//	/**
//	 * 计算收款单里的总价
//	 */
//	private void calcSumInCollection() {
//		ObservableList<InfoInFinancialRecipt> list = tableInCollection.getItems();
//		double sum = 0;
//		for(InfoInFinancialRecipt info : list) {
//			sum += info.getAmount();
//		}
//		sumInCollection.setText(""+sum);
//	}
//	/**
//	 * 计算付款单里的总价
//	 */
//	private void calcSumInPayment() {
//		ObservableList<InfoInFinancialRecipt> list = tableInPayment.getItems();
//		double sum = 0;
//		for(InfoInFinancialRecipt info : list) {
//			sum += info.getAmount();
//		}
//		sumInPayment.setText(""+sum);
//	}
//	/**
//	 * 计算现金费用单里的总价
//	 */
//	private void calcSumInCashBill() {
//		ObservableList<InfoInFinancialRecipt> list = tableInPayment.getItems();
//		double sum = 0;
//		for(InfoInFinancialRecipt info : list) {
//			sum += info.getAmount();
//		}
//		sumInCashBill.setText(""+sum);
//	}

	/**
	 * 显示一个进货单
	 */
	private void showGoodsIn(ManifestVO manifestVO) {
		idInGoodsInList.setText(manifestVO.getID());
		producerInGoodsInList.setText(manifestVO.getCustomerName());
		warehouseInGoodsInList.setText(manifestVO.getWarehouse());
		operatorInGoodsInList.setText(manifestVO.getOperator());
		commentInGoodsInList.setText(manifestVO.getComment());
		totalpriceInGoodsInList.setText(""+manifestVO.getSum());
		
		List<ReciptGoodsVO> goodsList = manifestVO.getGoodsList();
		tableInGoodsInList.getItems().clear();
		for(ReciptGoodsVO vo : goodsList) {
			tableInGoodsInList.getItems().add(new ListGoods(
					vo.getGoodsID(), vo.getName(), vo.getVersion()
					, ""+vo.getAmounts(), ""+vo.getBid(), vo.getComment()));
		}
		tabPane.getSelectionModel().select(4);
	}
	
	/**
	 * 显示一个进货退货单
	 */
	private void showGoodsInReturn(ManifestVO manifestVO) {
		idInGoodsInReturnList.setText(manifestVO.getID());
		producerInGoodsInReturnList.setText(manifestVO.getCustomerName());
		warehouseInGoodsInReturnList.setText(manifestVO.getWarehouse());
		operatorInGoodsInReturnList.setText(manifestVO.getOperator());
		commentInGoodsInReturnList.setText(manifestVO.getComment());
		totalpriceInGoodsInReturnList.setText(""+manifestVO.getSum());
		
		List<ReciptGoodsVO> goodsList = manifestVO.getGoodsList();
		tableInGoodsInReturnList.getItems().clear();
		for(ReciptGoodsVO vo : goodsList) {
			tableInGoodsInReturnList.getItems().add(new ListGoods(
					vo.getGoodsID(), vo.getName(), vo.getVersion()
					, ""+vo.getAmounts(), ""+vo.getBid(), vo.getComment()));
		}
		tabPane.getSelectionModel().select(5);
	}

	/**
	 * 显示一个销售单
	 */
	private void showSaleList(ManifestVO manifestVO) {
		idInSaleList.setText(manifestVO.getID());
		sellerInSaleList.setText(manifestVO.getCustomerName());
		warehouseInSaleList.setText(manifestVO.getWarehouse());
		operatorInSaleList.setText(manifestVO.getOperator());
		commentInSaleList.setText(manifestVO.getComment());
		totalpriceInSaleList.setText(""+manifestVO.getSum());
		
		List<ReciptGoodsVO> goodsList = manifestVO.getGoodsList();
		tableInSaleList.getItems().clear();
		for(ReciptGoodsVO vo : goodsList) {
			tableInSaleList.getItems().add(new ListGoods(
					vo.getGoodsID(), vo.getName(), vo.getVersion()
					, ""+vo.getAmounts(), ""+vo.getBid(), vo.getComment()));
		}
		tabPane.getSelectionModel().select(6);
	}
	
	/**
	 * 显示一个销售退货单
	 */
	private void showSaleReturnList(ManifestVO manifestVO) {
		idInSaleReturnList.setText(manifestVO.getID());
		sellerInSaleReturnList.setText(manifestVO.getCustomerName());
		warehouseInSaleReturnList.setText(manifestVO.getWarehouse());
		operatorInSaleReturnList.setText(manifestVO.getOperator());
		commentInSaleReturnList.setText(manifestVO.getComment());
		totalpriceInSaleReturnList.setText(""+manifestVO.getSum());
		
		List<ReciptGoodsVO> goodsList = manifestVO.getGoodsList();
		tableInSaleReturnList.getItems().clear();
		for(ReciptGoodsVO vo : goodsList) {
			tableInSaleReturnList.getItems().add(new ListGoods(
					vo.getGoodsID(), vo.getName(), vo.getVersion()
					, ""+vo.getAmounts(), ""+vo.getBid(), vo.getComment()));
		}
		tabPane.getSelectionModel().select(7);
	}

	/**
	 * 显示一个报溢单
	 */
	private void showOverflowList(CommodityReciptVO reciptVO) {
		idInOverflowList.setText(""+reciptVO.getID());
		goodsnameInOverflowList.setText(reciptVO.getGoodsName());
		goodsidInOverflowList.setText(reciptVO.getGoodsID());
		amountInOverflowList.setText(""+reciptVO.getChangedNumbers());
		
		tabPane.getSelectionModel().select(8);
	}
	
	/**
	 * 显示一个报损单
	 */
	private void showDamageList(CommodityReciptVO reciptVO) {
		idInDamageList.setText(""+reciptVO.getID());
		goodsnameInDamageList.setText(reciptVO.getGoodsName());
		goodsidInDamageList.setText(reciptVO.getGoodsID());
		amountInDamageList.setText(""+reciptVO.getChangedNumbers());
		
		tabPane.getSelectionModel().select(9);
	}
	
	/**
	 * 显示一个报损单
	 */
	private void showWarningList(CommodityReciptVO reciptVO) {
		idInWarningList.setText(""+reciptVO.getID());
		goodsnameInWarningList.setText(reciptVO.getGoodsName());
		goodsidInWarningList.setText(reciptVO.getGoodsID());
		amountInWarningList.setText(""+reciptVO.getChangedNumbers());
		
		tabPane.getSelectionModel().select(10);
	}

	/**
	 * 显示一个报损单
	 */
	private void showGiftList(CommodityReciptVO reciptVO) {
		idIngiftList.setText(""+reciptVO.getID());
		goodsnameIngiftList.setText(reciptVO.getGoodsName());
		goodsidIngiftList.setText(reciptVO.getGoodsID());
		amountIngiftList.setText(""+reciptVO.getChangedNumbers());
		
		tabPane.getSelectionModel().select(11);
	}

	@FXML
	private void cancel() {
		tabPane.getSelectionModel().select(0);
	}
	
	@FXML
	private void sure() {
		//TODO
		
		cancel();
	}

	/**
	 * 用于TableView数据绑定的类
	 * @author yyr
	 *
	 */
	public class Info{
		private SimpleStringProperty reciptType;
		private SimpleStringProperty reciptId;
		private SimpleStringProperty operator;
		private SimpleStringProperty date;
		
		public Info(String rt, String id, String o, String d) {
			reciptType = new SimpleStringProperty(rt);
			reciptId = new SimpleStringProperty(id);
			operator = new SimpleStringProperty(o);
			date = new SimpleStringProperty(d);
		}

		public String getReciptType() {
			return reciptType.get();
		}

		public void setReciptType(String type) {
			this.reciptType.set(type);
		}

		public String getReciptId() {
			return reciptId.get();
		}

		public void setReciptId(String id) {
			this.reciptId.set(id);
		}

		public String getOperator() {
			return operator.get();
		}

		public void setOperator(String op) {
			this.operator.set(op);
		}

		public String getDate() {
			return date.get();
		}

		public void setDate(String date) {
			this.date.set(date);
		}

	}

	/**
	 * 用于TableView数据绑定的类
	 * @author 杨袁瑞
	 *
	 */
	public class InfoInFinancialRecipt{
		private SimpleStringProperty name;
		private SimpleDoubleProperty amount;
		private SimpleStringProperty comment;
		
		/**
		 * 构造一个空的Info
		 */
		public InfoInFinancialRecipt(){
			name = new SimpleStringProperty("");
			amount = new SimpleDoubleProperty(0);
			comment = new SimpleStringProperty("");
		}
		
		/**
		 * 构造一个已有信息的Info
		 * @param name 账户名称
		 * @param amount 转账金额
		 * @param comment 备注
		 */
		public InfoInFinancialRecipt(String name, double amount, String comment){
			this.name = new SimpleStringProperty(name);
			this.amount = new SimpleDoubleProperty(amount);
			this.comment = new SimpleStringProperty(comment);
		}
		
		public void setName(String n) {
			name.set(n);
		}
		
		public String getName() {
			return name.get();
		}
		
		public void setAmount(double a) {
			amount.set(a);
		}
		
		public double getAmount() {
			return amount.get();
		}
		
		public void setComment(String c) {
			comment.set(c);
		}
		
		public String getComment() {
			return comment.get();
		}
	}

	/**
	 * 用于TableView数据绑定的类
	 * @author 周正伟
	 *
	 */
	public class ListGoods{
		private String id;
		private String name;
		private String version;
		private String amount;
		private String bid;
		private String comment;
		
		public ListGoods(String id, String name, String version, String amount, String bid, String comment) {
			super();
			this.id = id;
			this.name = name;
			this.version = version;
			this.amount = amount;
			this.bid = bid;
			this.comment = comment;
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
		public String getComment() {
			return comment;
		}
		public void setComment(String comment) {
			this.comment = comment;
		}

	}
}
