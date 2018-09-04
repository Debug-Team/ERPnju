package main.VO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import main.PO.ManifestPO;
import main.PO.ReciptGoodsPO;

public class ManifestVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4451889364918266892L;

	private String ID = "";
	private String type = "";
	
	private String customerName = "";
	private String warehouse = "";
	private String operator = "";
	private List<ReciptGoodsVO> goodsList = new ArrayList<>();
	private String comment = "";
	private double sum = 0;//总额 or 折让后总额
	
	//销售单使用的类型
	private String salesman = "";//业务员
	private double discount = 0;//折让
	private double promotionDiscount = 0;//促销策略优惠
	private List<ReciptGoodsVO> giftGoodslist = new ArrayList<>();//赠送商品列表
	private double couponValue = 0;
	private int couponID = 0;
	
	private String createDate = "";
	private String state = "";//draft,sumbit,checked
	private String staffID = "";
	
	public ManifestVO(){}

	public ManifestVO(String iD, String type, String customerName, List<ReciptGoodsVO> goodsList) {
		ID = iD;
		this.type = type;
		this.customerName = customerName;
		this.goodsList = goodsList;
	}
	
	public ManifestVO(String iD, String customerName, String warehouse, String operator, List<ReciptGoodsVO> goodsList,
			String comment, double sum, String state, String staffID) {
		super();
		ID = iD;
		this.customerName = customerName;
		this.warehouse = warehouse;
		this.operator = operator;
		this.goodsList = goodsList;
		this.comment = comment;
		this.sum = sum;
		this.state = state;
		this.staffID = staffID;
	}

	public ManifestVO(String iD, String customerName, String warehouse, String operator, List<ReciptGoodsVO> goodsList,
			String comment, double sum, String salesman, double discount, String state, String staffID,int couponid,Double couponvalue) {
		super();
		ID = iD;
		this.customerName = customerName;
		this.warehouse = warehouse;
		this.operator = operator;
		this.goodsList = goodsList;
		this.comment = comment;
		this.sum = sum;
		this.salesman = salesman;
		this.discount = discount;
		this.state = state;
		this.staffID = staffID;
		this.couponID = couponid;
		this.couponValue = couponvalue;
	}
	
	

	public ManifestVO(String iD, String type, String customerName, String warehouse, String operator,
			List<ReciptGoodsVO> goodsList, String comment, double sum, String salesman, double discount,
			double promotionDiscount, List<ReciptGoodsVO> giftGoodslist, String createDate, String state,
			String staffID) {
		ID = iD;
		this.type = type;
		this.customerName = customerName;
		this.warehouse = warehouse;
		this.operator = operator;
		this.goodsList = goodsList;
		this.comment = comment;
		this.sum = sum;
		this.salesman = salesman;
		this.discount = discount;
		this.promotionDiscount = promotionDiscount;
		this.giftGoodslist = giftGoodslist;
		this.createDate = createDate;
		this.state = state;
		this.staffID = staffID;
	}

	public ManifestVO(ManifestPO po){
		this.ID = po.getID();
		this.type = po.getType();
		this.customerName = po.getCustomerName();
		this.warehouse = po.getWarehouse();
		this.operator = po.getOperator();
		
		for(ReciptGoodsPO tmp : po.getGoodsList()){
			this.goodsList.add(new ReciptGoodsVO(tmp));
		}
		
		this.comment = po.getComment();
		this.sum = po.getSum();
		this.salesman = po.getSalesman();
		this.discount = po.getDiscount();
		this.promotionDiscount = po.getPromotionDiscount();
		this.couponID = po.getCouponID();
		this.couponValue = po.getCouponValue();
		
		for(ReciptGoodsPO tmp : po.getGiftGoodslist()){
			this.giftGoodslist.add(new ReciptGoodsVO(tmp));
		}
		
		this.createDate = po.getCreateDate();
		this.state = po.getState();
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

	public List<ReciptGoodsVO> getGoodsList() {
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

	public List<ReciptGoodsVO> getGiftGoodslist() {
		return giftGoodslist;
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

	public void setGoodsList(List<ReciptGoodsVO> goodsList) {
		this.goodsList = goodsList;
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

	public void setGiftGoodslist(List<ReciptGoodsVO> giftGoodslist) {
		this.giftGoodslist = giftGoodslist;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStaffID() {
		return staffID;
	}

	public void setStaffID(String staffID) {
		this.staffID = staffID;
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
