package main.DataService.CommodityDataService;

import java.util.ArrayList;

import com.aliyuncs.exceptions.ClientException;

import main.PO.GoodsPO;
import main.PO.ReciptGoodsPO;
import main.utility.ResultMessage;

public interface GoodsDataService {
	/**
	 * 新增商品
	 * @param po
	 * @return 返回操作是否成功
	 */
	public boolean add(GoodsPO po);
	
	/**
	 * 删除商品
	 * @param po
	 * @return 返回操作是否成功
	 */
	public boolean delete(GoodsPO po);
	
	/**
	 * 修改商品
	 * @param po
	 * @return 返回操作是否成功
	 */
	public boolean modify(GoodsPO po);
	
	/**
	 * 通过关键字和类型查找商品
	 * @param keyword
	 * @param type
	 * @return ArrayList<GoodsPO>
	 */
	public ArrayList<GoodsPO> find(String keyword, String type);

	/**
	 * 更新库存商品数量，最近进售价，库存均价等信息
	 * @param list
	 * @param type 单据类型
	 * @param lebal 状态
	 * @return resultmessage
	 */
	public ResultMessage updateCommodity(ArrayList<ReciptGoodsPO> list, String type, String lebal);

	/**
	 * 下一个新商品属于id的后半部分，即在该分类下的添加次序
	 * @param string
	 * @return 下一个商品id的后半部分
	 */
	int newID(String string);

	/**
	 * 发送警戒数量信息给仓库管理人员提醒进货
	 * @param po
	 * @throws ClientException
	 */
	void sentAlertMessage(GoodsPO po) throws ClientException;
}
