package main.BussinessLogic.Mock;

import java.util.List;

import main.BussinessLogic.CheckBL.CheckBL;
import main.VO.InfoVO;
import main.utility.ResultMessage;

public class MockCheckBL extends CheckBL{
	
	public InfoVO getInfo() {
		return null;
	}
	
	public ResultMessage setSuggestion(InfoVO vo) {
		return ResultMessage.SUCCESS;
	}
	
}
