package main.VO;

import java.io.Serializable;
import java.util.ArrayList;

import main.PO.LevelPromotionPO;

public class LevelPromotionVO extends PromotionVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String level;
	private double discount;  //折让
	private ArrayList<ReciptGoodsVO> giftList;  //赠品列表
	private ArrayList<CouponVO> couponList;  //代金券列表
	
	public LevelPromotionVO() {}
	
	public LevelPromotionVO(LevelPromotionPO po) {
		super(po.getId(), po.getStartTime(), po.getEndTime());
		this.level = po.getLevel();
		this.discount = po.getDiscount();
		
		this.giftList = new ArrayList<ReciptGoodsVO>();
		for(int i = 0; i < po.getGiftList().size(); i++) {
			ReciptGoodsVO tempVO = new ReciptGoodsVO(po.getGiftList().get(i));
			this.giftList.add(tempVO);
		}
		
		this.couponList = new ArrayList<CouponVO>();
		for(int i = 0; i < po.getCouponList().size(); i++) {
			CouponVO tempVO = new CouponVO(po.getCouponList().get(i));
			this.couponList.add(tempVO);
		}
	}

	public LevelPromotionVO(String startTime, String endTime, String level, 
			double discount, ArrayList<GoodsVO> giftList, ArrayList<CouponVO> couponList) {
		super("JB", startTime, endTime);
		this.level = level;
		this.discount = discount;
		
		this.giftList = new ArrayList<ReciptGoodsVO>(); 
		for(int i = 0; i < giftList.size(); i++) {
			ReciptGoodsVO tempVO = new ReciptGoodsVO(giftList.get(i));
			this.giftList.add(tempVO);
		}
		this.couponList = couponList;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public ArrayList<ReciptGoodsVO> getGiftList() {
		return giftList;
	}

	public void setGiftList(ArrayList<ReciptGoodsVO> giftList) {
		this.giftList = giftList;
	}

	public ArrayList<CouponVO> getCouponList() {
		return couponList;
	}

	public void setCouponList(ArrayList<CouponVO> couponList) {
		this.couponList = couponList;
	}

}
