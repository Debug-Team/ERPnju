package main.Presentation.FinancialStaffUI;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import main.Presentation.FinancialStaffUI.AccountUI.CheckAccountFrame;
import main.Presentation.FinancialStaffUI.AccountUI.CreateAccountFrame;
import main.Presentation.MainUI.MainFrame;
import main.Presentation.MainUI.MainUIController;

/**
 * 账界面的controller
 * @author 49869
 *
 */
public class AccountFrame extends MainUIController{
	
	@FXML
	private ImageView head;
	
	public static Parent init(){
		try {
			GridPane accountFrame = FXMLLoader.load(AccountFrame.class.getResource("AccountFrameFXML.fxml"));
			return accountFrame;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new GridPane();
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
	 * 期初建账按钮的监听
	 */
	@FXML
	private void createAccount() {
		Parent createAccountFrame = CreateAccountFrame.init();
		MainFrame.setSceneRoot(createAccountFrame);
	}
	
	/**
	 * 查看期初信息按钮的监听
	 */
	@FXML
	private void checkAccountInfo() {
		Parent checkAccountFrame = CheckAccountFrame.init();
		MainFrame.setSceneRoot(checkAccountFrame);
	}
}
