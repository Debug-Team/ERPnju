package main.BussinessLogic.UserBL;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import main.BussinessLogicService.UserBLService.UserBLService;
import main.PO.UserPO;
import main.VO.UserVO;
import main.utility.UserType;

public class UserBLController extends UnicastRemoteObject implements UserBLService {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	UserBL bl = new UserBL();

	public UserBLController() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public UserType login(String jobNum, String password) throws RemoteException {
		return bl.login(jobNum, password);
	}

	@Override
	public UserType faceLogin(String jobNum, String imagePath) throws RemoteException {
		return bl.faceLogin(jobNum, imagePath);
	}

	@Override
	public UserType setFaceImage(String jobNum, String password, String imagePath) throws RemoteException {
		return bl.setFaceImage(jobNum, password, imagePath);
	}

	@Override
	public UserType register(UserVO vo) throws RemoteException {
		return bl.register(new UserPO(vo));
	}

	@Override
	public UserVO getUser(String jobNum) throws RemoteException {
		return bl.getUser(jobNum);
	}
	
	@Override
	public ArrayList<UserVO> getUserList() throws RemoteException {
		return bl.getUserList();
	}

	@Override
	public boolean modifyUser(UserVO vo) throws RemoteException {
		return bl.modifyUser(new UserPO(vo));
	}

	@Override
	public boolean deleteUser(UserVO vo) throws RemoteException {
		return bl.deleteUser(new UserPO(vo));
	}
	
}
