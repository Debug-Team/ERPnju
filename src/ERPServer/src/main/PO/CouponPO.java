package main.PO;

import main.VO.CouponVO;

public class CouponPO {
	
	private int id;
	private String startTime;
	private String endTime;
	private double amount;
	
	public CouponPO() {}
	
	public CouponPO(CouponVO vo) {
		this.id = vo.getId();
		this.startTime = vo.getStartTime();
		this.endTime = vo.getEndTime();
		this.amount = vo.getAmount();
	}
	
	public CouponPO(String startTime, String endTime, double amount) {
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
