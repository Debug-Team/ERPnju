package main.BussinessLogicService.AccountBLService;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import main.VO.GoodsVO;
import main.utility.ResultMessage;
import main.VO.CustomerVO;
import main.VO.AccountVO;
import main.VO.BankAccountVO;

/**
 * @author Cauchy不是你
 */
public interface AccountBLService extends Remote {
	/**
	 * 设置期初信息
	 * @param vo
	 * @return ResultMessage
	 * @throws RemoteException
	 */
	public ResultMessage set (AccountVO vo) throws RemoteException;
	
	/**
	 * 更新期初信息
	 * @param vo
	 * @return ResultMessage
	 * @throws RemoteException
	 */
	public ResultMessage update (AccountVO vo) throws RemoteException;
	
	/**
	 * 商品模糊查找方法
	 * @param condition
	 * @param part
	 * @return ArrayList<GoodsVO>
	 * @throws RemoteException
	 */
	public ArrayList<GoodsVO> getGoods (String condition, String part) throws RemoteException;

	/**
	 * 客户模糊查找表
	 * @param condition
	 * @param part
	 * @return ArrayList<CustomerVO>
	 * @throws RemoteException
	 */
	public ArrayList<CustomerVO> getCustomer (String condition, String part) throws RemoteException;
	
	/**
	 * 银行账户模糊查找方法
	 * @param part
	 * @return ArrayList<BankAccountVO>
	 * @throws RemoteException
	 */
	public ArrayList<BankAccountVO> getBankAccount (String part) throws RemoteException;

	/**
	 * 获取期初信息
	 * @return AccountVO
	 * @throws RemoteException
	 */
	public AccountVO get () throws RemoteException;
}
