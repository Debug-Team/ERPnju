package main.BussinessLogic.LogBL;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import main.BussinessLogicService.LogBLService.LogBLService;
import main.Data.LogData.LogData;
import main.PO.LogPO;
import main.VO.LogVO;
/**
 * 
 * @author 周益冰
 *
 */
public class LogBL extends UnicastRemoteObject implements LogBLService{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	LogData service;
	
	public LogBL() throws RemoteException{
		super();
		service = new LogData();
	}
	
	//创建日志，参数为操作员名称和消息
	public boolean createLog(String workNum, String message){
		LogPO po = new LogPO(workNum,message);
		
		return service.addLog(po);
	}
	
	@Override
	public ArrayList<LogVO> findLog(String keyword, String type){
		ArrayList<LogPO> list = service.findLog(keyword, type);
		ArrayList<LogVO> voList = new ArrayList<>();
		
		if(list!=null && !list.isEmpty()){
			for(LogPO po : list)
				voList.add(new LogVO(po));
		}
		return voList;
	}

	@Override
	public ArrayList<LogVO> findLogByTime(String startTime, String endTime) {
		ArrayList<LogPO> list = service.findLogByTime(startTime, endTime);
		ArrayList<LogVO> voList = new ArrayList<>();
		
		if(list!=null && !list.isEmpty()){
			for(LogPO po : list)
				voList.add(new LogVO(po));
		}
		return voList;
	}
}
