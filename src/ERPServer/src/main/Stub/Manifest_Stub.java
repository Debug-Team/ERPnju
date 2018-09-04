package main.Stub;

import java.rmi.RemoteException;
import java.util.ArrayList;

import main.BussinessLogicService.ManifestBLService.ManifestBLService;
import main.VO.CustomerVO;
import main.VO.GoodsVO;
import main.VO.ManifestVO;
import main.utility.ResultMessage;

public class Manifest_Stub implements ManifestBLService{

	@Override
	public ResultMessage setGoodsInList(ManifestVO vo) throws RemoteException {
		// TODO Auto-generated method stub
		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage setGoodsInReturnList(ManifestVO vo) throws RemoteException {
		// TODO Auto-generated method stub
		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage setSaleList(ManifestVO vo) throws RemoteException {
		// TODO Auto-generated method stub
		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage setSaleReturnList(ManifestVO vo) throws RemoteException {
		// TODO Auto-generated method stub
		return ResultMessage.SUCCESS;
	}

	@Override
	public ArrayList<ManifestVO> getOperatorManifests(String operator) throws RemoteException {
		// TODO Auto-generated method stub
		return new ArrayList<>();
	}

	@Override
	public ResultMessage deleteManifest(ManifestVO vo) throws RemoteException {
		// TODO Auto-generated method stub
		return ResultMessage.SUCCESS;
	}

	@Override
	public ArrayList<GoodsVO> getGoods() throws RemoteException {
		// TODO Auto-generated method stub
		return new ArrayList<>();
	}

	@Override
	public String getNextManifestID(String type) throws RemoteException {
		// TODO Auto-generated method stub
		return "manifestID";
	}

	@Override
	public ArrayList<CustomerVO> getAllCustomerName(String type) throws RemoteException {
		// TODO Auto-generated method stub
		return new ArrayList<>();
	}

	@Override
	public ResultMessage modifyManifest(ManifestVO vo) throws RemoteException {
		// TODO Auto-generated method stub
		return ResultMessage.SUCCESS;
	}

}
