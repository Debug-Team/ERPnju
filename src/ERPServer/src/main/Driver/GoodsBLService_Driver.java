package main.Driver;

import java.rmi.RemoteException;

import main.BussinessLogicService.CommodityBLService.GoodsBLService;
import main.VO.GoodsVO;

public class GoodsBLService_Driver {
	public void drive(GoodsBLService service) throws RemoteException {
		GoodsVO vo = new GoodsVO(null, null);
		service.goodsAdd(vo);
		service.goodsDelete(vo);
		service.goodsFind("", "");
		service.goodsModify(vo);
	}
}
