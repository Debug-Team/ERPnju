package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import main.BussinessLogic.CommodityBL.CommodityReciptBL;
import main.BussinessLogic.CommodityBL.GoodsBL;
import main.Data.hibernateHelper.HibernateHelper;
import main.PO.GoodsPO;
import main.VO.CommodityReciptVO;
import main.utility.ResultMessage;

public class CommodityReciptBLTest {
	
	CommodityReciptBL bl = new CommodityReciptBL();
	GoodsPO po = null;
	@Before
	public void Start(){
		HibernateHelper.initHibernateHelper();
		ArrayList<GoodsPO> vos = new GoodsBL().find("", "id");
		assertNotEquals(null, vos.get(0));
		po = vos.get(0);
	}
	
	@Test
	public void testSetGiftList() {
		CommodityReciptVO vo =new CommodityReciptVO("ZS", po.getName(), po.getID(), 1, "", "Unchecked", "Test");
		assertEquals(ResultMessage.SUCCESS, bl.setGiftList(vo));
	}

	@Test
	public void testSetOverflowList() {
		CommodityReciptVO vo =new CommodityReciptVO("BY", po.getName(), po.getID(), 1, "", "Unchecked", "Test");
		assertEquals(ResultMessage.SUCCESS, bl.setOverflowList(vo));
	}

	@Test
	public void testSetDamageList() {
		CommodityReciptVO vo =new CommodityReciptVO("BS", po.getName(), po.getID(), 1, "", "Unchecked", "Test");
		assertEquals(ResultMessage.SUCCESS, bl.setDamageList(vo));
	}

	@Test
	public void testSetWarningList() {
		CommodityReciptVO vo =new CommodityReciptVO("BJ", po.getName(), po.getID(), 1, "", "Unchecked", "Test");
		assertEquals(ResultMessage.SUCCESS, bl.setDamageList(vo));
	}
	
	@Test
	public void testServiceMethod1(){
		assertNotEquals(null, bl.getcheckedCommodityRecipt());
	}
	
	public void testServiceMethod2(){
		assertNotEquals(null, bl.getUncheckedCommodityRecipt());
	}

}
