package main.Presentation.StockManagerUI.GoodsManageDetail;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javafx.beans.value.ChangeListener;
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
import main.Presentation.StockManagerUI.GoodsManageFrame;
import main.RMI.RemoteHelper;
import main.VO.CustomerVO;
import main.VO.GoodsVO;
import main.utility.ResultMessage;

/**
 * 库存管理人员商品管理界面的控制类
 * @author 周正伟
 *
 */
public class GoodsAddFrame extends MainUIController{
	
	@FXML
	TextField id;
	
	@FXML
	TextField name;
	
	@FXML
	TextField version;
	
	@FXML
	TextField amount;
	
	@FXML
	TextField bid;
	
	@FXML
	TextField retailprice;
	
	@FXML
	TextField recentbid;
	
	@FXML
	TextField recentretailprice;
	
	@FXML
	TextField catagory;
	
	static String rootcategory;
	
	private Stage popupWindow = new Stage();
	
	/**
	 * 库存管理人员商品添加界面的初始化方法
	 */
	public void init(String category) {
		
		rootcategory = category;
		AnchorPane popup = new AnchorPane();
		
		try {
			popup = FXMLLoader.load(GoodsAddFrame.class.getResource("GoodsAddFrameFXML.fxml"));
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
		
		String idd = null;
		try {
			idd = RemoteHelper.getGoodsBLService().getNextGoodsId(rootcategory);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		id.setText(idd);
		
		
		catagory.setText(rootcategory);
		
		
		bid.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            	recentbid.setText(bid.getText());
            }
        });
		retailprice.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            	recentretailprice.setText(retailprice.getText());
            }
        });
	}
	

	/**
	 * 确认添加商品按钮的监听
	 */
	@FXML
	protected void goodsaddsure(ActionEvent event) {
		if (name.getText().equals("")||version.getText().equals("")||bid.getText().equals("")||retailprice.getText().equals("")||recentbid.getText().equals("")||recentretailprice.getText().equals("")) {
			
		}else {
		GoodsVO addedgoodvo;
		addedgoodvo = new GoodsVO(
				name.getText(),
				id.getText(),
				version.getText(),
				Long.parseLong(amount.getText()),
				Double.parseDouble(bid.getText()),
				Double.parseDouble(retailprice.getText()),
				Double.parseDouble(recentbid.getText()),
				Double.parseDouble(recentretailprice.getText()),
				rootcategory, 
				Double.parseDouble(bid.getText()), 
				username);
		
		ResultMessage resultMessage;
		try {
				resultMessage = RemoteHelper.getGoodsBLService().goodsAdd(addedgoodvo);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		
		 Button temp = (Button)event.getSource();
	     temp.getScene().getWindow().hide();
	     MainFrame.setSceneRoot(GoodsManageFrame.init());
		}
	}
	
	/**
	 * 取消添加按钮的监听
	 */
	@FXML
	private void canceladd(ActionEvent event) {
	       Button temp = (Button)event.getSource();
	       temp.getScene().getWindow().hide();
	}
}
