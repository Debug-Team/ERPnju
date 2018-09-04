package main.Presentation.ManagerUI;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import main.Presentation.MainUI.MainFrame;
import main.Presentation.MainUI.MainUIController;
import main.Presentation.ManagerUI.PromotionUI.CombineBagPromotionFrame;
import main.Presentation.ManagerUI.PromotionUI.CustomerPromotionFrame;
import main.Presentation.ManagerUI.PromotionUI.TotalPromotionFrame;

public class PromotionFrame extends MainUIController{

	@FXML
	private ImageView head;
	
	/**
	 * 经营记录查看界面的初始化方法
	 * @return 返回加载好的界面
	 */
	public static Parent init(){
		try {
			GridPane promotionFrame = FXMLLoader.load(PromotionFrame.class.getResource("PromotionFrameFXML.fxml"));
			return promotionFrame;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new GridPane();		//方便调试
	}


	/**
	 * 制定客户促销策略按钮的监听
	 */
	@FXML
	public void createCustomerPromotion(){
		Parent customerPromotionFrame = CustomerPromotionFrame.init();
		MainFrame.setSceneRoot(customerPromotionFrame);
	}

	/**
	 * 制定组合包促销策略按钮的监听
	 */
	@FXML
	public void createCombineBagPromotion(){
		Parent combineBagPromotionFrame = CombineBagPromotionFrame.init();
		MainFrame.setSceneRoot(combineBagPromotionFrame);
	}

	/**
	 * 制定总价促销策略按钮的监听
	 */
	@FXML
	public void createTotalPromotion(){
		Parent totalPromotionFrame = TotalPromotionFrame.init();
		MainFrame.setSceneRoot(totalPromotionFrame);
	}

	/**
	 * 主页按钮的监听
	 */
	@FXML
	public void home(){
		Parent home = ManagerHomeFrame.init();
		MainFrame.setSceneRoot(home);
	}

	/**
	 * 返回按钮的监听
	 */
	@FXML
	public void back(){
		Parent back = ManagerHomeFrame.init();
		MainFrame.setSceneRoot(back);
	}

}
