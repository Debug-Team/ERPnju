package main.BussinessLogic.ManifestBL;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import main.BussinessLogic.CommodityBL.GoodsBL;
import main.BussinessLogic.CustomerBL.CustomerBL;
import main.BussinessLogic.LogBL.LogBL;
import main.BussinessLogicService.ManifestBLService.ManifestBLService;
import main.VO.CouponVO;
import main.VO.CustomerVO;
import main.VO.GoodsVO;
import main.VO.ManifestVO;
import main.utility.ResultMessage;

public class ManifestBLController extends UnicastRemoteObject implements ManifestBLService {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ManifestBLController() throws RemoteException {
		super();
	}

	ManifestBL bl = new ManifestBL();
	LogBL logBL = new LogBL();

	@Override
	public ResultMessage setGoodsInList(ManifestVO vo) throws RemoteException {
		logBL.createLog(vo.getStaffID(), "新建进货单");
		return bl.setGoodsInList(vo);			
	}

	@Override
	public ResultMessage setGoodsInReturnList(ManifestVO vo) throws RemoteException {
		logBL.createLog(vo.getStaffID(), "新建进货退货单");
		return bl.setGoodsInReturnList(vo);
	}

	@Override
	public ResultMessage setSaleList(ManifestVO vo) throws RemoteException {
		logBL.createLog(vo.getStaffID(), "新建销售单");
		return bl.setSaleList(vo);
	}

	@Override
	public ResultMessage setSaleReturnList(ManifestVO vo) throws RemoteException {
		logBL.createLog(vo.getStaffID(), "新建销售退货单");
		return bl.setSaleReturnList(vo);
	}

	@Override
	public ArrayList<GoodsVO> getGoods() throws RemoteException {	
		return new GoodsBL().goodsFind("", "name");
	}

	@Override
	public String getNextManifestID(String type) throws RemoteException {	
		return bl.getNextManifestID(type);
	}

	@Override
	public ArrayList<ManifestVO> getOperatorManifests(String operator) throws RemoteException {
		return bl.getOperatorManifests(operator);
	}

	@Override
	public ResultMessage deleteManifest(ManifestVO vo) throws RemoteException {
		logBL.createLog(vo.getStaffID(), "删除了单据"+vo.getID());
		return bl.deleteManifest(vo);
	}

	@Override
	public ArrayList<CustomerVO> getAllCustomerName(String type) throws RemoteException {
		ArrayList<CustomerVO> list = new CustomerBL().customerFind(type, "category");
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String currentDate = format.format(new Date());

		//将过期的代金券从账户删除
		if(!list.isEmpty())
			for(CustomerVO vo : list){	
				CouponVO coupon = null;
				for(int i = 0 ; i < vo.getCouponList().size();i++){
					coupon = vo.getCouponList().get(i);
					if(Integer.parseInt(coupon.getStartTime()) > Integer.parseInt(currentDate) || Integer.parseInt(coupon.getEndTime()) < Integer.parseInt(currentDate)){
						vo.getCouponList().remove(i);
						i--;
					}
				}	
			}	
		
		return list;
	}

	@Override
	public ResultMessage modifyManifest(ManifestVO vo) {

		return bl.modifyManifest(vo);
	}

}
