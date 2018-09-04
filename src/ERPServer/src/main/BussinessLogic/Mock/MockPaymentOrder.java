//package main.BussinessLogic.Mock;
//
//import java.util.ArrayList;
//
//import main.BussinessLogic.ReciptBL.PaymentOrder;
//import main.PO.PaymentOrderPO;
//import main.VO.PaymentItemVO;
//import main.VO.PaymentOrderVO;
//
//public class MockPaymentOrder extends PaymentOrder{
//	private String id;
//	private String operator;
//	private TransferList transferList;
//	private double sum;
//	
//	private PaymentOrderPO orderPO;
//	
//	public MockPaymentOrder(PaymentOrderVO vo) {
//		id = vo.getId();
//		operator = vo.getOperator();
//		transferList = new TransferList(vo.getItemList());
//		
////		orderPO = new PaymentOrderPO(vo.getID(),vo.getOperator(),vo.getItemList(),vo.getSum());
//	}
//	
//	public class TransferList{
//		ArrayList<TransferLineItem> list = new ArrayList<TransferLineItem>();
//		
//		public TransferList(ArrayList<PaymentItemVO> l) {
//			for(PaymentItemVO item : l) {
//				list.add(new TransferLineItem(item.getBankAccountID(),item.getAmount(),item.getComment()));
//			}
//		}
//	}
//
//	public class TransferLineItem{
//		String bankAccountID;
//		double amount;
//		String comment;
//		
//		public TransferLineItem(String bankAccountID, double amount, String comment){
//			this.bankAccountID = bankAccountID;
//			this.amount = amount;
//			this.comment = comment;
//		}
//	}
//}
//
