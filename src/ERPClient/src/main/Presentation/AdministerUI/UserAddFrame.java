package main.Presentation.AdministerUI;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.Presentation.MainUI.MainFrame;
import main.Presentation.MainUI.MainUIController;
import main.RMI.RemoteHelper;
import main.VO.UserVO;
import main.utility.UserType;

/**
 * 系统管理人员添加用户界面的控制类
 * @author 周正伟
 *
 */
public class UserAddFrame extends MainUIController{
	
	@FXML
	TextField name;
	
	@FXML
	TextField jobnumhead;
	
	@FXML
	TextField jobnumtail;
	
	@FXML
	TextField password;
	
	@FXML
	TextField age;
	
	@FXML
	ComboBox<String> sex;
	
	@FXML
	ComboBox<String> type;
	
	@FXML
	RadioButton supre;
	
	@FXML
	Button surebutton;
	
	private Stage popupWindow = new Stage();
	
	/**
	 * 系统管理员主界面的初始化方法
	 */
	public void init(){
		
		AnchorPane popup = new AnchorPane();
		try {
			popup = FXMLLoader.load(UserAddFrame.class.getResource("UserAddFrameFXML.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
	 * 加载fxml时默认加载的方法，会对所有的商品列表进行初始化
	 */
	@FXML
	public void initialize(){
		type.getSelectionModel().selectedIndexProperty().addListener(
				(ObservableValue<? extends Number> ov,
						Number oldval,Number newval)->{
						if (type.getSelectionModel().getSelectedItem().equals("StockManager")) {
							jobnumhead.setText("stm");
						}
						if (type.getSelectionModel().getSelectedItem().equals("SalesMan")) {
							jobnumhead.setText("sam");
						}
						if (type.getSelectionModel().getSelectedItem().equals("FinancialStaff")) {
							jobnumhead.setText("fs");
						}
						if (type.getSelectionModel().getSelectedItem().equals("Manager")) {
							jobnumhead.setText("ma");
						}
						
						
		});
	}
	
	/**
	 * 确认添加用户的按钮的监听
	 */
	@FXML
	public void useraddsure(ActionEvent event){
		if (name.getText().equals("")||age.getText().equals("")||jobnumhead.equals("")||jobnumtail.equals("")||password.getText().equals("")||sex.getValue().equals("")||sex.getValue().equals("")) {
			System.out.println("信息未完整无法添加");
		}else {
			UserVO adddvo = new UserVO(name.getText(),
					sex.getValue(),
					Integer.parseInt(age.getText()),
					jobnumhead.getText()+jobnumtail.getText(),
					password.getText(),
					type.getValue(),
					supre.isSelected());
			UserType result = null;
			try {
				result = RemoteHelper.getLoginBLService().register(adddvo);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (result == UserType.ALREADY_EXIT) {
				System.out.println("无法添加");
			}else if (result == UserType.REGISTER_SUCCESS) {
				 Button temp = (Button)event.getSource();
			     temp.getScene().getWindow().hide();
				MainFrame.setSceneRoot(AdminHomeFrame.init());
			}
		}
	}
	
	/**
	 * 取消添加用户按钮的监听
	 */
	@FXML
	public void canceladd(ActionEvent event){
		 Button temp = (Button)event.getSource();
	     temp.getScene().getWindow().hide();
	}
}
