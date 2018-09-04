package main.Driver;

import java.rmi.RemoteException;

import main.BussinessLogic.AccountBL.AccountBLController;
import main.BussinessLogic.BankAccountBL.BankAccountBLController;
import main.BussinessLogic.PromotionBL.PromotionBLController;
import main.BussinessLogic.ReciptBL.ReciptBLController;
import main.BussinessLogic.UserBL.UserBLController;
import main.BussinessLogicService.AccountBLService.AccountBLService;
import main.BussinessLogicService.BankAccountBLService.BankAccountBLService;
import main.BussinessLogicService.PromotionBLService.PromotionBLService;
import main.BussinessLogicService.ReciptBLService.ReciptBLService;
import main.BussinessLogicService.UserBLService.UserBLService;
import main.Data.hibernateHelper.HibernateHelper;

public class Client {
	
	public static void init(){
		HibernateHelper.initHibernateHelper();
		System.out.println("init");
	}

	public static void main(String[] args) throws RemoteException {
		Client.init();
		//期初建账
		AccountBLService accountBLController = new AccountBLController();
		AccountBLService_Driver accountDriver = new AccountBLService_Driver();
		accountDriver.drive(accountBLController);
		//银行账户管理
		BankAccountBLService bankAccountBLService = new BankAccountBLController();
		BankAccountBLService_Driver bankAccountDriver = new BankAccountBLService_Driver();
		bankAccountDriver.drive(bankAccountBLService);
		//财务类单据
		ReciptBLService reciptBLService = new ReciptBLController();
		ReciptBLService_Driver reciptDriver = new ReciptBLService_Driver();
		reciptDriver.drive(reciptBLService);
		//销售策略
		PromotionBLService promotionBLService = new PromotionBLController();
		PromotionBLService_Driver promotionDriver = new PromotionBLService_Driver();
		promotionDriver.drive(promotionBLService);
		//客户管理
		UserBLService userBLService = new UserBLController();
		UserBLService_Driver userDriver = new UserBLService_Driver();
		userDriver.drive(userBLService);
	}

}
