package main.Stub;

import java.rmi.RemoteException;
import java.util.ArrayList;

import main.BussinessLogicService.CustomerBLService.CustomerBLService;
import main.VO.CustomerVO;
import main.utility.ResultMessage;

public class Customer_Stub implements CustomerBLService{

	@Override
	public ResultMessage customerAdd(CustomerVO vo) throws RemoteException {
		// TODO Auto-generated method stub
		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage customerDelete(CustomerVO vo) throws RemoteException {
		// TODO Auto-generated method stub
		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage customerModify(CustomerVO vo) throws RemoteException {
		// TODO Auto-generated method stub
		return ResultMessage.SUCCESS;
	}

	@Override
	public ArrayList<CustomerVO> customerFind(String keyword, String type) throws RemoteException {
		// TODO Auto-generated method stub
		return new ArrayList<>();
	}

	@Override
	public String getNextCustomerID(String type) throws RemoteException {
		// TODO Auto-generated method stub
		return "nextCustomerID";
	}

}
