package main.PO;

import java.util.ArrayList;
import java.util.List;

import main.VO.TotalPromotionVO;

public class TotalPromotionPO extends PromotionPO {
	
	private double totalNumber;    //激发折扣的消费下线
	private ArrayList<ReciptGoodsPO> giftList;  //赠品列表
	private ArrayList<CouponPO> couponList;  //代金券列表
	
	public TotalPromotionPO() {}
	
	public TotalPromotionPO(TotalPromotionVO vo) {
		super(vo.getId(), vo.getStartTime(), vo.getEndTime());
		this.totalNumber = vo.getTotalNumber();
		
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

	public TotalPromotionPO(String startTime, String endTime, double totalNumber, 
			ArrayList<GoodsPO> giftList, ArrayList<CouponPO> couponList) {
		super("ZJ", startTime, endTime);
		this.totalNumber = totalNumber;
		
		this.giftList = new ArrayList<ReciptGoodsPO>(); 
		for(int i = 0; i < giftList.size(); i++) {
			ReciptGoodsPO tempPO = new ReciptGoodsPO(giftList.get(i));
			this.giftList.add(tempPO);
		}
		
		this.couponList = couponList;
	}

	public double getTotalNumber() {
		return totalNumber;
	}

	public void setTotalNumber(double totalNumber) {
		this.totalNumber = totalNumber;
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
