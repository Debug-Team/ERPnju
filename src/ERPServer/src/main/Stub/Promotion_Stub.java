package main.Stub;

import java.rmi.RemoteException;
import java.util.ArrayList;

import main.BussinessLogicService.PromotionBLService.PromotionBLService;
import main.VO.GoodsVO;
import main.VO.PromotionVO;
import main.utility.PromotionType;
import main.utility.ResultMessage;

public class Promotion_Stub implements PromotionBLService {

	@Override
	public ResultMessage setPromotion(PromotionVO vo, String operator) throws RemoteException {
		// TODO Auto-generated method stub
		return ResultMessage.SUCCESS;
	}

	@Override
	public PromotionVO getPromotion(String promotionID) throws RemoteException {
		// TODO Auto-generated method stub
		return new PromotionVO();
	}

	@Override
	public ArrayList<PromotionVO> getPromotionList(PromotionType type) throws RemoteException {
		// TODO Auto-generated method stub
		return new ArrayList<PromotionVO>();
	}

	@Override
	public ResultMessage deletePromotions(ArrayList<String> promotionIDs, String operator) throws RemoteException {
		// TODO Auto-generated method stub
		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage modifyPromotion(PromotionVO vo, String operator) throws RemoteException {
		// TODO Auto-generated method stub
		return ResultMessage.SUCCESS;
	}

	@Override
	public ArrayList<GoodsVO> getGoods(String condition, String part) throws RemoteException {
		// TODO Auto-generated method stub
		return new ArrayList<GoodsVO>();
	}

}
