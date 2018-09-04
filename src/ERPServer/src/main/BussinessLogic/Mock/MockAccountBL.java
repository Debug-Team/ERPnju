package main.BussinessLogic.Mock;

import java.util.ArrayList;

import main.BussinessLogic.AccountBL.AccountBL;
import main.VO.AccountVO;
import main.VO.CustomerVO;
import main.VO.GoodsVO;
import main.utility.ResultMessage;

public class MockAccountBL extends AccountBL{
	public ResultMessage set(AccountVO vo) {
		return ResultMessage.FAIL;
	}
	
	public ResultMessage addGoods(String id) {
		return ResultMessage.FAIL;
	}
	
	public ArrayList<GoodsVO> getGoods (String partID){
		return null;
	}
	
	public boolean addCustomer (String id) {
		return true;
	}
	
	public ArrayList<CustomerVO> getCustomer (String partID){
		return null;
	}
	
	public AccountVO get (String id) {
		return null;
	}
}
