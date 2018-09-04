//package main.BussinessLogic.Mock;
//
//import java.util.ArrayList;
//
//import main.BussinessLogic.ReciptBL.CashBill;
//import main.PO.CashBillPO;
//import main.VO.CashBillVO;
//import main.VO.CashItemVO;
//
//public class MockCashBill extends CashBill{
//	private String id;
//	private String operator;
//	private String bankAccountID;
//	private CashList itemList;
//	private double sum;
//	
//	private CashBillPO orderPO;
//	
//	public MockCashBill(CashBillVO vo) {
//		id = vo.getId();
//		operator = vo.getOperator();
//		bankAccountID = vo.getBankAccountID();
//		itemList = new CashList(vo.getItemList());
//		
////		orderPO = new CashBillPO(id, operator, bankAccountID, vo.getItemList(), sum);
//	}
//	
//	public class CashList{
//		ArrayList<CashLineItem> list = new ArrayList<CashLineItem>();
//		
//		public CashList(ArrayList<CashItemVO> l) {
//			for(CashItemVO item : l) {
//				list.add(new CashLineItem(item.getName(),item.getAmount(),item.getComment()));
//			}
//		}
//	}
//
//	public class CashLineItem{
//		String name;
//		double amount;
//		String comment;
//		
//		public CashLineItem(String name, double amount, String comment){
//			this.name = name;
//			this.amount = amount;
//			this.comment = comment;
//		}
//	}
//}
//
