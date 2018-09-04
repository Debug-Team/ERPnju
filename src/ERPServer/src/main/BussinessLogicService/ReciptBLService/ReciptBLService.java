package main.BussinessLogicService.ReciptBLService;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import main.VO.BankAccountVO;
import main.VO.CashBillVO;
import main.VO.CollectionOrderVO;
import main.VO.CustomerVO;
import main.VO.PaymentOrderVO;
import main.VO.ReciptVO;
import main.utility.ResultMessage;

/**
 * @author Cauchy不是你
 */
public interface ReciptBLService extends Remote {
	/**
	 * 设置收款单
	 * @param vo
	 * @return ResultMessage
	 * @throws RemoteException
	 */
	public ResultMessage setCollection (CollectionOrderVO vo) throws RemoteException;
	
	/**
	 * 设置付款单
	 * @param vo
	 * @return ResultMessage
	 * @throws RemoteException
	 */
	public ResultMessage setPaymentOrder (PaymentOrderVO vo) throws RemoteException;
	
	/**
	 * 设置现金复用单
	 * @param vo
	 * @return ResultMessage
	 * @throws RemoteException
	 */
	public ResultMessage setCashBill (CashBillVO vo) throws RemoteException;	
	
	/**
	 * 根据id查找单据
	 * @param id
	 * @return ReciptVO
	 * @throws RemoteException
	 */
	public ReciptVO findOne (String id) throws RemoteException;
	
	/**
	 * 银行账户模糊查找方法
	 * @param part
	 * @return ArrayList<BankAccountVO>
	 * @throws RemoteException
	 */
	public ArrayList<BankAccountVO> getBankAccount(String part) throws RemoteException;
	
	/**
	 * 客户模糊查找方法
	 * @param keyword
	 * @param customerName
	 * @return ArrayList<CustomerVO>
	 * @throws RemoteException
	 */
	public ArrayList<CustomerVO> getCustomerList(String keyword, String customerName) throws RemoteException;
	
	/**
	 * 获取所有单据列表
	 * @return ArrayList<ReciptVO>
	 * @throws RemoteException
	 */
	public ArrayList<ReciptVO> getAllRecipts() throws RemoteException;
	
	/**
	 * 获取所有的收款单
	 * @return ArrayList<CollectionOrderVO>
	 * @throws RemoteException
	 */
	public ArrayList<CollectionOrderVO> getAllCollectionOrderList() throws RemoteException;
	
	/**
	 * 获取所有的付款单
	 * @return ArrayList<PaymentOrderVO>
	 * @throws RemoteException
	 */
	public ArrayList<PaymentOrderVO> getAllPaymentOrderList() throws RemoteException;
	
	/**
	 * 获取所有的现金复用单
	 * @return ArrayList<CashBillVO>
	 * @throws RemoteException
	 */
	public ArrayList<CashBillVO> getAllCashBillList() throws RemoteException;
}
