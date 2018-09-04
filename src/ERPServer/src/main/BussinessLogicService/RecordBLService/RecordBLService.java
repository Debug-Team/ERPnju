package main.BussinessLogicService.RecordBLService;

import java.util.ArrayList;
import java.rmi.Remote;
import java.rmi.RemoteException;

import main.VO.CustomerVO;
import main.VO.GoodsVO;
import main.VO.InfoVO;
import main.VO.ReciptGoodsVO;
import main.VO.SaleStateVO;
import main.utility.DocumentsType;
import main.utility.ResultMessage;

/**
 * @author Cauchy不是你
 */
public interface RecordBLService extends Remote {
	/**
	 * 获取销售明细表方法
	 * @param startTime
	 * @param endTime
	 * @param goodsName
	 * @param customerName
	 * @param operator
	 * @return ArrayList<ReciptGoodsVO>
	 * @throws RemoteException
	 */
	public ArrayList<ReciptGoodsVO> getSalesDetailList(String startTime, String endTime, 
			String goodsName, String customerName, String operator) throws RemoteException;
	
	/**
	 * 获取经营历程表
	 * @param startTime
	 * @param endTime
	 * @param type
	 * @param customerName
	 * @param operator
	 * @return InfoVO
	 * @throws RemoteException
	 */
	public InfoVO getSalesHistoryList(String startTime, String endTime, 
			DocumentsType type, String customerName, String operator) throws RemoteException;
	
	/**
	 * 获取销售状态表
	 * @param startTime
	 * @param endTime
	 * @return SaleStateVO
	 * @throws RemoteException
	 */
	public SaleStateVO getSalesStateList(String startTime, String endTime) throws RemoteException;
	
	/**
	 * 商品模糊查找方法
	 * @param condition
	 * @param part
	 * @return ArrayList<GoodsVO>
	 * @throws RemoteException
	 */
	public ArrayList<GoodsVO> getGoods (String condition, String part) throws RemoteException;

	/**
	 * 客户模糊查找方法
	 * @param condition
	 * @param part
	 * @return ArrayList<CustomerVO>
	 * @throws RemoteException
	 */
	public ArrayList<CustomerVO> getCustomer (String condition, String part) throws RemoteException;
	
	/**
	 * 设置红冲方法
	 * @param type
	 * @param id
	 * @param operator
	 * @return boolean
	 * @throws RemoteException
	 */
	public boolean setRedDashed(String type, String id, String operator) throws RemoteException;
	
	/**
	 * 提供经营情况表导出方法
	 * @param vo
	 * @param fileName
	 * @param storeAdress
	 * @return
	 * @throws RemoteException
	 */
	public ResultMessage saleStateToExcel(SaleStateVO vo, String fileName, String storeAdress) throws RemoteException;
	
	/**
	 * 提供销售明细表导出方法
	 * @param voList
	 * @param fileName
	 * @param storeAdress
	 * @return
	 * @throws RemoteException
	 */
	public ResultMessage salesDetailToExcel(ArrayList<ReciptGoodsVO> voList, String fileName, String storeAdress) throws RemoteException;
	
	/**
	 * 提供经营历程表导出方法
	 * @param info
	 * @param fileName
	 * @param storeAdress
	 * @return
	 * @throws RemoteException
	 */
	public ResultMessage salesHistoryToExcel(InfoVO info, String fileName, String storeAdress) throws RemoteException;
}
