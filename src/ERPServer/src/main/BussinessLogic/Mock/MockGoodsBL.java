package main.BussinessLogic.Mock;

import java.rmi.RemoteException;
import java.util.ArrayList;

import main.BussinessLogic.CommodityBL.GoodsBL;
import main.VO.GoodsVO;

public class MockGoodsBL {
	
	protected MockGoodsBL() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}


	public boolean goodsAdd(GoodsVO vo){
		return true;
	}	


	public boolean goodsDelete(GoodsVO vo){
		return true;
	}

	public boolean goodsModify(GoodsVO vo){
		return true;
	}

	public ArrayList<GoodsVO> goodsFind(String keyword) {
		return new ArrayList<GoodsVO>();
	}
}
