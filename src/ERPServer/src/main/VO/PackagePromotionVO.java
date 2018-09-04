package main.VO;

import java.io.Serializable;
import java.util.ArrayList;

import main.PO.PackagePromotionPO;

public class PackagePromotionVO extends PromotionVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private double reducePrice;  //降价
	private ArrayList<ReciptGoodsVO> giftList;  //特价包列表
	
	public PackagePromotionVO() {}
	
	public PackagePromotionVO(PackagePromotionPO po) {
		super(po.getId(), po.getStartTime(), po.getEndTime());
		this.reducePrice = po.getReducePrice();
		
		this.giftList = new ArrayList<ReciptGoodsVO>();
		for(int i = 0; i < po.getGiftList().size(); i++) {
			ReciptGoodsVO tempVO = new ReciptGoodsVO(po.getGiftList().get(i));
			this.giftList.add(tempVO);
		}
	}
	
	public PackagePromotionVO(String startTime, String endTime, 
			double reducePrice, ArrayList<GoodsVO> giftList) {
		super("TJB", startTime, endTime);
		this.reducePrice = reducePrice;
		
		this.giftList = new ArrayList<ReciptGoodsVO>(); 
		for(int i = 0; i < giftList.size(); i++) {
			ReciptGoodsVO tempVO = new ReciptGoodsVO(giftList.get(i));
			this.giftList.add(tempVO);
		}
	}

	public double getReducePrice() {
		return reducePrice;
	}

	public void setReducePrice(double reducePrice) {
		this.reducePrice = reducePrice;
	}

	public ArrayList<ReciptGoodsVO> getGiftList() {
		return giftList;
	}

	public void setGiftList(ArrayList<ReciptGoodsVO> giftList) {
		this.giftList = new ArrayList<ReciptGoodsVO>(giftList);
	}
	
}
