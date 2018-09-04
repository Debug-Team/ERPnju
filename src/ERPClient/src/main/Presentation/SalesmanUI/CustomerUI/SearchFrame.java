package main.Presentation.SalesmanUI.CustomerUI;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import org.w3c.dom.NamedNodeMap;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.Presentation.MainUI.MainFrame;
import main.Presentation.MainUI.MainUIController;
import main.Presentation.SalesmanUI.CustomerManageFrame;
import main.RMI.RemoteHelper;
import main.VO.CustomerVO;

/**
 * 进货销售人员搜索界面的控制类
 * @author 周正伟
 *
 */
public class SearchFrame extends MainUIController{
	
	@FXML
	ComboBox<String> searchtype;
	
	@FXML
	TextField keyword;
	
	static String key;
	
	static String keytype;
	
	Stage popupWindow = new Stage();
	
	/**
	 * 进货销售人员搜索界面初始化
	 */
	public void init() {
		AnchorPane searchpopup = new AnchorPane();
		
		try {
			searchpopup = FXMLLoader.load(SearchFrame.class.getResource("SearchFXML.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scene popupscene = new Scene(searchpopup);
		popupscene.setFill(null);
		popupWindow.setScene(popupscene);
		popupWindow.initStyle(StageStyle.UNDECORATED);
		popupWindow.initStyle(StageStyle.TRANSPARENT);
		popupWindow.show();
		
	}
	

	/**
	 * 客户管理查找搜索取消按钮的监听
	 */
	@FXML
	protected void searchcancel(ActionEvent event) {
		Button temp = (Button)event.getSource();
		temp.getScene().getWindow().hide();
	}
	
	/**
	 * 客户管理查找搜索按钮的监听
	 */
	@FXML
	protected  void customerfindsure(ActionEvent event) {
		if (keyword.getText().equals("")) {
			
		}else {
			Button temp = (Button)event.getSource();
			temp.getScene().getWindow().hide();
			 key = keyword.getText();
			 keytype = searchtype.getSelectionModel().getSelectedItem();
			System.out.println(key);
			Parent searchresult = SearchResultFrame.init(key,keytype);
	        MainFrame.setSceneRoot(searchresult);
		}
    }
	
	
}
