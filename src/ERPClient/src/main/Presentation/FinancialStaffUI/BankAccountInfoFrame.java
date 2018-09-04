package main.Presentation.FinancialStaffUI;

import java.io.IOException;
import java.rmi.RemoteException;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.BussinessLogicService.BankAccountBLService.BankAccountBLService;
import main.Presentation.MainUI.MainFrame;
import main.Presentation.MainUI.MainUIController;
import main.RMI.RemoteHelper;
import main.VO.BankAccountVO;
import main.utility.ResultMessage;

/**
 * 用于银行账户信息弹窗的初始化及事件处理
 * @author 杨袁瑞
 *
 */
public class BankAccountInfoFrame extends MainUIController{
	
	public static BankAccountBLService bankAccountBL = RemoteHelper.getBankAccountBLService();
	
	@FXML
	private TextField nameField;
	
	@FXML
	private TextField amountField;
	
	@FXML
	private Button sureButton;
	@FXML
	private Button modifyButton;
	
	@FXML
	private Label titleLabel;
		
	private Stage popUpWindow = new Stage();
	
	private static String initName = "";		//仅用作传递初始值，不作其他用途
	private static double initAmount = 0;
	private static boolean editable = false;	//仅用作传递是否是编辑模式，不做其他用途
	
	/**
	 * 初始化信息界面
	 * @param name 要显示的名字，如果是新增，name为""
	 * @param amount 要显示的金额
	 * @param isModify 是否是修改模式
	 */
	public void initInfo(String name, double amount, boolean isModify){
		initName = name;
		initAmount = amount;
		editable = isModify;
		
		GridPane popUp = new GridPane();
		try {
			popUp = FXMLLoader.load(BankAccountInfoFrame.class.getResource("BankAccountInfoFXML.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scene popUpScene = new Scene(popUp);
		popUpWindow.setScene(popUpScene);
		popUpWindow.initStyle(StageStyle.UNDECORATED);
		popUpWindow.show();
	}
	
	/**
	 * 加载FXML时自动调用的方法，设置文本框显示的值，并根据初始值，设定组件的属性
	 */
	@FXML
	public void initialize(){
		if(editable){				//如果是编辑模式
			modifyInInfo();
		}
		if(initName.equals("")){	//如果是新增模式
			titleLabel.setText("新增银行账户");
			modifyButton.setVisible(false);
		}
		nameField.setText(initName);
		amountField.setText(""+initAmount);
		
		editable = false;
	}
	
	/**
	 * 确认按钮的监听
	 */
	@FXML
	private void sureInInfo(Event e){
		if(nameField.isEditable()){		//编辑模式（修改或者新增）
			//调用接口，新增
			BankAccountVO vo = new BankAccountVO(nameField.getText(), Double.parseDouble(amountField.getText()), username);
			try {
				ResultMessage rm = null;
//				System.out.println(bankAccountBL.add(vo));
				if(titleLabel.getText().equals("新增银行账户")) {
					rm = bankAccountBL.add(vo);
				}
				if(titleLabel.getText().equals("修改银行账户信息")) {
					rm = bankAccountBL.modify(vo);
				}
				if(rm == ResultMessage.FAIL) {
					System.out.println("fail");
				}
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
//			BankAccountFrame.updateTable();
			MainFrame.setSceneRoot(BankAccountFrame.init());
		}
		cancelInInfo(e);
	}
	
	/**
	 * 修改按钮的监听
	 */
	@FXML
	public void modifyInInfo(){
		titleLabel.setText("修改银行账户信息");
		modifyButton.setVisible(false);
		nameField.setEditable(true);
		amountField.setEditable(true);
	}
	
	/**
	 * 取消按钮的监听
	 * @param e Event
	 */
	@FXML
	private void cancelInInfo(Event e){
		Button temp = (Button)e.getSource();
		temp.getScene().getWindow().hide();
	}
}
