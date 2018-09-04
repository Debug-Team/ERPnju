package main.DataService.CommodityDataService;

import java.util.ArrayList;

import main.PO.CategoryPO;

public interface CatagoryDataService {
	/**
	 * 新增商品分类
	 * @param po
	 * @return 操作是否成功
	 */
	public boolean catagoryAdd(CategoryPO po);
	
	/**
	 * 删除商品分类
	 * @param po
	 * @return 操作是否成功
	 */
	public boolean catagoryDelete(CategoryPO po);
	
	/**
	 * 修改商品分类
	 * @param po
	 * @return 操作是否成功
	 */
	public boolean catagoryModify(CategoryPO po);
	
	/**
	 * 通过分类名找到分类PO（因查询语句限制直接返回未做筛选）
	 * @param catagoryName
	 * @return ArrayList<CategoryPO>
	 */
	public ArrayList<CategoryPO> catagoryFind(String catagoryName);
	
}
