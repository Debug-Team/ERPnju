package main.Stub;

import java.rmi.RemoteException;
import java.util.ArrayList;

import main.BussinessLogicService.ReciptBLService.ReciptBLService;
import main.VO.BankAccountVO;
import main.VO.CashBillVO;
import main.VO.CollectionOrderVO;
import main.VO.CustomerVO;
import main.VO.PaymentOrderVO;
import main.VO.ReciptVO;
import main.utility.ResultMessage;

public class Recipt_Stub implements ReciptBLService {

	@Override
	public ResultMessage setCollection(CollectionOrderVO vo) throws RemoteException {
		// TODO Auto-generated method stub
		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage setPaymentOrder(PaymentOrderVO vo) throws RemoteException {
		// TODO Auto-generated method stub
		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage setCashBill(CashBillVO vo) throws RemoteException {
		// TODO Auto-generated method stub
		return ResultMessage.SUCCESS;
	}

	@Override
	public ReciptVO findOne(String id) throws RemoteException {
		// TODO Auto-generated method stub
		return new ReciptVO();
	}

	@Override
	public ArrayList<BankAccountVO> getBankAccount(String part) throws RemoteException {
		// TODO Auto-generated method stub
		return new ArrayList<BankAccountVO>();
	}

	@Override
	public ArrayList<CustomerVO> getCustomerList(String keyword, String customerName) throws RemoteException {
		// TODO Auto-generated method stub
		return new ArrayList<CustomerVO>();
	}

	@Override
	public ArrayList<ReciptVO> getAllRecipts() throws RemoteException {
		// TODO Auto-generated method stub
		return new ArrayList<ReciptVO>();
	}

	@Override
	public ArrayList<CollectionOrderVO> getAllCollectionOrderList() throws RemoteException {
		// TODO Auto-generated method stub
		return new ArrayList<CollectionOrderVO>();
	}

	@Override
	public ArrayList<PaymentOrderVO> getAllPaymentOrderList() throws RemoteException {
		// TODO Auto-generated method stub
		return new ArrayList<PaymentOrderVO>();
	}

	@Override
	public ArrayList<CashBillVO> getAllCashBillList() throws RemoteException {
		// TODO Auto-generated method stub
		return new ArrayList<CashBillVO>();
	}

}
