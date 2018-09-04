package main.PO;

import main.VO.CommodityReciptVO;

public class CommodityReciptPO{
	private int ID;
	private String type;//BS BY BJ ZS 对应 报损 报溢 报警 赠送单
	private String goodsName;
	private String goodsID;
	private int changedNumbers;//在库存报警单为警戒数量
	private String createDate;
	private String state;
	
	public CommodityReciptPO(CommodityReciptVO vo){
		this.ID = vo.getID();
		this.type = vo.getType();
		this.goodsName = vo.getGoodsName();
		this.goodsID = vo.getGoodsID();
		this.changedNumbers = vo.getChangedNumbers();
		this.createDate = vo.getCreateDate();
		this.state = vo.getState();
	}
	
	public CommodityReciptPO(String type,String goodsName,String goodsID,int changedNumbers) {
		this.type = type;
		this.goodsName = goodsName;
		this.changedNumbers = changedNumbers;
		this.goodsID = goodsID;
	}
	
	public CommodityReciptPO(){}

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

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

}
