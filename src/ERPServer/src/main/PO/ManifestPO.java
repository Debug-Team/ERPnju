package main.PO;

import java.util.ArrayList;
import java.util.List;

import main.VO.ManifestVO;
import main.VO.ReciptGoodsVO;

public class ManifestPO {
	private String ID = "";
	private String type = "";
	
	private String customerName = "";
	private String warehouse = "";
	private String operator = "";
	private ArrayList<ReciptGoodsPO> goodsList = new ArrayList<>();
	private String comment = "";
	private double sum = 0;//总额 or 折让后总额
	
	//销售单使用的类型
	private String salesman = "";//业务员
	private double discount = 0;//折让
	private double promotionDiscount = 0;//促销策略优惠
	private ArrayList<ReciptGoodsPO> giftGoodslist = new ArrayList<>();//赠送商品列表
	private double couponValue = 0;
	private int couponID = 0;
	
	private String createDate = "";
	private String state = "";//draft,sumbit,checked
	
	public ManifestPO(){}
	
	public ManifestPO(ManifestVO vo){
		this.ID = vo.getID();
		this.type = vo.getType();
		this.customerName = vo.getCustomerName();
		this.warehouse = vo.getWarehouse();
		this.operator = vo.getOperator();
		
		for(ReciptGoodsVO tmp : vo.getGoodsList()){
			this.goodsList.add(new ReciptGoodsPO(tmp));
		}
		
		this.comment = vo.getComment();
		this.sum = vo.getSum();
		this.salesman = vo.getSalesman();
		this.discount = vo.getDiscount();
		this.promotionDiscount = vo.getPromotionDiscount();
		this.couponID = vo.getCouponID();
		this.couponValue = vo.getCouponValue();
		
		for(ReciptGoodsVO tmp : vo.getGiftGoodslist()){
			this.giftGoodslist.add(new ReciptGoodsPO(tmp));
		}
		
		this.createDate = vo.getCreateDate();
		this.state = vo.getState();
	}

	public String getID() {
		return ID;
	}

	public String getType() {
		return type;
	}

	public String getCustomerName() {
		return customerName;
	}

	public String getWarehouse() {
		return warehouse;
	}

	public String getOperator() {
		return operator;
	}

	public ArrayList<ReciptGoodsPO> getGoodsList() {
		return goodsList;
	}

	public String getComment() {
		return comment;
	}

	public double getSum() {
		return sum;
	}

	public String getSalesman() {
		return salesman;
	}

	public double getDiscount() {
		return discount;
	}

	public double getPromotionDiscount() {
		return promotionDiscount;
	}

	public String getCreateDate() {
		return createDate;
	}

	public String getState() {
		return state;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public void setGoodsList(List<ReciptGoodsPO> goodsList) {
		this.goodsList = new ArrayList<>( goodsList);
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}

	public void setSalesman(String salesman) {
		this.salesman = salesman;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public void setPromotionDiscount(double promotionDiscount) {
		this.promotionDiscount = promotionDiscount;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public void setState(String state) {
		this.state = state;
	}

	public ArrayList<ReciptGoodsPO> getGiftGoodslist() {
		return giftGoodslist;
	}

	public void setGiftGoodslist(List<ReciptGoodsPO> giftGoodslist) {
		this.giftGoodslist = new ArrayList<ReciptGoodsPO>(giftGoodslist);
	}

	public double getCouponValue() {
		return couponValue;
	}

	public void setCouponValue(double couponValue) {
		this.couponValue = couponValue;
	}

	public int getCouponID() {
		return couponID;
	}

	public void setCouponID(int couponID) {
		this.couponID = couponID;
	}
	
}
