package main.BussinessLogicService.LogBLService;

import java.util.ArrayList;

import main.VO.LogVO;

public interface LogBLService {
	/**
	 * 通过关键字和类型查找日志
	 * @param keyword
	 * @param type
	 * @return ArrayList<LogVO>
	 */
	public ArrayList<LogVO> findLog(String keyword, String type);
	
	/**
	 * 通过起始和结束时间查找日志
	 * @param startTime
	 * @param endTime
	 * @return ArrayList<LogVO>
	 */
	public ArrayList<LogVO> findLogByTime(String startTime, String endTime);
}
