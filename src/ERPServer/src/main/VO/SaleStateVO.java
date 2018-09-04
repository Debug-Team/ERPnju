package main.VO;

import java.io.Serializable;

public class SaleStateVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private double saleIncome;  //销售收入
	private double goodsIncome;  //商品收入
	private double saleCost;  //销售成本
	private double goodsCost;  //商品支出
	private double goodsDiscount;  //商品折让
	private double total;  //总利润
	
	public SaleStateVO() {}
	
	public SaleStateVO(double saleIncome, double goodsIncome, double saleCost, 
			double goodsCost, double goodsDiscount) {
		this.saleIncome = saleIncome;
		this.goodsIncome = goodsIncome;
		this.saleCost = saleCost;
		this.goodsCost = goodsCost;
		this.goodsDiscount = goodsDiscount;
		this.total = saleIncome - saleCost;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getSaleIncome() {
		return saleIncome;
	}

	public void setSaleIncome(double saleIncome) {
		this.saleIncome = saleIncome;
	}

	public double getGoodsIncome() {
		return goodsIncome;
	}

	public void setGoodsIncome(double goodsIncome) {
		this.goodsIncome = goodsIncome;
	}

	public double getSaleCost() {
		return saleCost;
	}

	public void setSaleCost(double saleCost) {
		this.saleCost = saleCost;
	}

	public double getGoodsCost() {
		return goodsCost;
	}

	public void setGoodsCost(double goodsCost) {
		this.goodsCost = goodsCost;
	}

	public double getGoodsDiscount() {
		return goodsDiscount;
	}

	public void setGoodsDiscount(double goodsDiscount) {
		this.goodsDiscount = goodsDiscount;
	}
	
}
