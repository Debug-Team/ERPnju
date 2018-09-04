package main.BussinessLogic.Mock;

import java.util.ArrayList;

import main.BussinessLogic.ReciptBL.ReciptBL;
import main.PO.ReciptPO;

public class MockReciptBL extends ReciptBL {
	
	public ArrayList<String> getList(){
		ArrayList<String> temp = new ArrayList<String>();
		temp.add("test");
		return temp;
	}
	
	public ReciptPO getRecipt(String id) {
		return null;
	}
	
	public boolean modify(ReciptPO po) {
		return true;
	}
}
