package main.Driver;

import java.rmi.RemoteException;

import main.BussinessLogicService.ReciptBLService.ReciptBLService;
import main.VO.CashBillVO;
import main.VO.CollectionOrderVO;
import main.VO.PaymentOrderVO;
import main.utility.ResultMessage;

public class ReciptBLService_Driver {
	
	public void drive(ReciptBLService recipBLService) throws RemoteException {
		CollectionOrderVO vo1 = new CollectionOrderVO();
		ResultMessage result = recipBLService.setCollection(vo1);
		if(result == ResultMessage.SUCCESS)
			System.out.println("设置收款单成功");
		else
			System.out.println("设置收款单失败");
		
		PaymentOrderVO vo2 = new PaymentOrderVO();
		result = recipBLService.setPaymentOrder(vo2);
		if(result == ResultMessage.SUCCESS)
			System.out.println("设置付款单成功");
		else
			System.out.println("设置付款单失败");
		
		CashBillVO vo3 = new CashBillVO();
		result = recipBLService.setCashBill(vo3);
		if(result == ResultMessage.SUCCESS)
			System.out.println("设置现金费用单成功");
		else
			System.out.println("设置现金费用单失败");
	}

}
