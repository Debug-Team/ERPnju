package main.BussinessLogicService.CommodityBLService;

import java.rmi.Remote;
import java.rmi.RemoteException;

import main.VO.CommodityReciptVO;
import main.utility.ResultMessage;

public interface CommodityReciptBLService extends Remote{
	/**
	 * 添加库存赠送单
	 * @param vo 库存单vo
	 * @return resultmessage
	 * @throws RemoteException
	 */
	public ResultMessage setGiftList(CommodityReciptVO vo) throws RemoteException;
	
	/**
	 * 添加库存报溢单
	 * @param vo 库存单vo
	 * @return resultmessage
	 * @throws RemoteException
	 */
	public ResultMessage setOverflowList(CommodityReciptVO vo) throws RemoteException;
	
	/**
	 * 添加库存报损单
	 * @param vo 库存单vo
	 * @return resultmessage
	 * @throws RemoteException
	 */
	public ResultMessage setDamageList(CommodityReciptVO vo) throws RemoteException;
	
	/**
	 * 添加库存报警单
	 * @param vo 库存单vo
	 * @return resultmessage
	 * @throws RemoteException
	 */
	public ResultMessage setWarningList(CommodityReciptVO vo) throws RemoteException;
}
