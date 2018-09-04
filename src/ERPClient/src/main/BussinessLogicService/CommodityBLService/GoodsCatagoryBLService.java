package main.BussinessLogicService.CommodityBLService;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import main.VO.CategoryVO;
import main.utility.ResultMessage;

public interface GoodsCatagoryBLService extends Remote{
	/**
	 * 商品分类增加
	 * @param vo 分类vo
	 * @return resultmessage
	 * @throws RemoteException
	 */
	public ResultMessage catagoryAdd(CategoryVO vo) throws RemoteException;
	
	/**
	 * 商品分类删除
	 * @param vo 分类vo
	 * @return resultmessage
	 * @throws RemoteException
	 */
	public ResultMessage catagoryDelete(CategoryVO vo) throws RemoteException;
	
	/**
	 * 商品分类修改
	 * @param vo 分类vo
	 * @return resultmessage
	 * @throws RemoteException
	 */
	public ResultMessage catagoryModify(CategoryVO vo) throws RemoteException;
	
	/**
	 * 返回所有分类VO
	 * @return ArrayList<CategoryVO> 所有分类Vo
	 * @throws RemoteException
	 */
	public ArrayList<CategoryVO> categoryAll() throws RemoteException;
}
