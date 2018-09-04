package main.BussinessLogic.CommodityBL;

import java.util.ArrayList;
import java.util.UUID;

import main.Data.CommodityData.CatagoryData;
import main.DataService.CommodityDataService.CatagoryDataService;
import main.PO.CategoryPO;
import main.VO.CategoryVO;
import main.VO.GoodsVO;
import main.utility.ResultMessage;
/**
 * 
 * @author 周益冰
 *
 */
public class GoodsCatagoryBL{
	
	CatagoryDataService service;

	public GoodsCatagoryBL(){
		service = new CatagoryData();
	}

	//新增分类
	public ResultMessage catagoryAdd(CategoryVO vo){
		
		//添加ID
		CategoryPO po = new CategoryPO(vo);
		String random = UUID.randomUUID().toString();
		String id = random.substring(random.length()-6);
		
		po.setCategoryID(id);
		
		//在父类po中添加该分类，即添加了该分类，cascade = all 
		ArrayList<CategoryPO> fatherList = service.catagoryFind(po.getFatherStr());
		CategoryPO father = null;
		if(fatherList!=null && !fatherList.isEmpty()){
			father = fatherList.get(0);
		}
		
		if(father!=null){
			father.getSonscategory().add(po);
			service.catagoryModify(father);
		}

		return ResultMessage.SUCCESS;

	}

	//商品删除
	public ResultMessage catagoryDelete(CategoryVO vo){

		if(service.catagoryDelete(new CategoryPO(vo)))
			return ResultMessage.SUCCESS;
		else 
			return ResultMessage.FAIL;
	}

	//商品修改
	public ResultMessage catagoryModify(CategoryVO vo){
		
		if(vo.getSonscategory()!=null && !vo.getSonscategory().isEmpty()){
			for(CategoryVO tmp : vo.getSonscategory()){
				tmp.setFatherStr(vo.getMyvalue());
				service.catagoryModify(new CategoryPO(tmp));
			}
		}
		
		ArrayList<GoodsVO> list = new GoodsBL().goodsFind(vo.getCategoryID(), "ID");
		if(list!=null && !list.isEmpty()){
			for(GoodsVO tmp : list){
				tmp.setCatagory(vo.getMyvalue());
				new GoodsBL().goodsModify(tmp);
			}
		}
		
		if(service.catagoryModify(new CategoryPO(vo)))
			return ResultMessage.SUCCESS;
		else 
			return ResultMessage.FAIL;
	}
	
	//返回所有分来
	public ArrayList<CategoryVO> categoryAll(){
		ArrayList<CategoryPO> list =  service.catagoryFind("");
		ArrayList<CategoryVO> retlist = new ArrayList<>();
		if(list!=null && !list.isEmpty()){
			for(CategoryPO tmp : list){
				retlist.add(new CategoryVO(tmp));
			}
		}
		return retlist;
	}
	
	//得到分类名的ID
	public String getCatagoryID(String catagoryName){
		
		ArrayList<CategoryPO> list = service.catagoryFind(catagoryName);
		if(list!=null && !list.isEmpty())
			return list.get(0).getCategoryID();
		else
			return null;
	}

}
