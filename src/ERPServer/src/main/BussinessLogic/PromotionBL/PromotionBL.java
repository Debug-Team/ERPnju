package main.BussinessLogic.PromotionBL;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import main.BussinessLogic.CommodityBL.CommodityBL;
import main.BussinessLogic.CommodityBL.CommodityService;
import main.Data.PromotionData.PromotionData;
import main.DataService.PromotionDataService.PromotionDataService;
import main.PO.CouponPO;
import main.PO.CustomerPO;
import main.PO.GoodsPO;
import main.PO.LevelPromotionPO;
import main.PO.PackagePromotionPO;
import main.PO.PromotionPO;
import main.PO.ReciptGoodsPO;
import main.PO.TotalPromotionPO;
import main.VO.GoodsVO;
import main.VO.LevelPromotionVO;
import main.VO.PackagePromotionVO;
import main.VO.PromotionVO;
import main.VO.TotalPromotionVO;
import main.utility.PromotionResult;
import main.utility.PromotionType;
import main.utility.ResultMessage;

public class PromotionBL {
	
	PromotionDataService pds = new PromotionData();

	public ResultMessage setPromotion(PromotionVO vo) {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");  //设置日期格式
		String date = df.format(new Date());  // new Date()为获取当前系统时间
		
		String type = vo.getId();  //传下来的时候只有种类，没有编号
		String id = type + "-" + date;  //自动生成编号
		vo.setId(id);
		
		//特价包
		if(type.equals("TJB"))
			pds.insert(new PackagePromotionPO((PackagePromotionVO)vo));
		//总价
		else if(type.equals("ZJ")) 
			pds.insert(new TotalPromotionPO((TotalPromotionVO)vo));
		//级别
		else if(type.equals("JB")) 
			pds.insert(new LevelPromotionPO((LevelPromotionVO)vo));
		
		return ResultMessage.SUCCESS;
	}
	
	public PromotionVO getPromotion(String promotionID) {
		
		if(pds.find(promotionID) != null) {
			String type = promotionID.split("-")[0];
			//代金券
			if(type.equals("TJB"))
				return new PackagePromotionVO((PackagePromotionPO)pds.find(promotionID));
			//总额赠品
			else if(type.equals("ZJ")) 
				return new TotalPromotionVO((TotalPromotionPO)pds.find(promotionID));
			//赠品
			else if(type.equals("JB")) 
				return new LevelPromotionVO((LevelPromotionPO)pds.find(promotionID));
		}
		
		return null;
	}

	public ArrayList<PromotionVO> getPromotionList(PromotionType type) {
		ArrayList<PromotionPO> temp = pds.getPromotionList(type);
		ArrayList<PromotionVO> result = new ArrayList<PromotionVO>();
		for(int i = 0; i < temp.size(); i++) {
			PromotionPO po = temp.get(i);
			String cmd = po.getId().split("-")[0];
			if(cmd.equals("TJB")) {
				PackagePromotionVO vo = new PackagePromotionVO((PackagePromotionPO)po);
				result.add(vo);
			}
			else if(cmd.equals("ZJ")) {
				TotalPromotionVO vo = new TotalPromotionVO((TotalPromotionPO)po);
				result.add(vo);
			}
			else if(cmd.equals("JB")) {
				LevelPromotionVO vo = new LevelPromotionVO((LevelPromotionPO)po);
				result.add(vo);
			}
		}
		return result;
	}

	public ResultMessage deletePromotions(ArrayList<String> promotionIDs) {
		for(int i = 0; i < promotionIDs.size(); i++) {
			if(pds.find(promotionIDs.get(i)) != null)
				pds.delete(promotionIDs.get(i));
		}
		return ResultMessage.SUCCESS;
	}

	public ResultMessage modifyPromotion(PromotionVO vo) {
		if(pds.find(vo.getId()) != null) {
			String cmd = vo.getId().split("-")[0];
			if(cmd.equals("TJB")) {
				PackagePromotionPO po = new PackagePromotionPO((PackagePromotionVO)vo);
				pds.update(po);
			}
			else if(cmd.equals("ZJ")) {
				TotalPromotionPO po = new TotalPromotionPO((TotalPromotionVO)vo);
				pds.update(po);
			}
			else if(cmd.equals("JB")) {
				LevelPromotionPO po = new LevelPromotionPO((LevelPromotionVO)vo);
				pds.update(po);
			}
			else 
				return ResultMessage.FAIL;
			
			return ResultMessage.SUCCESS;
		}
		else
			return ResultMessage.FAIL;
	}
	
	public ArrayList<GoodsVO> getGoods(String condition, String part) throws RemoteException {
		CommodityService cs = new CommodityBL();
		ArrayList<GoodsPO> goodsList = cs.getGoodsList(part, condition);
		ArrayList<GoodsVO> result = new ArrayList<GoodsVO>();
		for(int i = 0; i < goodsList.size(); i++) {
			GoodsVO vo = new GoodsVO(goodsList.get(i));
			result.add(vo);
		}
		return result;
	}
	
	public PromotionResult getAppropriatePromotion(ArrayList<ReciptGoodsPO> reciptGoodsList, 
			CustomerPO customer) {
		PromotionResult result = new PromotionResult();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");  //设置日期格式
		String currentTime = df.format(new Date());  //获取当前系统时间
		//计算总消费
		double totalConsumption = 0;
		for(int i = 0; i < reciptGoodsList.size(); i++) {
			totalConsumption += reciptGoodsList.get(i).getSum();
		}
		//获取合适的特价包优惠
		ArrayList<PackagePromotionPO> suitTjb = new ArrayList<PackagePromotionPO>();
		ArrayList<PromotionPO> tjb = pds.getPromotionList(PromotionType.PACKAGE);
		for(int i = 0; i <tjb.size(); i++) {
			if((currentTime.compareTo(tjb.get(i).getStartTime()) >= 0 
					&& currentTime.compareTo(tjb.get(i).getEndTime()) <= 0)) {
				//匹配特价包
				PackagePromotionPO po = (PackagePromotionPO)tjb.get(i);
				ArrayList<ReciptGoodsPO> pack = po.getGiftList();
				ArrayList<ReciptGoodsPO> temp = reciptGoodsList;
				for(int p = 0; p < pack.size(); p++) {
					for(int j = 0; j < temp.size(); j++) {
						if(temp.get(j).getGoodsID().equals(pack.get(p).getGoodsID())) {
							pack.remove(pack.get(p));
							temp.remove(temp.get(j));
							p--; j--;
							break;
						}
					}
				}
				if(pack.isEmpty()) {
					suitTjb.add(po);
				}
			}
		}
		//处理特价包优惠（如果有的话）
		if(suitTjb.size() != 0) {
			for(int i = 0; i < suitTjb.size(); i++) {
				totalConsumption -= suitTjb.get(i).getReducePrice();
			}
		}
		//获取合适的特价包优惠
		ArrayList<TotalPromotionPO> suitZj = new ArrayList<TotalPromotionPO>();
		ArrayList<PromotionPO> zj = pds.getPromotionList(PromotionType.TOTAL);
		for(int i = 0; i <zj.size(); i++) {
			if(currentTime.compareTo(zj.get(i).getStartTime()) >= 0 
					&& currentTime.compareTo(zj.get(i).getEndTime()) <= 0) {
				TotalPromotionPO po = (TotalPromotionPO)zj.get(i);
				if(totalConsumption >= po.getTotalNumber()) {
					suitZj.add(po);
				}
			}
		}
		//处理总价优惠（如果有的话）
		if(suitZj.size() != 0) {
			//排序，选出默认优惠力度最大的（即限额最高的）
			Collections.sort(suitZj, new Comparator<TotalPromotionPO>() {  
			    @Override  
			    public int compare(TotalPromotionPO a, TotalPromotionPO b) {  
			    	if(a.getTotalNumber() > b.getTotalNumber()) return -1;
			    	else return 1;
			    }  
			});
			TotalPromotionPO fianlZj = suitZj.get(0);
			//赠与代金券
			ArrayList<CouponPO> couponList = customer.getCouponList();
			couponList.addAll(fianlZj.getCouponList());
			customer.setCouponList(couponList);
			//赠与赠品
			ArrayList<ReciptGoodsPO> giftList = result.getGiftList();
			giftList.addAll(fianlZj.getGiftList());
			result.setGiftList(giftList);
		}
		
		//获取合适的级别优惠
		ArrayList<LevelPromotionPO> suitJb = new ArrayList<LevelPromotionPO>();
		ArrayList<PromotionPO> jb = pds.getPromotionList(PromotionType.LEVEL);
		for(int i = 0; i <jb.size(); i++) {
			if(currentTime.compareTo(jb.get(i).getStartTime()) >= 0 
					&& currentTime.compareTo(jb.get(i).getEndTime()) <= 0) {
				LevelPromotionPO po = (LevelPromotionPO)jb.get(i);
				if(po.getLevel().equals(customer.getLevel())) {
					suitJb.add(po);
				}
			}
		}
		//处理级别优惠（如果有的话）
		if(suitJb.size() != 0) {
			//排序，选出默认优惠力度最大的（即按从高到低依次以 折扣、赠品数量、代金券数量优先顺序排序）
			Collections.sort(suitJb, new Comparator<LevelPromotionPO>() {  
			    @Override  
			    public int compare(LevelPromotionPO a, LevelPromotionPO b) {  
			    	if(a.getDiscount() > b.getDiscount()) return -1;
			    	else if(a.getDiscount() < b.getDiscount()) return 1;
			    	else {
			    		if(a.getGiftList().size() > b.getGiftList().size()) return -1;
			    		else if(a.getGiftList().size() < b.getGiftList().size()) return 1;
			    		else {
			    			if(a.getCouponList().size() > b.getCouponList().size()) return -1;
				    		else return 1;
			    		}
			    	}
			    }  
			});
			LevelPromotionPO fianlJb = suitJb.get(0);
			//给与总价格优惠
			totalConsumption *= fianlJb.getDiscount();
			//赠与代金券
			ArrayList<CouponPO> couponList = customer.getCouponList();
//			couponList.addAll(fianlJb.getCouponList());
			for(int i=0;i<fianlJb.getCouponList().size();i++){
				couponList.add(fianlJb.getCouponList().get(i));
			}
			customer.setCouponList(couponList);
			//赠与赠品
			ArrayList<ReciptGoodsPO> giftList = result.getGiftList();
//			giftList.addAll(fianlJb.getGiftList());
			for(int i=0;i<fianlJb.getGiftList().size();i++){
				giftList.add(fianlJb.getGiftList().get(i));
			}
			result.setGiftList(giftList);
		}
		//最后将总价加入结果中
		result.setAmount(totalConsumption);
		
		return result;
	}
	
}
