package main.Presentation.FinancialStaffUI;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import main.Presentation.MainUI.LoginUI;
import main.Presentation.MainUI.MainFrame;
import main.Presentation.MainUI.MainUIController;

/**
 * 负责财务人员主界面的初始化以及控制财务人员部分的界面跳转
 * @author 杨袁瑞
 * @version 1.0
 * @date 2017/11/27
 */
public class FinancialStaffHomeFrame extends MainUIController{
	
	@FXML
	private ImageView head;
	
	/**
	 * 财务人员主界面的初始化方法
	 * @return 返回加载好的界面
	 */
	public static Parent init(){
		try {
			GridPane homeFrame = FXMLLoader.load(FinancialStaffHomeFrame.class.getResource("FinancialStaffHomeFrameFXML.fxml"));
			return homeFrame;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new GridPane();
	}
	
	/**
	 * 切换用户按钮的监听
	 */
	@FXML
	public void changeUser(){
		//提示是否确认切换
		
		MainFrame.setSceneRoot(LoginUI.init());
	}
	
	/**
	 * 退出按钮的监听
	 */
//	@FXML
//	public void exit(){
//		//提示是否确认退出
//		
//		System.exit(0);
//	}
//	
//	/**
//	 * 最小化按钮的监听
//	 */
//	@FXML
//	public void minimize(){
//		MainFrame.minimize();
//	}
	
	/**
	 * 银行账户管理按钮的监听，用以跳转至银行账户管理界面
	 */
	@FXML
	public void bankAccountManage(){
		Parent bankAccountFrame = BankAccountFrame.init();
		MainFrame.setSceneRoot(bankAccountFrame);
	}
	
	/**
	 * 财务单据按钮的监听，用以跳转至财务单据界面
	 */
	@FXML
	public void recipt(){
		Parent reciptFrame = ReciptFrame.init();
		MainFrame.setSceneRoot(reciptFrame);
	}
	
	/**
	 * 销售与经营记录查看按钮的监听，用以跳转至销售与经营记录查看界面
	 */
	@FXML
	public void checkRecord(){
		Parent recordFrame = RecordFrame.init();
		MainFrame.setSceneRoot(recordFrame);
	}
	
	/**
	 * 账按钮的监听，用以跳转至账界面
	 */
	@FXML
	public void account(){
		Parent accountFrame = AccountFrame.init();
		MainFrame.setSceneRoot(accountFrame);
	}
	
	/**
	 * 查看日志按钮的监听，用以跳转至查看日志界面
	 */
	@FXML
	public void checkLog() {
		
	}
}
