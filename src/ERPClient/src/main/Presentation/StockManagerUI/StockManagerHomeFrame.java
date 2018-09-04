package main.Presentation.StockManagerUI;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import main.Presentation.MainUI.LoginUI;
import main.Presentation.MainUI.MainFrame;
import main.Presentation.MainUI.MainUIController;
import main.Presentation.SalesmanUI.SalesmanHomeFrame;

/**
 * 库存管理人员主界面的控制类
 * @author 周正伟
 *
 */
public class StockManagerHomeFrame extends MainUIController{
	
	@FXML
	private ImageView head;
	
	/**
	 * 库存管理人员主界面的初始化方法
	 * @return 返回加载好的界面
	 */
	public static Parent init(){
		try {
			GridPane homeFrame = FXMLLoader.load(StockManagerHomeFrame.class.getResource("StockManagerHomeFrameFXML.fxml"));
			return homeFrame;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new GridPane();
	}
	
	/**
	 * 商品分类管理主界面
	 */
	@FXML
	public void categoryManageFrameHandle(){
		MainFrame.setSceneRoot(CategoryManageFrame.init());
	}
	
	/**
	 * 商品管理主界面
	 */
	@FXML
	public void GoodsManageFrameHandle(){
		MainFrame.setSceneRoot(GoodsManageFrame.init());
	}
	
	/**
	 * 库存管理主界面
	 */
	@FXML
	public void CommodityFrameHandle(){
		MainFrame.setSceneRoot(CommodityFrame.init());
	}
	
	/**
	 * 切换用户按钮的监听
	 */
	@FXML
	public void changeusers(){
		MainFrame.setSceneRoot(LoginUI.init());
	}
	
	
}
