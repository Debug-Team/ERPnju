package main.Driver;

import java.rmi.RemoteException;

import main.BussinessLogicService.BankAccountBLService.BankAccountBLService;
import main.VO.BankAccountVO;
import main.utility.ResultMessage;

public class BankAccountBLService_Driver {

	public void drive(BankAccountBLService bankAccountBLService) throws RemoteException {
		BankAccountVO vo = new BankAccountVO("test", 123, "yyr");
		ResultMessage result = bankAccountBLService.add(vo);
		if(result == ResultMessage.SUCCESS)
			System.out.println("添加银行账号成功");
		else 
			System.out.println("添加银行账号失败");
		
		vo = new BankAccountVO("test", 256, "yyr");
		result = bankAccountBLService.modify(vo);
		if(result == ResultMessage.SUCCESS)
			System.out.println("更改银行账号成功");
		else 
			System.out.println("更改银行账号失败");
		
		result = bankAccountBLService.delete("test", "yyr");
		if(result == ResultMessage.SUCCESS)
			System.out.println("批量删除银行账号成功");
		else 
			System.out.println("批量删除银行账号失败");
	}
	
}
