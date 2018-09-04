package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.BussinessLogic.CommodityBL.CommodityBL;
import main.Data.hibernateHelper.HibernateHelper;
import main.VO.CommodityInfoVO;

public class CommodityBLTest {
	
	CommodityBL bl = new CommodityBL();
	
	@Before
	public void Start(){
		HibernateHelper.initHibernateHelper();
	}

	@Test
	public void testViewCommodity() {
		CommodityInfoVO vo = bl.viewCommodity("20171225", "20180112");
		assertNotEquals(null, vo);
	}

	@Test
	public void testCheckCommodity() {
		bl.checkCommodityInfoToExcel("x", "");
	}

}
