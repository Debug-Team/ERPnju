package main.VO;

import java.io.Serializable;

import main.PO.CommodityReciptPO;

public class CommodityReciptVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int ID = 0;
	private String type = "";//BS BY BJ ZS 对应 报损 报溢 报警 赠送单
	private String goodsName = "";
	private String goodsID = "";
	private int changedNumbers = 0;//在库存报警单为警戒数量
	private String createDate = "";
	private String state = "";
	
	private String staffID = "";

	public CommodityReciptVO(CommodityReciptPO po){
		this.ID = po.getID();
		this.type = po.getType();
		this.goodsName = po.getGoodsName();
		this.goodsID = po.getGoodsID();
		this.changedNumbers = po.getChangedNumbers();
		this.createDate = po.getCreateDate();
		this.state = po.getState();
	}

	public CommodityReciptVO(String type, String goodsName, String goodsID, int changedNumbers, String createDate,
			String state, String staffID) {
		this.type = type;
		this.goodsName = goodsName;
		this.goodsID = goodsID;
		this.changedNumbers = changedNumbers;
		this.createDate = createDate;
		this.state = state;
		this.staffID = staffID;
	}

	public String getType() {
		return type;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public String getGoodsID() {
		return goodsID;
	}

	public int getChangedNumbers() {
		return changedNumbers;
	}

	public String getCreateDate() {
		return createDate;
	}

	public String getState() {
		return state;
	}

	public String getStaffID() {
		return staffID;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public void setGoodsID(String goodsID) {
		this.goodsID = goodsID;
	}

	public void setChangedNumbers(int changedNumbers) {
		this.changedNumbers = changedNumbers;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setStaffID(String staffID) {
		this.staffID = staffID;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}
	
}
