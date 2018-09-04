package main.PO;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LogPO {
	private int sqlID;
	private String jobNum;
	private String createTime;
	private String message;
	
	public LogPO(String jobNum, String message) {
		this.jobNum = jobNum;
		this.message = message;
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd-HHmmss");
		String date = format.format(new Date());
		this.createTime = date;
	}
	
	public LogPO(){}
	
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
