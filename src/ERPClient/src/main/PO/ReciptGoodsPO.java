package main.PO;

import main.VO.ReciptGoodsVO;

public class ReciptGoodsPO {
	private int sqlID;
	private String name;
	private String version;
	private String goodsID;
	private int amounts;
	private double bid;
	private double sum;
	private String comment;

	public ReciptGoodsPO(String name, String version, String goodsID, int amounts, double bid, double sum,
			String comment) {
		this.name = name;
		this.version = version;
		this.goodsID = goodsID;
		this.amounts = amounts;
		this.bid = bid;
		this.sum = sum;
		this.comment = comment;
	}
	
	public ReciptGoodsPO(GoodsPO po){
		this.name = po.getName();
		this.version = po.getVersion();
		this.goodsID = po.getID();
		this.amounts = (int) po.getAmounts();
		this.bid = po.getBid();
		this.sum = 0;
		this.comment = "";
	}

	public ReciptGoodsPO() {}

	public ReciptGoodsPO(ReciptGoodsVO vo) {
		this.sqlID = vo.getSqlID();
		this.name = vo.getName();
		this.version = vo.getVersion();
		this.goodsID = vo.getGoodsID();
		this.amounts = vo.getAmounts();
		this.bid = vo.getBid();
		this.sum = vo.getSum();
		this.comment = vo.getComment();
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
