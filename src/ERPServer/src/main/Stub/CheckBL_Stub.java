package main.Stub;

import java.rmi.RemoteException;

import main.BussinessLogicService.CheckBLService.CheckBLService;
import main.VO.InfoVO;
import main.utility.ResultMessage;

public class CheckBL_Stub implements CheckBLService {

	@Override
	public InfoVO getInfo() throws RemoteException {
		// TODO Auto-generated method stub
		return new InfoVO();
	}

	@Override
	public ResultMessage setSuggestion(InfoVO vo, String operator) throws RemoteException {
		// TODO Auto-generated method stub
		return ResultMessage.SUCCESS;
	}

}
