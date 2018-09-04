package main.VO;

import java.io.Serializable;
import java.util.ArrayList;

import main.PO.CashBillPO;
import main.PO.CollectionOrderPO;
import main.PO.CommodityReciptPO;
import main.PO.InfoPO;
import main.PO.ManifestPO;
import main.PO.PaymentOrderPO;
import main.PO.ReciptPO;

public class InfoVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ArrayList<ManifestVO> manifestList;
	private ArrayList<CommodityReciptVO> commodityReciptList;
	private ArrayList<CollectionOrderVO> collectionOrderList;
	private ArrayList<PaymentOrderVO> paymentOrderList;
	private ArrayList<CashBillVO> cashBillList;
	private ArrayList<ReciptVO> reciptList;
	
	public InfoVO() {
		this.manifestList = new ArrayList<ManifestVO>();
		this.commodityReciptList = new ArrayList<CommodityReciptVO>();
		this.collectionOrderList = new ArrayList<CollectionOrderVO>();
		this.paymentOrderList = new ArrayList<PaymentOrderVO>();
		this. cashBillList = new ArrayList<CashBillVO>();
		this.reciptList = new ArrayList<ReciptVO>();
	}
	
	public InfoVO(InfoPO po) {
		ArrayList<ManifestVO> manifestVOList = new ArrayList<ManifestVO>();
		ArrayList<ManifestPO> manifestPOList = po.getManifestList();
		for(int i = 0; i < manifestPOList.size(); i++) {
			ManifestVO temp = new ManifestVO(manifestPOList.get(i));
			manifestVOList.add(temp);
		}
		this.manifestList = manifestVOList;
		
		ArrayList<CommodityReciptVO> commodityReciptVOList = new ArrayList<CommodityReciptVO>();
		ArrayList<CommodityReciptPO> commodityReciptPOList = po.getCommodityReciptList();
		for(int i = 0; i < commodityReciptPOList.size(); i++) {
			CommodityReciptVO temp = new CommodityReciptVO(commodityReciptPOList.get(i));
			commodityReciptVOList.add(temp);
		}
		this.commodityReciptList = commodityReciptVOList;
		
		ArrayList<CollectionOrderVO> collectionOrderVOList = new ArrayList<CollectionOrderVO>();
		ArrayList<CollectionOrderPO> collectionOrderPOList = po.getCollectionOrderList();
		for(int i = 0; i < collectionOrderPOList.size(); i++) {
			CollectionOrderVO temp = new CollectionOrderVO(collectionOrderPOList.get(i));
			collectionOrderVOList.add(temp);
		}
		this.collectionOrderList = collectionOrderVOList;
		
		ArrayList<PaymentOrderVO> paymentOrderVOList = new ArrayList<PaymentOrderVO>();
		ArrayList<PaymentOrderPO> paymentOrderPOList = po.getPaymentOrderList();
		for(int i = 0; i < paymentOrderPOList.size(); i++) {
			PaymentOrderVO temp = new PaymentOrderVO(paymentOrderPOList.get(i));
			paymentOrderVOList.add(temp);
		}
		this.paymentOrderList = paymentOrderVOList;
		
		ArrayList<CashBillVO> cashBillVOList = new ArrayList<CashBillVO>();
		ArrayList<CashBillPO> cashBillPOList = po.getCashBillList();
		for(int i = 0; i < cashBillPOList.size(); i++) {
			CashBillVO temp = new CashBillVO(cashBillPOList.get(i));
			cashBillVOList.add(temp);
		}
		this.cashBillList = cashBillVOList;
		
		ArrayList<ReciptVO> reciptVOList = new ArrayList<ReciptVO>();
		ArrayList<ReciptPO> reciptPOList = po.getReciptList();
		for(int i = 0; i < reciptPOList.size(); i++) {
			ReciptVO temp = new ReciptVO(reciptPOList.get(i));
			reciptVOList.add(temp);
		}
		this.reciptList = reciptVOList;
	}
	
	public InfoVO(ArrayList<ManifestVO> manifestList, ArrayList<CommodityReciptVO> commodityReciptList, 
			ArrayList<CollectionOrderVO> collectionOrderList, ArrayList<PaymentOrderVO> paymentOrderList, 
			ArrayList<CashBillVO> cashBillList, ArrayList<ReciptVO> reciptList) {
		this.manifestList = manifestList;
		this.commodityReciptList = commodityReciptList;
		this.collectionOrderList = collectionOrderList;
		this.paymentOrderList = paymentOrderList;
		this.cashBillList = cashBillList;
		this.reciptList = reciptList;
	}

	public ArrayList<CashBillVO> getCashBillList() {
		return cashBillList;
	}

	public void setCashBillList(ArrayList<CashBillVO> cashBillList) {
		this.cashBillList = cashBillList;
	}

	public ArrayList<ManifestVO> getManifestList() {
		return manifestList;
	}

	public void setManifestList(ArrayList<ManifestVO> manifestList) {
		this.manifestList = manifestList;
	}

	public ArrayList<CommodityReciptVO> getCommodityReciptList() {
		return commodityReciptList;
	}

	public void setCommodityReciptList(ArrayList<CommodityReciptVO> commodityReciptList) {
		this.commodityReciptList = commodityReciptList;
	}

	public ArrayList<CollectionOrderVO> getCollectionOrderList() {
		return collectionOrderList;
	}

	public void setCollectionOrderList(ArrayList<CollectionOrderVO> collectionOrderList) {
		this.collectionOrderList = collectionOrderList;
	}

	public ArrayList<PaymentOrderVO> getPaymentOrderList() {
		return paymentOrderList;
	}

	public void setPaymentOrderList(ArrayList<PaymentOrderVO> paymentOrderList) {
		this.paymentOrderList = paymentOrderList;
	}

	public ArrayList<ReciptVO> getReciptList() {
		return reciptList;
	}

	public void setReciptList(ArrayList<ReciptVO> reciptList) {
		this.reciptList = reciptList;
	}
	
}

