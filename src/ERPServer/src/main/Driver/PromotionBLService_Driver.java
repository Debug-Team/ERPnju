package main.Driver;

import java.rmi.RemoteException;
import java.util.ArrayList;

import main.BussinessLogicService.PromotionBLService.PromotionBLService;
import main.VO.PromotionVO;
import main.utility.ResultMessage;

public class PromotionBLService_Driver {
	
	public void drive(PromotionBLService promotionBLService) throws RemoteException {
		PromotionVO vo = new PromotionVO();
		ResultMessage result = promotionBLService.setPromotion(vo, "yyr");
		if(result == ResultMessage.SUCCESS)
			System.out.println("添加促销策略成功");
		else 
			System.out.println("添加促销策略失败");
		
		vo = new PromotionVO();
		result = promotionBLService.modifyPromotion(vo, "yyr");
		if(result == ResultMessage.SUCCESS)
			System.out.println("修改促销策略成功");
		else 
			System.out.println("修改促销策略失败");
		
		result = promotionBLService.deletePromotions(new ArrayList<String>(), "yyr");
		if(result == ResultMessage.SUCCESS)
			System.out.println("删除促销策略成功");
		else 
			System.out.println("删除促销策略失败");
	}

}
