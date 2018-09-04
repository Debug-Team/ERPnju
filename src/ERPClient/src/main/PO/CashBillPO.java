package main.PO;

import java.util.ArrayList;
import java.util.List;

import main.VO.CashBillVO;
import main.VO.CashItemVO;

public class CashBillPO extends ReciptPO {
	private String operator;
	private String bankAccountID;
	private ArrayList<CashItemPO> itemList;
	private double sum;
	
	public CashBillPO() {
		super();
	}
	
	public CashBillPO(CashBillVO vo) {
		super(vo.getId(), vo.getState());
		this.operator = vo.getOperator();
		this.bankAccountID = vo.getBankAccountID();
		
		ArrayList<CashItemPO> poList = new ArrayList<CashItemPO>();
		ArrayList<CashItemVO> voList = vo.getItemList();
		for(int i = 0; i < voList.size(); i++) {
			CashItemPO po = new CashItemPO(voList.get(i));
			poList.add(po);
		}
		this.itemList = poList;
		
		this.sum = vo.getSum();
	}
	
	public CashBillPO(String operator, String bankAccountID, ArrayList<CashItemPO> list, double sum) {
		super();
		this.operator = operator;
		this.bankAccountID = bankAccountID;
		this.itemList = list;
		this.sum = sum;
	}
	
	//所有属性配备get、set方法
	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getBankAccountID() {
		return bankAccountID;
	}

	public void setBankAccountID(String bankAccountID) {
		this.bankAccountID = bankAccountID;
	}

	public ArrayList<CashItemPO> getItemList() {
		return itemList;
	}

	public void setItemList(List<CashItemPO> itemList) {
		this.itemList = new ArrayList<CashItemPO>(itemList);
	}

	public double getSum() {
		return sum;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}
	
}

