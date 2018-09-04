package main.Stub;

import java.rmi.RemoteException;
import java.util.ArrayList;

import main.BussinessLogicService.RecordBLService.RecordBLService;
import main.VO.CustomerVO;
import main.VO.GoodsVO;
import main.VO.InfoVO;
import main.VO.ReciptGoodsVO;
import main.VO.SaleStateVO;
import main.utility.DocumentsType;
import main.utility.ResultMessage;

public class Record_Stub implements RecordBLService {

	@Override
	public ArrayList<ReciptGoodsVO> getSalesDetailList(String startTime, String endTime, String goodsName,
			String customerName, String operator) throws RemoteException {
		// TODO Auto-generated method stub
		return new ArrayList<ReciptGoodsVO>();
	}

	@Override
	public InfoVO getSalesHistoryList(String startTime, String endTime, DocumentsType type, String customerName,
			String operator) throws RemoteException {
		// TODO Auto-generated method stub
		return new InfoVO();
	}

	@Override
	public SaleStateVO getSalesStateList(String startTime, String endTime) throws RemoteException {
		// TODO Auto-generated method stub
		return new SaleStateVO();
	}

	@Override
	public ArrayList<GoodsVO> getGoods(String condition, String part) throws RemoteException {
		// TODO Auto-generated method stub
		return new ArrayList<GoodsVO>();
	}

	@Override
	public ArrayList<CustomerVO> getCustomer(String condition, String part) throws RemoteException {
		// TODO Auto-generated method stub
		return new ArrayList<CustomerVO>();
	}

	@Override
	public boolean setRedDashed(String type, String id, String operator) throws RemoteException {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public ResultMessage saleStateToExcel(SaleStateVO vo, String fileName, String storeAdress) throws RemoteException {
		// TODO Auto-generated method stub
		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage salesDetailToExcel(ArrayList<ReciptGoodsVO> voList, String fileName, String storeAdress)
			throws RemoteException {
		// TODO Auto-generated method stub
		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage salesHistoryToExcel(InfoVO info, String fileName, String storeAdress) throws RemoteException {
		// TODO Auto-generated method stub
		return ResultMessage.SUCCESS;
	}

}
