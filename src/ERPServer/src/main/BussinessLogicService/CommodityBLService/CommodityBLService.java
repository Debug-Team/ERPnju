package main.BussinessLogicService.CommodityBLService;

import java.rmi.Remote;
import java.rmi.RemoteException;

import main.VO.CommodityInfoVO;
import main.utility.ResultMessage;

public interface CommodityBLService extends Remote{
	
	/**
	 * 库存查看
	 * @param startTime
	 * @param endTime
	 * @return commodityInfoVO 包含库存查看的4类信息
	 * @throws RemoteException
	 */
	public CommodityInfoVO viewCommodity(String startTime, String endTime) throws RemoteException;
	
	/**
	 * 将库存盘点信息导出Excel
	 * @param fileName
	 * @param storeAdress
	 * @return ResultMessage
	 * @throws RemoteException
	 */
	public ResultMessage checkCommodityInfoToExcel(String fileName, String storeAdress) throws RemoteException;
	
}
