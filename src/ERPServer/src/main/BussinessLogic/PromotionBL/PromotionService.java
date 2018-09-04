package main.BussinessLogic.PromotionBL;

import java.util.ArrayList;

import main.PO.*;
import main.utility.PromotionResult;

public interface PromotionService {
	
	/**
	 * 同层提供自动计算合适的促销策略方法
	 * @param reciptGoodsList
	 * @param customer
	 * @return
	 */
	public PromotionResult getAppropriatePromotion(ArrayList<ReciptGoodsPO> reciptGoodsList, CustomerPO customer);
	
}
