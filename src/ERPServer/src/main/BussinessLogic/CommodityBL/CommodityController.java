package main.BussinessLogic.CommodityBL;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import main.BussinessLogic.LogBL.LogBL;
import main.BussinessLogicService.CommodityBLService.CommodityBLService;
import main.BussinessLogicService.CommodityBLService.CommodityReciptBLService;
import main.BussinessLogicService.CommodityBLService.GoodsBLService;
import main.BussinessLogicService.CommodityBLService.GoodsCatagoryBLService;
import main.VO.CategoryVO;
import main.VO.CommodityInfoVO;
import main.VO.CommodityReciptVO;
import main.VO.GoodsVO;
import main.utility.ResultMessage;
/**
 * 
 * @author 周益冰
 *
 */
public class CommodityController extends UnicastRemoteObject implements GoodsBLService,GoodsCatagoryBLService,CommodityReciptBLService,CommodityBLService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GoodsBL goodsbl;
	private CommodityReciptBL commodityReciptBL;
	private GoodsCatagoryBL categorybl;
	private CommodityBL commoditybl;
	private LogBL logBL;
	

	public CommodityController() throws RemoteException {
		super();
		goodsbl = new GoodsBL();
		commodityReciptBL = new CommodityReciptBL();
		categorybl = new GoodsCatagoryBL();
		commoditybl = new CommodityBL();
		logBL= new LogBL();
	}

	@Override
	public CommodityInfoVO viewCommodity(String startTime, String endTime) throws RemoteException {
		return commoditybl.viewCommodity(startTime, endTime);
	}

	@Override
	public ResultMessage setGiftList(CommodityReciptVO vo) throws RemoteException {
		logBL.createLog(vo.getStaffID(), "设置了库存赠送单");
		return commodityReciptBL.setGiftList(vo);
	}

	@Override
	public ResultMessage setOverflowList(CommodityReciptVO vo) throws RemoteException {
		logBL.createLog(vo.getStaffID(), "设置了库存报溢单");
		return commodityReciptBL.setOverflowList(vo);
	}

	@Override
	public ResultMessage setDamageList(CommodityReciptVO vo) throws RemoteException {
		logBL.createLog(vo.getStaffID(), "设置了库存报损单");
		return commodityReciptBL.setDamageList(vo);
	}

	@Override
	public ResultMessage setWarningList(CommodityReciptVO vo) throws RemoteException {
		logBL.createLog(vo.getStaffID(), "设置了赠送单");
		return commodityReciptBL.setWarningList(vo);
	}

	@Override
	public ResultMessage catagoryAdd(CategoryVO vo) throws RemoteException {
		logBL.createLog(vo.getStaffID(), "新增分类"+vo.getMyvalue());
		return categorybl.catagoryAdd(vo);
	}

	@Override
	public ResultMessage catagoryDelete(CategoryVO vo) throws RemoteException {
		logBL.createLog(vo.getStaffID(), "删除分类"+vo.getMyvalue());
		return categorybl.catagoryDelete(vo);
	}

	@Override
	public ResultMessage catagoryModify(CategoryVO vo) throws RemoteException {
		logBL.createLog(vo.getStaffID(), "修改了分类"+vo.getMyvalue());
		return categorybl.catagoryModify(vo);
	}

	@Override
	public ArrayList<CategoryVO> categoryAll() throws RemoteException {
		return categorybl.categoryAll();
	}

	@Override
	public ResultMessage goodsAdd(GoodsVO vo) throws RemoteException {
		logBL.createLog(vo.getStaffID(), "新增商品"+vo.getName());
		return goodsbl.goodsAdd(vo);
	}

	@Override
	public ResultMessage goodsDelete(GoodsVO vo) throws RemoteException {
		logBL.createLog(vo.getStaffID(), "删除商品"+vo.getName());
		return goodsbl.goodsDelete(vo);
	}

	@Override
	public ResultMessage goodsModify(GoodsVO vo) throws RemoteException {
		logBL.createLog(vo.getStaffID(), "修改商品"+vo.getName());
		return goodsbl.goodsModify(vo);
	}

	@Override
	public ArrayList<GoodsVO> goodsFind(String keyword, String type) throws RemoteException {
		return goodsbl.goodsFind(keyword,type);
	}

	@Override
	public String getNextGoodsId(String category) throws RemoteException {
		return goodsbl.getNextGoodsId(category);
	}

	@Override
	public ResultMessage checkCommodityInfoToExcel(String fileName, String storeAdress) throws RemoteException {
		return commoditybl.checkCommodityInfoToExcel(fileName, storeAdress);
	}

	@Override
	public boolean couldBeDeleted(String goodID) throws RemoteException {
		return goodsbl.couldBeDeleted(goodID);
	}



}
