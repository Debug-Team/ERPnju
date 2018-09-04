package main.VO;

import java.io.Serializable;

import main.PO.BankAccountPO;

public class BankAccountVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private double amount;
	private String operator;
	
	public BankAccountVO(BankAccountPO po) {
		this.operator = "";
		this.id = po.getId();
		this.amount = po.getAmount();
	}
	
	public BankAccountVO(String id, double amount, String operater) {
		this.operator = operater;
		this.id = id;
		this.amount = amount;
	}
	
	public String getId() {
		return id;
	}
	
	public double getAmount() {
		return amount;
	}
	
	public String getOperator() {
		return operator;
	}
}
