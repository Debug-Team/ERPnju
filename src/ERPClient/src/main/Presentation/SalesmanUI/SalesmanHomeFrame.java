package main.Presentation.SalesmanUI;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import main.Presentation.MainUI.LoginUI;
import main.Presentation.MainUI.MainFrame;
import main.Presentation.MainUI.MainUIController;

/**
 * 进货销售人员主界面的控制类
 * @author 周正伟
 *
 */
public class SalesmanHomeFrame extends MainUIController {
	
	/**
	 * 进货销售人员主界面的初始化方法
	 * @return 返回加载好的界面
	 */
	public static Parent init(){
		try {
			GridPane homeFrame = FXMLLoader.load(SalesmanHomeFrame.class.getResource("SalesManHomeFrameFXML.fxml"));
			return homeFrame;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new GridPane();
	}
	
	/**
	 * 切换用户按钮的监听
	 */
	@FXML
	public void changeusers(){
		MainFrame.setSceneRoot(LoginUI.init());
	}
	
	/**	
	 * 进入客户管理主界面按钮的监听
	 */
	@FXML
	public void CustomerManageHandle(ActionEvent event) {
		Parent customermanage = CustomerManageFrame.init();
        MainFrame.setSceneRoot(customermanage);
    }
	
	/**
	 * 进入货单管理主界面按钮的监听
	 */
	@FXML
	public void ManifestManageHandle(ActionEvent event) {
        MainFrame.setSceneRoot(ManifestFrame.init());
    }
	
}
