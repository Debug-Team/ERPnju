package main.BussinessLogic.ManifestBL;

import java.util.ArrayList;

import main.PO.ManifestPO;
import main.VO.ManifestVO;
import main.VO.ReciptGoodsVO;
import main.utility.DocumentsType;
import main.utility.ResultMessage;

public interface ManifestService {
	/**
	 * 得到所有未审批的单据
	 * @return ArrayList<ManifestPO>
	 */
	public ArrayList<ManifestPO> getUncheckedManifest();
	
	/**
	 * 得到所有已审批的单据
	 * @return ArrayList<ManifestPO>
	 */
	public ArrayList<ManifestPO> getCheckedManifest();
	
	/**
	 * 保存所有已审批的manifs
	 * @param list 已审批的manifest列表
	 * @return ResultMessage
	 */
	public ResultMessage saveManifest(ArrayList<ManifestPO> list);
	
	/**
	 * 通过创建时间查找单据
	 * @param startTime
	 * @param endTime
	 * @return 符合该时间段的单据
	 */
	public ArrayList<ManifestPO> findManifestByTime(String startTime, String endTime);
	
	/**
	 * 通过组合条件判断符合条件的销售单商品
	 * @param startTime
	 * @param endTime
	 * @param goodsName 商品名称
	 * @param customerName 客户名称
	 * @param operator 操作员
	 * @return ArrayList<ReciptGoodsVO>
	 */
	public ArrayList<ReciptGoodsVO> getSalesDetailList(String startTime, String endTime, 
			String goodsName, String customerName, String operator);
	
	/**
	 * 通过组合条件返回单据PO
	 * @param startTime
	 * @param endTime
	 * @param customerName 客户名称
	 * @param operator 操作员
	 * @param type 单据类型
	 * @return ArrayList<ManifestVO>
	 */
	public ArrayList<ManifestVO> getSalesHistoryList(String startTime, String endTime,
			String customerName, String operator, DocumentsType type);
	
	/**
	 * 设置红冲单据
	 * @param type 红冲单据类型
	 * @param id 红冲单据编号
	 * @return boolean
	 */
	public boolean setRedDashed(String type, String id);
}

