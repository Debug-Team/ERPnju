package main.VO;

import java.io.Serializable;

import main.PO.CashItemPO;

public class CashItemVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	private double amount;
	private String comment;
	
	public CashItemVO(CashItemPO po) {
		this.id = po.getId();
		this.name = po.getName();
		this.amount = po.getAmount();
		this.comment = po.getComment();
	}
	
	public CashItemVO(String name, double amount, String comment){
		this.name = name;
		this.amount = amount;
		this.comment = comment;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
