package main.BussinessLogic.RecordBL;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import main.BussinessLogicService.RecordBLService.RecordBLService;
import main.VO.CustomerVO;
import main.VO.GoodsVO;
import main.VO.InfoVO;
import main.VO.ReciptGoodsVO;
import main.VO.SaleStateVO;
import main.utility.DocumentsType;
import main.utility.ResultMessage;

public class RecordBLController extends UnicastRemoteObject implements RecordBLService {

	public RecordBLController() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	RecordBL bl = new RecordBL();

	@Override
	public ArrayList<ReciptGoodsVO> getSalesDetailList(String startTime, String endTime, 
			String goodsName, String customerName, String operator) throws RemoteException {
		return bl.getSalesDetailList(startTime, endTime, goodsName, customerName, operator);
	}

	@Override
	public SaleStateVO getSalesStateList(String startTime, String endTime) throws RemoteException {
		return bl.getSalesStateList(startTime, endTime);
	}

	@Override
	public InfoVO getSalesHistoryList(String startTime, String endTime, 
			DocumentsType type, String customerName, String operator)
			throws RemoteException {
		return bl.getSalesHistoryList(startTime, endTime, type, customerName, operator);
	}

	@Override
	public ArrayList<GoodsVO> getGoods(String condition, String part) throws RemoteException {
		return bl.getGoods(condition, part);
	}

	@Override
	public ArrayList<CustomerVO> getCustomer(String condition, String part) throws RemoteException {
		return bl.getCustomer(condition, part);
	}

	@Override
	public boolean setRedDashed(String type, String id, String operator) throws RemoteException {
		return bl.setRedDashed(type, id, operator);
	}

	@Override
	public ResultMessage saleStateToExcel(SaleStateVO vo, String fileName, String storeAdress) throws RemoteException {
		return bl.saleStateToExcel(vo, fileName, storeAdress);
	}

	@Override
	public ResultMessage salesDetailToExcel(ArrayList<ReciptGoodsVO> voList, String fileName, String storeAdress)
			throws RemoteException {
		return bl.salesDetailToExcel(voList, fileName, storeAdress);
	}

	@Override
	public ResultMessage salesHistoryToExcel(InfoVO info, String fileName, String storeAdress) throws RemoteException {
		return bl.salesHistoryToExcel(info, fileName, storeAdress);
	}

}
