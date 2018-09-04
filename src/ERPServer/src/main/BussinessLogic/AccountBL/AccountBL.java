package main.BussinessLogic.AccountBL;

import java.rmi.RemoteException;
import java.util.ArrayList;

import main.BussinessLogic.BankAccountBL.BankAccountBL;
import main.BussinessLogic.BankAccountBL.BankAccountService;
import main.BussinessLogic.CommodityBL.CommodityBL;
import main.BussinessLogic.CommodityBL.CommodityService;
import main.BussinessLogic.CustomerBL.CustomerBL;
import main.BussinessLogic.CustomerBL.CustomerService;
import main.Data.AccountData.AccountData;
import main.DataService.AccountDataService.AccountDataService;
import main.PO.*;
import main.VO.*;
import main.utility.ResultMessage;

public class AccountBL {
	
	AccountDataService ads = new AccountData();

	public ResultMessage set(AccountVO vo) {
		ads.set(new AccountPO(vo));
		return ResultMessage.SUCCESS;
	}
	
	public ResultMessage update(AccountVO vo) {
		ads.update(new AccountPO(vo));
		return ResultMessage.SUCCESS;
	}
	
	public ArrayList<GoodsVO> getGoods(String condition, String part) throws RemoteException {
		CommodityService cs = new CommodityBL();
		ArrayList<GoodsPO> goodsList = cs.getGoodsList(part, condition);
		ArrayList<GoodsVO> result = new ArrayList<GoodsVO>();
		for(int i = 0; i < goodsList.size(); i++) {
			GoodsVO vo = new GoodsVO(goodsList.get(i));
			result.add(vo);
		}
		return result;
	}

	public ArrayList<CustomerVO> getCustomer(String condition, String part) throws RemoteException {
		CustomerService cs = new CustomerBL();
		return cs.customerFind(part, condition);
	}
	
	public ArrayList<BankAccountVO> getBankAccount (String part) {
		BankAccountService bas = new BankAccountBL();
		return bas.getBankAccount(part);
	}

	public AccountVO get() {
		return new AccountVO(ads.get());
	}

}
