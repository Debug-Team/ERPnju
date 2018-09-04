package main.PO;

import java.util.ArrayList;
import java.util.List;

import main.VO.CategoryVO;

public class CategoryPO {
	private ArrayList<CategoryPO> sonscategory = new ArrayList<>();
	private String myvalue;
	private Boolean epanded;
	private String fatherStr;
	private String categoryID;
	private boolean hasGoods;

	private String staffID;
	
	public CategoryPO(CategoryVO vo) {
		if(vo.getSonscategory()!=null &&!vo.getSonscategory().isEmpty()){
			for(CategoryVO tmp : vo.getSonscategory()){
				if(tmp!=null)
					this.sonscategory.add(new CategoryPO(tmp));
			}
		}
		this.myvalue = vo.getMyvalue();
		this.epanded = vo.getEpanded();
		this.fatherStr = vo.getFatherStr();
		this.categoryID = vo.getCategoryID();
		this.hasGoods = vo.getHasGoods();
	}
	
	public CategoryPO(){}
	
	public CategoryPO(ArrayList<CategoryPO> sonscategory, Boolean epanded, String categoryID, String staffID) {
		this.sonscategory = sonscategory;
		this.epanded = epanded;
		this.categoryID = categoryID;
		this.staffID = staffID;
	}

	public CategoryPO(ArrayList<CategoryPO> sonscategory, String myvalue, Boolean epanded, String fatherStr,
			String categoryID, String staffID, boolean hasGoods) {
		this.sonscategory = sonscategory;
		this.myvalue = myvalue;
		this.epanded = epanded;
		this.fatherStr = fatherStr;
		this.categoryID = categoryID;
		this.staffID = staffID;
		this.hasGoods = hasGoods;
	}

	public ArrayList<CategoryPO> getSonscategory() {
		return sonscategory;
	}

	public String getMyvalue() {
		return myvalue;
	}

	public Boolean getEpanded() {
		return epanded;
	}

	public String getFatherStr() {
		return fatherStr;
	}

	public String getCategoryID() {
		return categoryID;
	}

	public String getStaffID() {
		return staffID;
	}

	public void setSonscategory(List<CategoryPO> sonscategory) {
		this.sonscategory = new ArrayList<CategoryPO>(sonscategory);
	}

	public void setMyvalue(String myvalue) {
		this.myvalue = myvalue;
	}

	public void setEpanded(Boolean epanded) {
		this.epanded = epanded;
	}

	public void setFatherStr(String fatherStr) {
		this.fatherStr = fatherStr;
	}

	public void setCategoryID(String categoryID) {
		this.categoryID = categoryID;
	}

	public void setStaffID(String staffID) {
		this.staffID = staffID;
	}

	public boolean getHasGoods() {
		return hasGoods;
	}

	public void setHasGoods(boolean hasGoods) {
		this.hasGoods = hasGoods;
	}

}
