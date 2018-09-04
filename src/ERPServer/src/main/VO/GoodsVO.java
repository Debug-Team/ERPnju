package main.VO;

import java.io.Serializable;

import main.PO.GoodsPO;

public class GoodsVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name = "";
	private String ID = "";
	private String version = "";
	private long amounts = 0;
	private double bid = 0;//进价
	private double retailPrice = 0;//零售价
	private double recentBid = 0;//最近进价
	private double recentRetailPrice = 0;//最近零售价
	private String catagory = "";//分类
	private double avgValue = 0;//库存均价
	private int alertAmounts = 0;//警戒数量
	
	private String staffID;
	
	public GoodsVO(GoodsPO po) {
		this.name = po.getName();
		this.ID = po.getID();
		this.version = po.getVersion();
		this.amounts = po.getAmounts();
		this.bid = po.getBid();
		this.retailPrice = po.getRetailPrice();
		this.recentBid = po.getRecentBid();
		this.recentRetailPrice = po.getRecentRetailPrice();
		this.catagory = po.getCatagory();
		this.avgValue = po.getAvgValue();
		this.alertAmounts = po.getAlertAmounts();
	}

	public GoodsVO(String name, String iD, String version, long amounts, double bid, double retailPrice,
			double recentBid, double recentRetailPrice, String catagory, double avgValue, String staffID) {
		this.name = name;
		ID = iD;
		this.version = version;
		this.amounts = amounts;
		this.bid = bid;
		this.retailPrice = retailPrice;
		this.recentBid = recentBid;
		this.recentRetailPrice = recentRetailPrice;
		this.catagory = catagory;
		this.avgValue = avgValue;
		this.staffID = staffID;
	}

	public GoodsVO(String name,String id){
		this.name = name;
		this.ID = id;
	}

	public String getName() {
		return name;
	}

	public String getID() {
		return ID;
	}

	public String getVersion() {
		return version;
	}

	public long getAmounts() {
		return amounts;
	}

	public double getBid() {
		return bid;
	}

	public double getRetailPrice() {
		return retailPrice;
	}

	public double getRecentBid() {
		return recentBid;
	}

	public double getRecentRetailPrice() {
		return recentRetailPrice;
	}

	public String getCatagory() {
		return catagory;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public void setAmounts(long amounts) {
		this.amounts = amounts;
	}

	public void setBid(double bid) {
		this.bid = bid;
	}

	public void setRetailPrice(double retailPrice) {
		this.retailPrice = retailPrice;
	}

	public void setRecentBid(double recentBid) {
		this.recentBid = recentBid;
	}

	public void setRecentRetailPrice(double recentRetailPrice) {
		this.recentRetailPrice = recentRetailPrice;
	}

	public void setCatagory(String catagory) {
		this.catagory = catagory;
	}

	public String getStaffID() {
		return staffID;
	}

	public void setStaffID(String staffID) {
		this.staffID = staffID;
	}

	public double getAvgValue() {
		return avgValue;
	}

	public void setAvgValue(double avgValue) {
		this.avgValue = avgValue;
	}

	public int getAlertAmounts() {
		return alertAmounts;
	}

	public void setAlertAmounts(int alertAmounts) {
		this.alertAmounts = alertAmounts;
	}
	
}
