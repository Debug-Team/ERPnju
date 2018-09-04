package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import main.BussinessLogic.BankAccountBL.BankAccountBL;
import main.Data.hibernateHelper.HibernateHelper;
import main.VO.BankAccountVO;
import main.utility.ResultMessage;

public class BankAccountTest {

	BankAccountBL bl = new BankAccountBL();
	
	@Before
	public void Start(){
		HibernateHelper.initHibernateHelper();
		System.out.println("before");
	}
	
	@Test
	public void test01() {
		// test add and find.
		BankAccountVO vo = new BankAccountVO("test01", 123.456, "operator01");
		bl.add(vo);
		assertEquals(123.456, bl.find("test01").getAmount(), 0.0);
		bl.delete("test01");
	}
	
	@Test
	public void test02() {
		// test add and find.
		BankAccountVO vo = new BankAccountVO("test02", 20.05, "operator02");
		bl.add(vo);
		assertEquals(20.05, bl.find("test02").getAmount(), 0.0);
		bl.delete("test02");
	}
	
	@Test
	public void test03() {
		// test add and delete.
		BankAccountVO vo = new BankAccountVO("test03", 58.23, "operator03");
		bl.add(vo);
		assertEquals(ResultMessage.SUCCESS, bl.delete("test03"));
	}
	
	@Test
	public void test04() {
		// test add and modify.
		BankAccountVO vo = new BankAccountVO("test04", 20.05, "operatorX");
		bl.add(vo);
		vo = new BankAccountVO("test04", 19.98, "operatorX");
		bl.modify(vo);
		assertEquals(19.98, bl.find("test04").getAmount(), 0.0);
		bl.delete("test04");
	}
	
	@Test
	public void test05() {
		// test add and modify.
		BankAccountVO vo = new BankAccountVO("test05", 123, "operatorP");
		bl.add(vo);
		vo = new BankAccountVO("test05", 666, "operatorX");
		bl.modify(vo);
		assertEquals(666, bl.find("test05").getAmount(), 0.0);
		bl.delete("test05");
	}
	
	@Test
	public void test06() {
		// test add and find.
		BankAccountVO vo = new BankAccountVO("test06", 123.456, "operator01");
		bl.add(vo);
		assertEquals("test06", bl.find("test06").getId());
		bl.delete("test06");
	}
	
}
