package main.VO;

import java.io.Serializable;

import main.PO.ReciptPO;

public class ReciptVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String state;  //约定state只有几种状态 Checked Unchecked Draft
	
	public ReciptVO() {
		this.id = "";
		this.state = "Unchecked";
	}
	
	public ReciptVO(ReciptPO po) {
		this.id = po.getId();
		this.state = po.getState();
	}
	
	public ReciptVO(String id, String state) {
		this.id = id;
		this.state = state;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
