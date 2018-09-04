package main.PO;

import main.VO.PromotionVO;

public class PromotionPO {
	
	private String id;
	private String startTime;	//开始时间
	private String endTime;    //结束时间
	
	public PromotionPO() {}
	
	public PromotionPO(PromotionVO vo) {
		this.id = vo.getId();
		this.startTime = vo.getStartTime();
		this.endTime = vo.getEndTime();
	}
	
	public PromotionPO(String id, String startTime, String endTime) {
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
