package main.BussinessLogicService.ManifestBLService;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import main.VO.CustomerVO;
import main.VO.GoodsVO;
import main.VO.ManifestVO;
import main.utility.ResultMessage;

public interface ManifestBLService extends Remote{
	
	/**
	 * 设置进货单
	 * @param vo
	 * @return ResultMessage
	 * @throws RemoteException
	 */
	public ResultMessage setGoodsInList(ManifestVO vo) throws RemoteException;
	
	/**
	 * 设置进货退货单
	 * @param vo
	 * @return ResultMessage
	 * @throws RemoteException
	 */
	public ResultMessage setGoodsInReturnList(ManifestVO vo) throws RemoteException;
	
	/**
	 * 设置销售单
	 * @param vo
	 * @return ResultMessage
	 * @throws RemoteException
	 */
	public ResultMessage setSaleList(ManifestVO vo) throws RemoteException;
	
	/**
	 * 设置销售退货单
	 * @param vo
	 * @return ResultMessage
	 * @throws RemoteException
	 */
	public ResultMessage setSaleReturnList(ManifestVO vo) throws RemoteException;
	
	/**
	 * 得到当前操作员的素有manifests
	 * @param operator
	 * @return ArrayList<ManifestVO>
	 * @throws RemoteException
	 */
	public ArrayList<ManifestVO> getOperatorManifests(String operator) throws RemoteException;
	
	/**
	 * 删除manifest
	 * @param vo
	 * @return ResultMessage
	 * @throws RemoteException
	 */
	public ResultMessage deleteManifest(ManifestVO vo) throws RemoteException;
	
	/**
	 * 得到所有商品VO
	 * @return ArrayList<GoodsVO>
	 * @throws RemoteException
	 */
	public ArrayList<GoodsVO> getGoods() throws RemoteException;
	
	/**
	 * 通过类型得到下一个manifestID
	 * @param type
	 * @return String
	 * @throws RemoteException
	 */
	public String getNextManifestID(String type) throws RemoteException;
	
	/**
	 * 通过客户类型得到所有客户名字
	 * @param type
	 * @return ArrayList<CustomerVO>
	 * @throws RemoteException
	 */
	public ArrayList<CustomerVO> getAllCustomerName(String type) throws RemoteException;

	/**
	 * 修改单据
	 * @param vo
	 * @return ResultMessage
	 * @throws RemoteException
	 */
	public ResultMessage modifyManifest(ManifestVO vo) throws RemoteException;
}
