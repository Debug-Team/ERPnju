package main.PO;

import main.VO.PaymentItemVO;

public class PaymentItemPO {
	private int id;
	private String bankAccountID;
	private double amount;
	private String comment;
	
	public PaymentItemPO(){}
	
	public PaymentItemPO(PaymentItemVO vo) {
		this.id = vo.getId();
		this.bankAccountID = vo.getBankAccountID();
		this.amount = vo.getAmount();
		this.comment = vo.getComment();
	}
	
	public PaymentItemPO(String bankAccountID, double amount, String comment) {
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
