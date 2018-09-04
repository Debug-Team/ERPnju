package test;

import java.util.ArrayList;

import main.BussinessLogic.CheckBL.CheckBL;
import main.Data.hibernateHelper.HibernateHelper;
import main.VO.InfoVO;
import main.VO.PaymentOrderVO;

public class InfoTest {
	
	public static void init() {
		HibernateHelper.initHibernateHelper();
		System.out.println("init");
	}
	
	public static void main(String[] args) {
		InfoTest.init();
		CheckBL bl = new CheckBL();
		InfoVO vo = bl.getInfo();
		ArrayList<PaymentOrderVO> temp = vo.getPaymentOrderList();
		for(int i = 0; i < temp.size(); i++) {
			System.out.println(temp.get(i).toString());
		}
	}

}
