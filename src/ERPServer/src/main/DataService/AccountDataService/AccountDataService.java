package main.DataService.AccountDataService;

import main.PO.AccountPO;
import main.utility.ResultMessage;

/**
 * @author Cauchy不是你
 */
public interface AccountDataService {
	/**
	 * 获取期初信息
	 * @return AccountPO
	 */
	public AccountPO get();
	
	/**
	 * 更新期初信息
	 * @param po
	 * @return ResultMessage
	 */
	public ResultMessage update(AccountPO po);
	
	/**
	 * 设置期初建账
	 * @param po
	 * @return ResultMessage
	 */
	public ResultMessage set(AccountPO po);
}
