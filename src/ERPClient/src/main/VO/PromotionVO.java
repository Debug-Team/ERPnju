package main.VO;

import java.io.Serializable;

import main.PO.PromotionPO;

public class PromotionVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String startTime;	//开始时间
	private String endTime;    //结束时间
	
	public PromotionVO() {}
	
	public PromotionVO(PromotionPO po) {
		this.id = po.getId();
		this.startTime = po.getStartTime();
		this.endTime = po.getEndTime();
	}

	public PromotionVO(String id, String startTime, String endTime) {
		this.id = id;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

}
