package main.Driver;

import java.rmi.RemoteException;

import main.BussinessLogicService.UserBLService.UserBLService;
import main.VO.UserVO;
import main.utility.UserType;

public class UserBLService_Driver {
	
	public void drive(UserBLService userBLService) throws RemoteException {
		UserVO vo = new UserVO();
		UserType result = userBLService.register(vo);
		if(result == UserType.ALREADY_EXIT)
			System.out.println("客户已存在");
		else if(result == UserType.FINANCIAL_STAFF)
			System.out.println("成功注册财务人员账号");
		else if(result == UserType.MANAGER)
			System.out.println("成功注册总经理账号");
		else if(result == UserType.SALES_MAN)
			System.out.println("成功注册销售人员账号");
		else if(result == UserType.STOCK_MANAGER)
			System.out.println("成功注册库存管理人员账号");
	}

}
