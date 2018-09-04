package main.BussinessLogicService.CustomerBLService;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import main.VO.CustomerVO;
import main.utility.ResultMessage;
/**
 * 
 * @author 周益冰
 *
 */
public interface CustomerBLService extends Remote {
	
	/**
	 * 新增客户
	 * @param vo 客户vo
	 * @return ResultMessage
	 * @throws RemoteException
	 */
	public ResultMessage customerAdd(CustomerVO vo ) throws RemoteException;
	
	/**
	 * 删除客户
	 * @param vo 客户vo
	 * @return ResultMessage
	 * @throws RemoteException
	 */
	public ResultMessage customerDelete(CustomerVO vo ) throws RemoteException;
	
	/**
	 * 修改客户
	 * @param vo 客户vo
	 * @return ResultMessage
	 * @throws RemoteException
	 */
	public ResultMessage customerModify(CustomerVO vo ) throws RemoteException;
	
	/**
	 * 查找客户
	 * @param keyword 关键字
	 * @param type 类型
	 * @return ArrayList<CustomerVO>客户列表
	 * @throws RemoteException
	 */
	public ArrayList<CustomerVO> customerFind(String keyword, String type ) throws RemoteException;

	/**
	 * 得到下一个客户ID
	 * @param type
	 * @return string 客户id
	 * @throws RemoteException
	 */
	public String getNextCustomerID(String type ) throws RemoteException;
}
