package main.BussinessLogic.CheckBL;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import main.BussinessLogic.LogBL.LogBL;
import main.BussinessLogicService.CheckBLService.CheckBLService;
import main.VO.InfoVO;
import main.utility.ResultMessage;

public class CheckBLController extends UnicastRemoteObject implements CheckBLService {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CheckBLController() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	CheckBL bl = new CheckBL();
	LogBL logBl = new LogBL();

	@Override
	public InfoVO getInfo() throws RemoteException {
		return bl.getInfo();
	}

	@Override
	public ResultMessage setSuggestion(InfoVO vo, String operator) throws RemoteException {
		logBl.createLog(operator, "对单据进行了审批");
		return bl.setSuggestion(vo);
	}

}
