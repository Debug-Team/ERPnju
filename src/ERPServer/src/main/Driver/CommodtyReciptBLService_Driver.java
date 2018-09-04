package main.Driver;

import java.rmi.RemoteException;

import main.BussinessLogicService.CommodityBLService.CommodityReciptBLService;
import main.VO.CommodityReciptVO;

public class CommodtyReciptBLService_Driver {
	public void drive(CommodityReciptBLService service) throws RemoteException {
		CommodityReciptVO vo = new CommodityReciptVO(null, null, null, 0, null, null, null);
		service.setDamageList(vo);
		service.setGiftList(vo);
		service.setOverflowList(vo);
		service.setWarningList(vo);
	}
}
