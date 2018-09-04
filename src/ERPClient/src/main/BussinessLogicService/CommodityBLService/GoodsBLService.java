package main.BussinessLogicService.CommodityBLService;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import main.VO.GoodsVO;
import main.utility.ResultMessage;

public interface GoodsBLService extends Remote{
	/**
	 * 新增商品
	 * @param vo 商品vo
	 * @return resultmessage
	 * @throws RemoteException
	 */
	public ResultMessage goodsAdd(GoodsVO vo) throws RemoteException;
	
	/**
	 * 删除商品
	 * @param vo 商品vo
	 * @return resultmessage
	 * @throws RemoteException
	 */
	public ResultMessage goodsDelete(GoodsVO vo) throws RemoteException;
	
	/**
	 * 修改商品
	 * @param vo 商品vo
	 * @return resultmessage
	 * @throws RemoteException
	 */
	public ResultMessage goodsModify(GoodsVO vo) throws RemoteException;
	

	/**
	 * 查找商品
	 * @param keyword 关键字
	 * @param type 类型
	 * @return ArrayList<GoodsVO>
	 * @throws RemoteException
	 */
	public ArrayList<GoodsVO> goodsFind(String keyword, String type) throws RemoteException;
	
	/**
	 * 返回商品的下一个ID
	 * @param category商品种类
	 * @return string 商品ID
	 * @throws RemoteException
	 */
	public String getNextGoodsId(String category) throws RemoteException;
	
	/**
	 * 商品是否能被删除（是否有进货信息）
	 * @param goodID 商品ID
	 * @return boolean 商品是否能删除
	 * @throws RemoteException
	 */
	public boolean couldBeDeleted(String goodID) throws RemoteException;
	
}
