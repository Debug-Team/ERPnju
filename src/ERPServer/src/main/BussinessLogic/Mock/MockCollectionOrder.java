//package main.BussinessLogic.Mock;
//
//import java.util.ArrayList;
//
//import main.BussinessLogic.ReciptBL.CollectionOrder;
//import main.PO.CollectionOrderPO;
//import main.VO.CollectionItemVO;
//import main.VO.CollectionOrderVO;
//
//public class MockCollectionOrder extends CollectionOrder{
//	private String id;
//	private String operator;
//	private TransferList transferList;
//	private double sum;
//	
//	private CollectionOrderPO orderPO;
//	
//	public MockCollectionOrder(CollectionOrderVO vo) {
////		id = vo.getID();
////		operator = vo.getOperator();
////		transferList = new TransferList(vo.getItemList());
////		
////		orderPO = new CollectionOrderPO(vo.getId(),vo.getOperator(),vo.getItemList(),vo.getSum());
//	}
//	
//	public class TransferList{
//		ArrayList<TransferLineItem> list = new ArrayList<TransferLineItem>();
//		
//		public TransferList(ArrayList<CollectionItemVO> l) {
//			for(CollectionItemVO item : l) {
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
