package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import main.BussinessLogic.CommodityBL.GoodsBL;
import main.BussinessLogic.ManifestBL.ManifestBL;
import main.Data.hibernateHelper.HibernateHelper;
import main.PO.GoodsPO;
import main.PO.ReciptGoodsPO;
import main.VO.ManifestVO;
import main.VO.ReciptGoodsVO;
import main.utility.ResultMessage;

public class ManifestBLTest {
		
	ManifestBL bl = new ManifestBL();
	GoodsPO po = null;
	
	@Before
	public void Start(){
		HibernateHelper.initHibernateHelper();
		ArrayList<GoodsPO> vos = new GoodsBL().find("", "id");
		assertNotEquals(null, vos.get(0));
		po = vos.get(0);
	}
	
	@Test
	public void test1(){
		ManifestVO vo = new ManifestVO();
		vo.setType("JHD");
		vo.setID(bl.getNextManifestID("JHD"));
		assertEquals(ResultMessage.SUCCESS,bl.setGoodsInList(vo));
	}
	
	@Test
	public void test2(){
		ManifestVO vo = new ManifestVO();
		vo.setType("JHTHD");
		vo.setID(bl.getNextManifestID("JHTHD"));
		assertEquals(ResultMessage.SUCCESS,bl.setGoodsInReturnList(vo));
	}
	
	@Test
	public void test3(){
		ManifestVO vo = new ManifestVO();
		vo.setType("XSD");
		vo.setID(bl.getNextManifestID("XSD"));
		ArrayList<ReciptGoodsVO> list = new ArrayList<>();
		list.add(new ReciptGoodsVO(new ReciptGoodsPO(po)));
		vo.setGoodsList(list);
		//assertEquals(ResultMessage.SUCCESS,bl.setSaleList(vo));
	}
	
	@Test
	public void test4(){
		ManifestVO vo = new ManifestVO();
		vo.setType("XSTHD");
		vo.setID(bl.getNextManifestID("XSTHD"));
		assertEquals(ResultMessage.SUCCESS,bl.setSaleReturnList(vo));
	}
	
	@Test 
	public void testServiceMethod(){
		assertNotEquals(null, bl.findManifestByTime("20180110", "20180113"));
	}
	
	@Test
	public void interfaceTest(){
		ArrayList<ReciptGoodsVO> t = bl.getSalesDetailList("20180104", "20180110", "日光灯", "杨袁瑞", "周正伟");
		assertNotEquals(null, t);
	}
	
}
