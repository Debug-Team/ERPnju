package main.Stub;

import java.rmi.RemoteException;
import java.util.ArrayList;

import main.BussinessLogicService.CommodityBLService.GoodsCatagoryBLService;
import main.VO.CategoryVO;
import main.utility.ResultMessage;

public class GoodsCategory_Stub implements GoodsCatagoryBLService{

	@Override
	public ResultMessage catagoryAdd(CategoryVO vo) throws RemoteException {
		// TODO Auto-generated method stub
		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage catagoryDelete(CategoryVO vo) throws RemoteException {
		// TODO Auto-generated method stub
		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage catagoryModify(CategoryVO vo) throws RemoteException {
		// TODO Auto-generated method stub
		return ResultMessage.SUCCESS;
	}

	@Override
	public ArrayList<CategoryVO> categoryAll() throws RemoteException {
		// TODO Auto-generated method stub
		return new ArrayList<>();
	}

}
