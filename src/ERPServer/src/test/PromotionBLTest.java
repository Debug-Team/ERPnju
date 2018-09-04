package test;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

import main.BussinessLogic.PromotionBL.PromotionBL;
import main.Data.hibernateHelper.HibernateHelper;
import main.VO.CouponVO;
import main.VO.GoodsVO;
import main.VO.LevelPromotionVO;
import main.VO.PackagePromotionVO;
import main.VO.TotalPromotionVO;
import main.utility.ResultMessage;

public class PromotionBLTest {
	
	PromotionBL pb = new PromotionBL();
	
	@Before
	public void Start(){
		HibernateHelper.initHibernateHelper();
		System.out.println("before");
	}
	
	@Test
	public void testSetPackagePromotion() {
		ArrayList<GoodsVO> packageList = new ArrayList<GoodsVO>();
		PackagePromotionVO pp = new PackagePromotionVO("20180102", "20180106", 123.123, packageList);
		assertEquals(ResultMessage.SUCCESS, pb.setPromotion(pp));
	}
	
	@Test
	public void testSetTotalPromotion() {
		ArrayList<GoodsVO> giftList = new ArrayList<GoodsVO>();
		ArrayList<CouponVO> couponList = new ArrayList<CouponVO>();
		CouponVO temp = new CouponVO("20180102", "20180106", 100);
		couponList.add(temp);
		TotalPromotionVO tp = new TotalPromotionVO("20180213", "20180226", 1223, giftList, couponList);
		assertEquals(ResultMessage.SUCCESS, pb.setPromotion(tp));
	}
	
	@Test
	public void testSetLevelPromotion() {
		ArrayList<GoodsVO> giftList = new ArrayList<GoodsVO>();
		ArrayList<CouponVO> couponList = new ArrayList<CouponVO>();
		LevelPromotionVO lp = new LevelPromotionVO("20180321", "20180326", "ÈýÐÇ¼¶", 0.75, giftList, couponList);
		assertEquals(ResultMessage.SUCCESS, pb.setPromotion(lp));
	}
	
}
