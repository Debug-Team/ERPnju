package main.VO;

import java.io.Serializable;

import main.PO.CollectionItemPO;

public class CollectionItemVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String bankAccountID;
	private double amount;
	private String comment;
	
	public CollectionItemVO(CollectionItemPO po) {
		this.id = po.getId();
		this.bankAccountID = po.getBankAccountID();
		this.amount = po.getAmount();
		this.comment = po.getComment();
	}
	
	public CollectionItemVO(String bankAccountID, double amount, String comment) {
		this.bankAccountID = bankAccountID;
		this.amount = amount;
		this.comment = comment;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBankAccountID() {
		return bankAccountID;
	}

	public void setBankAccountID(String bankAccountID) {
		this.bankAccountID = bankAccountID;
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
