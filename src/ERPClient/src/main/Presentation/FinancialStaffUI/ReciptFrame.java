package main.Presentation.FinancialStaffUI;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import main.Presentation.FinancialStaffUI.ReciptUI.CashBillFrame;
import main.Presentation.FinancialStaffUI.ReciptUI.CheckHistoryFrame;
import main.Presentation.FinancialStaffUI.ReciptUI.CollectionOrderFrame;
import main.Presentation.FinancialStaffUI.ReciptUI.PaymentOrderFrame;
import main.Presentation.MainUI.MainFrame;
import main.Presentation.MainUI.MainUIController;

/**
 * 财务单据界面的初始化，监听，界面跳转
 * @author 杨袁瑞
 *
 */
public class ReciptFrame extends MainUIController{
	
	@FXML
	private ImageView head;
	
	/**
	 * 财务单据界面的初始化方法
	 * @return 返回加载好的界面
	 */
	public static Parent init(){
		try {
			GridPane reciptFrame = FXMLLoader.load(ReciptFrame.class.getResource("ReciptFrameFXML.fxml"));
			return reciptFrame;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new GridPane();		//方便调试
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
	 * 主页按钮的监听
	 */
	@FXML
	public void home(){
		Parent home = FinancialStaffHomeFrame.init();
		MainFrame.setSceneRoot(home);
	}

	/**
	 * 返回按钮的监听
	 */
	@FXML
	public void back(){
		Parent back = FinancialStaffHomeFrame.init();
		MainFrame.setSceneRoot(back);
	}

	/**
	 * 制定收款单按钮的监听
	 */
	@FXML
	public void collectionOrder(){
		Parent collectionOrderFrame = CollectionOrderFrame.init();
		MainFrame.setSceneRoot(collectionOrderFrame);
	}
	
	/**
	 * 制定付款单按钮的监听
	 */
	@FXML
	public void paymentOrder(){
		Parent paymentOrderFrame = PaymentOrderFrame.init();
		MainFrame.setSceneRoot(paymentOrderFrame);
	}

	/**
	 * 制定现金费用单按钮的监听
	 */
	@FXML
	public void cashBill(){
		Parent cashBillFrame = CashBillFrame.init();
		MainFrame.setSceneRoot(cashBillFrame);
	}

	/**
	 * 查看以往单据按钮的监听
	 */
	@FXML
	public void checkHistory(){
		Parent checkHistoryFrame = CheckHistoryFrame.init();
		MainFrame.setSceneRoot(checkHistoryFrame);
	}

}
