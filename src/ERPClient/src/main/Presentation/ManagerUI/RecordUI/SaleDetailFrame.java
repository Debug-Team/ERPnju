package main.Presentation.ManagerUI.RecordUI;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import main.Presentation.MainUI.MainFrame;
import main.Presentation.ManagerUI.ManagerHomeFrame;
import main.Presentation.ManagerUI.RecordFrame;

/**
 * 总经理的销售明细表界面的controller
 * @author yyr
 *
 */
public class SaleDetailFrame extends main.Presentation.FinancialStaffUI.RecordUI.SaleDetailFrame{
	/**
	 * 加载查看销售明细表界面
	 * @return 加载好的界面
	 */
	public static Parent init() {
		try {
			GridPane saleDetailFrame = FXMLLoader.load(SaleDetailFrame.class.getResource("SaleDetailFrameFXML.fxml"));
			return saleDetailFrame;
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
	protected void home() {
		Parent home = ManagerHomeFrame.init();
		MainFrame.setSceneRoot(home);
	}
	
	/**
	 * 返回按钮的监听
	 */
	@FXML
	protected void back() {
		Parent back = RecordFrame.init();
		MainFrame.setSceneRoot(back);
	}

}
