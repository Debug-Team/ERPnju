package test;

import static org.junit.Assert.*;
import org.junit.Test;

import main.BussinessLogic.Mock.MockCheckBL;
import main.VO.InfoVO;
import main.utility.ResultMessage;;

public class CheckBLTest {
	
	MockCheckBL cb = new MockCheckBL();
	
	@Test
	public void testGetInfo() {
		assertEquals(null, cb.getInfo());
	}
	
	@Test
	public void testSetGiftList() {
		assertEquals(ResultMessage.SUCCESS, cb.setSuggestion(new InfoVO()));
	}
}
