package main.PO;

import main.VO.CashItemVO;

public class CashItemPO {
	private int id;
	private String name;
	private double amount;
	private String comment;
	
	public CashItemPO(CashItemVO vo) {
		this.id = vo.getId();
		this.name = vo.getName();
		this.amount = vo.getAmount();
		this.comment = vo.getComment();
	}
	
	public CashItemPO(String name, double amount, String comment) {
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

	public CashItemPO(){}

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
