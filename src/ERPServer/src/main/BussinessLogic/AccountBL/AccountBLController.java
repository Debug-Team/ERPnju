package main.BussinessLogic.AccountBL;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import main.BussinessLogic.LogBL.LogBL;
import main.BussinessLogicService.AccountBLService.AccountBLService;
import main.VO.AccountVO;
import main.VO.BankAccountVO;
import main.VO.CustomerVO;
import main.VO.GoodsVO;
import main.utility.ResultMessage;

public class AccountBLController extends UnicastRemoteObject implements AccountBLService {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AccountBLController() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	AccountBL bl = new AccountBL();
	LogBL logBl = new LogBL();

	@Override
	public ResultMessage set(AccountVO vo) throws RemoteException {
		logBl.createLog(vo.getOperator(), "建立了一套账");
		return bl.set(vo);
	}
	
	@Override
	public ResultMessage update(AccountVO vo) throws RemoteException {
		logBl.createLog(vo.getOperator(), "更新了期初信息");
		return bl.update(vo);
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
	public ArrayList<BankAccountVO> getBankAccount(String part) throws RemoteException {
		return bl.getBankAccount(part);
	}

	@Override
	public AccountVO get() throws RemoteException {
		return bl.get();
	}
	
}
