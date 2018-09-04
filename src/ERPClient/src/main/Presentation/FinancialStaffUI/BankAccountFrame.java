package main.Presentation.FinancialStaffUI;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import main.BussinessLogicService.BankAccountBLService.BankAccountBLService;
import main.Presentation.MainUI.MainFrame;
import main.Presentation.MainUI.MainUIController;
import main.RMI.RemoteHelper;
import main.VO.BankAccountVO;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;

/**
 * 银行账户管理界面的初始化，监听，界面跳转
 * @author 杨袁瑞
 *
 */
public class BankAccountFrame extends MainUIController{
	
	public static BankAccountBLService bankAccountBL;
	
	@FXML
	ImageView head;
	
	@FXML
	TextField searchField;
	
	@FXML
	TableView<Info> table;
	@FXML
	TableColumn<Info, String> nameColumn;
//	@FXML
//	TableColumn<Info, Boolean> multiSelectedColumn;
	
	@FXML
	Button checkButton;
	@FXML
	Button modifyButton;
	@FXML
	Button addButton;
	@FXML
	Button deleteButton;
	
	private ObservableList<Info> list;
	
	/**
	 * 初始化银行账户管理界面
	 * @return
	 */
	public static Parent init(){
		bankAccountBL = RemoteHelper.getBankAccountBLService();
		
		try {
			GridPane bankAccountFrame = FXMLLoader.load(BankAccountFrame.class.getResource("BankAccountFrameFXML.fxml"));
			return bankAccountFrame;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new GridPane();
	}
	
	/**
	 * 每次加载FXML时，对Table进行初始化操作
	 */
	@FXML
	public void initialize(){
		nameColumn.setCellValueFactory(new PropertyValueFactory<Info, String>("name"));
//		multiSelectedColumn.setCellValueFactory(new PropertyValueFactory<Info, Boolean>("isMultiSelected"));
//		multiSelectedColumn.setCellFactory(CheckBoxTableCell.forTableColumn(multiSelectedColumn));
		
		loadInfo();
		table.setItems(list);
		table.setRowFactory(new Callback<TableView<Info>, TableRow<Info>>(){
			@Override
			public TableRow<Info> call(TableView<Info> param) {
				return new TableRowControl();
			}
		});
		table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

	}
	
	/**
	 * 加载银行账户的信息
	 */
	private void loadInfo(){
		//list = FXCollections.observableArrayList(new Info("test"));
		try {
			ArrayList<BankAccountVO> banklist = bankAccountBL.getBankAccountList();
			ArrayList<Info> infoList = new ArrayList<Info>();
			for(int i = 0;i < banklist.size();i++) {
				BankAccountVO vo = banklist.get(i);
				Info info = new Info(vo.getId());
				infoList.add(info);
			}
			list = FXCollections.observableArrayList(infoList);
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
		Parent back = FinancialStaffHomeFrame.init();
		MainFrame.setSceneRoot(back);
	}

	/**
	 * 搜索框的键盘监听
	 */
	@FXML
	private void searchFieldActionHandle(KeyEvent ke){
		if(ke.getCode().equals(KeyCode.ENTER)){
			search();
		}
	}
	
	/**
	 * 搜索图标的监听
	 */
	@FXML
	private void search(){
//		list.add(new Info("hh"));
		try {
			ArrayList<BankAccountVO> banklist = bankAccountBL.partFind(searchField.getText());
			ArrayList<Info> infoList = new ArrayList<Info>();
			for(int i = 0;i < banklist.size();i++) {
				BankAccountVO vo = banklist.get(i);
				Info info = new Info(vo.getId());
				infoList.add(info);
			}
			list = FXCollections.observableArrayList(infoList);
			table.setItems(list);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	
	/**
	 * 增加银行账户按钮的监听
	 */
	@FXML
	private void add(){
		BankAccountInfoFrame addFrame = new BankAccountInfoFrame();
		addFrame.initInfo("", 0, true);
	}
	
	/**
	 * 删除银行账户的监听
	 */
	@FXML
	private void delete(){
//		System.out.println(list.size());
//		for(int i = 0;i < list.size();i++){
//			Info temp = list.get(i);
//			System.out.println(temp.isMultiSelected());
//			if(temp.isMultiSelected()){
//				deleteBankAccount(temp.getName());
//			}
//		}
//		list.iterator().next().isMultiSelected();
//		multiSelectedColumn.getCellFactory().call(multiSelectedColumn).getItem().booleanValue();
		
//		ObservableList<Info> toDeleteList = table.getSelectionModel().getSelectedItems();
//		for(int i = 0;i < toDeleteList.size();i++){
//			//删除该项
//			toDeleteList.get(i);
//		}
		ObservableList<Info> toDeleteInfo = table.getSelectionModel().getSelectedItems();
		ObservableList<Info> tempList = table.getItems();
		
		//删除
		for(int i = 0;i < toDeleteInfo.size();i++){
			deleteBankAccount(toDeleteInfo.get(i).getName());
		}

		tempList.removeAll(toDeleteInfo);
		
		table.getSelectionModel().clearSelection();
		table.setItems(tempList);
	}
	
	/**
	 * 删除一个银行账户的方法
	 * @param name 要删除的银行账户的名字
	 */
	private void deleteBankAccount(String name){
//		System.out.println("to delete:" + name);
		try {
			bankAccountBL.delete(name, username);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 修改银行账户的监听
	 */
	@FXML
	private void modify(){
		String name = table.getSelectionModel().getSelectedItem().getName();
//		double amount = 100;
		//搜索对应的用户信息，并打开
		try {
			BankAccountVO vo = bankAccountBL.find(name);
			BankAccountInfoFrame modifyFrame = new BankAccountInfoFrame();
//			modifyFrame.initInfo(name, amount, true);
			modifyFrame.initInfo(name, vo.getAmount(), true);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	/**
	 * 查看银行账户的监听
	 */
	@FXML
	private void check(){
		String name = table.getSelectionModel().getSelectedItem().getName();
		//搜索对应的用户信息，并打开
//		double amount = 100;
//		new BankAccountInfoFrame().initInfo(name, amount, false);

		try {
			BankAccountVO vo = bankAccountBL.find(name);
			new BankAccountInfoFrame().initInfo(name, vo.getAmount(), false);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
//	@FXML
//	public void doubleClickedCheck(MouseEvent me){
//		if(me.getClickCount() == 2){
//		}
//		else{
//			return;
//		}
//	}
	
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
							&& TableRowControl.this.getIndex() < table.getItems().size()){
						//设置按钮可用 
						checkButton.setDisable(false);
						modifyButton.setDisable(false);
						deleteButton.setDisable(false);
						
						if(table.getSelectionModel().getSelectedItems().size() > 1){		//多选时不可以查看和修改
							modifyButton.setDisable(true);
							checkButton.setDisable(true);
						}
					}
					if(event.getButton() == MouseButton.PRIMARY
							&& event.getClickCount() == 2
							&& TableRowControl.this.getIndex() < table.getItems().size()){
//						System.out.println("test");
						check();
					}
				}
			});
		}
	}

	/**
	 * tableView中用于数据绑定的类
	 * @author 杨袁瑞
	 */
	public class Info{
		private SimpleStringProperty name;
//		private SimpleBooleanProperty isMultiSelected;
		
		/**
		 * 
		 * @param 银行账户的名称
		 */
		Info(String name){
			this.name = new SimpleStringProperty(name);
//			this.isMultiSelected = new SimpleBooleanProperty(false);
		}

		public String getName() {
			return name.get();
		}

		public void setName(String name) {
			this.name.set(name);
		}
		
//		public boolean isMultiSelected(){
//			return isMultiSelected.get();
//		}
//		
//		public void setMultiSelected(boolean newMultiSelected){
//			isMultiSelected.set(newMultiSelected);
//		}
	}
	
//	private Stage popUpWindow = new Stage();
//	
//	/**
//	 * 加载一个弹窗
//	 * @param popUp 需要弹窗的组件
//	 */
//	void initPopupWindow(Parent popUp){
//		popUpWindow.initStyle(StageStyle.UNDECORATED);
//		
//		Scene popUpScene = new Scene(popUp);
//		popUpWindow.setScene(popUpScene);
//		popUpWindow.show();
//	}
	
}
