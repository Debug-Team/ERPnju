package main.DataService.ManifestDataService;

import java.util.ArrayList;

import main.PO.ManifestPO;
import main.utility.ResultMessage;
/**
 * 
 * @author 周益冰
 *
 */
public interface ManifestDataService {
	/**
	 * 保存Manifest
	 * @param po
	 * @return boolean
	 */
	public boolean setManifest(ManifestPO po);
	
	/**
	 * 更新Manifest
	 * @param po
	 * @return boolean
	 */
	public boolean updateManifest(ManifestPO po);
	
	/**
	 * 下一个单据属于当日第几条的部分
	 * @param string
	 * @param date
	 * @return 下一个单据的int值
	 */
	public int newID(String string, String date);
	
	/**
	 * 通过关键字和类型查找Manifest
	 * @param keyword
	 * @param type
	 * @return ArrayList<ManifestPO>
	 */
	public ArrayList<ManifestPO> find(String keyword, String type);
	
	/**
	 * 修改manifests
	 * @param list
	 * @return resultmessage
	 */
	public ResultMessage modify(ArrayList<ManifestPO> list);
	
	/**
	 * 删除Manifest
	 * @param po
	 * @return boolean
	 */
	public boolean deleteManifest(ManifestPO po);
	
	/**
	 * 通过组合条件得到manifest
	 * @param startTime
	 * @param endTime
	 * @param customerName
	 * @param operator
	 * @return 
	 */
	public ArrayList<ManifestPO> getSalesHistoryList(String startTime, String endTime,
			String customerName,String operator);

	/**
	 * 修改manifestPO
	 * @param po
	 * @return ResultMessage
	 */
	public ResultMessage modifyManifest(ManifestPO po);
	
	/**
	 * 通过时间查找单据
	 * @param startTime
	 * @param endTime
	 * @return ArrayList<ManifestPO>
	 */
	public ArrayList<ManifestPO> findByTime(String startTime, String endTime);

}
