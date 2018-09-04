package main.PO;

import java.util.ArrayList;
import java.util.List;

import main.VO.PackagePromotionVO;

public class PackagePromotionPO extends PromotionPO {
	
	private double reducePrice;  //降价
	private ArrayList<ReciptGoodsPO> giftList;  //特价包列表
	
	public PackagePromotionPO() {}
	
	public PackagePromotionPO(PackagePromotionVO vo) {
		super(vo.getId(), vo.getStartTime(), vo.getEndTime());
		this.reducePrice = vo.getReducePrice();
		
		this.giftList = new ArrayList<ReciptGoodsPO>();
		for(int i = 0; i < vo.getGiftList().size(); i++) {
			ReciptGoodsPO tempPO = new ReciptGoodsPO(vo.getGiftList().get(i));
			this.giftList.add(tempPO);
		}
	}
	
	public PackagePromotionPO(String startTime, String endTime, 
			double reducePrice, ArrayList<GoodsPO> giftList) {
		super("TJB", startTime, endTime);
		this.reducePrice = reducePrice;
		
		this.giftList = new ArrayList<ReciptGoodsPO>(); 
		for(int i = 0; i < giftList.size(); i++) {
			ReciptGoodsPO tempPO = new ReciptGoodsPO(giftList.get(i));
			this.giftList.add(tempPO);
		}
	}

	public double getReducePrice() {
		return reducePrice;
	}

	public void setReducePrice(double reducePrice) {
		this.reducePrice = reducePrice;
	}

	public ArrayList<ReciptGoodsPO> getGiftList() {
		return giftList;
	}

	public void setGiftList(List<ReciptGoodsPO> giftList) {
		this.giftList = new ArrayList<ReciptGoodsPO>(giftList);
	}

}
