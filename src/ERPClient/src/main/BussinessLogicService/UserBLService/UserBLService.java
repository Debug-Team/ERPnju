package main.BussinessLogicService.UserBLService;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import main.VO.UserVO;
import main.utility.UserType;

/**
 * @author Cauchy不是你
 */
public interface UserBLService extends Remote {
	/**
	 * 普通登陆方法
	 * @param jobNum
	 * @param password
	 * @return UserType
	 * @throws RemoteException
	 */
	public UserType login(String jobNum, String password) throws RemoteException;
	
	/**
	 * 人脸识别登陆方法
	 * @param jobNum
	 * @param imagePath
	 * @return UserType
	 * @throws RemoteException
	 */
	public UserType faceLogin(String jobNum, String imagePath) throws RemoteException;
	
	/**
	 * 人脸注册方法
	 * @param jobNum
	 * @param password
	 * @param imagePath
	 * @return UserType
	 * @throws RemoteException
	 */
	public UserType setFaceImage(String jobNum, String password, String imagePath) throws RemoteException;
	
	/**
	 * 普通注册方法
	 * @param vo
	 * @return UserType
	 * @throws RemoteException
	 */
	public UserType register(UserVO vo) throws RemoteException;
	
	/**
	 * 根据工号获取用户信息
	 * @param jobNum
	 * @return UserVO
	 * @throws RemoteException
	 */
	public UserVO getUser(String jobNum) throws RemoteException;
	
	/**
	 * 获取用户列表
	 * @return ArrayList<UserVO>
	 * @throws RemoteException
	 */
	public ArrayList<UserVO> getUserList() throws RemoteException;
	
	/**
	 * 修改客户信息
	 * @param vo
	 * @return boolean
	 * @throws RemoteException
	 */
	public boolean modifyUser(UserVO vo) throws RemoteException;
	
	/**
	 * 删除客户
	 * @param vo
	 * @return boolean
	 * @throws RemoteException
	 */
	public boolean deleteUser(UserVO vo) throws RemoteException;

}
