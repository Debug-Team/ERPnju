package main.Presentation.StockManagerUI;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import main.Presentation.MainUI.MainFrame;
import main.Presentation.MainUI.MainUIController;
import main.RMI.RemoteHelper;
import main.VO.CategoryVO;
import main.VO.GoodsVO;
import main.utility.ResultMessage;

/**
 * 库存管理人员商品管理界面的控制类
 * @author 周正伟
 *
 */
public class CategoryManageFrame extends MainUIController{
	
	@FXML
	private ImageView head;
	
	@FXML
	TreeView<String> treeview;
	
	@FXML
	TextField categoryid;
	
	@FXML
	TextField detailcategory;
	
	@FXML
	TextField hasitem;
	
	@FXML
	TextField addtextfield;
	
	@FXML
	TextField modifytextfield;
	
	@FXML
	Label addmessagelabel;
	
	@FXML
	Label modifymessagelabel;
	
	@FXML 
	Label isdeletedmessage;
	
	@FXML
	AnchorPane addmessageanchor;
	
	@FXML
	AnchorPane deletemessageanchor;
	
	@FXML
	AnchorPane modifymessageanchor;
	
	ArrayList<Goodscategory> allcategorydata = new ArrayList<>();
	
	ArrayList<TreeItem<String>> categorytreeitems = new ArrayList<>();
	
	ArrayList<CategoryVO> allcategory = null;
	
	ArrayList<String> readycheckleafcategory = new ArrayList<>();
		
//	CategoryVO voget = null;
	
	

	/**
	 * 进货销售人员主界面的初始化方法
	 * @return 返回加载好的界面
	 */
	public static Parent init(){
		
		try {
			GridPane categorymanage = FXMLLoader.load(CategoryManageFrame.class.getResource("CategoryManageFrameFXML.fxml"));
			return categorymanage;
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

			try {
				allcategory = RemoteHelper.getGoodsCatagoryBLService().categoryAll();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

		for (int i = 0; i < allcategory.size(); i++) {
			allcategorydata.add(new Goodscategory(allcategory.get(i)));

		}
		
		
		System.out.println(allcategorydata.size());
		for (int i = 0; i < allcategorydata.size(); i++) {

			categorytreeitems.add(new TreeItem<String>(allcategorydata.get(i).getMyvalue()));

			categorytreeitems.get(i).setExpanded(true);

		}
		
		for (int i = 0; i < allcategorydata.size(); i++) {
			if (allcategorydata.get(i).getFatherStr().equals("Nofather")) {
				treeview.setRoot(categorytreeitems.get(i));
			}
		}

		
		
		for (int i = 0; i < allcategorydata.size(); i++) {
			ArrayList<Goodscategory> temp = allcategorydata.get(i).getSonscategory();
			for (int j = 0; j < allcategorydata.size(); j++) {
					for (int j2 = 0; j2 < temp.size(); j2++) {
						if (allcategorydata.get(j).getMyvalue().equals(temp.get(j2).getMyvalue())) {
//							System.out.println(temp.get(j2).equals(allcategorydata.get(j)));
//							System.out.println(allcategorydata.get(j).getMyvalue()+" "+allcategorydata.get(j).getEpanded());
//							System.out.println(temp.get(j2).getMyvalue()+" "+temp.get(j2).getEpanded());
							categorytreeitems.get(i).getChildren().add(categorytreeitems.get(j));

						}
					}			

			}
			temp = null;
		}	

		//分类管理选定某分类后 的监听
		  treeview.getSelectionModel().selectedItemProperty().addListener(
                  new ChangeListener<TreeItem <String>>() {
                  @Override
                  public void changed(ObservableValue<? extends TreeItem<String>> observableValue,
                  TreeItem<String> oldItem, TreeItem<String> newItem) {
                	  String detail = "";
                	  String categoryidd = "";
//                	  boolean hasit = false;
                          for (int i = 0; i < allcategory.size(); i++) {
      						if (allcategory.get(i).getMyvalue().equals(newItem.getValue())) {
      							categoryidd = allcategory.get(i).getCategoryID();
//      							hasit = allcategory.get(i).getHasGoods();
      	                	  if (!newItem.getValue().equals(treeview.getRoot().getValue())) {
      							CategoryVO vos = null;
      							vos = allcategory.get(i);
      							detail = vos.getMyvalue();
      							
      							while (!vos.getFatherStr().equals("Nofather")) {
      								detail = vos.getFatherStr()+"-"+ detail;
      								for (int j = 0; j < allcategory.size(); j++) {
      									if (allcategory.get(j).getMyvalue().equals(vos.getFatherStr())) {
      										vos = allcategory.get(j);

      										break;
      									}
      								}
      							}
//      							detail = treeview.getRoot().getValue()+"-"+detail;
      	                	  }else {
								detail = treeview.getRoot().getValue();
							}
      							break;
      						}
      					}
                	detailcategory.setText(detail);
                	categoryid.setText(categoryidd);
//                	if (hasit==true) {
//						hasitem.setText("含商品");
//					}else {
//						hasitem.setText("不含商品");
//					}
                	Goodscategory seclectcategory = null;
                	ArrayList<GoodsVO> check = null;
                	for (int i = 0; i < allcategorydata.size(); i++) {
						if (allcategorydata.get(i).getMyvalue().equals(newItem.getValue())) {
							seclectcategory = allcategorydata.get(i);
						}
					}
                	if (!seclectcategory.getSonscategory().isEmpty()) {
						hasitem.setText("不含商品");
					}else {
						try {
							check = RemoteHelper.getGoodsBLService().goodsFind(seclectcategory.getMyvalue(), "catagory");
						} catch (RemoteException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if (check.isEmpty()) {
							hasitem.setText("不含商品");
						}else {
							hasitem.setText("含商品");
						}
					}
                	
                  }
              });
	}
	
	
	/**
	 * 增加分类
	 */
	@FXML
	protected void addcategory(ActionEvent event) {
		if (treeview.getSelectionModel().getSelectedItem()!=null) {
		boolean canbeadd;
		Goodscategory selectedgoodscategory = null;
		ArrayList<GoodsVO> sonsgoods = null;
		for (int i = 0; i < allcategorydata.size(); i++) {
			if (treeview.getSelectionModel().getSelectedItem().getValue().equals(allcategorydata.get(i).getMyvalue())) {
				selectedgoodscategory = allcategorydata.get(i);
				break;
			}
		}
		try {
			sonsgoods = RemoteHelper.getGoodsBLService().goodsFind(selectedgoodscategory.getMyvalue(), "catagory");
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (sonsgoods.isEmpty()) {
			canbeadd = true;
		}else {
			canbeadd = false;
		}
		
		CategoryVO addvo = null;
			if (addtextfield.getText().equals("")) {
				addmessageanchor.setVisible(true);
				addmessagelabel.setText("!请输入待添加分类的名称");
			}else if(canbeadd==false){
				addmessageanchor.setVisible(true);
				addmessagelabel.setText("!该分类下含有商品，无法新增分类");
			}else {			
				addvo = new CategoryVO(new ArrayList<>(), addtextfield.getText(), false, treeview.getSelectionModel().getSelectedItem().getValue(), "", "库存管理人员",false);
				ResultMessage resultMessage = null;
				try {
					resultMessage =RemoteHelper.getGoodsCatagoryBLService().catagoryAdd(addvo);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (resultMessage.equals(ResultMessage.SUCCESS)) {
					addmessageanchor.setVisible(true);
					addmessagelabel.setText("添加成功");
				}
			}
		
		}else {
			addmessageanchor.setVisible(true);
			addmessagelabel.setText("!请选择根分类");
		}
		
    }
	
	
	void find(Goodscategory a){  //得到选择结点的所有叶节点的name
		if (a.getSonscategory().isEmpty()) {
			readycheckleafcategory.add(a.getMyvalue());
		}else {
			for (int i = 0; i < a.getSonscategory().size(); i++) {
				find(a.getSonscategory().get(i));
			}
		}
	}
	
	/**
	 * 删除分类
	 */
	@FXML
	protected void deletecategory(ActionEvent event) {
	   if (treeview.getSelectionModel().getSelectedItem()!=null) {
	   boolean canbedelete = true;
       Goodscategory seclectitem = null;
	   ArrayList<GoodsVO> goodscheck = null;
	   for (int i = 0; i < allcategorydata.size(); i++) {
				if (allcategorydata.get(i).getMyvalue().equals(treeview.getSelectionModel().getSelectedItem().getValue())) {
					seclectitem = allcategorydata.get(i);
				}
			}
	   find(seclectitem);	   	
	   for (int j = 0; j < readycheckleafcategory.size(); j++) {
	   		
				try {
					goodscheck = RemoteHelper.getGoodsBLService().goodsFind(readycheckleafcategory.get(j), "catagory");
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (!goodscheck.isEmpty()) {
					canbedelete = false;
					break;
				}
				
			}
	   
	   
       CategoryVO deletevo = null;
       if (canbedelete == false) {
    	   deletemessageanchor.setVisible(true);
    	   isdeletedmessage.setText("!该分类或其子分类下含有商品无法删除该分类");
    	   readycheckleafcategory.clear();
       }else {
    	   for (int i = 0; i < allcategory.size(); i++) {
    			if (allcategory.get(i).getMyvalue().equals(treeview.getSelectionModel().getSelectedItem().getValue())) {
    				deletevo = allcategory.get(i);
    			}
    	       }
    	   ResultMessage resultMessage = null;
           try {
        	   resultMessage = RemoteHelper.getGoodsCatagoryBLService().catagoryDelete(deletevo);
           } catch (RemoteException e) {
        	   // TODO Auto-generated catch block
        	   e.printStackTrace();
           }
           if (resultMessage.equals(ResultMessage.SUCCESS)) {
        	   deletemessageanchor.setVisible(true);
        	   isdeletedmessage.setText("删除成功");
           }
//           updateExpanded();
       }
	   }else {
		   deletemessageanchor.setVisible(true);
    	   isdeletedmessage.setText("!请选择预删除的分类");
	}
    }
	
	/**
	 * 修改分类
	 */
	@FXML
	protected void modifycategory(ActionEvent event) {
		CategoryVO modifyvo = null;
		if (treeview.getSelectionModel().getSelectedItem()!=null) {
			for (int i = 0; i < allcategorydata.size(); i++) {
				if (allcategorydata.get(i).getMyvalue().equals(treeview.getSelectionModel().getSelectedItem().getValue())) {
					modifyvo = allcategory.get(i);
				}
			}
			if (modifytextfield.getText().equals("")) {
				modifymessageanchor.setVisible(true);
				modifymessagelabel.setText("!请输入修改名称");
			}else {
				
				modifyvo.setMyvalue(modifytextfield.getText());
				ResultMessage resultMessage = null;
				try {
					resultMessage = RemoteHelper.getGoodsCatagoryBLService().catagoryModify(modifyvo);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					
				if (resultMessage.equals(ResultMessage.SUCCESS)) {
					modifymessageanchor.setVisible(true);
					modifymessagelabel.setText("修改成功");
				}
//				updateExpanded();
			}
			
		}else {
			modifymessageanchor.setVisible(true);
			modifymessagelabel.setText("!请选择预修改的分类");
		}

    }
	
	/**
	 * 返回库存管理人员主界面按钮的监听
	 */
	@FXML
	protected void returnStockManagerHomeHandle(ActionEvent event) {
        MainFrame.setSceneRoot(StockManagerHomeFrame.init());
    }
	
	/**
	 * 添加进程信息提示
	 */
	@FXML
	public void addmessagesure(){
		addmessageanchor.setVisible(false);
		if(addmessagelabel.getText().equals("添加成功")){
			MainFrame.setSceneRoot(CategoryManageFrame.init());
		}
	}
	
	/**
	 * 删除进程信息提示
	 */
	@FXML
	public void deletemessagesure(){
		deletemessageanchor.setVisible(false);
        if (isdeletedmessage.getText().equals("删除成功")) {
        	MainFrame.setSceneRoot(CategoryManageFrame.init());
		}
	}
	
	/**
	 * 修改进程信息提示
	 */
	@FXML
	public void modifymessagesure(){
		modifymessageanchor.setVisible(false);
		if (modifymessagelabel.getText().equals("修改成功")) {
			MainFrame.setSceneRoot(CategoryManageFrame.init());
		}
	}
	
	/**
	 * 用于分类treeview绑定的数据类
	 * @author 周正伟
	 */
	class Goodscategory {

			private ArrayList<Goodscategory> sonscategory = new ArrayList<>();
			private String myvalue;
//			private Boolean epanded;
			private String fatherStr;
			private Boolean hasitems;
			
			
			public Goodscategory(CategoryVO vo) {
				setMyvalue(vo.getMyvalue());
//				setEpanded(vo.getEpanded());
				setFatherStr(vo.getFatherStr());
				setHasitems(vo.getHasGoods());
				ArrayList<CategoryVO> list = vo.getSonscategory();
				ArrayList<Goodscategory> listchange = new ArrayList<>();

					for (int i = 0; i < list.size(); i++) {
						CategoryVO voson= list.get(i);
						Goodscategory changeson = new Goodscategory(voson);
						listchange.add(changeson);
					}
				setSonscategory(listchange);
			}
			
			public String getMyvalue() {
				return myvalue;
			}
			public void setMyvalue(String myvalue) {
				this.myvalue = myvalue;
			}
			public ArrayList<Goodscategory> getSonscategory() {
				return sonscategory;
			}
			public void setSonscategory(ArrayList<Goodscategory> sonscategory) {
				this.sonscategory = sonscategory;
			}
			
			public void addsons(Goodscategory s){
				this.sonscategory.add(s);
			}

			public String getFatherStr() {
				return fatherStr;
			}

			public void setFatherStr(String fatherStr) {
				this.fatherStr = fatherStr;
			}

			public Boolean getHasitems() {
				return hasitems;
			}

			public void setHasitems(Boolean hasitems) {
				this.hasitems = hasitems;
			}
		    	
		
	}
	
	
}
