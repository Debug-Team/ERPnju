package main.VO;

import java.io.Serializable;

import main.PO.ReciptGoodsPO;

public class ReciptGoodsVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int sqlID = 0;
	private String name  = "";
	private String version = "";
	private String goodsID = "";
	private int amounts = 0;
	private double bid = 0;
	private double sum = 0;			//在账里的商品中作为售价属性
	private String comment = "";	//在账里的商品中作为分类属性

	public ReciptGoodsVO() {}

	public ReciptGoodsVO(int sqlID, String name, String version, String goodsID, int amounts, double bid, double sum,
			String comment) {
		this.sqlID = sqlID;
		this.name = name;
		this.version = version;
		this.goodsID = goodsID;
		this.amounts = amounts;
		this.bid = bid;
		this.sum = sum;
		this.comment = comment;
	}

	public ReciptGoodsVO(GoodsVO vo){
		this.name = vo.getName();
		this.version = vo.getVersion();
		this.goodsID = vo.getCatagory();
		this.amounts = (int) vo.getAmounts();
		this.bid = vo.getBid();
		this.sum = 0;
		this.comment = "";
	}
	
	public ReciptGoodsVO(ReciptGoodsPO po) {
		this.sqlID = po.getSqlID();
		this.name = po.getName();
		this.version = po.getVersion();
		this.goodsID = po.getGoodsID();
		this.amounts = po.getAmounts();
		this.bid = po.getBid();
		this.sum = po.getSum();
		this.comment = po.getComment();
	}

	public int getSqlID() {
		return sqlID;
	}

	public String getName() {
		return name;
	}

	public String getVersion() {
		return version;
	}

	public String getGoodsID() {
		return goodsID;
	}

	public int getAmounts() {
		return amounts;
	}

	public double getBid() {
		return bid;
	}

	public double getSum() {
		return sum;
	}

	public String getComment() {
		return comment;
	}

	public void setSqlID(int sqlID) {
		this.sqlID = sqlID;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public void setGoodsID(String goodsID) {
		this.goodsID = goodsID;
	}

	public void setAmounts(int amounts) {
		this.amounts = amounts;
	}

	public void setBid(double bid) {
		this.bid = bid;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	
}
