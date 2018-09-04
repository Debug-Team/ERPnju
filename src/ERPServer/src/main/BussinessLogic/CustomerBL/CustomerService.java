package main.BussinessLogic.CustomerBL;

import java.util.ArrayList;

import main.PO.CustomerPO;
import main.VO.CustomerVO;
import main.utility.ResultMessage;

public interface CustomerService {
	/**
	 * 通过客户名找到customer对象
	 * @param customerName
	 * @return customerPO
	 */
	public CustomerPO findCustomer(String customerName);
	
	/**
	 * 通过类型和关键字查找客户
	 * @param keyword 关键字
	 * @param type 查找类型
	 * @return ArrayList<CustomerVO>
	 */
	public ArrayList<CustomerVO> customerFind(String keyword, String type);

	/**
	 * 通过单据类型和金钱搞懂客户应收应付
	 * @param customerName
	 * @param type
	 * @param money
	 * @return ResultMessage
	 */
	public ResultMessage updateCustomer(String customerName, String type, double money);
	
}
