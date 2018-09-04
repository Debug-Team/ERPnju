package main.BussinessLogic.ReciptBL;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import main.BussinessLogic.LogBL.LogBL;
import main.BussinessLogicService.ReciptBLService.ReciptBLService;
import main.VO.BankAccountVO;
import main.VO.CashBillVO;
import main.VO.CollectionOrderVO;
import main.VO.CustomerVO;
import main.VO.PaymentOrderVO;
import main.VO.ReciptVO;
import main.utility.ResultMessage;

public class ReciptBLController extends UnicastRemoteObject implements ReciptBLService {
	
	public ReciptBLController() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ReciptBL bl = new ReciptBL();
	LogBL logBl = new LogBL();

	@Override
	public ResultMessage setCollection(CollectionOrderVO vo) throws RemoteException {
		logBl.createLog(vo.getOperator(), "设置了收款单");
		return bl.setCollection(vo);
	}

	@Override
	public ResultMessage setPaymentOrder(PaymentOrderVO vo) throws RemoteException {
		logBl.createLog(vo.getOperator(), "设置了付款单");
		return bl.setPaymentOrder(vo);
	}

	@Override
	public ResultMessage setCashBill(CashBillVO vo) throws RemoteException {
		logBl.createLog(vo.getOperator(), "设置了现金复用单");
		return bl.setCashBill(vo);
	}

	@Override
	public ReciptVO findOne(String id) throws RemoteException {
		return bl.findOne(id);
	}

	@Override
	public ArrayList<BankAccountVO> getBankAccount(String part) throws RemoteException {
		return bl.getBankAccount(part);
	}

	@Override
	public ArrayList<CustomerVO> getCustomerList(String keyword, String customerName) throws RemoteException {
		return bl.getCustomerList(keyword, customerName);
	}

	@Override
	public ArrayList<ReciptVO> getAllRecipts() throws RemoteException {
		return bl.getAllRecipts();
	}

	@Override
	public ArrayList<CollectionOrderVO> getAllCollectionOrderList() throws RemoteException {
		return bl.getAllCollectionOrderList();
	}

	@Override
	public ArrayList<PaymentOrderVO> getAllPaymentOrderList() throws RemoteException {
		return bl.getAllPaymentOrderList();
	}

	@Override
	public ArrayList<CashBillVO> getAllCashBillList() throws RemoteException {
		return bl.getAllCashBillList();
	}

}
