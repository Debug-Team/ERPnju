package main.PO;

import java.util.ArrayList;

import main.VO.CashBillVO;
import main.VO.CollectionOrderVO;
import main.VO.CommodityReciptVO;
import main.VO.InfoVO;
import main.VO.ManifestVO;
import main.VO.PaymentOrderVO;
import main.VO.ReciptVO;

public class InfoPO {
	private ArrayList<ManifestPO> manifestList;
	private ArrayList<CommodityReciptPO> commodityReciptList;
	private ArrayList<CollectionOrderPO> collectionOrderList;
	private ArrayList<PaymentOrderPO> paymentOrderList;
	private ArrayList<CashBillPO> cashBillList;
	private ArrayList<ReciptPO> reciptList;
	
	public InfoPO() {
		this.manifestList = new ArrayList<ManifestPO>();
		this.commodityReciptList = new ArrayList<CommodityReciptPO>();
		this.collectionOrderList = new ArrayList<CollectionOrderPO>();
		this.paymentOrderList = new ArrayList<PaymentOrderPO>();
		this. cashBillList = new ArrayList<CashBillPO>();
		this.reciptList = new ArrayList<ReciptPO>();
	}
	
	public InfoPO(InfoVO vo) {
		ArrayList<ManifestPO> manifestPOList = new ArrayList<ManifestPO>();
		ArrayList<ManifestVO> manifestVOList = vo.getManifestList();
		for(int i = 0; i < manifestVOList.size(); i++) {
			ManifestPO temp = new ManifestPO(manifestVOList.get(i));
			manifestPOList.add(temp);
		}
		this.manifestList = manifestPOList;
		
		ArrayList<CommodityReciptPO> commodityReciptPOList = new ArrayList<CommodityReciptPO>();
		ArrayList<CommodityReciptVO> commodityReciptVOList = vo.getCommodityReciptList();
		for(int i = 0; i < commodityReciptVOList.size(); i++) {
			CommodityReciptPO temp = new CommodityReciptPO(commodityReciptVOList.get(i));
			commodityReciptPOList.add(temp);
		}
		this.commodityReciptList = commodityReciptPOList;
		
		ArrayList<CollectionOrderPO> collectionOrderPOList = new ArrayList<CollectionOrderPO>();
		ArrayList<CollectionOrderVO> collectionOrderVOList = vo.getCollectionOrderList();
		for(int i = 0; i < collectionOrderVOList.size(); i++) {
			CollectionOrderPO temp = new CollectionOrderPO(collectionOrderVOList.get(i));
			collectionOrderPOList.add(temp);
		}
		this.collectionOrderList = collectionOrderPOList;
		
		ArrayList<PaymentOrderPO> paymentOrderPOList = new ArrayList<PaymentOrderPO>();
		ArrayList<PaymentOrderVO> paymentOrderVOList = vo.getPaymentOrderList();
		for(int i = 0; i < paymentOrderVOList.size(); i++) {
			PaymentOrderPO temp = new PaymentOrderPO(paymentOrderVOList.get(i));
			paymentOrderPOList.add(temp);
		}
		this.paymentOrderList = paymentOrderPOList;
		
		ArrayList<CashBillPO> cashBillPOList = new ArrayList<CashBillPO>();
		ArrayList<CashBillVO> cashBillVOList = vo.getCashBillList();
		for(int i = 0; i < cashBillVOList.size(); i++) {
			CashBillPO temp = new CashBillPO(cashBillVOList.get(i));
			cashBillPOList.add(temp);
		}
		this.cashBillList = cashBillPOList;
		
		ArrayList<ReciptPO> reciptPOList = new ArrayList<ReciptPO>();
		ArrayList<ReciptVO> reciptVOList = vo.getReciptList();
		for(int i = 0; i < reciptVOList.size(); i++) {
			ReciptPO temp = new ReciptPO(reciptVOList.get(i));
			reciptPOList.add(temp);
		}
		this.reciptList = reciptPOList;
	}
	
	public InfoPO(ArrayList<ManifestPO> manifestList, ArrayList<CommodityReciptPO> commodityReciptList, 
			ArrayList<CollectionOrderPO> collectionOrderList, ArrayList<PaymentOrderPO> paymentOrderList, 
			ArrayList<CashBillPO> cashBillList, ArrayList<ReciptPO> reciptList) {
		this.manifestList = manifestList;
		this.commodityReciptList = commodityReciptList;
		this.collectionOrderList = collectionOrderList;
		this.paymentOrderList = paymentOrderList;
		this.cashBillList = cashBillList;
		this.reciptList = reciptList;
	}

	public ArrayList<ManifestPO> getManifestList() {
		return manifestList;
	}

	public void setManifestList(ArrayList<ManifestPO> manifestList) {
		this.manifestList = manifestList;
	}

	public ArrayList<CommodityReciptPO> getCommodityReciptList() {
		return commodityReciptList;
	}

	public void setCommodityReciptList(ArrayList<CommodityReciptPO> commodityReciptList) {
		this.commodityReciptList = commodityReciptList;
	}

	public ArrayList<CollectionOrderPO> getCollectionOrderList() {
		return collectionOrderList;
	}

	public void setCollectionOrderList(ArrayList<CollectionOrderPO> collectionOrderList) {
		this.collectionOrderList = collectionOrderList;
	}

	public ArrayList<PaymentOrderPO> getPaymentOrderList() {
		return paymentOrderList;
	}

	public void setPaymentOrderList(ArrayList<PaymentOrderPO> paymentOrderList) {
		this.paymentOrderList = paymentOrderList;
	}

	public ArrayList<CashBillPO> getCashBillList() {
		return cashBillList;
	}

	public void setCashBillList(ArrayList<CashBillPO> cashBillList) {
		this.cashBillList = cashBillList;
	}

	public ArrayList<ReciptPO> getReciptList() {
		return reciptList;
	}

	public void setReciptList(ArrayList<ReciptPO> reciptList) {
		this.reciptList = reciptList;
	}

}
