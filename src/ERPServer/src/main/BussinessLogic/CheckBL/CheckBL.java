package main.BussinessLogic.CheckBL;

import main.BussinessLogic.CommodityBL.CommodityBL;
import main.BussinessLogic.CommodityBL.CommodityService;
import main.BussinessLogic.ManifestBL.ManifestBL;
import main.BussinessLogic.ManifestBL.ManifestService;
import main.BussinessLogic.ReciptBL.ReciptBL;
import main.BussinessLogic.ReciptBL.ReciptService;
import main.PO.InfoPO;
import main.VO.InfoVO;
import main.utility.ResultMessage;

public class CheckBL {
	
	ReciptService rs = new ReciptBL();
	ManifestService ms = new ManifestBL();
	CommodityService cs = new CommodityBL();

	public InfoVO getInfo() {
		InfoPO result = new InfoPO();
		
		result.setCollectionOrderList(rs.getUncheckedCollectionOrder());
		result.setPaymentOrderList(rs.getUncheckedPaymentOrder());
		result.setCashBillList(rs.getUncheckedCashBillPO());
		result.setManifestList(ms.getUncheckedManifest());
		result.setCommodityReciptList(cs.getUncheckedCommodityRecipt());
		
		return new InfoVO(result);
	}

	public ResultMessage setSuggestion(InfoVO vo) {
		InfoPO po = new InfoPO(vo);
		
		rs.saveCollectionOrder(po.getCollectionOrderList());
		rs.savePaymentOrder(po.getPaymentOrderList());
		rs.saveCashBillPO(po.getCashBillList());
		ms.saveManifest(po.getManifestList());
		cs.saveCommodityRecipt(po.getCommodityReciptList());
		
		return ResultMessage.SUCCESS;
	}
}
