package main.BussinessLogicService.CheckBLService;

import java.rmi.RemoteException;
import java.rmi.Remote;

import main.VO.InfoVO;
import main.utility.ResultMessage;

/**
 * @author Cauchy²»ÊÇÄã
 */
public interface CheckBLService extends Remote {
	
	/**
	 * @return InfoVO
	 * @throws RemoteException
	 */
	public InfoVO getInfo() throws RemoteException;
	
	/**
	 * @param vo
	 * @param operator
	 * @return ResultMessage
	 * @throws RemoteException
	 */
	public ResultMessage setSuggestion(InfoVO vo, String operator) throws RemoteException;
	
}
