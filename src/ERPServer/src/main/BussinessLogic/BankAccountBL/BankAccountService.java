package main.BussinessLogic.BankAccountBL;

import java.util.ArrayList;

import main.VO.BankAccountVO;

public interface BankAccountService {
	
	/**
	 * 同层提供银行账户模糊查找方法
	 * @param part
	 * @return
	 */
	public ArrayList<BankAccountVO> getBankAccount(String part);
}
