package main.VO;

import java.io.Serializable;
import java.util.ArrayList;

import main.PO.AccountPO;
import main.PO.BankAccountPO;
import main.PO.CustomerPO;
import main.PO.ReciptGoodsPO;
import main.VO.CustomerVO;

public class AccountVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String operator;
	private ArrayList<ReciptGoodsVO> goodsList;
	private ArrayList<CustomerVO> customerList;
	private ArrayList<BankAccountVO> bankAccountList;
	
	public AccountVO(AccountPO po) {
		this.id = po.getId();
		this.operator = "";
		
		ArrayList<ReciptGoodsVO> goodsVoList = new ArrayList<ReciptGoodsVO>();
		ArrayList<ReciptGoodsPO> goodsPoList = po.getGoodsList();
		for(int i = 0; i < goodsPoList.size(); i++) {
			ReciptGoodsVO vo = new ReciptGoodsVO(goodsPoList.get(i));
			goodsVoList.add(vo);
		}
		this.goodsList = goodsVoList;
		
		ArrayList<CustomerVO> customerVoList = new ArrayList<CustomerVO>();
		ArrayList<CustomerPO> customerPoList = po.getCustomerList();
		for(int i = 0; i < customerPoList.size(); i++) {
			CustomerVO vo = new CustomerVO(customerPoList.get(i));
			customerVoList.add(vo);
		}
		this.customerList = customerVoList;
		
		ArrayList<BankAccountVO> bankAccountVoList = new ArrayList<BankAccountVO>();
		ArrayList<BankAccountPO> bankAccountPoList = po.getBankAccountList();
		for(int i = 0; i < bankAccountPoList.size(); i++) {
			BankAccountVO vo = new BankAccountVO(bankAccountPoList.get(i));
			bankAccountVoList.add(vo);
		}
		this.bankAccountList = bankAccountVoList;
	}
	
	public AccountVO(ArrayList<ReciptGoodsVO> goodsList, ArrayList<CustomerVO> customerList, ArrayList<BankAccountVO> bankAccountList, String operater) {
		this.operator = operater;
		this.goodsList = goodsList;
		this.customerList = customerList;
		this.bankAccountList = bankAccountList;
	}
	
	public int getId() {
		return id;
	}

	public String getOperator() {
		return operator;
	}
	
	public ArrayList<ReciptGoodsVO> getGoodsList() {
		return goodsList;
	}
	
	public ArrayList<CustomerVO> getCustomerList() {
		return customerList;
	}
	
	public ArrayList<BankAccountVO> getBankAccountList() {
		return bankAccountList;
	}
}
