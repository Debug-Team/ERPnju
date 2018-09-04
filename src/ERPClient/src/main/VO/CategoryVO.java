package main.VO;

import java.io.Serializable;
import java.util.ArrayList;

import main.PO.CategoryPO;

public class CategoryVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ArrayList<CategoryVO> sonscategory = new ArrayList<>();
	private String myvalue;
	private Boolean epanded;
	private String fatherStr;
	private String categoryID;
	private boolean hasGoods;

	private String staffID;
	
	public CategoryVO(CategoryPO po) {
		if(po.getSonscategory()!=null &&!po.getSonscategory().isEmpty()){
			for(CategoryPO tmp : po.getSonscategory()){
				if(tmp!=null)
					this.sonscategory.add(new CategoryVO(tmp));
			}
		}
		this.myvalue = po.getMyvalue();
		this.epanded = po.getEpanded();
		this.fatherStr = po.getFatherStr();
		this.categoryID = po.getCategoryID();
		this.hasGoods = po.getHasGoods();
	}
	
	public CategoryVO(ArrayList<CategoryVO> sonscategory, Boolean epanded, String categoryID, String staffID) {
		this.sonscategory = sonscategory;
		this.epanded = epanded;
		this.categoryID = categoryID;
		this.staffID = staffID;
	}

	public CategoryVO(ArrayList<CategoryVO> sonscategory, String myvalue, Boolean epanded, String fatherStr,
			String categoryID, String staffID, boolean hasGoods) {
		this.sonscategory = sonscategory;
		this.myvalue = myvalue;
		this.epanded = epanded;
		this.fatherStr = fatherStr;
		this.categoryID = categoryID;
		this.staffID = staffID;
		this.hasGoods = hasGoods;
	}

	public ArrayList<CategoryVO> getSonscategory() {
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

	public void setSonscategory(ArrayList<CategoryVO> sonscategory) {
		this.sonscategory = sonscategory;
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
