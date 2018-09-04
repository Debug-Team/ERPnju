package main.PO;

import java.util.ArrayList;
import java.util.List;

import main.VO.LevelPromotionVO;

public class LevelPromotionPO extends PromotionPO {
	
	private String level;
	private double discount;  //折让
	private ArrayList<ReciptGoodsPO> giftList;  //赠品列表
	private ArrayList<CouponPO> couponList;  //代金券列表
	
	public LevelPromotionPO() {}
	
	public LevelPromotionPO(LevelPromotionVO vo) {
		super(vo.getId(), vo.getStartTime(), vo.getEndTime());
		this.level = vo.getLevel();
		this.discount = vo.getDiscount();
		
		this.giftList = new ArrayList<ReciptGoodsPO>();
		for(int i = 0; i < vo.getGiftList().size(); i++) {
			ReciptGoodsPO tempPO = new ReciptGoodsPO(vo.getGiftList().get(i));
			this.giftList.add(tempPO);
		}
		
		this.couponList = new ArrayList<CouponPO>();
		for(int i = 0; i < vo.getCouponList().size(); i++) {
			CouponPO tempPO = new CouponPO(vo.getCouponList().get(i));
			this.couponList.add(tempPO);
		}
	}

	public LevelPromotionPO(String startTime, String endTime, String level, 
			double discount, ArrayList<GoodsPO> giftList, ArrayList<CouponPO> couponList) {
		super("JB", startTime, endTime);
		this.level = level;
		this.discount = discount;
		
		this.giftList = new ArrayList<ReciptGoodsPO>(); 
		for(int i = 0; i < giftList.size(); i++) {
			ReciptGoodsPO tempPO = new ReciptGoodsPO(giftList.get(i));
			this.giftList.add(tempPO);
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

	public ArrayList<ReciptGoodsPO> getGiftList() {
		return giftList;
	}

	public void setGiftList(List<ReciptGoodsPO> giftList) {
		this.giftList = new ArrayList<ReciptGoodsPO>(giftList);
	}

	public ArrayList<CouponPO> getCouponList() {
		return couponList;
	}

	public void setCouponList(List<CouponPO> couponList) {
		this.couponList = new ArrayList<CouponPO>(couponList);
	}

}
