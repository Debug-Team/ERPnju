package main.VO;

import main.PO.LogPO;

public class LogVO {
	private int sqlID;
	private String jobNum;
	private String createTime;
	private String message;
	
	public LogVO(LogPO po) {
		this.jobNum = po.getJobNum();
		this.createTime = po.getCreateTime();
		this.message = po.getMessage();
		this.sqlID = po.getSqlID();
	}
	
	public LogVO(){}
	
	public String getJobNum() {
		return jobNum;
	}
	public String getCreateTime() {
		return createTime;
	}
	public String getMessage() {
		return message;
	}
	public void setJobNum(String jobNum) {
		this.jobNum = jobNum;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getSqlID() {
		return sqlID;
	}
	public void setSqlID(int sqlID) {
		this.sqlID = sqlID;
	}
}

