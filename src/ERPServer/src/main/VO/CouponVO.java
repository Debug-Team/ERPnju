package main.VO;

import java.io.Serializable;

import main.PO.CouponPO;

public class CouponVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String startTime;
	private String endTime;
	private double amount;
	
	public CouponVO() {}
	
	public CouponVO(CouponPO po) {
		this.id = po.getId();
		this.startTime = po.getStartTime();
		this.endTime = po.getEndTime();
		this.amount = po.getAmount();
	}
	
	public CouponVO(String startTime, String endTime, double amount) {
		this.startTime = startTime;
		this.endTime = endTime;
		this.amount = amount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

}
