package main.Presentation.AdministerUI;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.GridPane;
import main.Presentation.MainUI.LoginUI;
import main.Presentation.MainUI.MainFrame;
import main.Presentation.MainUI.MainUIController;
import main.RMI.RemoteHelper;
import main.VO.UserVO;

/**
 * 系统管理员管理界面的控制类
 * @author 周正伟
 *
 */
public class AdminHomeFrame extends MainUIController {
		
	@FXML
	TableView<Users> table;
	
	@FXML
	TableColumn<Users, CheckBox> isSupremeAuthorityCol;
	
	@FXML
	TableColumn<Users, String> idCol;
	
	@FXML
	TableColumn<Users, String> nameCol;
	
	@FXML
	TableColumn<Users, String> sexCol;
	
	@FXML
	TableColumn<Users, String> ageCol;
	
	@FXML
	TableColumn<Users, String> jobmunCol;
	
	@FXML
	TableColumn<Users, String> passwordCol;
	
	@FXML
	TableColumn<Users, String> typeCol;

	ArrayList<UserVO> allusers = null;
	
	ObservableList<Users> allusersdata;
	
	/**
	 * 系统管理人员界面的初始化方法
	 */
	public static Parent init(){
		try {
			GridPane homeFrame = FXMLLoader.load(AdminHomeFrame.class.getResource("AdminHomeFrameFXML.fxml"));
			return homeFrame;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new GridPane();
	}
	
	/**
	 * 加载fxml时默认加载的方法，会对所有的商品列表进行初始化
	 */
	@FXML
	public void initialize(){
//		add
//		CheckBox aBox  = new CheckBox();
//		aBox.setSelected(true);
//		a.getChildren().add(new checkbox(true));
//		System.out.println("222");
		loadallusers();
		
		
	}
	
	//自动加载使用系统的用户列表
	public void loadallusers(){
		allusersdata = FXCollections.observableArrayList();
		
        idCol.setCellValueFactory(new PropertyValueFactory<>("ID"));//映射          
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name")); 
        sexCol.setCellValueFactory(new PropertyValueFactory<>("sex"));
		ageCol.setCellValueFactory(new PropertyValueFactory<>("age"));
		jobmunCol.setCellValueFactory(new PropertyValueFactory<>("jobNum"));
		passwordCol.setCellValueFactory(new PropertyValueFactory<>("password"));
		typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
		isSupremeAuthorityCol.setCellValueFactory(new PropertyValueFactory<Users,CheckBox>("isSupremeAuthority"));
		
//		System.out.println("1");
		
		try {
			allusers = RemoteHelper.getLoginBLService().getUserList();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.out.println("2");
		
		for (int i = 0; i < allusers.size(); i++) {
			allusersdata.add(new Users(
					allusers.get(i).getId()+"",
					allusers.get(i).getName(),
					allusers.get(i).getSex(),
					allusers.get(i).getAge()+"",
					allusers.get(i).getJobNum(),
					allusers.get(i).getPassword(),
					allusers.get(i).getType(),
					new CheckBox("")));
		}
		for (int i = 0; i < allusers.size(); i++) {
			allusersdata.get(i).setsupreme(allusers.get(i).getIsSupremeAuthority());
		}
		for (int i = 0; i < allusersdata.size(); i++) {
			
			String name = allusersdata.get(i).getName();
			allusersdata.get(i).getIsSupremeAuthority().selectedProperty().addListener(
			        (ObservableValue<? extends Boolean> ov,
			                Boolean old_val, Boolean new_val) -> {
			                    for (int j = 0; j < allusers.size(); j++) {
									if (allusers.get(j).getName().equals(name)) {
										UserVO modified = allusers.get(j);
										modified.setIsSupremeAuthority(new_val);
										Boolean result = null;
										try {
											result = RemoteHelper.getLoginBLService().modifyUser(modified);
										} catch (RemoteException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
										if (result == true) {
											System.out.println("修改成功");
										}
										break;
									}
								}             
			        });
		}
		table.setEditable(true);
		passwordCol.setCellFactory(TextFieldTableCell.<Users>forTableColumn());
		passwordCol.setOnEditCommit(
			    (CellEditEvent<Users, String> t) -> {
			        ((Users) t.getTableView().getItems().get(
			            t.getTablePosition().getRow())
			            ).setPassword(t.getNewValue());
			        UserVO modified = null;
			        for (int i = 0; i < allusers.size(); i++) {
						if (allusers.get(i).getName().equals(table.getSelectionModel().getSelectedItem().getName())) {
							modified = allusers.get(i);
							break;
						}
					}
			        modified.setPassword(t.getNewValue());
			        boolean result = false;
			        try {
						result = RemoteHelper.getLoginBLService().modifyUser(modified);
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			        if (result==true) {
						System.out.println("修改成功");
					}
			});
		
		table.setItems(allusersdata);
		table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

	}
	 

	/**
	 * 切换用户按钮的监听
	 */
	@FXML
	public void changeusers(){
		MainFrame.setSceneRoot(LoginUI.init());
	}
	
	/**
	 * 添加用户按钮的监听
	 */
	@FXML
	public void adduser(){
		UserAddFrame userAddFrame = new UserAddFrame();
		userAddFrame.init();
	}
	
	/**
	 * 删除用户按钮的监听
	 */
	@FXML
	public void deleteusers(){
		ObservableList<Users> selected = null;
		ArrayList<UserVO> deletedvos = new ArrayList<>();
		selected = table.getSelectionModel().getSelectedItems();
		for (int i = 0; i < selected.size(); i++) {
			for (int j = 0; j < allusers.size(); j++) {
				if (selected.get(i).getJobNum().equals(allusers.get(j).getJobNum())) {
					deletedvos.add(allusers.get(j));
					break;
				}
			}
		}
		for (int i = 0; i < deletedvos.size(); i++) {
			Boolean result = null;
			try {
				result = RemoteHelper.getLoginBLService().deleteUser(deletedvos.get(i));
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (result==true) {
				System.out.println("删除成功");
				MainFrame.setSceneRoot(AdminHomeFrame.init());
			}
		}
		System.out.println("无选中");
	}
	
	

	/**
	 * 用于系统用户tableview绑定的数据类
	 * @author 周正伟
	 */
	public	class Users{
		
		String ID;
		String name;
		String sex;
		String age;
		String jobNum;
		String password;
		String type;
		CheckBox isSupremeAuthority;
		
		public Users(String iD, String name, String sex, String age, String jobNum, String password, String type,
				CheckBox isSupremeAuthority) {
			super();
			ID = iD;
			this.name = name;
			this.sex = sex;
			this.age = age;
			this.jobNum = jobNum;
			this.password = password;
			this.type = type;
			this.isSupremeAuthority = isSupremeAuthority;
		}

		public String getID() {
			return ID;
		}

		public void setID(String iD) {
			ID = iD;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getSex() {
			return sex;
		}

		public void setSex(String sex) {
			this.sex = sex;
		}

		public String getAge() {
			return age;
		}

		public void setAge(String age) {
			this.age = age;
		}

		public String getJobNum() {
			return jobNum;
		}

		public void setJobNum(String jobNum) {
			this.jobNum = jobNum;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public CheckBox getIsSupremeAuthority() {
			return isSupremeAuthority;
		}

		public void setIsSupremeAuthority(CheckBox isSupremeAuthority) {
			this.isSupremeAuthority = isSupremeAuthority;
		}
		
		public void setsupreme(Boolean s){
			this.isSupremeAuthority.setSelected(s);
		}
		
		
	}
	
	
}
