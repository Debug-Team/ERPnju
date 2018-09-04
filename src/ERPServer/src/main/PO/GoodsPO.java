package main.PO;

import main.VO.GoodsVO;

public class GoodsPO {
	private String name;
	private String ID;
	private String version;
	private long amounts;
	private double bid;//进价
	private double retailPrice;//零售价
	private double recentBid;//最近进价
	private double recentRetailPrice;//最近零售价
	private String catagory;
	private double avgValue;//库存均价
	private int alertAmounts;//警戒数量
	
	public GoodsPO(GoodsVO vo) {
		this.name = vo.getName();
		this.ID = vo.getID();
		this.version = vo.getVersion();
		this.amounts = vo.getAmounts();
		this.bid = vo.getBid();
		this.retailPrice = vo.getRetailPrice();
		this.recentBid = vo.getRecentBid();
		this.recentRetailPrice = vo.getRecentRetailPrice();
		this.catagory = vo.getCatagory();
		this.avgValue = vo.getAvgValue();
		this.alertAmounts = vo.getAlertAmounts();
	}

	public GoodsPO(String name,String id){
		this.name = name;
		this.ID = id;
	}
	
	public GoodsPO(){}

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

