package main.PO;

import java.util.ArrayList;
import java.util.List;

import main.VO.PaymentItemVO;
import main.VO.PaymentOrderVO;

public class PaymentOrderPO extends ReciptPO {
	private String operator;  //操作员
	private String supplier;  //供货商
	private String retailer;  //销售商
	private ArrayList<PaymentItemPO> itemList;
	private double sum;
	
	public PaymentOrderPO() {
		super();
	}
	
	public PaymentOrderPO(PaymentOrderVO vo) {
		super(vo.getId(), vo.getState());
		this.operator = vo.getOperator();
		this.supplier = vo.getSupplier();
		this.retailer = vo.getRetailer();
		
		ArrayList<PaymentItemPO> poList = new ArrayList<PaymentItemPO>();
		ArrayList<PaymentItemVO> voList = vo.getItemList();
		for(int i = 0; i < voList.size(); i++) {
			PaymentItemPO po = new PaymentItemPO(voList.get(i));
			poList.add(po);
		}
		this.itemList = poList;
		this.sum = vo.getSum();
	}

	public PaymentOrderPO(String operator, String supplier, String retailer, ArrayList<PaymentItemPO> list, double sum) {
		super();
		this.operator = operator;
		this.supplier = supplier;
		this.retailer = retailer;
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

	public ArrayList<PaymentItemPO> getItemList() {
		return itemList;
	}

	public void setItemList(List<PaymentItemPO> itemList) {
		this.itemList = new ArrayList<PaymentItemPO>(itemList);
	}

	public double getSum() {
		return sum;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getRetailer() {
		return retailer;
	}

	public void setRetailer(String retailer) {
		this.retailer = retailer;
	}

}

