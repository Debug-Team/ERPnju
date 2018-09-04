package main.BussinessLogicService.BankAccountBLService;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.rmi.Remote;

import main.VO.BankAccountVO;
import main.utility.ResultMessage;

/**
 * @author Cauchy不是你
 */
public interface BankAccountBLService extends Remote {
	/**
	 * 添加银行账户
	 * @param vo
	 * @return ResultMessage
	 * @throws RemoteException
	 */
	public ResultMessage add (BankAccountVO vo) throws RemoteException;
	
	/**
	 * 根据id删除银行账户，并传入操作员
	 * @param id
	 * @param operator
	 * @return ResultMessage
	 * @throws RemoteException
	 */
	public ResultMessage delete (String id, String operator) throws RemoteException;
	
	/**
	 * 更改银行账户
	 * @param vo
	 * @return ResultMessage
	 * @throws RemoteException
	 */
	public ResultMessage modify (BankAccountVO vo) throws RemoteException;

	/**
	 * 根据id查找银行账户
	 * @param id
	 * @return BankAccountVO
	 * @throws RemoteException
	 */
	public BankAccountVO find (String id) throws RemoteException;
	
	/**
	 * 根据id模糊查找银行账户
	 * @param partId
	 * @return ArrayList<BankAccountVO>
	 * @throws RemoteException
	 */
	public ArrayList<BankAccountVO> partFind(String partId) throws RemoteException;
	
	/**
	 * 获取银行账户列表
	 * @return ArrayList<BankAccountVO>
	 * @throws RemoteException
	 */
	public ArrayList<BankAccountVO> getBankAccountList() throws RemoteException;
}
