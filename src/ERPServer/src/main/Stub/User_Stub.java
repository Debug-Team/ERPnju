package main.Stub;

import java.rmi.RemoteException;
import java.util.ArrayList;

import main.BussinessLogicService.UserBLService.UserBLService;
import main.VO.UserVO;
import main.utility.UserType;

public class User_Stub implements UserBLService {

	@Override
	public UserType login(String jobNum, String password) throws RemoteException {
		// TODO Auto-generated method stub
		return UserType.ALREADY_EXIT;
	}

	@Override
	public UserType faceLogin(String jobNum, String imagePath) throws RemoteException {
		// TODO Auto-generated method stub
		return UserType.ALREADY_EXIT;
	}

	@Override
	public UserType setFaceImage(String jobNum, String password, String imagePath) throws RemoteException {
		// TODO Auto-generated method stub
		return UserType.ALREADY_EXIT;
	}

	@Override
	public UserType register(UserVO vo) throws RemoteException {
		// TODO Auto-generated method stub
		return UserType.ALREADY_EXIT;
	}

	@Override
	public UserVO getUser(String jobNum) throws RemoteException {
		// TODO Auto-generated method stub
		return new UserVO();
	}

	@Override
	public ArrayList<UserVO> getUserList() throws RemoteException {
		// TODO Auto-generated method stub
		return new ArrayList<UserVO>();
	}

	@Override
	public boolean modifyUser(UserVO vo) throws RemoteException {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean deleteUser(UserVO vo) throws RemoteException {
		// TODO Auto-generated method stub
		return true;
	}

}
