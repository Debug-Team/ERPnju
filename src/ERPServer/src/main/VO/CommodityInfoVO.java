package main.VO;

import java.io.Serializable;
import java.util.ArrayList;

public class CommodityInfoVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ArrayList<ReciptGoodsVO> commodityInList = new ArrayList<>();
	private ArrayList<ReciptGoodsVO> commodityOutList = new ArrayList<>();
	private ArrayList<ReciptGoodsVO> saleList = new ArrayList<>();
	private ArrayList<ReciptGoodsVO> goodsInList = new ArrayList<>();
	private int commodityInAmounts = 0;
	private int commodityOutAmouts = 0;
	private int saleAmounts = 0;
	private int goodsInAmounts = 0;
	private double commodityInSumValue = 0;
	private double commodityOutSumValue = 0;
	private double saleAmountsSumValue = 0;
	private double goodsInAmountsSumValue = 0;
	
	public CommodityInfoVO(){}

	public final ArrayList<ReciptGoodsVO> getCommodityInList() {
		return commodityInList;
	}

	public final ArrayList<ReciptGoodsVO> getCommodityOutList() {
		return commodityOutList;
	}

	public final ArrayList<ReciptGoodsVO> getSaleList() {
		return saleList;
	}

	public final ArrayList<ReciptGoodsVO> getGoodsInList() {
		return goodsInList;
	}

	public final int getCommodityInAmounts() {
		return commodityInAmounts;
	}

	public final int getCommodityOutAmouts() {
		return commodityOutAmouts;
	}

	public final int getSaleAmounts() {
		return saleAmounts;
	}

	public final int getGoodsInAmounts() {
		return goodsInAmounts;
	}

	public final double getCommodityInSumValue() {
		return commodityInSumValue;
	}

	public final double getCommodityOutSumValue() {
		return commodityOutSumValue;
	}

	public final double getSaleAmountsSumValue() {
		return saleAmountsSumValue;
	}

	public final double getGoodsInAmountsSumValue() {
		return goodsInAmountsSumValue;
	}

	public final void setCommodityInList(ArrayList<ReciptGoodsVO> commodityInList) {
		this.commodityInList = commodityInList;
	}

	public final void setCommodityOutList(ArrayList<ReciptGoodsVO> commodityOutList) {
		this.commodityOutList = commodityOutList;
	}

	public final void setSaleList(ArrayList<ReciptGoodsVO> saleList) {
		this.saleList = saleList;
	}

	public final void setGoodsInList(ArrayList<ReciptGoodsVO> goodsInList) {
		this.goodsInList = goodsInList;
	}

	public final void setCommodityInAmounts(int commodityInAmounts) {
		this.commodityInAmounts = commodityInAmounts;
	}

	public final void setCommodityOutAmouts(int commodityOutAmouts) {
		this.commodityOutAmouts = commodityOutAmouts;
	}

	public final void setSaleAmounts(int saleAmounts) {
		this.saleAmounts = saleAmounts;
	}

	public final void setGoodsInAmounts(int goodsInAmounts) {
		this.goodsInAmounts = goodsInAmounts;
	}

	public final void setCommodityInSumValue(double commodityInSumValue) {
		this.commodityInSumValue = commodityInSumValue;
	}

	public final void setCommodityOutSumValue(double commodityOutSumValue) {
		this.commodityOutSumValue = commodityOutSumValue;
	}

	public final void setSaleAmountsSumValue(double saleAmountsSumValue) {
		this.saleAmountsSumValue = saleAmountsSumValue;
	}

	public final void setGoodsInAmountsSumValue(double goodsInAmountsSumValue) {
		this.goodsInAmountsSumValue = goodsInAmountsSumValue;
	}

}
