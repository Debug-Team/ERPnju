package main.VO;

import java.io.Serializable;
import java.util.ArrayList;

import main.PO.CollectionItemPO;
import main.PO.CollectionOrderPO;

public class CollectionOrderVO extends ReciptVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String operator;  //操作员
	private String supplier;  //供货商
	private String retailer;  //销售商
	private ArrayList<CollectionItemVO> itemList;
	private double sum;
	
	public CollectionOrderVO() {
		super();
	}
	
	public CollectionOrderVO(CollectionOrderPO po) {
		super(po.getId(), po.getState());
		this.operator = po.getOperator();
		this.supplier = po.getSupplier();
		this.retailer = po.getRetailer();
		
		ArrayList<CollectionItemVO> voList = new ArrayList<CollectionItemVO>();
		ArrayList<CollectionItemPO> poList = po.getItemList();
		for(int i = 0; i < poList.size(); i++) {
			CollectionItemVO vo = new CollectionItemVO(poList.get(i));
			voList.add(vo);
		}
		this.itemList = voList;
		this.sum = po.getSum();
	}

	public CollectionOrderVO(String operator, String supplier, String retailer, ArrayList<CollectionItemVO> list, double sum) {
		super();
		this.operator = operator;
		this.supplier = supplier;
		this.retailer = retailer;
		this.itemList = list;
		this.sum = sum;
	}
	
	public ArrayList<CollectionItemVO> getItemList(){
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

