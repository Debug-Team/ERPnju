package main.Presentation.FinancialStaffUI.ReciptUI;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Optional;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import main.VO.BankAccountVO;
import main.VO.CashBillVO;
import main.VO.CashItemVO;
import main.VO.CustomerVO;;

/**
 * 现金费用单界面的controller
 * @author 49869
 *
 */
public class CashBillFrame extends EachReciptUI{
//	@FXML
//	private TextField bankAccountField;

	@FXML
	private ComboBox<String> searchBox;
	
	/**
	 * 制定现金费用单界面的初始化方法
	 * @return 返回加载好的界面
	 */
	public static Parent init(){
		try {
			GridPane cashBillFrame = FXMLLoader.load(CashBillFrame.class.getResource("CashBillFrameFXML.fxml"));
			return cashBillFrame;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new GridPane();		//方便调试
	}
	
	/**
	 * 确认按钮的监听
	 */
	@FXML
	private void sure() {
		//打包数据为VO
//		String bankAccountID = bankAccountField.getText();
		String bankAccountID = searchBox.getValue();
		
		ArrayList<CashItemVO> itemVOList = new ArrayList<CashItemVO>();
		ObservableList<Info> infoList = table.getItems();
		for(int i = 0; i < infoList.size();i++) {
			Info info = infoList.get(i);
			CashItemVO vo = new CashItemVO(info.getName(), info.getAmount(), info.getComment());
			itemVOList.add(vo);
		}
		double sum = Double.parseDouble(sumField.getText());
		
		CashBillVO vo = new CashBillVO(username, bankAccountID, itemVOList, sum);
		
//		try {
//			reciptBL.setCashBill(vo);
//		} catch (RemoteException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		back();
		Alert alert = new Alert(AlertType.CONFIRMATION, "", ButtonType.OK, new ButtonType("保存为草稿"), ButtonType.CANCEL);
		alert.setHeaderText("是否提交？");
		Optional<ButtonType> buttonType = alert.showAndWait();
		if(buttonType.get().getText().equals("确定")) {
			//System.out.println("确定");
			try {
				reciptBL.setCashBill(vo);
				back();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		else if(buttonType.get().getText().equals("保存为草稿")) {
			//System.out.println("草稿");
			vo.setState("Draft");
			try {
				reciptBL.setCashBill(vo);
				back();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}

	}
	
	/**
	 * 加载银行账户列表
	 */
	@FXML
	private void loadBankAcocunt() {
		isModify = true;
		searchBox.getSelectionModel().clearSelection();
		searchBox.getItems().clear();
		isModify = false;
		try {
			String value = searchBox.getValue();
			if(value == null) {
				value = "";
			}
			bankAccountVOList = reciptBL.getBankAccount(value);
//			customerVOList = accountBL.getCustomer("name", value);

			for(int i = 0;i < bankAccountVOList.size();i++) {
				BankAccountVO vo = bankAccountVOList.get(i);
				searchBox.getItems().add(vo.getId());
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@FXML
	private void searchBankAccount() {
		if(isModify) {
			return;
		}
		if(searchBox.getSelectionModel().isEmpty()) {
			loadBankAccount();
			searchBox.show();
		}
	}
	
	/**
	 * 取消按钮的监听
	 */
	@FXML
	private void cancel() {
		//是否保存为草稿
		
		back();
	}

}
