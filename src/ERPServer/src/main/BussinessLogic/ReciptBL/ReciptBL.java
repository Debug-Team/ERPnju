package main.BussinessLogic.ReciptBL;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import main.BussinessLogic.BankAccountBL.BankAccountBL;
import main.BussinessLogic.BankAccountBL.BankAccountService;
import main.BussinessLogic.CustomerBL.CustomerBL;
import main.BussinessLogic.CustomerBL.CustomerService;
import main.Data.ReciptData.ReciptData;
import main.DataService.ReciptDataService.ReciptDataService;
import main.VO.*;
import main.utility.ResultMessage;
import main.PO.*;

public class ReciptBL implements ReciptService {
	
	ReciptDataService rds = new ReciptData();
	CustomerService cs = new CustomerBL();
	
	public CollectionOrderPO getACollectionOrder(String id) {
		return rds.getACollectionOrder(id);
	}
	
	public PaymentOrderPO getAPaymentOrder(String id) {
		return rds.getAPaymentOrder(id);
	}

	public CashBillPO getACashBill(String id) {
		return rds.getACashBill(id);
	}
	
	public String getNextID(String type) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String date = format.format(new Date());
		
		String ide = String.format("%05d", rds.newId(type));
		
		if(rds.newId(type) > 99999) 
			return "overflow";
		else 
			return type+"-"+date+"-"+ide;
	}

	public ResultMessage setCollection(CollectionOrderVO vo) {
		vo.setId(getNextID("SKD"));
		rds.addCollectionOrder(new CollectionOrderPO(vo));
		return ResultMessage.SUCCESS;
	}

	public ResultMessage setPaymentOrder(PaymentOrderVO vo) {
		vo.setId(getNextID("FKD"));
		rds.addPaymentOrder(new PaymentOrderPO(vo));
		return ResultMessage.SUCCESS;
	}

	public ResultMessage setCashBill(CashBillVO vo) {
		vo.setId(getNextID("XJFYD"));
		rds.addCashBill(new CashBillPO(vo));
		return ResultMessage.SUCCESS;
	}

	public ReciptVO findOne(String id) {
		ReciptVO result;
		String type = id.split("-")[0];
		if(type.equals("FKD")) {
			result = new PaymentOrderVO((PaymentOrderPO)rds.getOne(id));
		}
		else if(type.equals("SKD")) {
			result = new CollectionOrderVO((CollectionOrderPO)rds.getOne(id));
		}
		else {
			result = new CashBillVO((CashBillPO)rds.getOne(id));
		}
		return result;
	}

	@Override
	public ArrayList<CollectionOrderPO> getUncheckedCollectionOrder() {
		return rds.getCollectionOrderPOList("Unchecked");
	}

	@Override
	public ResultMessage saveCollectionOrder(ArrayList<CollectionOrderPO> list) {
		for(int i = 0; i < list.size(); i++) {
			CollectionOrderPO po = list.get(i);
			if(po.getState().equals("Checked")){
				cs.updateCustomer(po.getRetailer(), "SKD", po.getSum());
			}
			
			rds.updateCollectionOrder(po);
		}
		return ResultMessage.SUCCESS;
	}

	@Override
	public ArrayList<PaymentOrderPO> getUncheckedPaymentOrder() {
		return rds.getPaymentOrderPOList("Unchecked");
	}

	@Override
	public ResultMessage savePaymentOrder(ArrayList<PaymentOrderPO> list) {
		for(int i = 0; i < list.size(); i++) {
			PaymentOrderPO po = list.get(i);
			if(po.getState().equals("Checked")){
				cs.updateCustomer(po.getSupplier(), "FKD", po.getSum());
			}
			
			rds.updatePaymentOrder(po);
		}
		return ResultMessage.SUCCESS;
	}

	@Override
	public ArrayList<CashBillPO> getUncheckedCashBillPO() {
		return rds.getCashBillPOList("Unchecked");
	}

	@Override
	public ResultMessage saveCashBillPO(ArrayList<CashBillPO> list) {
		for(int i = 0; i < list.size(); i++) {
			rds.updateCashBill(list.get(i));
		}
		return ResultMessage.SUCCESS;
	}

	@Override
	public ArrayList<ReciptVO> getCheckedReciptList(String startTime, String endTime, 
			String customerName, String operator) {
		ArrayList<ReciptVO> results = new ArrayList<ReciptVO>();
		ArrayList<ReciptPO> tmpList = rds.getCheckedReciptList(startTime, endTime, customerName, operator);
		for(ReciptPO po : tmpList) {
			results.add(new ReciptVO(po));
		}
		return results;
	}
	
	@Override
	public ArrayList<CollectionOrderVO> getCheckedCollectionOrderList(String startTime, String endTime,
			String customerName, String operator) {
		ArrayList<CollectionOrderVO> results = new ArrayList<CollectionOrderVO>();
		ArrayList<CollectionOrderPO> tmpList = rds.getCheckedCollectionOrderList(startTime, endTime, customerName, operator);
		for(CollectionOrderPO po : tmpList) {
			results.add(new CollectionOrderVO(po));
		}
		return results;
	}

	@Override
	public ArrayList<PaymentOrderVO> getCheckedPaymentOrderList(String startTime, String endTime, String customerName,
			String operator) {
		ArrayList<PaymentOrderVO> results = new ArrayList<PaymentOrderVO>();
		ArrayList<PaymentOrderPO> tmpList = rds.getCheckedPaymentOrderList(startTime, endTime, customerName, operator);
		for(PaymentOrderPO po : tmpList) {
			results.add(new PaymentOrderVO(po));
		}
		return results;
	}

	@Override
	public ArrayList<CashBillVO> getCheckedCashBillList(String startTime, String endTime, String customerName,
			String operator) {
		ArrayList<CashBillVO> results = new ArrayList<CashBillVO>();
		ArrayList<CashBillPO> tmpList = rds.getCheckedCashBillList(startTime, endTime, customerName, operator);
		for(CashBillPO po : tmpList) {
			results.add(new CashBillVO(po));
		}
		return results;
	}
	
	public ArrayList<BankAccountVO> getBankAccount(String part) {
		BankAccountService bas = new BankAccountBL();
		return bas.getBankAccount(part);
	}
	
	public ArrayList<CustomerVO> getCustomerList(String keyword, String type) {
		CustomerService cs = new CustomerBL();
		return cs.customerFind(keyword, type);
	}
	
	public ArrayList<ReciptVO> getAllRecipts() {
		ArrayList<ReciptVO> result = new ArrayList<ReciptVO>();
		ArrayList<ReciptPO> temp = rds.getAllRecipts();
		for(int i = 0; i < temp.size(); i++) {
			result.add(new ReciptVO(temp.get(i)));
		}
		return result;
	}
	
	public ArrayList<CollectionOrderVO> getAllCollectionOrderList() {
		ArrayList<CollectionOrderVO> result = new ArrayList<CollectionOrderVO>();
		ArrayList<CollectionOrderPO> temp = rds.getCollectionOrderPOList("Checked");
		temp.addAll(rds.getCollectionOrderPOList("Unchecked"));
		temp.addAll(rds.getCollectionOrderPOList("Draft"));
		for(int i = 0; i < temp.size(); i++) {
			result.add(new CollectionOrderVO(temp.get(i)));
		}
		return result;
	}
	
	public ArrayList<PaymentOrderVO> getAllPaymentOrderList() {
		ArrayList<PaymentOrderVO> result = new ArrayList<PaymentOrderVO>();
		ArrayList<PaymentOrderPO> temp = rds.getPaymentOrderPOList("Checked");
		temp.addAll(rds.getPaymentOrderPOList("Unchecked"));
		temp.addAll(rds.getPaymentOrderPOList("Draft"));
		for(int i = 0; i < temp.size(); i++) {
			result.add(new PaymentOrderVO(temp.get(i)));
		}
		return result;
	}
	
	public ArrayList<CashBillVO> getAllCashBillList() {
		ArrayList<CashBillVO> result = new ArrayList<CashBillVO>();
		ArrayList<CashBillPO> temp = rds.getCashBillPOList("Checked");
		temp.addAll(rds.getCashBillPOList("Unchecked"));
		temp.addAll(rds.getCashBillPOList("Draft"));
		for(int i = 0; i < temp.size(); i++) {
			result.add(new CashBillVO(temp.get(i)));
		}
		return result;
	}

	@Override
	public boolean setRedDashed(String type, String id) {
		if(type.equals("XJFYD")) {
			CashBillVO vo = new CashBillVO(getACashBill(id));
			CashBillVO newVO = new CashBillVO(vo.getOperator(), vo.getBankAccountID(), vo.getItemList(), -vo.getSum());
			setCashBill(newVO);
			return true;
		}
		else if(type.equals("SKD")) {
			CollectionOrderVO vo = new CollectionOrderVO(getACollectionOrder(id));
			CollectionOrderVO newVO = new CollectionOrderVO(vo.getOperator(), vo.getSupplier(), vo.getRetailer(), vo.getItemList(), -vo.getSum());
			setCollection(newVO);
			return true;
		}
		else if(type.equals("FKD")) {
			PaymentOrderVO vo = new PaymentOrderVO(getAPaymentOrder(id));
			PaymentOrderVO newVO = new PaymentOrderVO(vo.getOperator(), vo.getSupplier(), vo.getRetailer(), vo.getItemList(), -vo.getSum());
			setPaymentOrder(newVO);
			return true;
		}
		return false;
	}

}
