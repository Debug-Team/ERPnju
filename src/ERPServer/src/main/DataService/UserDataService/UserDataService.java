package main.DataService.UserDataService;

import java.util.ArrayList;

import main.PO.UserPO;

/**
 * @author Cauchy不是你
 */
public interface UserDataService {
	/**
	 * 查询用户账号（工号）是否已存在
	 * @param jobNum
	 * @return boolean
	 */
	public boolean findUser(String jobNum);
	
	/**
	 * 根据工号获取用户信息
	 * @param jobNum
	 * @return UserPO
	 */
	public UserPO getUser(String jobNum);
	
	/**
	 * 获取用户列表
	 * @return ArrayList<UserPO>
	 */
	public ArrayList<UserPO> getUserList();
	
	/**
	 * 添加新用户
	 * @param po
	 * @return boolean
	 */
	public boolean addNewUser(UserPO po);
	
	/**
	 * 更改用户信息
	 * @param po
	 * @return boolean
	 */
	public boolean modifyUser(UserPO po);
	
	/**
	 * 删除用户
	 * @param po
	 * @return boolean
	 */
	public boolean deleteUser(UserPO po);
	
}
