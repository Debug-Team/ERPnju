package main.Driver;

import java.rmi.RemoteException;

import main.BussinessLogicService.AccountBLService.AccountBLService;
import main.VO.AccountVO;
import main.utility.ResultMessage;

public class AccountBLService_Driver {
	
	public void drive(AccountBLService accountBLService) throws RemoteException {
		AccountVO vo = new AccountVO();
		ResultMessage result = accountBLService.set(vo);
		if(result == ResultMessage.SUCCESS)
			System.out.println("新建期初信息成功");
		else
			System.out.println("新建期初信息失败");
		result = accountBLService.update(vo);
		if(result == ResultMessage.SUCCESS)
			System.out.println("更新期初信息成功");
		else
			System.out.println("更新期初信息失败");
	}

}
