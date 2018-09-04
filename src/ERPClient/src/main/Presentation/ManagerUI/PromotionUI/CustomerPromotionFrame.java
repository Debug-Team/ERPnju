package main.Presentation.ManagerUI.PromotionUI;

import java.io.IOException;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javafx.application.Platform;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import main.BussinessLogicService.PromotionBLService.PromotionBLService;
import main.Presentation.MainUI.MainFrame;
import main.Presentation.MainUI.MainUIController;
import main.Presentation.ManagerUI.ManagerHomeFrame;
import main.Presentation.ManagerUI.PromotionFrame;
import main.RMI.RemoteHelper;
import main.VO.CouponVO;
import main.VO.GoodsVO;
import main.VO.LevelPromotionVO;
import main.VO.PromotionVO;
import main.VO.ReciptGoodsVO;
import main.utility.ResultMessage;

public class CustomerPromotionFrame extends MainUIController{
	@FXML
	private ImageView head;
	
	@FXML
	DatePicker startDatePicker;
	@FXML
	DatePicker endDatePicker;

	@FXML
	ComboBox<String> customerLevelBox;
	@FXML
	TextField rebateField;
	
	@FXML
	ComboBox<String> searchConditionBox;
	@FXML
	ComboBox<String> searchBox;
	
	@FXML
	TableView<GiftInfo> giftTable;
	@FXML
	TableColumn<GiftInfo, String> goodsIDColumn;
	@FXML
	TableColumn<GiftInfo, String> goodsNameColumn;
	@FXML
	TableColumn<GiftInfo, Integer> goodsNumColumn;
	
	@FXML
	TableView<CouponInfo> couponTable;
	@FXML
	TableColumn<CouponInfo, Double> couponAmountColumn;
	@FXML
	TableColumn<CouponInfo, Integer> couponIndateColumn;
	@FXML
	TableColumn<CouponInfo, Integer> couponNumColumn;
	
	private PromotionBLService promotionBL;
	private boolean isModify = false;
	private ArrayList<GoodsVO> goodsVOList;
	
	/**
	 * 加载制定用户促销策略单据界面
	 * @return 加载好的界面
	 */
	public static Parent init() {
		try {
			GridPane customerPromotionFrame = FXMLLoader.load(CustomerPromotionFrame.class.getResource("CustomerPromotionFrameFXML.fxml"));
			return customerPromotionFrame;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new GridPane();
	}

	/**
	 * 初始化组件
	 */
	public void initialize() {
		promotionBL = RemoteHelper.getPromotionBLService();
		
		startDatePicker.setValue(LocalDate.now());
		endDatePicker.setValue(LocalDate.now());
		
		customerLevelBox.setItems(FXCollections.observableArrayList("一星级", "二星级", "三星级", "四星级", "五星级"));
		customerLevelBox.getSelectionModel().select(0);
		
		searchConditionBox.setItems(FXCollections.observableArrayList("ID", "商品名称"));
		searchConditionBox.valueProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				// TODO Auto-generated method stub
				isModify = true;
				searchBox.getItems().clear();
				isModify = false;
				searchAndAdd();
			}
			
		});
		
		goodsIDColumn.setCellValueFactory(new PropertyValueFactory<GiftInfo, String>("id"));
//		goodsIDColumn.setCellFactory(TextFieldTableCell.forTableColumn());
//		goodsIDColumn.setOnEditCommit((CellEditEvent<GiftInfo, String> t) -> {
//			t.getTableView().getItems().get(t.getTablePosition().getRow()).setId(t.getNewValue());
//		});
		goodsNameColumn.setCellValueFactory(new PropertyValueFactory<GiftInfo, String>("name"));
//		goodsNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
//		goodsNameColumn.setOnEditCommit((CellEditEvent<GiftInfo, String> t) -> {
//			t.getTableView().getItems().get(t.getTablePosition().getRow()).setName(t.getNewValue());
//		});
		goodsNumColumn.setCellValueFactory(new PropertyValueFactory<GiftInfo, Integer>("num"));
		goodsNumColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		goodsNumColumn.setOnEditCommit((CellEditEvent<GiftInfo, Integer> t) -> {
			t.getTableView().getItems().get(t.getTablePosition().getRow()).setNum(t.getNewValue());
		});
		giftTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		couponAmountColumn.setCellValueFactory(new PropertyValueFactory<CouponInfo, Double>("amount"));
		couponAmountColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
		couponAmountColumn.setOnEditCommit((CellEditEvent<CouponInfo, Double> t) -> {
			t.getTableView().getItems().get(t.getTablePosition().getRow()).setAmount(t.getNewValue());
		});
		couponIndateColumn.setCellValueFactory(new PropertyValueFactory<CouponInfo, Integer>("indate"));
		couponIndateColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		couponIndateColumn.setOnEditCommit((CellEditEvent<CouponInfo, Integer> t) -> {
			t.getTableView().getItems().get(t.getTablePosition().getRow()).setIndate(t.getNewValue());
		});
		couponNumColumn.setCellValueFactory(new PropertyValueFactory<CouponInfo, Integer>("num"));
		couponNumColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		couponNumColumn.setOnEditCommit((CellEditEvent<CouponInfo, Integer> t) -> {
			t.getTableView().getItems().get(t.getTablePosition().getRow()).setNum(t.getNewValue());
		});
		couponTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
	}
		
	/**
	 * 搜索框的监听
	 */
	@FXML
	private void searchAndAdd() {
		if(isModify) {
			return;
		}
		if(searchBox.getSelectionModel().isEmpty()) {		//是搜索
			isModify = true;
			searchBox.getItems().clear();
			isModify = false;

			String value = searchBox.getValue();
			if(value == null) {
				value = "";
			}
			switch(searchConditionBox.getValue()) {
			case "ID":{
				try {
					goodsVOList = promotionBL.getGoods("ID", value);
					for(int i = 0; i < goodsVOList.size();i++) {
						GoodsVO vo = goodsVOList.get(i);
						searchBox.getItems().add(vo.getID());
					}
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
			case "商品名称":{
				try {
					goodsVOList = promotionBL.getGoods("name", value);
					for(int i = 0; i < goodsVOList.size();i++) {
						GoodsVO vo = goodsVOList.get(i);
						searchBox.getItems().add(vo.getName());
					}
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
			}
			searchBox.show();
		}
		else {			//是选择添加
			int index = searchBox.getSelectionModel().getSelectedIndex();
			GoodsVO vo = goodsVOList.get(index);
			GiftInfo info = new GiftInfo(vo.getID(), vo.getName());
			giftTable.getItems().add(info);
			
			isModify = true;
			Platform.runLater(()->{
				searchBox.getItems().clear();
				searchBox.getSelectionModel().clearSelection();
				isModify = false;
			});
			
			searchBox.hide();
		}
	}
	
	/**
	 * 确认按钮的监听
	 */
	@FXML
	private void sure() {
		//打包VO
		String startTime = startDatePicker.getValue().format(DateTimeFormatter.BASIC_ISO_DATE);
		String endTime = endDatePicker.getValue().format(DateTimeFormatter.BASIC_ISO_DATE);
		String level = customerLevelBox.getSelectionModel().getSelectedItem();
		double discount = Double.parseDouble(rebateField.getText())/100;
		
        ArrayList<GoodsVO> giftList = new ArrayList<GoodsVO>();
        ObservableList<GiftInfo> giftInfoList = giftTable.getItems();
        for(int i = 0;i < giftInfoList.size();i++) {
            GiftInfo info = giftInfoList.get(i);
            giftList.add(new GoodsVO(info.getName(), info.getId(), "", info.getNum(), 0, 0, 0, 0, "", 0, ""));
        }
		
		ArrayList<CouponVO> couponList = new ArrayList<CouponVO>();
		ObservableList<CouponInfo> couponInfoList = couponTable.getItems();
		for(int i = 0;i < couponInfoList.size();i++) {
			CouponInfo info = couponInfoList.get(i);
			for(int j = 0;j < info.getNum();j++) {
				couponList.add(new CouponVO(startTime, endTime, info.getAmount()));
			}
		}
		
		LevelPromotionVO vo = new LevelPromotionVO(startTime, endTime, level, discount, giftList, couponList);
		
		//调用方法
		try {
			ResultMessage result = promotionBL.setPromotion(vo, username);
			System.out.println(result);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		back();
	}

	/**
	 * 取消按钮的监听
	 */
	@FXML
	private void cancel() {
		back();
	}

	/**
	 * 删除代金券列表行按钮的监听
	 */
	@FXML
	private void deleteCouponRow() {
		couponTable.getItems().removeAll(couponTable.getSelectionModel().getSelectedItems());
	}

	/**
	 * 新增代金券列表行按钮的监听
	 */
	@FXML
	private void addCouponRow() {
		couponTable.getItems().add(new CouponInfo());
	}

	/**
	 * 删除赠品列表行按钮的监听
	 */
	@FXML
	private void deleteGiftRow() {
		giftTable.getItems().removeAll(giftTable.getSelectionModel().getSelectedItems());
	}

	/**
	 * 主页按钮的监听
	 */
	@FXML
	private void home() {
		Parent home = ManagerHomeFrame.init();
		MainFrame.setSceneRoot(home);
	}
	
	/**
	 * 返回按钮的监听
	 */
	@FXML
	private void back() {
		Parent back = PromotionFrame.init();
		MainFrame.setSceneRoot(back);
	}

	/**
	 * 用于赠品列表giftTable数据绑定的类
	 * @author yyr
	 *
	 */
	public class GiftInfo {
		private SimpleStringProperty id;
		private SimpleStringProperty name;
		private SimpleIntegerProperty num;
		
		public GiftInfo(String id, String name) {
			this.id = new SimpleStringProperty(id);
			this.name = new SimpleStringProperty(name);
			this.num = new SimpleIntegerProperty(1);
		}
		
		public void setId(String id) {
			this.id.set(id);
		}
		public String getId() {
			return id.get();
		}
		
		public void setName(String name) {
			this.name.set(name);
		}
		public String getName() {
			return name.get();
		}
		
		public void setNum(int num) {
			this.num.set(num);
		}
		public int getNum() {
			return num.get();
		}
	}
	
	/**
	 * 用于代金券列表giftTable数据绑定的类
	 * @author yyr
	 *
	 */
	public class CouponInfo {
		private SimpleDoubleProperty amount;
		private SimpleIntegerProperty indate;
		private SimpleIntegerProperty num;
		
		public CouponInfo() {
			this.amount = new SimpleDoubleProperty(0);
			this.indate = new SimpleIntegerProperty(1);
			this.num = new SimpleIntegerProperty(1);
		}
		
		public void setAmount(double amount) {
			this.amount.set(amount);
		}
		public double getAmount() {
			return amount.get();
		}
		
		public void setIndate(int indate) {
			this.indate.set(indate);
		}
		public int getIndate() {
			return indate.get();
		}
		
		public void setNum(int num) {
			this.num.set(num);
		}
		public int getNum() {
			return num.get();
		}
	}

}
