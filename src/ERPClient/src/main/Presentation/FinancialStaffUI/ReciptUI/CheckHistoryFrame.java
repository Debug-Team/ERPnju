package main.Presentation.FinancialStaffUI.ReciptUI;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Comparator;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import javafx.util.converter.DoubleStringConverter;
import main.BussinessLogicService.ReciptBLService.ReciptBLService;
import main.Presentation.FinancialStaffUI.FinancialStaffHomeFrame;
import main.Presentation.FinancialStaffUI.ReciptFrame;
import main.Presentation.MainUI.MainFrame;
import main.Presentation.MainUI.MainUIController;
import main.RMI.RemoteHelper;
import main.VO.CashBillVO;
import main.VO.CashItemVO;
import main.VO.CollectionItemVO;
import main.VO.CollectionOrderVO;
import main.VO.PaymentItemVO;
import main.VO.PaymentOrderVO;

/**
 * 查看历史单据界面的controller
 * @author 49869
 *
 */
public class CheckHistoryFrame extends MainUIController{
	@FXML
	private ImageView head;

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
	TableColumn<Info, String> stateColumn;
	
	@FXML
	Button checkButton;
	
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

	private ReciptBLService reciptBL;
//	private ArrayList<ReciptVO> reciptList;
	private ArrayList<CollectionOrderVO> collectionOrderVOList;
	private ArrayList<PaymentOrderVO> paymentOrderVOList;
	private ArrayList<CashBillVO> cashBillVOList;
	
	/**
	 * 加载查看历史单据界面
	 * @return 加载好的界面
	 */
	public static Parent init() {
		try {
			GridPane checkHistoryFrame = FXMLLoader.load(CheckHistoryFrame.class.getResource("CheckHistoryFrameFXML.fxml"));
			return checkHistoryFrame;
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
		reciptBL = RemoteHelper.getReciptBLService();
		
//		startDatePicker.setValue(LocalDate.now());
//		endDatePicker.setValue(LocalDate.now());
		
		reciptTypeColumn.setCellValueFactory(new PropertyValueFactory<Info, String>("reciptType"));
		reciptIdColumn.setCellValueFactory(new PropertyValueFactory<Info, String>("reciptId"));
		operatorColumn.setCellValueFactory(new PropertyValueFactory<Info, String>("operator"));
		dateColumn.setCellValueFactory(new PropertyValueFactory<Info, String>("date"));
		stateColumn.setCellValueFactory(new PropertyValueFactory<Info, String>("state"));
		
		reciptTable.setRowFactory(new Callback<TableView<Info>, TableRow<Info>>(){
			@Override
			public TableRow<Info> call(TableView<Info> param) {
				return new TableRowControl();
			}
		});
		reciptTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		loadInfo();
		
		//收款单界面
		initCollectionOrderTab();
		initPaymentOrderTab();
		initCashBillTab();
	}
	
	private void initCollectionOrderTab() {
		//初始化table
		nameColumnInCollection.setCellValueFactory(new PropertyValueFactory<InfoInFinancialRecipt, String>("name"));
		
		amountColumnInCollection.setCellValueFactory(new PropertyValueFactory<InfoInFinancialRecipt, Double>("amount"));
//		amountColumnInCollection.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
//		amountColumnInCollection.setOnEditCommit((CellEditEvent<InfoInFinancialRecipt, Double> t) -> {
//			t.getTableView().getItems().get(t.getTablePosition().getRow()).setAmount(t.getNewValue());
//			calcSumInCollection();
//		});
		
		commentColumnInCollection.setCellValueFactory(new PropertyValueFactory<InfoInFinancialRecipt, String>("comment"));
		commentColumnInCollection.setCellFactory(TextFieldTableCell.forTableColumn());
		commentColumnInCollection.setOnEditCommit((CellEditEvent<InfoInFinancialRecipt, String> t) -> {
			t.getTableView().getItems().get(t.getTablePosition().getRow()).setComment(t.getNewValue());
		});
	}
	private void initPaymentOrderTab() {
		//初始化table
		nameColumnInPayment.setCellValueFactory(new PropertyValueFactory<InfoInFinancialRecipt, String>("name"));
		
		amountColumnInPayment.setCellValueFactory(new PropertyValueFactory<InfoInFinancialRecipt, Double>("amount"));
//		amountColumnInPayment.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
//		amountColumnInPayment.setOnEditCommit((CellEditEvent<InfoInFinancialRecipt, Double> t) -> {
//			t.getTableView().getItems().get(t.getTablePosition().getRow()).setAmount(t.getNewValue());
//			calcSumInCollection();
//		});
		
		commentColumnInPayment.setCellValueFactory(new PropertyValueFactory<InfoInFinancialRecipt, String>("comment"));
		commentColumnInPayment.setCellFactory(TextFieldTableCell.forTableColumn());
		commentColumnInPayment.setOnEditCommit((CellEditEvent<InfoInFinancialRecipt, String> t) -> {
			t.getTableView().getItems().get(t.getTablePosition().getRow()).setComment(t.getNewValue());
		});
	}
	private void initCashBillTab() {
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

	private void loadInfo() {
		try {
			//reciptList = reciptBL.getAllRecipts();
			collectionOrderVOList = reciptBL.getAllCollectionOrderList();
			paymentOrderVOList = reciptBL.getAllPaymentOrderList();
			cashBillVOList = reciptBL.getAllCashBillList();
			
			reciptTable.getItems().clear();
//			for(ReciptVO vo : reciptList) {
//				String id = vo.getId();
//				String type = id.substring(0, id.indexOf("-"));
//				String date = id.substring(id.indexOf("-")+1);
//				switch(type) {
//				case "SKD":{
//					type = "收款单";
//					CollectionOrderVO collectionVO = (CollectionOrderVO)vo;
//					reciptTable.getItems().add(new Info(
//							type, id, collectionVO.getOperator(), date, collectionVO.getState()));
//					break;
//				}
//				case "FKD":{
//					type = "付款单";
//					PaymentOrderVO paymentVO = (PaymentOrderVO)vo;
//					reciptTable.getItems().add(new Info(
//							type, id, paymentVO.getOperator(), date, paymentVO.getState()));
//					break;
//				}
//				case "XJFYD":{
//					type = "现金费用单";
//					CashBillVO cashVO = (CashBillVO)vo;
//					reciptTable.getItems().add(new Info(
//							type, id, cashVO.getOperator(), date, cashVO.getState()));
//					break;
//				}
//				}
//			}
			for(CollectionOrderVO vo : collectionOrderVOList) {
				String id = vo.getId();
				String date = id.substring(id.indexOf("-")+1);
				String type = "收款单";
				reciptTable.getItems().add(new Info(
						type, id, vo.getOperator(), date, vo.getState()));
			}
			for(PaymentOrderVO vo : paymentOrderVOList) {
				String id = vo.getId();
				String date = id.substring(id.indexOf("-")+1);
				String type = "付款单";
				reciptTable.getItems().add(new Info(
						type, id, vo.getOperator(), date, vo.getState()));
			}
			for(CashBillVO vo : cashBillVOList) {
				String id = vo.getId();
				String date = id.substring(id.indexOf("-")+1);
				String type = "现金费用单";
				reciptTable.getItems().add(new Info(
						type, id, vo.getOperator(), date, vo.getState()));
			}
			
			reciptTable.getItems().sort(new Comparator<Info>() {
				@Override
				public int compare(Info o1, Info o2) {
					return -o1.getDate().compareTo(o2.getDate());
				}
			});

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	 * 显示一张单据的方法
	 */
	@FXML
	private void showRecipt() {
		Info info = reciptTable.getSelectionModel().getSelectedItem();
		String type = info.getReciptType();
		switch(type) {
		case "收款单":{
			for(CollectionOrderVO vo : collectionOrderVOList) {
				if(vo.getId().equals(info.getReciptId())) {
					showCollectionOrder(vo);
				}
			}
			break;
		}
		case "付款单":{
			for(PaymentOrderVO vo : paymentOrderVOList) {
				if(vo.getId().equals(info.getReciptId())) {
					showPaymentOrder(vo);
				}
			}
			break;
		}
		case "现金费用单":{
			for(CashBillVO vo : cashBillVOList) {
				if(vo.getId().equals(info.getReciptId())) {
					showCashBill(vo);
				}
			}
		}
		}
	}
	
	/**
	 * 显示一张收款单
	 * @param vo 要显示的收款单VO
	 */
	private void showCollectionOrder(CollectionOrderVO vo) {
		idInCollection.setText(vo.getId());
		supplierInCollection.setText(vo.getSupplier());
		sellerInCollection.setText(vo.getRetailer());
		ArrayList<CollectionItemVO> itemList = vo.getItemList();
		
		tableInCollection.getItems().clear();
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
		Parent home = FinancialStaffHomeFrame.init();
		MainFrame.setSceneRoot(home);
	}
	
	/**
	 * 返回按钮的监听
	 */
	@FXML
	private void back() {
		Parent back = ReciptFrame.init();
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
	 * 用于TableView数据绑定的类
	 * @author yyr
	 *
	 */
	public class Info{
		private SimpleStringProperty reciptType;
		private SimpleStringProperty reciptId;
		private SimpleStringProperty operator;
		private SimpleStringProperty date;
		private SimpleStringProperty state;
		
		public Info(String reciptType, String reciptId, String operator, String date, String state) {
			this.reciptType = new SimpleStringProperty(reciptType);
			this.reciptId = new SimpleStringProperty(reciptId);
			this.operator = new SimpleStringProperty(operator);
			this.date = new SimpleStringProperty(date);
			this.state = new SimpleStringProperty(state);
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

		public void setState(String state) {
			this.state.set(state);
		}
		public String getState() {
			return state.get();
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

}
