package main.Presentation.StockManagerUI;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import main.Presentation.MainUI.MainFrame;
import main.Presentation.MainUI.MainUIController;
import main.Presentation.StockManagerUI.CommodityUI.CheckCommodityFrame;
import main.Presentation.StockManagerUI.CommodityUI.DamageListFrame;
import main.Presentation.StockManagerUI.CommodityUI.GiftListFrame;
import main.Presentation.StockManagerUI.CommodityUI.OverflowListFrame;
import main.Presentation.StockManagerUI.CommodityUI.ViewCommodityFrame;
import main.Presentation.StockManagerUI.CommodityUI.WarningListFrame;

/**
 * 库存管理人员库存管理界面的控制类
 * @author 周正伟
 *
 */
public class CommodityFrame extends MainUIController{
	
	@FXML
	private ImageView head;
	
	/**
	 * 进货销售人员主界面的初始化方法
	 * @return 返回加载好的界面
	 */
	public static Parent init(){
		
		try {
			GridPane commoditymanage = FXMLLoader.load(CommodityFrame.class.getResource("CommodityFrameFXML.fxml"));
			return commoditymanage;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new GridPane();
		
	}
	
	/**
	 * 进入库存查看界面的监听
	 */
	@FXML
	protected void viewCommodityframe(ActionEvent event) {
        MainFrame.setSceneRoot(ViewCommodityFrame.init());
    }
	
	/**
	 * 进入库存盘点界面的监听
	 */
	@FXML
	protected void checkCommodityFrame(ActionEvent event) {
        MainFrame.setSceneRoot(CheckCommodityFrame.init());
    }
	
	
	
	/**
	 * 返回库存管理人员主界面按钮的监听
	 */
	@FXML
	protected void returnStockManagerHomeHandle(ActionEvent event) {
        MainFrame.setSceneRoot(StockManagerHomeFrame.init());
    }
}
