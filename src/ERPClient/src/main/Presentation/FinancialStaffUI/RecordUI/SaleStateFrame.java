package main.Presentation.FinancialStaffUI.RecordUI;

import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import main.BussinessLogicService.RecordBLService.RecordBLService;
import main.Presentation.FinancialStaffUI.FinancialStaffHomeFrame;
import main.Presentation.FinancialStaffUI.RecordFrame;
import main.Presentation.MainUI.MainFrame;
import main.Presentation.MainUI.MainUIController;
import main.RMI.RemoteHelper;
import main.VO.SaleStateVO;

/**
 * 查看经营情况表界面的controller
 * @author 49869
 *
 */
public class SaleStateFrame extends MainUIController{
	@FXML
	private ImageView head;
	
	@FXML
	DatePicker startDatePicker;
	@FXML
	DatePicker endDatePicker;

	@FXML
	TextField saleIncomeField;
	@FXML
	TextField goodsIncomeField;
	@FXML
	TextField goodsDiscountField;
	@FXML
	TextField saleDiscountField;
	@FXML
	TextField saleCostField;
	@FXML
	TextField goodsCostField;
	@FXML
	TextField profitField;
	
	RecordBLService recordBL;
	SaleStateVO saleStateVO;
	
	/**
	 * 加载查看经营情况表界面
	 * @return 加载好的界面
	 */
	public static Parent init() {
		try {
			GridPane saleStateFrame = FXMLLoader.load(SaleStateFrame.class.getResource("SaleStateFrameFXML.fxml"));
			return saleStateFrame;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new GridPane();
	}

	public void initialize() {
		recordBL = RemoteHelper.getRecordBLService();
		startDatePicker.setValue(LocalDate.now());
		endDatePicker.setValue(LocalDate.now());
		
		loadInfo();
	}
	
	void loadInfo() {
		String startTime = startDatePicker.getValue().format(DateTimeFormatter.BASIC_ISO_DATE);
		String endTime = endDatePicker.getValue().format(DateTimeFormatter.BASIC_ISO_DATE);
		try {
			saleStateVO = recordBL.getSalesStateList(startTime, endTime);
			saleIncomeField.setText(""+saleStateVO.getSaleIncome());
			goodsIncomeField.setText(""+saleStateVO.getGoodsIncome());
			goodsDiscountField.setText(""+saleStateVO.getGoodsDiscount());
			saleDiscountField.setText("");		//TODO
			saleCostField.setText(""+saleStateVO.getSaleCost());
			goodsCostField.setText(""+saleStateVO.getGoodsCost());
			profitField.setText(""+saleStateVO.getTotal());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * datePicker的监听
	 */
	@FXML
	void setTime() {
		loadInfo();
	}
	
	/**
	 * 主页按钮的监听
	 */
	@FXML
	protected void home() {
		Parent home = FinancialStaffHomeFrame.init();
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
	
	/**
	 * 确认按钮的监听
	 */
	@FXML
	void sure() {
		back();
	}

	/**
	 * 导出为Excel按钮的监听
	 */
	@FXML
	void outputToExcel() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Excel 文件(*.xls)", "*.xls"));
		fileChooser.setInitialFileName("经营情况表-"+LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE)+".xls");
		File file = fileChooser.showSaveDialog(new Stage());
		if(file == null) {
			return;
		}
		
		String name = file.getName();
		String path = file.getParentFile().getAbsolutePath();

//		System.out.println(name);
//		System.out.println(path);
		try {
			recordBL.saleStateToExcel(saleStateVO, name, path);
			Alert alert = new Alert(AlertType.INFORMATION, "成功导出到 "+path+name);
			alert.show();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
