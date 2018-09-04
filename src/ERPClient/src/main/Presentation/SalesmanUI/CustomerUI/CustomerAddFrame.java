package main.Presentation.SalesmanUI.CustomerUI;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.Presentation.MainUI.MainFrame;
import main.Presentation.MainUI.MainUIController;
import main.Presentation.SalesmanUI.CustomerManageFrame;
import main.RMI.RemoteHelper;
import main.VO.CustomerVO;
import main.utility.ResultMessage;

/**
 * 进货销售人员添加客户界面的控制类
 * @author 周正伟
 *
 */
public class CustomerAddFrame extends MainUIController{
	
	
	@FXML
	TextField id;
	
	@FXML
	TextField name;
	
	@FXML
	ComboBox<String> category;
	
	@FXML
	ComboBox<String> level;
	
	@FXML
	TextField number;
	
	@FXML
	TextField address;
	
	@FXML
	TextField email;
	
	@FXML
	TextField receive;
	
	@FXML
	TextField receivelimit;
	
	@FXML
	TextField payment;
	
	@FXML
	TextField defaultsalesman;

	@FXML
	TextField postcode;
	
	private Stage popupWindow = new Stage();
	
	static String customerid;
	
	static ArrayList<String> saved = new ArrayList<>();

	public void init() {
		
		AnchorPane popup = new AnchorPane();
		
		try {
			popup = FXMLLoader.load(CustomerAddFrame.class.getResource("CustomeraddFXML.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Scene popupscene = new Scene(popup);
		popupscene.setFill(null);
		popupWindow.setScene(popupscene);
		popupWindow.initStyle(StageStyle.UNDECORATED);
		popupWindow.initStyle(StageStyle.TRANSPARENT);
		popupWindow.show();

	}
	
	/**
	 * 初始化客户添加界面时设置部分textfield
	 */
	@FXML
	public void initialize(){
		
		receive.setText("0");
		receive.setEditable(false);
		payment.setText("0");
		payment.setEditable(false);
		
		try {
			customerid = RemoteHelper.getCustomerBLService().getNextCustomerID("进货商");
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		id.setText(customerid);
		id.setEditable(false);
		
		category.getSelectionModel().selectedIndexProperty().addListener(
				(ObservableValue<? extends Number> ov,
						Number oldval,Number newval)->{
							if (newval.intValue()==0) {
								try {
									customerid = RemoteHelper.getCustomerBLService().getNextCustomerID("进货商");
								} catch (RemoteException e) {
									e.printStackTrace();
								}
								id.setText(customerid);
							}else {
								try {
									customerid = RemoteHelper.getCustomerBLService().getNextCustomerID("销售商");
								} catch (RemoteException e) {
									e.printStackTrace();
								}
								id.setText(customerid);
							}
							
						});
		
		
		
		
		
	}
	

	/**
	 * 确认添加客户按钮的监听（和BL层交互）
	 */
	@FXML
	public void customeraddsure(ActionEvent event) {
		
     if (name.getText().equals("")||defaultsalesman.getText().equals("")||address.getText().equals("")||number.getText().equals("")||email.getText().equals("")||postcode.getText().equals("")||receivelimit.getText().equals("")) {
		//检验是否未输入完整防报错的if判断
	}else {
		  CustomerVO customerVO = new CustomerVO(id.getText(),
	    		   category.getSelectionModel().getSelectedItem(),
	    		   level.getSelectionModel().getSelectedItem(),
	    		   name.getText(),
	    		   number.getText(), 
	    		   address.getText(),
	    		   email.getText(), 
	    		   postcode.getText(),
	    		   Double.parseDouble(receive.getText()),
	    		   Double.parseDouble(payment.getText()),
	    		   Double.parseDouble(receivelimit.getText()), 
	    		   defaultsalesman.getText(),
	    		   "",//进货销售人员的ID
	    		   false,new ArrayList<>());
	       ResultMessage addtry = null;
	       try {
			 addtry = RemoteHelper.getCustomerBLService().customerAdd(customerVO);
	       } catch (RemoteException e) {
	    	   e.printStackTrace();
	       }
	       
	       Button temp = (Button)event.getSource();
	       temp.getScene().getWindow().hide();
	       MainFrame.setSceneRoot(CustomerManageFrame.init());
	}

	}
	
	/**
	 * 取消添加客户按钮的监听
	 */
	@FXML
	public void canceladd(ActionEvent event) {
	       Button temp = (Button)event.getSource();
	       temp.getScene().getWindow().hide();
	}
	
}
