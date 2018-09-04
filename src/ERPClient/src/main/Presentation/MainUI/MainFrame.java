package main.Presentation.MainUI;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.RMI.RemoteHelper;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * 程序的入口，提供了对界面的一些基本操作，关闭，最小化，跳转等
 * @author 杨袁瑞、周正伟
 * @version 1.0
 * @date 2017/11/27
 */
public class MainFrame extends Application {
	public static final double WIDTH = 1280;
	public static final double HEIGHT = 720;
	
	private static Scene scene;
	private static Stage stage;
	
	@Override
	public void start(Stage primaryStage) {
		RemoteHelper.initRMI();

		stage = primaryStage;
		
		stage.initStyle(StageStyle.UNDECORATED);//设定窗口无边框
		stage.setResizable(false);
		stage.setTitle("ERPnju");

		Parent loginUI = LoginUI.init();
		scene = new Scene(loginUI, WIDTH, HEIGHT);
		stage.setScene(scene);
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	/**
	 * 设置Scene的root
	 * @param 要被设置为root的组件
	 */
	public static void setSceneRoot(Parent s){
		scene.setRoot(s);
	}
	
	/**
	 * 窗口最小化的方法，使整个窗口最小化
	 */
	public static void minimize(){
		stage.setIconified(true);
	}
}
