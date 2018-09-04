package main.DataService.BankAccountDataService;

import java.util.ArrayList;

import main.PO.BankAccountPO;
import main.utility.ResultMessage;

/**
 * @author Cauchy不是你
 */
public interface BankAccountDataService {
	/**
	 * 添加银行账户
	 * @param po
	 * @return ResultMessage
	 */
	public ResultMessage add (BankAccountPO po);

	/**
	 * 根据id删除银行账户
	 * @param id
	 * @return ResultMessage
	 */
	public ResultMessage delete (String id);

	/**
	 * 更新银行账户信息
	 * @param po
	 * @return ResultMessage
	 */
	public ResultMessage modify (BankAccountPO po);
	
	/**
	 * 根据id查找银行账户
	 * @param bankAccountID
	 * @return BankAccountPO
	 */
	public BankAccountPO find (String bankAccountID);
	
	/**
	 * 获取银行账户列表
	 * @return ArrayList<BankAccountPO>
	 */
	public ArrayList<BankAccountPO> getList();
	
	/**
	 * 根据部分信息模糊查找
	 * @param part
	 * @return ArrayList<BankAccountPO>
	 */
	public ArrayList<BankAccountPO> getList(String part);
	
}
