package main.DataService.PromotionDataService;

import java.util.ArrayList;

import main.PO.PromotionPO;
import main.utility.PromotionType;
import main.utility.ResultMessage;

/**
 * @author Cauchy不是你
 */
public interface PromotionDataService {
	
	/**
	 * 插入新的优惠策略
	 * @param po
	 * @return ResultMessage
	 */
	public ResultMessage insert(PromotionPO po);
	
	/**
	 * 根据id删除优惠策略
	 * @param id
	 * @return ResultMessage
	 */
	public ResultMessage delete(String id);
	
	/**
	 * 更新用户策略信息
	 * @param po
	 * @return ResultMessage
	 */
	public ResultMessage update (PromotionPO po);
	
	/**
	 * 更具id查找优惠策略
	 * @param id
	 * @return PromotionPO
	 */
	public PromotionPO find(String id);
	
	/**
	 * 根据类型拉取促销策略列表
	 * @param type
	 * @return ArrayList<PromotionPO>
	 */
	public ArrayList<PromotionPO> getPromotionList(PromotionType type);
	
}
