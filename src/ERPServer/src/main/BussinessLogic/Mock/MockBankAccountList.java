//package main.BussinessLogic.Mock;
//
//import java.util.ArrayList;
//
//import main.BussinessLogic.BankAccountBL.BankAccountList;
//import main.PO.BankAccountPO;
//import main.VO.BankAccountVO;
//
//public class MockBankAccountList extends BankAccountList{
//	
//	private ArrayList<BankAccountItem> list = new ArrayList<BankAccountItem>();
//	
////	public ArrayList<BankAccountItem> getBankAccountList(){
////		return list;
////	}
//	
//	public boolean add(BankAccountVO vo) {
//		return list.add(new BankAccountItem(vo.getId(),vo.getAmount()));
//	}
//	
//	public boolean delete(String id) {
//		list.add(new BankAccountItem(id, 0));
//		list.get(0).delete();
//		return list.remove(list.get(0));
//	}
//	
////	public boolean modify(BankAccountVO vo) {
////		list.add(new BankAccountItem(vo.getID(),vo.getAmount()));
////		return list.get(0).modify(vo);
////	}
//	
//}
//
//class BankAccountItem {
//	private BankAccountPO bankAccount;
//	
//	public BankAccountItem(BankAccountPO po){
//		bankAccount = po;
//	}
//	
//	public BankAccountItem(String name, double amout) {
//		bankAccount = new BankAccountPO(name, amout);
//	}
//	
//	public BankAccountPO getBankAccountPO () {
//		return bankAccount;
//	}
//	
//	public String getID() {
//		return bankAccount.getId();
//	}
//	
//	public boolean delete() {
//		bankAccount = null;
//		return true;
//	}
//	
////	public boolean modify(BankAccountVO vo) {
////		return bankAccount.modify(vo.getID(), vo.getAmount());
////	}
//}
