package main.utility;

import java.util.ArrayList;

import main.PO.ReciptGoodsPO;

public class PromotionResult {
	private double amount;  //折让后总消费金额
	private ArrayList<ReciptGoodsPO> giftList = new ArrayList<>();  //赠品列表
	
	public PromotionResult() {}
	
	public PromotionResult(double amount, ArrayList<ReciptGoodsPO> giftList) {
		this.amount = amount;
		this.giftList = giftList;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public ArrayList<ReciptGoodsPO> getGiftList() {
		return giftList;
	}

	public void setGiftList(ArrayList<ReciptGoodsPO> giftList) {
		this.giftList = giftList;
	}
	
}
