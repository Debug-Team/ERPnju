package main.PO;

import main.VO.BankAccountVO;

public class BankAccountPO {
	//所有属性必须为private
	private String id;  //银行账户id
	private double amount;  //银行账户金额
	
	//默认构造方法
	public BankAccountPO() {}
	
	public BankAccountPO(BankAccountVO vo) {
		this.id = vo.getId();
		this.amount = vo.getAmount();
	}
	
	public BankAccountPO (String id, double amount) {
		this.id = id;
		this.amount = amount;
	}
	
	//所有属性配备get、set方法
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public double getAmount() {
		return amount;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
}
