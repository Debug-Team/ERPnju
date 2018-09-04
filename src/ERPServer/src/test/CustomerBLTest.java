package test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import main.BussinessLogic.CustomerBL.CustomerBL;
import main.Data.hibernateHelper.HibernateHelper;
import main.PO.CustomerPO;
import main.VO.CustomerVO;
import main.utility.ResultMessage;

public class CustomerBLTest {
	
	CustomerBL bl = new CustomerBL();
	CustomerPO po;
	
	@Before
	public void Start(){
		HibernateHelper.initHibernateHelper();
	}
	
	@Test
	public void testl(){
	
		CustomerVO vo = new CustomerVO("", "进货商", "5", "zzw", "110", "NJU xianlinCampus", "zzw@163.com", 0, 0, 0, "zzw", "date", "xx1");
		bl.customerAdd(vo);
		
		assertEquals("zzw", bl.findByName("zzw").getName());
		assertEquals(ResultMessage.SUCCESS, bl.customerDelete(new CustomerVO(bl.findCustomer("zzw"))));
	}
	
	@Test
	public void test2(){
		CustomerVO vo = new CustomerVO("", "进货商", "5", "zzw", "110", "NJU xianlinCampus", "zzw@163.com", 0, 0, 0, "zzw", "date", "xx1");
		vo.setAddress("GulouCampus");
		bl.customerAdd(vo);
		
		assertEquals(ResultMessage.SUCCESS, bl.customerModify(vo));
		assertEquals("GulouCampus", bl.findByName("zzw").getAddress());
		
		bl.customerDelete(vo);
	}

}
