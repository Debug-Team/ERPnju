package main.BussinessLogic.CustomerBL;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import main.BussinessLogicService.CustomerBLService.CustomerBLService;
import main.VO.CustomerVO;
import main.utility.ResultMessage;

public class CustomerBLController extends UnicastRemoteObject implements CustomerBLService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomerBLController() throws RemoteException {
		super();
	}

	CustomerBL cust = new CustomerBL();
	
	@Override
	public ResultMessage customerAdd(CustomerVO vo) throws RemoteException {
		return cust.customerAdd(vo);
	}

	@Override
	public ResultMessage customerDelete(CustomerVO vo) throws RemoteException {
		return cust.customerDelete(vo);
	}

	@Override
	public ResultMessage customerModify(CustomerVO vo) throws RemoteException {
		return cust.customerModify(vo);
	}

	@Override
	public ArrayList<CustomerVO> customerFind(String keyword, String type) throws RemoteException {
		return cust.customerFind(keyword,type);
	}

	@Override
	public String getNextCustomerID(String type) throws RemoteException {
		return cust.getNextCustomerID(type);
	}

}
