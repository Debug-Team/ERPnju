package main.BussinessLogicService.PromotionBLService;

import java.util.*;
import java.rmi.Remote;
import java.rmi.RemoteException;
import main.VO.*;
import main.utility.PromotionType;
import main.utility.ResultMessage;

/**
 * @author Cauchy不是你
 */
public interface PromotionBLService extends Remote {
	
	/**
	 * 设置促销策略
	 * @param vo
	 * @param operator
	 * @return ResultMessage
	 * @throws RemoteException
	 */
	public ResultMessage setPromotion(PromotionVO vo, String operator) throws RemoteException;
	
	/**
	 * 根据id获取促销策略
	 * @param promotionID
	 * @return PromotionVO
	 * @throws RemoteException
	 */
	public PromotionVO getPromotion(String promotionID) throws RemoteException;
	
	/**
	 * 根据类型查找促销列表
	 * @param type
	 * @return ArrayList<PromotionVO>
	 * @throws RemoteException
	 */
	public ArrayList<PromotionVO> getPromotionList(PromotionType type) throws RemoteException;
	
	/**
	 * 批量删除促销策略方法
	 * @param promotionIDs
	 * @param operator
	 * @return ResultMessage
	 * @throws RemoteException
	 */
	public ResultMessage deletePromotions(ArrayList<String> promotionIDs, String operator) throws RemoteException;
	
	/**
	 * 修改促销策略
	 * @param vo
	 * @param operator
	 * @return ResultMessage
	 * @throws RemoteException
	 */
	public ResultMessage modifyPromotion(PromotionVO vo, String operator) throws RemoteException;
	
	/**
	 * 商品模糊查找方法
	 * @param condition
	 * @param part
	 * @return ArrayList<GoodsVO>
	 * @throws RemoteException
	 */
	public ArrayList<GoodsVO> getGoods (String condition, String part) throws RemoteException;
	
}
