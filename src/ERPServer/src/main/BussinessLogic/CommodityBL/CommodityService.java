package main.BussinessLogic.CommodityBL;

import java.util.ArrayList;

import main.PO.CommodityReciptPO;
import main.PO.GoodsPO;
import main.PO.ReciptGoodsPO;
import main.VO.CommodityReciptVO;
import main.VO.GoodsVO;
import main.utility.ResultMessage;

/**
 * 
 * @author 周益冰
 *
 */
public interface CommodityService {

	/**
	 * 按类型和关键字返回商品列表
	 * @param keyword
	 * @param type
	 * @return ArrayList<GoodsPO>
	 */
	public ArrayList<GoodsPO> getGoodsList(String keyword, String type);
	

	/**
	 * 返回所有商品列表
	 * @param all
	 * @return ArrayList<GoodsPO>
	 */
	public ArrayList<GoodsPO> getGoodsList(String all);
	
	/**
	 * 返回未审批库存单据
	 * @return ArrayList<CommodityReciptPO>
	 */
	public ArrayList<CommodityReciptPO> getUncheckedCommodityRecipt();
	
	/**
	 * 返回已审批库存单据
	 * 库存单据没有客户名和操作员，如两参数不为“” 则不返回库存单数据
	 * @param startTime
	 * @param endTime
	 * @param customerName
	 * @param operator
	 * @return ArrayList<CommodityReciptVO>
	 */
	public ArrayList<CommodityReciptVO> getCheckedCommodityRecipt(String startTime, String endTime,
			String customerName, String operator);
	
	/**
	 * 保存库存单List
	 * @param list
	 * @return ResultMessage
	 */
	public ResultMessage saveCommodityRecipt(ArrayList<CommodityReciptPO> list);
	
	/**
	 * 更新库存商品数量
	 * @param list 商品列表
	 * @param type 库存单类型
	 * @param lebal 单据状态标志
	 * @return ResultMessage
	 */
	public ResultMessage updateCommodity(ArrayList<ReciptGoodsPO> list, String type, String lebal);
	
	/**
	 * 建立赠送单
	 * @param giftlist 赠送商品列表
	 * @return ResultMessage
	 */
	public ResultMessage createGiftCommodityRecipt(ArrayList<ReciptGoodsPO> giftlist);
	
	/**
	 * 通过id查找商品
	 * @param id
	 * @return GoodsVO
	 */
	public GoodsVO findGoodsByID(String id);

}

