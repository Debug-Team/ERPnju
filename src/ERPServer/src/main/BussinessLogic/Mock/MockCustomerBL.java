package main.BussinessLogic.Mock;

import java.util.ArrayList;

import main.BussinessLogic.CustomerBL.CustomerBL;
import main.VO.CustomerVO;

public class MockCustomerBL extends CustomerBL {
	public ArrayList<CustomerVO> getList(){
		ArrayList<CustomerVO> temp = new ArrayList<CustomerVO>();
		CustomerVO vo1 = new CustomerVO("1", "ะกร๗", 1, 1, 1);
		temp.add(vo1);
		return temp;
	}
}
