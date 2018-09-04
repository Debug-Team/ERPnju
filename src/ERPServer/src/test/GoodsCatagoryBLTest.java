package test;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import main.BussinessLogic.CommodityBL.GoodsCatagoryBL;
import main.Data.hibernateHelper.HibernateHelper;
import main.VO.CategoryVO;

public class GoodsCatagoryBLTest {

	GoodsCatagoryBL bl = new GoodsCatagoryBL();
	
	@Before
	public void Start(){
		HibernateHelper.initHibernateHelper();
	}
	
	@Test
	public void testCatagoryAdd() {
		ArrayList<CategoryVO> list = new ArrayList<>();
		bl.catagoryAdd(new CategoryVO(list, "test", false, "All", "", "test", false));
		bl.catagoryAdd(new CategoryVO(list, "test_sub", false, "test", "", "test", false));
		
	}
	
	@Test
	public void t1(){
		bl.catagoryAdd(new CategoryVO(new ArrayList<>(), "B", false, "All", "", "test",false));
	}
	
	@Test
	public void t2(){
		bl.catagoryAdd(new CategoryVO(new ArrayList<>(), "C", false, "B", "", "test",false));
		bl.catagoryAdd(new CategoryVO(new ArrayList<>(), "D", false, "C", "", "test",false));
		bl.catagoryAdd(new CategoryVO(new ArrayList<>(), "E", false, "D", "", "test",false));
	}


}
