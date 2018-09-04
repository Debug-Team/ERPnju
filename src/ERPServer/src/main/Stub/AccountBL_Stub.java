package main.Stub;

import java.rmi.RemoteException;
import java.util.ArrayList;

import main.BussinessLogicService.AccountBLService.AccountBLService;
import main.VO.AccountVO;
import main.VO.BankAccountVO;
import main.VO.CustomerVO;
import main.VO.GoodsVO;
import main.utility.ResultMessage;

public class AccountBL_Stub implements AccountBLService {

	@Override
	public ResultMessage set(AccountVO vo) throws RemoteException {
		// TODO Auto-generated method stub
		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage update(AccountVO vo) throws RemoteException {
		// TODO Auto-generated method stub
		return ResultMessage.SUCCESS;
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
	public ArrayList<BankAccountVO> getBankAccount(String part) throws RemoteException {
		// TODO Auto-generated method stub
		return new ArrayList<BankAccountVO>();
	}

	@Override
	public AccountVO get() throws RemoteException {
		// TODO Auto-generated method stub
		return new AccountVO();
	}

}
