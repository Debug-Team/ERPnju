package main.BussinessLogic.PromotionBL;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import main.BussinessLogic.LogBL.LogBL;
import main.BussinessLogicService.PromotionBLService.PromotionBLService;
import main.PO.CustomerPO;
import main.PO.ReciptGoodsPO;
import main.VO.GoodsVO;
import main.VO.PromotionVO;
import main.utility.PromotionResult;
import main.utility.PromotionType;
import main.utility.ResultMessage;

public class PromotionBLController extends UnicastRemoteObject implements PromotionBLService, PromotionService {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PromotionBLController() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	PromotionBL bl = new PromotionBL();
	LogBL logBl = new LogBL();

	@Override
	public ResultMessage setPromotion(PromotionVO vo, String operator) throws RemoteException {
		logBl.createLog(operator, "设定了新的促销策略");
		return bl.setPromotion(vo);
	}

	@Override
	public PromotionVO getPromotion(String promotionID) throws RemoteException {
		return bl.getPromotion(promotionID);
	}

	@Override
	public ArrayList<PromotionVO> getPromotionList(PromotionType type) throws RemoteException {
		return bl.getPromotionList(type);
	}

	@Override
	public ResultMessage deletePromotions(ArrayList<String> promotionIDs, String operator) throws RemoteException {
		logBl.createLog(operator, "删除了部分促销策略");
		return bl.deletePromotions(promotionIDs);
	}

	@Override
	public ResultMessage modifyPromotion(PromotionVO vo, String operator) throws RemoteException {
		logBl.createLog(operator, "更改了促销策略");
		return bl.modifyPromotion(vo);
	}

	@Override
	public PromotionResult getAppropriatePromotion(ArrayList<ReciptGoodsPO> reciptGoodsList, CustomerPO customerList) {
		return bl.getAppropriatePromotion(reciptGoodsList, customerList);
	}

	@Override
	public ArrayList<GoodsVO> getGoods(String condition, String part) throws RemoteException {
		return bl.getGoods(condition, part);
	}

}
