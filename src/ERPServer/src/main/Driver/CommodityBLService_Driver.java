package main.Driver;

import java.rmi.RemoteException;

import main.BussinessLogicService.CommodityBLService.CommodityBLService;

public class CommodityBLService_Driver {
	
	public void drive(CommodityBLService service) throws RemoteException {
		service.viewCommodity("20180101", "20180202");
		service.checkCommodityInfoToExcel("test", "");
	}
}
