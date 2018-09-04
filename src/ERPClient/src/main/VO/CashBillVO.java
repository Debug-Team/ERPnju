package main.VO;

import java.io.Serializable;
import java.util.ArrayList;

import main.PO.CashBillPO;
import main.PO.CashItemPO;

public class CashBillVO extends ReciptVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String operator;
	private String bankAccountID;
	private ArrayList<CashItemVO> itemList;
	private double sum;
	
	public CashBillVO() {
		super();
	}
	
	public CashBillVO(CashBillPO po) {
		super(po.getId(), po.getState());
		this.operator = po.getOperator();
		this.bankAccountID = po.getBankAccountID();
		
		ArrayList<CashItemVO> voList = new ArrayList<CashItemVO>();
		ArrayList<CashItemPO> poList = po.getItemList();
		for(int i = 0; i < poList.size(); i++) {
			CashItemVO vo = new CashItemVO(poList.get(i));
			voList.add(vo);
		}
		this.itemList = voList;
		
		this.sum = po.getSum();
	}
	
	public CashBillVO(String operator, String bankAccountID, ArrayList<CashItemVO> list, double sum) {
		super();
		this.operator = operator;
		this.bankAccountID = bankAccountID;
		this.itemList = list;
		this.sum = sum;
	}
	
	public String getOperator() {
		return operator;
	}
	
	public String getBankAccountID() {
		return bankAccountID;
	}
	
	public ArrayList<CashItemVO> getItemList(){
		return itemList;
	}
	
	public double getSum() {
		return sum;
	}
}
