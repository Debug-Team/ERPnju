package main.RMI;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import main.BussinessLogic.AccountBL.AccountBLController;
import main.BussinessLogic.BankAccountBL.BankAccountBLController;
import main.BussinessLogic.CheckBL.CheckBLController;
import main.BussinessLogic.CommodityBL.CommodityController;
import main.BussinessLogic.CustomerBL.CustomerBLController;
import main.BussinessLogic.ManifestBL.ManifestBLController;
import main.BussinessLogic.PromotionBL.PromotionBLController;
import main.BussinessLogic.ReciptBL.ReciptBLController;
import main.BussinessLogic.RecordBL.RecordBLController;
import main.BussinessLogic.UserBL.UserBLController;
import main.BussinessLogicService.AccountBLService.AccountBLService;
import main.BussinessLogicService.BankAccountBLService.BankAccountBLService;
import main.BussinessLogicService.CheckBLService.CheckBLService;
import main.BussinessLogicService.CommodityBLService.CommodityBLService;
import main.BussinessLogicService.CommodityBLService.CommodityReciptBLService;
import main.BussinessLogicService.CommodityBLService.GoodsBLService;
import main.BussinessLogicService.CommodityBLService.GoodsCatagoryBLService;
import main.BussinessLogicService.CustomerBLService.CustomerBLService;
import main.BussinessLogicService.ManifestBLService.ManifestBLService;
import main.BussinessLogicService.PromotionBLService.PromotionBLService;
import main.BussinessLogicService.ReciptBLService.ReciptBLService;
import main.BussinessLogicService.RecordBLService.RecordBLService;
import main.BussinessLogicService.UserBLService.UserBLService;

public class RemoteHelper {
	public static String url = "rmi://127.0.0.1:8887/";
	
	public static void initServer() {
		try {
			LocateRegistry.createRegistry(8887);
			
//			TestRMIService trs = new TestRMIImpl();
//			Naming.rebind(url+"test", trs);

			//财务人员部分
			BankAccountBLService bankAccountBS = new BankAccountBLController();
			Naming.rebind(url+"BankAccountBL", bankAccountBS);

			ReciptBLService reciptBS = new ReciptBLController();
			Naming.rebind(url+"ReciptBL", reciptBS);
			
			AccountBLService accountBS = new AccountBLController();
			Naming.rebind(url+"AccountBL", accountBS);
			
			RecordBLService recordBS = new RecordBLController();
			Naming.rebind(url+"RecordBL", recordBS);
			
			//进货销售人员部分
			CustomerBLService customerBS = new CustomerBLController();
			Naming.rebind(url+"CustomerBL", customerBS);
			
			ManifestBLService manifestBS = new ManifestBLController();
			Naming.rebind(url+"ManifestBL", manifestBS);
			
			//库存管理人员部分
			CommodityBLService commodityBS = new CommodityController();
			Naming.rebind(url+"CommodityBL", commodityBS);
			
			CommodityReciptBLService commodityRCBS = new CommodityController();
			Naming.rebind(url+"CommodityReciptBL", commodityRCBS);
			
			GoodsBLService goodsBS = new CommodityController();
			Naming.rebind(url+"GoodsBL", goodsBS);
			
			GoodsCatagoryBLService goodscateBS = new CommodityController();
			Naming.rebind(url+"GoodsCatagoryBL", goodscateBS);
		
			//总经理部分
			CheckBLService checkBS = new CheckBLController();
			Naming.rebind(url+"CheckBL", checkBS);
			
			PromotionBLService promotionBS = new PromotionBLController();
			Naming.rebind(url+"PromotionBL", promotionBS);
			
			//管理员部分
			UserBLService loginBS = new UserBLController();
			Naming.rebind(url+"UserBL", loginBS);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
