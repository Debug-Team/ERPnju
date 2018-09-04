package test;

import org.junit.Before;
import org.junit.Test;

import main.BussinessLogic.AccountBL.AccountBL;
import main.Data.hibernateHelper.HibernateHelper;
import main.VO.AccountVO;

public class AccountBLTest {

	AccountBL bl = new AccountBL();
	
	@Before
	public void Start(){
		HibernateHelper.initHibernateHelper();
		System.out.println("before");
	}
	
	@Test
	public void testSet() {
		AccountVO vo = bl.get();
		System.out.println(vo.getOperator());
		System.out.println(vo.getBankAccountList().size());  //执行到这一步说明正常
	}
	
	@Test
	public void testDelet() {
		
	}
	
}
