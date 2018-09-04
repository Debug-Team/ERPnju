package main.Stub;

import java.util.ArrayList;

import main.BussinessLogic.BankAccountBL.BankAccountService;
import main.VO.BankAccountVO;

public class BankAccountBL_Stub implements BankAccountService {

	@Override
	public ArrayList<BankAccountVO> getBankAccount(String part) {
		// TODO Auto-generated method stub
		return new ArrayList<BankAccountVO>();
	}

}
