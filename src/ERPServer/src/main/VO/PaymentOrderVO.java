package main.VO;

import java.io.Serializable;
import java.util.ArrayList;

import main.PO.PaymentItemPO;
import main.PO.PaymentOrderPO;

public class PaymentOrderVO extends ReciptVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String operator;  //操作员
	private String supplier;  //供货商
	private String retailer;  //销售商
	private ArrayList<PaymentItemVO> itemList;
	private double sum;
	
	public PaymentOrderVO() { 
		super(); 
	}
	
	public PaymentOrderVO(PaymentOrderPO po) {
		super(po.getId(), po.getState());
		this.operator = po.getOperator();
		this.supplier = po.getSupplier();
		this.retailer = po.getRetailer();
		
		ArrayList<PaymentItemVO> voList = new ArrayList<PaymentItemVO>();
		ArrayList<PaymentItemPO> poList = po.getItemList();
		for(int i = 0; i < poList.size(); i++) {
			PaymentItemVO vo = new PaymentItemVO(poList.get(i));
			voList.add(vo);
		}
		this.itemList = voList;
		this.sum = po.getSum();
	}

	public PaymentOrderVO(String operator, String supplier, String retailer, ArrayList<PaymentItemVO> list, double sum) {
		super();
		this.operator = operator;
		this.supplier = supplier;
		this.retailer = retailer;
		this.itemList = list;
		this.sum = sum;
	}
	
	public ArrayList<PaymentItemVO> getItemList(){
		return itemList;
	}
	
	public double getSum() {
		return sum;
	}

	public String getSupplier() {
		return supplier;
	}

	public String getRetailer() {
		return retailer;
	}

	public String getOperator() {
		return operator;
	}

}
