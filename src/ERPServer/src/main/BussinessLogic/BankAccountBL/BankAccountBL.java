package main.BussinessLogic.BankAccountBL;

import java.util.ArrayList;

import main.Data.BankAccountData.BankAccountData;
import main.DataService.BankAccountDataService.BankAccountDataService;
import main.PO.BankAccountPO;
import main.VO.BankAccountVO;
import main.utility.ResultMessage;

public class BankAccountBL implements BankAccountService {

	BankAccountDataService bads = new BankAccountData();
	
	public ResultMessage add(BankAccountVO vo) {
		if(bads.find(vo.getId()) == null) {
			bads.add(new BankAccountPO(vo));
			return ResultMessage.SUCCESS;
		}
		else
			return ResultMessage.FAIL;
	}

	public ResultMessage delete(String bankAccountID) {
		if(bads.find(bankAccountID) != null) {
			bads.delete(bankAccountID);
			return ResultMessage.SUCCESS;
		}
		else
			return ResultMessage.FAIL;
	}

	public ResultMessage modify(BankAccountVO vo) {
		if(bads.find(vo.getId()) != null) {
			bads.modify(new BankAccountPO(vo));
			return ResultMessage.SUCCESS;
		}
		else
			return ResultMessage.FAIL;
	}

	public BankAccountVO find(String bankAccountID) {
		return new BankAccountVO(bads.find(bankAccountID));
	}

	public ArrayList<BankAccountVO> getBankAccountList() {
		ArrayList<BankAccountPO> temp = bads.getList();
		ArrayList<BankAccountVO> results = new ArrayList<BankAccountVO>();
		for(int i = 0; i < temp.size(); i++) {
			BankAccountVO vo = new BankAccountVO(temp.get(i));
			results.add(vo);
		}
		return results;
	}

	@Override
	public ArrayList<BankAccountVO> getBankAccount(String part) {
		ArrayList<BankAccountVO> results = new ArrayList<BankAccountVO>();
		ArrayList<BankAccountPO> temp = bads.getList(part);
		for(int i = 0; i < temp.size(); i++) {
			BankAccountVO vo = new BankAccountVO(temp.get(i));
			results.add(vo);
		}
		return results;
	}

}
