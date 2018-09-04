package main.DataService.CommodityDataService;

import java.util.ArrayList;

import main.PO.CommodityReciptPO;
import main.utility.ResultMessage;

public interface CommodityReciptDataService {
	/**
	 * 新增库存单据
	 * @param po
	 * @return resultmessage 操作是否成功
	 */
	public ResultMessage add(CommodityReciptPO po);

	/**
	 * 修改库存单据
	 * @param list 单据list
	 * @return 操作是否成功
	 */
	public ResultMessage modify(ArrayList<CommodityReciptPO> list);

	/**
	 * 通过关键字和类型查找库存单据
	 * @param keyword
	 * @param type
	 * @return ArrayList<CommodityReciptPO>
	 */
	public ArrayList<CommodityReciptPO> find(String keyword, String type);

	/**
	 * 通过时间、客户名、操作员组合查询库存单据
	 * @param startTime
	 * @param endTime
	 * @param customerName
	 * @param operator
	 * @return ArrayList<CommodityReciptPO>
	 */
	public ArrayList<CommodityReciptPO> find(String startTime, String endTime, String customerName, String operator);

}
