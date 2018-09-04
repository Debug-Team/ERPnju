package main.DataService.CustomerDataService;

import java.util.ArrayList;

import main.PO.CustomerPO;

public interface CustomerDataService {
	
	/**
	 * 添加客户
	 * @param po
	 * @return 操作是否成功
	 */
	public boolean customerAdd(CustomerPO po);
	
	/**
	 * 删除客户
	 * @param po
	 * @return 操作是否成功
	 */
	public boolean customerDelete(CustomerPO po);
	
	/**
	 * 修改客户
	 * @param po
	 * @return 操作是否成功
	 */
	public boolean customerModify(CustomerPO po);
	
	/**
	 * 查找客户
	 * @param id
	 * @return 通过id查找到的客户
	 */
	public CustomerPO customerFind(String id);
	
	/**
	 * 返回所有客户PO
	 * @return ArrayList<CustomerPO>
	 */
	public ArrayList<CustomerPO> customerAll();

	/**
	 * 通过关键字和类型查找客户
	 * @param keyword
	 * @param type
	 * @return ArrayList<CustomerPO> 符合条件的客户
	 */
	public ArrayList<CustomerPO> customerFind(String keyword, String type);

	/**
	 * 得到下一个用户属于客户编号的后半部分
	 * @param category
	 * @return 下一个用户属于客户编号的后半部分
	 */
	int getCustomerID(String category); 

}
