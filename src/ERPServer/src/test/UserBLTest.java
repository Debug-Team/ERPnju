package test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import main.BussinessLogic.UserBL.UserBL;
import main.Data.hibernateHelper.HibernateHelper;
import main.PO.UserPO;
import main.utility.UserType;

public class UserBLTest {
	
	@Before
	public void Start(){
		HibernateHelper.initHibernateHelper();
		System.out.println("before");
	}
	
	UserBL bl = new UserBL();
	
	@Test
	public void test01() {
		UserPO po = new UserPO("zzw", "male", 19, "sm-000001", "123456", "StockManager", false);
		bl.register(po);
		assertEquals(UserType.STOCK_MANAGER, bl.login("sm-000001", "123456"));
		bl.deleteUser(po);
	}
	
	@Test
	public void test02() {
		UserPO po = new UserPO("yyr", "male", 19, "fs-000001", "123456", "FinancialStaff", true);
		bl.register(po);
		assertEquals(UserType.FINANCIAL_STAFF, bl.login("fs-000001", "123456"));
		bl.deleteUser(po);
	}
	
	@Test
	public void test03() {
		UserPO po = new UserPO("wyl", "male", 18, "ma-000001", "123456", "Manager", true);
		bl.register(po);
		assertEquals(UserType.MANAGER, bl.login("ma-000001", "123456"));
		bl.deleteUser(po);
	}
	
	@Test
	public void test04() {
		UserPO po = new UserPO("zyb", "male", 19, "sa-000001", "123456", "SalesMan", false);
		bl.register(po);
		assertEquals(UserType.SALES_MAN, bl.login("sa-000001", "123456"));
		bl.deleteUser(po);
	}
	
	@Test
	public void test05() {
		UserPO po = new UserPO("zyb", "male", 19, "sa-000001", "123456", "SalesMan", false);
		bl.register(po);
		assertEquals(UserType.PASSWORD_WRONG, bl.login("sa-000001", "891613"));
		bl.deleteUser(po);
	}
	
	@Test
	public void test06() {
		assertEquals(UserType.NOT_FOUND, bl.login("5315165", "123456"));
	}
	
	@Test
	public void test07() {
		UserPO po = new UserPO("zyb", "male", 19, "sa-000001", "123456", "SalesMan", false);
		bl.register(po);
		assertEquals(UserType.ALREADY_EXIT, bl.register(po));
		bl.deleteUser(po);
	}

}
