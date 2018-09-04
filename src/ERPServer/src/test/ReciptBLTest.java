package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import main.BussinessLogic.ReciptBL.ReciptBL;
import main.Data.hibernateHelper.HibernateHelper;
import main.VO.CashBillVO;
import main.VO.CashItemVO;
import main.VO.CollectionItemVO;
import main.VO.CollectionOrderVO;
import main.VO.PaymentItemVO;
import main.VO.PaymentOrderVO;
import main.VO.ReciptVO;
import main.utility.ResultMessage;

public class ReciptBLTest {
	
	ReciptBL rb = new ReciptBL();
	
	@Before
	public void Start(){
		HibernateHelper.initHibernateHelper();
		System.out.println("before");
	}

//	@Test
//	public void testSetCashBillList() {
//		ArrayList<CashItemVO> itemList = new ArrayList<CashItemVO>();
//		CashBillVO vo = new CashBillVO("yyr", "123456", itemList, 123.123);
//		rb.setCashBill(vo);
//		assertEquals(ResultMessage.SUCCESS, rb.setCashBill(vo));
//	}
	
//	@Test
//	public void testSetCollectionOrderList() {
//		ArrayList<CollectionItemVO> itemList = new ArrayList<CollectionItemVO>();
//		CollectionItemVO temp = new CollectionItemVO("BankAccount01", 10, "");
//		itemList.add(temp);
//		CollectionOrderVO vo = new CollectionOrderVO("yyr", "zzw", "zyb", itemList, 256.256);
//		assertEquals(ResultMessage.SUCCESS, rb.setCollection(vo));
//	}
	
	@Test
	public void testSetPaymentOrderList() {
		//ArrayList<PaymentItemVO> itemList = new ArrayList<PaymentItemVO>();
		//PaymentOrderVO vo = new PaymentOrderVO("yyr", "zzw", "zyb", itemList, 137.137);
		//assertEquals(ResultMessage.SUCCESS, rb.setPaymentOrder(vo));
		//PaymentOrderVO vo = (PaymentOrderVO)rb.findOne("FKD-20180105-00000");
	}
	
}
