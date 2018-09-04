package main.RMI;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import main.BussinessLogicService.CustomerBLService.CustomerBLService;
import main.BussinessLogicService.AccountBLService.AccountBLService;
import main.BussinessLogicService.BankAccountBLService.BankAccountBLService;
import main.BussinessLogicService.CheckBLService.CheckBLService;
import main.BussinessLogicService.ManifestBLService.ManifestBLService;
import main.BussinessLogicService.PromotionBLService.PromotionBLService;
import main.BussinessLogicService.ReciptBLService.ReciptBLService;
import main.BussinessLogicService.RecordBLService.RecordBLService;
import main.BussinessLogicService.UserBLService.UserBLService;
import main.BussinessLogicService.CommodityBLService.CommodityBLService;
import main.BussinessLogicService.CommodityBLService.CommodityReciptBLService;
import main.BussinessLogicService.CommodityBLService.GoodsBLService;
import main.BussinessLogicService.CommodityBLService.GoodsCatagoryBLService;

public class RemoteHelper {
//	private static Context namingContext;
	private static String url = "rmi://127.0.0.1:8887/";
	
	private static BankAccountBLService bankAccountBLService;
	private static ReciptBLService reciptBLService;
	private static AccountBLService accountBLService;
	private static RecordBLService recordBLService;
	private static CustomerBLService customerBLService;
	private static ManifestBLService manifestBLService;
	private static CommodityBLService commodityBLService;
	private static GoodsCatagoryBLService goodsCatagoryBLService;
	private static GoodsBLService goodsBLService;
	private static CommodityReciptBLService commodityReciptBLService;
	private static CheckBLService checkBLService;
	private static PromotionBLService promotionBLService;
	private static UserBLService userBLService;
	
	public static void initRMI() {
//		try {
//			namingContext = new InitialContext();
//		} catch (NamingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		try {
			bankAccountBLService = (BankAccountBLService)Naming.lookup(url+"BankAccountBL");
			reciptBLService =  (ReciptBLService)Naming.lookup(url+"ReciptBL");
			accountBLService = (AccountBLService)Naming.lookup(url+"AccountBL");
			recordBLService = (RecordBLService)Naming.lookup(url+"RecordBL");
			customerBLService = (CustomerBLService)Naming.lookup(url+"CustomerBL");
			manifestBLService = (ManifestBLService)Naming.lookup(url+"ManifestBL");
			commodityBLService = (CommodityBLService)Naming.lookup(url+"CommodityBL");
			goodsCatagoryBLService = (GoodsCatagoryBLService)Naming.lookup(url+"GoodsCatagoryBL");
			goodsBLService = (GoodsBLService)Naming.lookup(url+"GoodsBL");
			commodityReciptBLService = (CommodityReciptBLService)Naming.lookup(url+"CommodityReciptBL");
			checkBLService = (CheckBLService)Naming.lookup(url+"CheckBL");
			promotionBLService = (PromotionBLService)Naming.lookup(url+"PromotionBL");
			userBLService = (UserBLService)Naming.lookup(url+"UserBL");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static BankAccountBLService getBankAccountBLService() {
		return bankAccountBLService;
	}
	
	public static ReciptBLService getReciptBLService() {
		return reciptBLService;
	}

	public static AccountBLService getAccountBLService() {
		return accountBLService;
	}

	public static RecordBLService getRecordBLService() {
		return recordBLService;
	}

	public static CustomerBLService getCustomerBLService() {
		return customerBLService;
	}

	public static ManifestBLService getManifestBLService() {
		return manifestBLService;
	}

	public static CommodityBLService getCommodityBLService() {
		return commodityBLService;
	}

	public static GoodsCatagoryBLService getGoodsCatagoryBLService() {
		return goodsCatagoryBLService;
	}

	public static GoodsBLService getGoodsBLService() {
		return goodsBLService;
	}

	public static CommodityReciptBLService getCommodityReciptBLService() {
		return commodityReciptBLService;
	}
	
	public static CheckBLService getCheckBLService() {
		return checkBLService;
	}
	
	public static PromotionBLService getPromotionBLService() {
		return promotionBLService;
	}
	
	public static UserBLService getLoginBLService() {
		return userBLService;
	}
}