package main.Presentation.ManagerUI;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import main.Presentation.MainUI.LoginUI;
import main.Presentation.MainUI.MainFrame;
import main.Presentation.MainUI.MainUIController;

public class ManagerHomeFrame extends MainUIController{
	@FXML
	private ImageView head;
	
	/**
	 * 总经理主界面的初始化方法
	 * @return 返回加载好的界面
	 */
	public static Parent init(){
		try {
			GridPane homeFrame = FXMLLoader.load(ManagerHomeFrame.class.getResource("ManagerHomeFrameFXML.fxml"));
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
	private void changeUser(){
		//提示是否确认切换
		
		MainFrame.setSceneRoot(LoginUI.init());
	}

	/**
	 * 审批单据按钮的监听
	 */
	@FXML
	private void checkRecipt() {
		Parent checkReciptFrame = CheckReciptFrame.init();
		MainFrame.setSceneRoot(checkReciptFrame);
	}
	
	/**
	 * 经营记录查看按钮的监听
	 */
	@FXML
	private void checkRecord() {
		Parent recordFrame = RecordFrame.init();
		MainFrame.setSceneRoot(recordFrame);
	}

	/**
	 * 制定促销策略按钮的监听
	 */
	@FXML
	private void promotion() {
		Parent promotionFrame = PromotionFrame.init();
		MainFrame.setSceneRoot(promotionFrame);
	}

}
