package test;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import main.BussinessLogic.CommodityBL.GoodsBL;
import main.Data.hibernateHelper.HibernateHelper;
import main.VO.GoodsVO;

public class GoodsBLTest {

	GoodsBL bl = new GoodsBL();
	
	@Before
	public void Start(){
		HibernateHelper.initHibernateHelper();
	}
	
	@Test
	public void test1(){
		bl.goodsAdd(new GoodsVO("x25灯", "", "x1", 50, 1, 2, 3, 4, "分类2", 2.5, "00"));
	}
	
	@Test
	public void test2(){
		ArrayList<GoodsVO> list = bl.goodsFind("x25灯", "name");
		list.get(0).setAlertAmounts(100);
		bl.goodsModify(list.get(0));
	}

	@Test
	public void test3(){
		ArrayList<GoodsVO> list = bl.goodsFind("x25灯", "name");
		list.get(0).setAlertAmounts(100);
		bl.goodsDelete(list.get(0));
	}
	
	@Test
	public void test4(){
		bl.goodsAdd(new GoodsVO("x26灯", "", "x1", 50, 1, 2, 3, 4, "分类2", 2.5, "00"));
		bl.goodsAdd(new GoodsVO("x27灯", "", "x1", 50, 1, 2, 3, 4, "分类2", 2.5, "00"));
		bl.goodsDelete(bl.goodsFind("x27灯", "name").get(0));
		bl.goodsDelete(bl.goodsFind("x26灯", "name").get(0));
	}

}
