package main.Data.stub;

import java.util.ArrayList;

import main.DataService.BankAccountDataService.BankAccountDataService;
import main.PO.BankAccountPO;
import main.utility.ResultMessage;

public class BankAccountData_Stub implements BankAccountDataService{

	@Override
	public ResultMessage add(BankAccountPO po) {
		// TODO Auto-generated method stub
		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage delete(String id) {
		// TODO Auto-generated method stub
		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage modify(BankAccountPO po) {
		// TODO Auto-generated method stub
		return ResultMessage.SUCCESS;
	}

	@Override
	public BankAccountPO find(String id) {
		// TODO Auto-generated method stub
		return new BankAccountPO("test stub", 100);
	}

	@Override
	public ArrayList<BankAccountPO> getList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<BankAccountPO> getList(String part) {
		// TODO Auto-generated method stub
		return null;
	}

}
