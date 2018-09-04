package main.BussinessLogic.CommodityBL;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import main.Data.CommodityData.CommodityReciptData;
import main.Data.CommodityData.GoodsData;
import main.DataService.CommodityDataService.CommodityReciptDataService;
import main.PO.CommodityReciptPO;
import main.PO.GoodsPO;
import main.VO.CommodityReciptVO;
import main.utility.ResultMessage;
/**
 * 
 * @author 周益冰
 *
 */
public class CommodityReciptBL{
	
	CommodityReciptDataService service;
	
	public CommodityReciptBL(){
		service = new CommodityReciptData();
	}

	//保存库存赠送单
	public ResultMessage setGiftList(CommodityReciptVO vo) {
		
		setDate(vo);		
		CommodityReciptPO po = new CommodityReciptPO(vo);
		
		return service.add(po); 
	}

	//保存库存报溢单
	public ResultMessage setOverflowList(CommodityReciptVO vo){
		
		setDate(vo);		
		CommodityReciptPO po = new CommodityReciptPO(vo);
		
		return service.add(po);
	}

	//保存库存报损单
	public ResultMessage setDamageList(CommodityReciptVO vo){
		
		setDate(vo);		
		CommodityReciptPO po = new CommodityReciptPO(vo);
		
		return service.add(po);
	}

	//保存报警单
	public ResultMessage setWarningList(CommodityReciptVO vo){
		
		setDate(vo);		
		CommodityReciptPO po = new CommodityReciptPO(vo);
		
		return service.add(po);
	}
	
	//为VO添加创建时间
	public void setDate(CommodityReciptVO vo){
		String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
		vo.setCreateDate(date);
	}
	
	//得到没有审批的库存单据
	public ArrayList<CommodityReciptPO> getUncheckedCommodityRecipt(){
		
		return service.find("Unchecked", "state");

	}
	
	
	 //库存单据没有客户名和操作员，如两参数不为“” 则不返回库存单数据 
	public ArrayList<CommodityReciptVO> getCheckedCommodityRecipt(String startTime, String endTime, String customerName,
			String operator){
		ArrayList<CommodityReciptPO> list = service.find(startTime, endTime, customerName, operator);
		ArrayList<CommodityReciptVO> voList = new ArrayList<>();
		
		if(customerName.equals("")&&operator.equals("")){
			if(list!=null && !list.isEmpty()){
				for(CommodityReciptPO tmp : list){
					voList.add(new CommodityReciptVO(tmp));
				}
			}
		}
		return voList;
	}
	
	//保存库存单据
	public ResultMessage saveCommodityRecipt(ArrayList<CommodityReciptPO> list){
		
		if(list!=null && !list.isEmpty()){
			for(CommodityReciptPO po : list){
				if(po.getState().equals("Checked")){
					checkSingleRecipt(po);
				}
			}
		}
		
		return service.modify(list);	
		
	}

	//得到审批过的单据
	public ArrayList<CommodityReciptPO> getcheckedCommodityRecipt() {
		
		return service.find("Checked", "state");
	}

	//审批单个库存单据
	private void checkSingleRecipt(CommodityReciptPO po){
		GoodsBL bl = new GoodsBL();
		ArrayList<GoodsPO> list = null;
		int changedNum = po.getChangedNumbers();
		
		if(po.getType().equals("BS")){
			if(!(list = bl.find(po.getGoodsID(), "ID")).isEmpty()){
				GoodsPO goods = list.get(0);
				int setNum = (int) (goods.getAmounts() - changedNum);
				goods.setAmounts(setNum>0 ? setNum : 0);
				new GoodsData().modify(goods);
			}
		}
		else if(po.getType().equals("BY")){
			if(!(list = bl.find(po.getGoodsID(), "ID")).isEmpty()){
				GoodsPO goods = list.get(0);
				goods.setAmounts(goods.getAmounts() + changedNum);
				new GoodsData().modify(goods);
			}
		}
		else if(po.getType().equals("ZS")){
			if(!(list = bl.find(po.getGoodsID(), "ID")).isEmpty()){
				GoodsPO goods = list.get(0);
				int setNum = (int) (goods.getAmounts() - changedNum);
				goods.setAmounts(setNum>0 ? setNum : 0);
				new GoodsData().modify(goods);
			}
		}
		else if(po.getType().equals("BJ")){
			if(!(list = bl.find(po.getGoodsID(), "ID")).isEmpty()){
				GoodsPO goods = list.get(0);
				goods.setAlertAmounts(changedNum);
				new GoodsData().modify(goods);
			}
		}
	}
}
