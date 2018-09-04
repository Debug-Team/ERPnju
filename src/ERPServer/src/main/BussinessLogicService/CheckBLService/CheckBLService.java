package main.BussinessLogicService.CheckBLService;

import java.rmi.RemoteException;
import java.rmi.Remote;

import main.VO.InfoVO;
import main.utility.ResultMessage;

/**
 * @author Cauchy不是你
 */
public interface CheckBLService extends Remote {
	
	/**
	 * 获取待审批单据
	 * @return InfoVO
	 * @throws RemoteException
	 */
	public InfoVO getInfo() throws RemoteException;
	
	/**
	 * 总经理更改单据信息后返回设置
	 * @param vo
	 * @param operator
	 * @return ResultMessage
	 * @throws RemoteException
	 */
	public ResultMessage setSuggestion(InfoVO vo, String operator) throws RemoteException;
	
}
