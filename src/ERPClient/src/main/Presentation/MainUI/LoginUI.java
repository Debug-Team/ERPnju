package main.Presentation.MainUI;

import java.rmi.RemoteException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import main.Presentation.AdministerUI.AdminHomeFrame;
import main.Presentation.FinancialStaffUI.FinancialStaffHomeFrame;
import main.Presentation.ManagerUI.ManagerHomeFrame;
import main.Presentation.SalesmanUI.SalesmanHomeFrame;
import main.Presentation.StockManagerUI.StockManagerHomeFrame;
import main.RMI.RemoteHelper;
import main.VO.UserVO;
import main.utility.UserType;


/**
 * 负责LoginUI的初始化、按键监听、界面跳转
 * @author 杨袁瑞、周正伟
 * @version 1.0
 * @date 2017/11/27
 */
public class LoginUI extends MainUIController{
	@FXML
	TextField username;
	
	@FXML
	PasswordField password;
	
	/**
	 * 初始化LoginUI
	 * @return 返回初始化加载好FXML的界面
	 */
	public static Parent init(){
		try {
			GridPane loginUI = FXMLLoader.load(MainFrame.class.getResource("LoginFXML.fxml"));
			return loginUI;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 登录按钮的监听
	 */
	@FXML
	public boolean login(){
		if (username.getText().equals("admin")&&password.getText().equals("admin")) {
			MainFrame.setSceneRoot(AdminHomeFrame.init());
		}else {
			
			UserType loginmessage = null;
			try {
				loginmessage = RemoteHelper.getLoginBLService().login(username.getText(), password.getText());
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			switch(loginmessage){
			case FINANCIAL_STAFF:{
				UserVO checked = null;
				try {
					checked = RemoteHelper.getLoginBLService().getUser(username.getText());
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				MainUIController.user = checked;
				MainUIController.username = checked.getName();
				MainFrame.setSceneRoot(FinancialStaffHomeFrame.init());
				break;
			}
			case SALES_MAN:{
				UserVO checked = null;
				try {
					checked = RemoteHelper.getLoginBLService().getUser(username.getText());
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				MainUIController.user = checked;
				MainUIController.username = checked.getName();
				MainFrame.setSceneRoot(SalesmanHomeFrame.init());
				break;
			}
			case MANAGER:{
				UserVO checked = null;
				try {
					checked = RemoteHelper.getLoginBLService().getUser(username.getText());
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				MainUIController.user = checked;
				MainUIController.username = checked.getName();
				MainFrame.setSceneRoot(ManagerHomeFrame.init());
				break;
			}
			case STOCK_MANAGER:{
				UserVO checked = null;
				try {
					checked = RemoteHelper.getLoginBLService().getUser(username.getText());
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				MainUIController.user = checked;
				MainUIController.username = checked.getName();
				MainFrame.setSceneRoot(StockManagerHomeFrame.init());
				break;
			}
			case NOT_FOUND:{
				Alert alert = new Alert(AlertType.ERROR);
				alert.setHeaderText(null);
				alert.setContentText("没有找到工号");
				alert.showAndWait();
				break;
			}
			case PASSWORD_WRONG:{
				Alert alert = new Alert(AlertType.ERROR);
				alert.setHeaderText(null);
				alert.setContentText("密码错误");
				alert.showAndWait();
				break;
			}
			}
		
		}
		return true;
	}
	
	/**
	 * 登录键盘的监听
	 */
	@FXML
	public void loginenter(KeyEvent event){
		if (event.getCode().equals(KeyCode.ENTER)) {
			login();
		}
	}

	/**
	 * 退出按钮的监听
	 */
	@FXML
	public void exit(){
		System.exit(0);
	}
	
	/**
	 * 最小化按钮的监听
	 */
	@FXML
	public void minimize(){
		MainFrame.minimize();
	}

}
