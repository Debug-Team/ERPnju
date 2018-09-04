package test;

import java.rmi.RemoteException;
import java.util.ArrayList;

import main.BussinessLogic.CommodityBL.CommodityController;
import main.BussinessLogic.PromotionBL.PromotionBL;
import main.Data.hibernateHelper.HibernateHelper;
import main.VO.CouponVO;
import main.VO.GoodsVO;
import main.VO.LevelPromotionVO;
import main.VO.PackagePromotionVO;
import main.VO.TotalPromotionVO;

public class MainTest {
	
	public static void init() {
		HibernateHelper.initHibernateHelper();
		System.out.println("init");
	}
	
	public static void main(String[] args) {
		MainTest.init();
		PromotionBL bl = new PromotionBL();
		ArrayList<GoodsVO> goods = new ArrayList<GoodsVO>();
		try {
			CommodityController cc = new CommodityController();
			goods.addAll(cc.goodsFind("", "name"));
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		System.out.println(goods.size());
		//设置代金券
		CouponVO vo01 = new CouponVO("20180106", "20180206", 20);
		CouponVO vo02 = new CouponVO("20180106", "20180206", 15);
		CouponVO vo03 = new CouponVO("20180115", "20180202", 30);
		CouponVO vo04 = new CouponVO("20180126", "20180206", 6.6);
		//设置特价包促销
		ArrayList<GoodsVO> packageList = new ArrayList<GoodsVO>();
		packageList.add(goods.get(0)); 
		packageList.add(goods.get(1));
		packageList.add(goods.get(2));
		PackagePromotionVO tjb01 = new PackagePromotionVO("20171215", "20180106", 25.5, packageList);
		bl.setPromotion(tjb01);
		//设置总价促销
		ArrayList<GoodsVO> giftList01 = new ArrayList<GoodsVO>();
		giftList01.add(goods.get(goods.size()-1));
		ArrayList<CouponVO> couponList01 = new ArrayList<CouponVO>();
		couponList01.add(vo01);
		couponList01.add(vo02);
		TotalPromotionVO zj01 = new TotalPromotionVO("20171218", "20180103", 100, giftList01, couponList01);
		bl.setPromotion(zj01);
		//设置级别促销
		ArrayList<GoodsVO> giftList02 = new ArrayList<GoodsVO>();
		giftList01.add(goods.get(goods.size()-1));
		ArrayList<CouponVO> couponList02 = new ArrayList<CouponVO>();
		couponList01.add(vo03);
		couponList01.add(vo04);
		LevelPromotionVO jb01 = new LevelPromotionVO("12345678", "20180201", "  五星级", 0.85, giftList02, couponList02);
		bl.setPromotion(jb01);
	}

}
