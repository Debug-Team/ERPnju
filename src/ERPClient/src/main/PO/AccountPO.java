package main.PO;

import java.util.ArrayList;
import java.util.List;

import main.VO.AccountVO;
import main.VO.BankAccountVO;
import main.VO.CustomerVO;
import main.VO.ReciptGoodsVO;

public class AccountPO {
	
	private int id;  //×ÔÔö³¤ID
	private ArrayList<ReciptGoodsPO> goodsList;
	private ArrayList<CustomerPO> customerList;
	private ArrayList<BankAccountPO> bankAccountList;
	
	public AccountPO() {}
	
	public AccountPO(AccountVO vo) {
		this.id = vo.getId();
		
		this.goodsList = new ArrayList<ReciptGoodsPO>();
		ArrayList<ReciptGoodsVO> goodsVoList = vo.getGoodsList();
		for(int i = 0; i < goodsVoList.size(); i++) {
			ReciptGoodsPO po = new ReciptGoodsPO(goodsVoList.get(i));
			goodsList.add(po);
		}
		
		this.customerList = new ArrayList<CustomerPO>();
		ArrayList<CustomerVO> customerVoList = vo.getCustomerList();
		for(int i = 0; i < customerVoList.size(); i++) {
			CustomerPO po = new CustomerPO(customerVoList.get(i));
			customerList.add(po);
		}
		
		this.bankAccountList = new ArrayList<BankAccountPO>();
		ArrayList<BankAccountVO> bankAccountVoList = vo.getBankAccountList();
		for(int i = 0; i < bankAccountVoList.size(); i++) {
			BankAccountPO po = new BankAccountPO(bankAccountVoList.get(i));
			bankAccountList.add(po);
		}
	}
	
	public AccountPO(ArrayList<ReciptGoodsPO> goodsList, ArrayList<CustomerPO> customerList, ArrayList<BankAccountPO> bankAccountList) {
		this.goodsList = goodsList;
		this.customerList = customerList;
		this.bankAccountList = bankAccountList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ArrayList<ReciptGoodsPO> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<ReciptGoodsPO> goodsList) {
		this.goodsList = new ArrayList<ReciptGoodsPO>(goodsList);
	}

	public ArrayList<CustomerPO> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(List<CustomerPO> customerList) {
		this.customerList = new ArrayList<CustomerPO>(customerList);
	}
	
	public ArrayList<BankAccountPO> getBankAccountList() {
		return bankAccountList;
	}

	public void setBankAccountList(List<BankAccountPO> bankAccountList) {
		this.bankAccountList = new ArrayList<BankAccountPO>(bankAccountList);
	}
}
