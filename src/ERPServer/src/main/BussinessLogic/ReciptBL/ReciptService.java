package main.BussinessLogic.ReciptBL;

import java.util.ArrayList;

import main.PO.CashBillPO;
import main.PO.CollectionOrderPO;
import main.PO.PaymentOrderPO;
import main.VO.CashBillVO;
import main.VO.CollectionOrderVO;
import main.VO.PaymentOrderVO;
import main.VO.ReciptVO;
import main.utility.ResultMessage;

public interface ReciptService {
	/**
	 * 同层提供查找未审批收款单方法
	 * @return ArrayList<CollectionOrderPO>
	 */
	public ArrayList<CollectionOrderPO> getUncheckedCollectionOrder();
	
	/**
	 * 同层提供保存收款单方法
	 * @param list
	 * @return ResultMessage
	 */
	public ResultMessage saveCollectionOrder(ArrayList<CollectionOrderPO> list);
	
	/**
	 * 同层提供查找未审批付款单方法
	 * @return ArrayList<PaymentOrderPO>
	 */
	public ArrayList<PaymentOrderPO> getUncheckedPaymentOrder();
	
	/**
	 * 同层提供保存付款单方法
	 * @param list
	 * @return ResultMessage
	 */
	public ResultMessage savePaymentOrder(ArrayList<PaymentOrderPO> list);
	
	/**
	 * 同层提供查找未审批现金复用单方法
	 * @return ArrayList<CashBillPO>
	 */
	public ArrayList<CashBillPO> getUncheckedCashBillPO();
	
	/**
	 * 同层提供保存现金复用单方法
	 * @param list
	 * @return ResultMessage
	 */
	public ResultMessage saveCashBillPO(ArrayList<CashBillPO> list);
	
	/**
	 * 同层提供获取已审批单据方法
	 * @param startTime
	 * @param endTime
	 * @param customerName
	 * @param operator
	 * @return ArrayList<ReciptVO>
	 */
	public ArrayList<ReciptVO> getCheckedReciptList(String startTime, String endTime, 
			String customerName, String operator);
	
	/**
	 * 同层提供获取已审批付款单方法
	 * @param startTime
	 * @param endTime
	 * @param customerName
	 * @param operator
	 * @return ArrayList<CollectionOrderVO>
	 */
	public ArrayList<CollectionOrderVO> getCheckedCollectionOrderList(String startTime, String endTime, 
			String customerName, String operator);
	
	/**
	 * 同层提供获取已审批付款单方法
	 * @param startTime
	 * @param endTime
	 * @param customerName
	 * @param operator
	 * @return ArrayList<PaymentOrderVO>
	 */
	public ArrayList<PaymentOrderVO> getCheckedPaymentOrderList(String startTime, String endTime, 
			String customerName, String operator);
	
	/**
	 * 同层提供获取已审批现金复用单方法
	 * @param startTime
	 * @param endTime
	 * @param customerName
	 * @param operator
	 * @return ArrayList<CashBillVO>
	 */
	public ArrayList<CashBillVO> getCheckedCashBillList(String startTime, String endTime, 
			String customerName, String operator);
	
	/**
	 * 设置红冲
	 * @param type
	 * @param id
	 * @return boolean
	 */
	public boolean setRedDashed(String type, String id);
}
