package main.Stub;

import java.rmi.RemoteException;
import java.util.ArrayList;

import main.BussinessLogicService.CommodityBLService.GoodsBLService;
import main.VO.GoodsVO;
import main.utility.ResultMessage;

public class GoodsBL_Stub implements GoodsBLService{

	@Override
	public ResultMessage goodsAdd(GoodsVO vo) throws RemoteException {
		// TODO Auto-generated method stub
		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage goodsDelete(GoodsVO vo) throws RemoteException {
		// TODO Auto-generated method stub
		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage goodsModify(GoodsVO vo) throws RemoteException {
		// TODO Auto-generated method stub
		return ResultMessage.SUCCESS;
	}

	@Override
	public ArrayList<GoodsVO> goodsFind(String keyword, String type) throws RemoteException {
		// TODO Auto-generated method stub
		return new ArrayList<>();
	}

	@Override
	public String getNextGoodsId(String category) throws RemoteException {
		// TODO Auto-generated method stub
		return "nextGoodsID";
	}

	@Override
	public boolean couldBeDeleted(String goodID) throws RemoteException {
		// TODO Auto-generated method stub
		return true;
	}

}
