package main.DataService.ReciptDataService;

import java.util.ArrayList;

import main.PO.CashBillPO;
import main.PO.CollectionOrderPO;
import main.PO.PaymentOrderPO;
import main.PO.ReciptPO;
import main.utility.ResultMessage;

/**
 * @author Cauchy不是你
 */
public interface ReciptDataService {
	
	/**
	 * 添加收款单
	 * @param po
	 * @return ResultMessage
	 */
	public ResultMessage addCollectionOrder (CollectionOrderPO po);

	/**
	 * 添加付款单
	 * @param po
	 * @return ResultMessage
	 */
	public ResultMessage addPaymentOrder (PaymentOrderPO po);

	/**
	 * 添加现金复用单
	 * @param po
	 * @return ResultMessage
	 */
	public ResultMessage addCashBill (CashBillPO po);
	
	/**
	 * 更新收款单
	 * @param po
	 * @return ResultMessage
	 */
	public ResultMessage updateCollectionOrder (CollectionOrderPO po);

	/**
	 * 更新付款单
	 * @param po
	 * @return ResultMessage
	 */
	public ResultMessage updatePaymentOrder (PaymentOrderPO po);

	/**
	 * 更新现金复用单
	 * @param po
	 * @return ResultMessage
	 */
	public ResultMessage updateCashBill (CashBillPO po);
	
	/**
	 * 根据id获取一个收款单
	 * @param id
	 * @return CollectionOrderPO
	 */
	public CollectionOrderPO getACollectionOrder(String id);
	
	/**
	 * 根据id获取一个付款单
	 * @param id
	 * @return PaymentOrderPO
	 */
	public PaymentOrderPO getAPaymentOrder(String id);

	/**
	 * 根据id获取一个现金复用单
	 * @param id
	 * @return CashBillPO
	 */
	public CashBillPO getACashBill(String id);
	
	/**
	 * 根据类型（草稿、审批、未审批）查找收款单
	 * @param cmd
	 * @return ArrayList<CollectionOrderPO>
	 */
	public ArrayList<CollectionOrderPO> getCollectionOrderPOList(String cmd);
	
	/**
	 * 根据类型（草稿、审批、未审批）查找付款单
	 * @param cmd
	 * @return ArrayList<PaymentOrderPO>
	 */
	public ArrayList<PaymentOrderPO> getPaymentOrderPOList(String cmd);
	
	/**
	 * 根据类型（草稿、审批、未审批）查找现金复用单
	 * @param cmd
	 * @return ArrayList<CashBillPO>
	 */
	public ArrayList<CashBillPO> getCashBillPOList(String cmd);
	
	/**
	 * 根据条件筛选单据
	 * @param startTime
	 * @param endTime
	 * @param customerName
	 * @param operator
	 * @return ArrayList<ReciptPO>
	 */
	public ArrayList<ReciptPO> getCheckedReciptList(String startTime, String endTime, 
			String customerName, String operator);
	
	/**
	 * 根据条件筛选收款单
	 * @param startTime
	 * @param endTime
	 * @param customerName
	 * @param operator
	 * @return ArrayList<CollectionOrderPO>
	 */
	public ArrayList<CollectionOrderPO> getCheckedCollectionOrderList(String startTime, String endTime, 
			String customerName, String operator);
	
	/**
	 * 根据条件筛选付款单
	 * @param startTime
	 * @param endTime
	 * @param customerName
	 * @param operator
	 * @return ArrayList<PaymentOrderPO>
	 */
	public ArrayList<PaymentOrderPO> getCheckedPaymentOrderList(String startTime, String endTime, 
			String customerName, String operator);
	
	/**
	 * 根据条件筛选现金复用单
	 * @param startTime
	 * @param endTime
	 * @param customerName
	 * @param operator
	 * @return ArrayList<CashBillPO>
	 */
	public ArrayList<CashBillPO> getCheckedCashBillList(String startTime, String endTime, 
			String customerName, String operator);

	/**
	 * 根据id获取一个单据
	 * @param id
	 * @return ReciptPO
	 */ 
	public ReciptPO getOne (String id);
	
	/**
	 * 自动获取下一个新的系统自动生成id
	 * @param type
	 * @return int
	 */
	public int newId(String type);
	
	/**
	 * 获取所有的单据
	 * @return ArrayList<ReciptPO>
	 */
	public ArrayList<ReciptPO> getAllRecipts();

}
