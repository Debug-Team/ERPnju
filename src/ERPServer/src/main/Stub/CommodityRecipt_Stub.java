package main.Stub;

import java.rmi.RemoteException;

import main.BussinessLogicService.CommodityBLService.CommodityBLService;
import main.VO.CommodityInfoVO;
import main.utility.ResultMessage;

public class CommodityRecipt_Stub implements CommodityBLService{

	@Override
	public CommodityInfoVO viewCommodity(String startTime, String endTime) throws RemoteException {
		// TODO Auto-generated method stub
		return new CommodityInfoVO();
	}

	@Override
	public ResultMessage checkCommodityInfoToExcel(String fileName, String storeAdress) throws RemoteException {
		// TODO Auto-generated method stub
		return ResultMessage.SUCCESS;
	}

}
