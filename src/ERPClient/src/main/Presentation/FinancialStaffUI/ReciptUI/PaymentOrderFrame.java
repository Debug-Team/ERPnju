package main.Presentation.FinancialStaffUI.ReciptUI;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Optional;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import main.Presentation.FinancialStaffUI.ReciptUI.EachReciptUI.Info;
import main.VO.BankAccountVO;
import main.VO.CustomerVO;
import main.VO.PaymentItemVO;
import main.VO.PaymentOrderVO;

/**
 * 付款单界面的controller
 * @author 49869
 *
 */
public class PaymentOrderFrame extends EachReciptUI{

	@FXML
	private ComboBox<String> supplierBox;
	@FXML
	private ComboBox<String> sellerBox;
	
	@FXML
	private ComboBox<String> searchBox;
	
//	private ArrayList<BankAccountVO> bankAccountVOList;
	private boolean isModify = false;
	private ArrayList<CustomerVO> customerVOList;

	/**
	 * 制定付款单界面的初始化方法
	 * @return 返回加载好的界面
	 */
	public static Parent init(){
		try {
			GridPane paymentOrderFrame = FXMLLoader.load(PaymentOrderFrame.class.getResource("PaymentOrderFrameFXML.fxml"));
			return paymentOrderFrame;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new GridPane();		//方便调试
	}

	public void initialize() {
		super.initialize();
		
		loadSupplier();
		loadSeller();
	}
		
	private void loadSupplier() {
		isModify = true;
		supplierBox.getSelectionModel().clearSelection();
		supplierBox.getItems().clear();
		isModify = false;
		try {
			String value = supplierBox.getValue();
			if(value == null) {
				value = "";
			}
			customerVOList = reciptBL.getCustomerList(value, "name");
//			customerVOList = accountBL.getCustomer("name", value);

			for(int i = 0;i < customerVOList.size();i++) {
				CustomerVO vo = customerVOList.get(i);
				if(vo.getCategory().equals("进货商")) {
					supplierBox.getItems().add(vo.getName());
				}
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void loadSeller() {
		isModify = true;
		sellerBox.getSelectionModel().clearSelection();
		sellerBox.getItems().clear();
		isModify = false;
		try {
			String value = sellerBox.getValue();
			if(value == null) {
				value = "";
			}
			customerVOList = reciptBL.getCustomerList(value, "name");
//			customerVOList = accountBL.getCustomer("name", value);

			for(int i = 0;i < customerVOList.size();i++) {
				CustomerVO vo = customerVOList.get(i);
				if(vo.getCategory().equals("销售商")) {
					sellerBox.getItems().add(vo.getName());
				}
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML
	private void searchSupplier() {
		if(isModify) {
			return;
		}
		if(supplierBox.getSelectionModel().isEmpty()) {
			loadSupplier();
			supplierBox.show();
		}
	}
	
	@FXML
	private void searchSeller() {
		if(isModify) {
			return;
		}
		if(sellerBox.getSelectionModel().isEmpty()) {
			loadSeller();
			sellerBox.show();
		}
	}
	
	@FXML
	private void sure() {
//		System.out.println("test");
		//打包VO
//		String supplier = supplierField.getText();
//		String seller = sellerField.getText();

		String supplier = supplierBox.getValue();
		String seller = sellerBox.getValue();
		ArrayList<PaymentItemVO> itemVOList = new ArrayList<PaymentItemVO>();
		ObservableList<Info> infoList = table.getItems();
		for(int i = 0; i < infoList.size();i++) {
			Info info = infoList.get(i);
			PaymentItemVO vo = new PaymentItemVO(info.getName(), info.getAmount(), info.getComment());
			itemVOList.add(vo);
		}
		double sum = Double.parseDouble(sumField.getText());
		PaymentOrderVO vo = new PaymentOrderVO(username, supplier, seller, itemVOList, sum);

//		try {
//			reciptBL.setPaymentOrder(vo);
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
				reciptBL.setPaymentOrder(vo);
				back();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		else if(buttonType.get().getText().equals("保存为草稿")) {
			//System.out.println("草稿");
			vo.setState("Draft");
			try {
				reciptBL.setPaymentOrder(vo);
				back();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}

	}
	
	@FXML
	private void cancel() {
		
		back();
	}
}
