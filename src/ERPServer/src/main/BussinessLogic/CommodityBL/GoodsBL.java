package main.BussinessLogic.CommodityBL;

import java.util.ArrayList;

import main.BussinessLogic.ManifestBL.ManifestBL;
import main.BussinessLogic.ManifestBL.ManifestService;
import main.Data.CommodityData.GoodsData;
import main.DataService.CommodityDataService.GoodsDataService;
import main.PO.GoodsPO;
import main.PO.ManifestPO;
import main.PO.ReciptGoodsPO;
import main.VO.GoodsVO;
import main.utility.ResultMessage;
/**
 * 
 * @author 周益冰
 *
 */
public class GoodsBL {

	GoodsDataService service = new GoodsData();


	//商品新增
	public ResultMessage goodsAdd(GoodsVO vo) {
		GoodsPO po = new GoodsPO(vo);
		String ID = new GoodsCatagoryBL().getCatagoryID(po.getCatagory());
		ID += "-" + String.format("%04d", service.newID(po.getCatagory()));
		po.setID(ID);
		
		//TODO 同名&同型号商品不能添加
		if(service.add(po)) 
			return ResultMessage.SUCCESS;
		return ResultMessage.FAIL;
	}	

	//商品删除
	public ResultMessage goodsDelete(GoodsVO vo) {
		GoodsPO po = new GoodsPO(vo);
		if(service.delete(po)) 
			return ResultMessage.SUCCESS;
		return ResultMessage.FAIL;
	}

	//商品修改
	public ResultMessage goodsModify(GoodsVO vo) {
		GoodsPO po = new GoodsPO(vo);
		if(service.modify(po)) 
			return ResultMessage.SUCCESS;
		return ResultMessage.FAIL;
	}

	//查找商品，通过类型和关键字，返回goodsVO
	public ArrayList<GoodsVO> goodsFind(String keyword, String type){
		ArrayList<GoodsPO> list = service.find(keyword, type);
		ArrayList<GoodsVO> retlist = new ArrayList<>();
		for(GoodsPO po : list){
			retlist.add(new GoodsVO(po));
		}
		
		return retlist;
	}
	
	//查找商品，通过类型和关键字，返回goodsPO
	public ArrayList<GoodsPO> find(String keyword, String type){
		return service.find(keyword, type);
	}

	//更新商品库存
	public ResultMessage updateCommodity(ArrayList<ReciptGoodsPO> list,String type, String lebal) {
		return service.updateCommodity(list, type, lebal);
	}

	//得到下一个商品ID
	public String getNextGoodsId(String category) {
		String ID = new GoodsCatagoryBL().getCatagoryID(category);
		ID += "-" + String.format("%04d", service.newID(category));
		return ID;
	}
	
	//判断商品是否能删除
	public boolean couldBeDeleted(String goodID){
		ManifestService service = new ManifestBL();
		ArrayList<ManifestPO> unchecked = service.getCheckedManifest();
		
		for(int i=0;i<unchecked.size();i++){
			ManifestPO po = unchecked.get(i);
			for(int j=0;j<po.getGoodsList().size();j++){
				ReciptGoodsPO goodsPO = po.getGoodsList().get(j);
				if(goodsPO.getGoodsID().equals(goodID))
					return false;
			}
		}
		
		return true;
	}

}
