package main.BussinessLogic.ManifestBL;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import main.BussinessLogic.CommodityBL.CommodityBL;
import main.BussinessLogic.CommodityBL.CommodityService;
import main.BussinessLogic.CustomerBL.CustomerBL;
import main.BussinessLogic.CustomerBL.CustomerService;
import main.BussinessLogic.PromotionBL.PromotionBLController;
import main.BussinessLogic.PromotionBL.PromotionService;
import main.Data.ManifestData.ManifestData;
import main.DataService.ManifestDataService.ManifestDataService;
import main.PO.ManifestPO;
import main.PO.ReciptGoodsPO;
import main.VO.ManifestVO;
import main.VO.ReciptGoodsVO;
import main.utility.DocumentsType;
import main.utility.PromotionResult;
import main.utility.ResultMessage;

public class ManifestBL implements ManifestService{
	
	ManifestDataService service;
	CommodityService commodityService;
	
	public ManifestBL() {
		service = new ManifestData();
		commodityService = new CommodityBL();
	}

	//保存进货单
	public ResultMessage setGoodsInList(ManifestVO vo) {
		
		ManifestPO po = new ManifestPO(vo);
		po.setType("JHD");
		setCreateDate(po);

		//计算总额
		double sum = 0;
		for(ReciptGoodsPO goods : po.getGoodsList())
			sum += goods.getAmounts()*goods.getBid();
		po.setSum(sum);
		
		service.setManifest(po);

		return ResultMessage.SUCCESS;
	}

	//保存进货退货单
	public ResultMessage setGoodsInReturnList(ManifestVO vo) {
		ManifestPO po = new ManifestPO(vo);
		po.setType("JHTHD");
		setCreateDate(po);

		// 计算总额
		double sum = 0;
		for(ReciptGoodsPO goods : po.getGoodsList())
			sum += goods.getAmounts()*goods.getBid();
		po.setSum(sum);
		
		// 改动库存商品数据
		commodityService.updateCommodity(po.getGoodsList(), "JHTHD", "Unchecked");
		
		po.setSum(sum);
		service.setManifest(po);

		return ResultMessage.SUCCESS;
	}

	//保存销售单
	public ResultMessage setSaleList(ManifestVO vo) {
		
		ManifestPO po = new ManifestPO(vo);
		po.setType("XSD");
		setCreateDate(po);
	
		//草稿直接报错，非草稿进一步计算
		if(!po.getState().equals("Draft")){
			//调用promotion方法得到 促销策略优惠，赠送商品列表
			PromotionService prService = null;
			try {
				prService = new PromotionBLController();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			CustomerService cusService = new CustomerBL();
			PromotionResult result = prService.getAppropriatePromotion(po.getGoodsList(), cusService.findCustomer(po.getCustomerName()));
			if(result!=null && result.getGiftList()!=null)
				po.setGiftGoodslist(result.getGiftList());
			
			//计算总额，折让后总额（=总额-代金券-折让-促销优惠）
			double sum = 0;
			for(ReciptGoodsPO goods : po.getGoodsList())
				sum += goods.getAmounts()*goods.getBid();
			po.setSum(sum);
			po.setPromotionDiscount(sum-result.getAmount());
			//TODO 删除客户包中的代金券
			
			double total = sum - po.getDiscount() - po.getPromotionDiscount() - po.getCouponValue();
			po.setSum(total);
		}
		
		service.setManifest(po);

		return ResultMessage.SUCCESS;
	}

	//保存销售退货单
	public ResultMessage setSaleReturnList(ManifestVO vo) {

		ManifestPO po = new ManifestPO(vo);
		po.setType("XSTHD");
		setCreateDate(po);
		
		// 计算总额
		double sum = 0;
		for(ReciptGoodsPO goods : po.getGoodsList())
			sum += goods.getAmounts()*goods.getBid();
		po.setSum(sum);
		
		service.setManifest(po);

		return ResultMessage.SUCCESS;
	}
	
	//为ID增加CreateDate
	private void setCreateDate(ManifestPO po){
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String date = format.format(new Date());
		po.setCreateDate(date);
	}
	
	//审批单据
	public ResultMessage manifestChecked(ManifestPO po){
		
		String manifestType = po.getType();
		if(manifestType.equals("JHD")){
			// 库存增加
			// 改动商品最近进价&库存均价
			commodityService.updateCommodity(po.getGoodsList(), po.getType(), po.getState());
			
			//改动客户应收应付
			new CustomerBL().updateCustomer(po.getCustomerName(), po.getType(), po.getSum());
			

		}
		else if(manifestType.equals("XSTHD")){
			//库存增加
			commodityService.updateCommodity(po.getGoodsList(), po.getType(), po.getState());
			
			//改动客户应收应付
			new CustomerBL().updateCustomer(po.getCustomerName(), po.getType(), po.getSum());
		}
		else if(manifestType.equals("XSD")){

			
			//改动客户应收应付数据	
			new CustomerBL().updateCustomer(po.getCustomerName(), po.getType(), po.getSum());
			
			//改动商品最近售价
			commodityService.updateCommodity(po.getGoodsList(), po.getType(), po.getState());
			
			//建立赠送单
			commodityService.createGiftCommodityRecipt(po.getGiftGoodslist());
		}
		else if(manifestType.equals("JHTHD")){
			//库存数据已改动，不变
			
			//改动客户应收应付数据	
			new CustomerBL().updateCustomer(po.getCustomerName(), po.getType(), po.getSum());
			
			//库存均价
			commodityService.updateCommodity(po.getGoodsList(), po.getType(), po.getState());
		}
		else
			return ResultMessage.FAIL;
		
		
		if(!service.updateManifest(po))
			return ResultMessage.FAIL;
		else
			return ResultMessage.SUCCESS;
	}

	@Override
	public ArrayList<ManifestPO> getUncheckedManifest() {
		
		return service.find("Unchecked", "state");
	}

	@Override
	public ResultMessage saveManifest(ArrayList<ManifestPO> list) {

		for(ManifestPO po : list){
			if(po.getState().equals("Checked"))
				 manifestChecked(po);
		}
		return service.modify(list);
	}

	//根据类型得到下一个manifestID
	public String getNextManifestID(String type) {
		
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String date = format.format(new Date());

		int id = service.newID(type,date);
		String ide = String.format("%05d", id);
		
		if(id>99999) return "overflow";
		else return type+"-"+date+"-"+ide;
		
	}
	
	//删除manifest
	public ResultMessage deleteManifest(ManifestVO vo){
		
		if(service.deleteManifest(new ManifestPO(vo)))
			return ResultMessage.SUCCESS;
		else 
			return ResultMessage.FAIL;
	}
	
	//得到该操作员的所有manifest
	public ArrayList<ManifestVO> getOperatorManifests(String operator){
		
		ArrayList<ManifestPO> list = service.find(operator, "operator");
		ArrayList<ManifestVO> voList = new ArrayList<>();
		
		if(list!=null && !list.isEmpty()){
			for(ManifestPO tmp : list){
				voList.add(new ManifestVO(tmp));
			}
		}
		return voList;
	}

	@Override
	public ArrayList<ManifestPO> getCheckedManifest() {
		
		return service.find("Checked", "state");
	}

	@Override
	public ArrayList<ReciptGoodsVO> getSalesDetailList(String startTime, String endTime, 
			String goodsName, String customerName, String operator) {

		ArrayList<ManifestPO> list = service.getSalesHistoryList(startTime, endTime, customerName, operator);
		ArrayList<ReciptGoodsVO> retlist = new ArrayList<>();
		
		if(list!=null && !list.isEmpty()){
			for(ManifestPO vo : list){
				if(!vo.getType().equals("XSD")) 
					continue;
				ArrayList<ReciptGoodsPO> goodslist = vo.getGoodsList();
				for(int i=0; i<goodslist.size(); i++){
					ReciptGoodsPO tmp = goodslist.get(i);
					if(tmp.getName().equals(goodsName)||goodsName.equals("")){
						tmp.setComment(vo.getCreateDate());
						tmp.setGoodsID(vo.getOperator());
						retlist.add(new ReciptGoodsVO(tmp));
					}
				}		
			}
		}

		return retlist;
	}

	@Override
	public ArrayList<ManifestVO> getSalesHistoryList(String startTime, String endTime,
			String customerName,String operator,DocumentsType type) {
		ArrayList<ManifestPO> list = service.getSalesHistoryList(startTime, endTime, customerName, operator);
		ArrayList<ManifestVO> voList = new ArrayList<>();
		
		if(list!=null && !list.isEmpty()){
			for(ManifestPO tmp : list){
				if(type==DocumentsType.GOODSIN_DOCUMENTS){
					if(tmp.getType().equals("JHD")||tmp.getType().equals("JHTHD"))
						voList.add(new ManifestVO(tmp));
				}
				else if(type==DocumentsType.SALES_DOCUMENTS){
					if(tmp.getType().equals("XSD")||tmp.getType().equals("XSTHD"))
						voList.add(new ManifestVO(tmp));
				}		
			}
		}
		return voList;
	}
	
	//修改Manifest
	public ResultMessage modifyManifest(ManifestVO vo){
		
		return service.modifyManifest(new ManifestPO(vo));
	}

	@Override
	public ArrayList<ManifestPO> findManifestByTime(String startTime, String endTime) {
		
		return service.findByTime(startTime, endTime);
	}

	@Override
	public boolean setRedDashed(String type, String id) {
		ArrayList<ManifestPO> pos = service.find(id, "id");
		if(pos!=null&&pos.isEmpty()){
			return false;
		}
		
		ManifestPO newPO = pos.get(0);
		newPO.setID(getNextManifestID(newPO.getType()));	
		newPO.setState("Checked");
		newPO.setCreateDate(new SimpleDateFormat("yyyyMMdd").format(new Date()));
		newPO.setSum(-newPO.getSum());
		for(int i=0;i<newPO.getGoodsList().size();i++){
			ReciptGoodsPO goodsPO = newPO.getGoodsList().get(i);
			goodsPO.setAmounts(-goodsPO.getAmounts());
		}
		
		service.setManifest(newPO);
		manifestChecked(newPO);
	
		return true;
	}
}

