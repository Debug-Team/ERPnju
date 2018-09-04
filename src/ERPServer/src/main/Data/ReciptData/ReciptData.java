package main.Data.ReciptData;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import main.Data.hibernateHelper.HibernateHelper;
import main.DataService.ReciptDataService.ReciptDataService;
import main.PO.CashBillPO;
import main.PO.CollectionOrderPO;
import main.PO.PaymentOrderPO;
import main.PO.ReciptPO;
import main.utility.ResultMessage;

@SuppressWarnings("deprecation")
public class ReciptData implements ReciptDataService{
	
//	private static SessionFactory factory;
//	
//	public ReciptData() {
//		try {
//			factory = new Configuration().configure().buildSessionFactory();
//	    } catch (Throwable ex) { 
//	    	System.err.println("Failed to create sessionFactory object." + ex);
//	    	throw new ExceptionInInitializerError(ex); 
//	    }
//	}

	@Override
	public ResultMessage addCollectionOrder(CollectionOrderPO po) {
		Session session = HibernateHelper.getSession();
		Transaction tx = null;
		ResultMessage result = ResultMessage.SUCCESS;
		
		try {
			tx = session.beginTransaction();
			session.save(po);
			tx.commit();
		} catch (HibernateException e) {
			if(tx!=null) 
				tx.rollback();
			e.printStackTrace();
			result = ResultMessage.FAIL;
		} finally {
			session.close();
		}
		
		return result;
	}

	@Override
	public ResultMessage addPaymentOrder(PaymentOrderPO po) {
		Session session = HibernateHelper.getSession();
		Transaction tx = null;
		ResultMessage result = ResultMessage.SUCCESS;
		
		try {
			tx = session.beginTransaction();
			session.save(po);
			tx.commit();
		} catch (HibernateException e) {
			if(tx!=null) 
				tx.rollback();
			e.printStackTrace();
			result = ResultMessage.FAIL;
		} finally {
			session.close();
		}
		
		return result;
	}

	@Override
	public ResultMessage addCashBill(CashBillPO po) {
		Session session = HibernateHelper.getSession();
		Transaction tx = null;
		ResultMessage result = ResultMessage.SUCCESS;
		
		try {
			tx = session.beginTransaction();
			session.save(po);
			tx.commit();
		} catch (HibernateException e) {
			if(tx!=null) 
				tx.rollback();
			e.printStackTrace();
			result = ResultMessage.FAIL;
		} finally {
			session.close();
		}
		
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ReciptPO getOne(String id) {
		Session session = HibernateHelper.getSession();
		Transaction tx = null;
		ReciptPO reciptPO = null;
		
		try {
			tx = session.beginTransaction();
			
			Criteria cr1 = session.createCriteria(CashBillPO.class);
			cr1.add(Restrictions.eq("id", id));
			Criteria cr2 = session.createCriteria(CollectionOrderPO.class);
			cr2.add(Restrictions.eq("id", id));
			Criteria cr3 = session.createCriteria(PaymentOrderPO.class);
			cr3.add(Restrictions.eq("id", id));
			
			List<ReciptPO> results = new ArrayList<ReciptPO>();
			results.addAll(cr1.list());
			results.addAll(cr2.list());
			results.addAll(cr3.list());
			
			if(!results.isEmpty()) 
				reciptPO = results.get(0);
			tx.commit();
		} catch (HibernateException e) {
			if(tx != null) 
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return reciptPO;
	}
	
	

	@Override
	public ResultMessage updateCollectionOrder(CollectionOrderPO po) {
		Session session = HibernateHelper.getSession();
		Transaction tx = null;
		ResultMessage result = ResultMessage.SUCCESS;
		
		try {
			tx = session.beginTransaction();
			session.update(po);
			tx.commit();
		} catch (HibernateException e) {
			if(tx!=null) 
				tx.rollback();
			e.printStackTrace();
			result = ResultMessage.FAIL;
		} finally {
			session.close();
		}
		
		return result;
	}

	@Override
	public ResultMessage updatePaymentOrder(PaymentOrderPO po) {
		Session session = HibernateHelper.getSession();
		Transaction tx = null;
		ResultMessage result = ResultMessage.SUCCESS;
		
		try {
			tx = session.beginTransaction();
			session.update(po);
			tx.commit();
		} catch (HibernateException e) {
			if(tx!=null) 
				tx.rollback();
			e.printStackTrace();
			result = ResultMessage.FAIL;
		} finally {
			session.close();
		}
		
		return result;
	}

	@Override
	public ResultMessage updateCashBill(CashBillPO po) {
		Session session = HibernateHelper.getSession();
		Transaction tx = null;
		ResultMessage result = ResultMessage.SUCCESS;
		
		try {
			tx = session.beginTransaction();
			session.update(po);
			tx.commit();
		} catch (HibernateException e) {
			if(tx!=null) 
				tx.rollback();
			e.printStackTrace();
			result = ResultMessage.FAIL;
		} finally {
			session.close();
		}
		
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<CollectionOrderPO> getCollectionOrderPOList(String cmd) {
		
		ArrayList<CollectionOrderPO> results = new ArrayList<CollectionOrderPO>();
		
		Session session = HibernateHelper.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			
			Criteria cr = session.createCriteria(CollectionOrderPO.class);
			results.addAll(cr.list());
			for(int p = 0; p < results.size(); p++) {
				if(!results.get(p).getState().equals(cmd)) {
					results.remove(results.get(p));
					p--;
				}
			}
			
			tx.commit();
		} catch (HibernateException e) {
			if(tx != null) 
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return results;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<PaymentOrderPO> getPaymentOrderPOList(String cmd) {
		
		ArrayList<PaymentOrderPO> results = new ArrayList<PaymentOrderPO>();
		
		Session session = HibernateHelper.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			
			Criteria cr = session.createCriteria(PaymentOrderPO.class);
			results.addAll(cr.list());
			for(int p = 0; p < results.size(); p++) {
				if(!results.get(p).getState().equals(cmd)) {
					results.remove(results.get(p));
					p--;
				}
			}
			
			tx.commit();
		} catch (HibernateException e) {
			if(tx != null) 
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return results;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<CashBillPO> getCashBillPOList(String cmd) {
		
		ArrayList<CashBillPO> results = new ArrayList<CashBillPO>();
		
		Session session = HibernateHelper.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			
			Criteria cr = session.createCriteria(CashBillPO.class);
			results.addAll(cr.list());
			for(int p = 0; p < results.size(); p++) {
				if(!results.get(p).getState().equals(cmd)) {
					results.remove(results.get(p));
					p--;
				}
			}
			
			tx.commit();
		} catch (HibernateException e) {
			if(tx != null) 
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
			
		return results;
	}

	@Override
	public int newId(String type) {
		int result = 0;
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");  //设置当前日期
		String date = df.format(new Date());
		String num, tempDate;
		if(type.equals("SKD")) {
			Session session = HibernateHelper.getSession();
			Transaction tx = null;
			try {
				tx = session.beginTransaction();
				
				Criteria cr = session.createCriteria(CollectionOrderPO.class);
				if(!cr.list().isEmpty()) {
					CollectionOrderPO po = (CollectionOrderPO)cr.list().get(cr.list().size()-1);
					tempDate = po.getId().split("-")[1];
					if(tempDate.equals(date)) {
						num = po.getId().split("-")[2];
						result = Integer.parseInt(num) + 1;
					}
					else {
						result = 0;
					}
				}
				else {
					result = 0;
				}
				
				tx.commit();
			} catch (HibernateException e) {
				if(tx != null) 
					tx.rollback();
				e.printStackTrace();
			} finally {
				session.close();
			}
		}
		else if(type.equals("FKD")) {
			Session session = HibernateHelper.getSession();
			Transaction tx = null;
			try {
				tx = session.beginTransaction();
				
				Criteria cr = session.createCriteria(PaymentOrderPO.class);
				if(!cr.list().isEmpty()) {
					PaymentOrderPO po = (PaymentOrderPO)cr.list().get(cr.list().size()-1);
					tempDate = po.getId().split("-")[1];
					if(tempDate.equals(date)) {
						num = po.getId().split("-")[2];
						result = Integer.parseInt(num) + 1;
					}
					else {
						result = 0;
					}
				}
				else {
					result = 0;
				}
				
				tx.commit();
			} catch (HibernateException e) {
				if(tx != null) 
					tx.rollback();
				e.printStackTrace();
			} finally {
				session.close();
			}
		}
		else if(type.equals("XJFYD")) {
			Session session = HibernateHelper.getSession();
			Transaction tx = null;
			try {
				tx = session.beginTransaction();
				
				Criteria cr = session.createCriteria(CashBillPO.class);
				if(!cr.list().isEmpty()) {
					CashBillPO po = (CashBillPO)cr.list().get(cr.list().size()-1);
					tempDate = po.getId().split("-")[1];
					if(tempDate.equals(date)) {
						num = po.getId().split("-")[2];
						result = Integer.parseInt(num) + 1;
					}
					else {
						result = 0;
					}
				}
				else {
					result = 0;
				}
				
				tx.commit();
			} catch (HibernateException e) {
				if(tx != null) 
					tx.rollback();
				e.printStackTrace();
			} finally {
				session.close();
			}
		}
		return result;
	}

	@SuppressWarnings({ "unchecked" })
	@Override
	public ArrayList<ReciptPO> getCheckedReciptList(String startTime, String endTime, String customerName,
			String operator) {
		Session session = HibernateHelper.getSession();
		Transaction tx = null;
		ArrayList<ReciptPO> results = new ArrayList<ReciptPO>();
		
		String hql1 = "from CashBillPO where operator like '%" + operator + "%'";
		String hql21 = "from CollectionOrderPO where operator like '%" + operator + "%' and supplier like '%" 
				+ customerName + "%'";
		String hql22 = "from CollectionOrderPO where operator like '%" + operator + "%' and retailer like '%" 
				+ customerName + "%'";
		String hql31 = "from PaymentOrderPO where operator like '%" + operator + "%' and supplier like '%" 
				+ customerName + "%'";
		String hql32 = "from PaymentOrderPO where operator like '%" + operator + "%' and retailer like '%" 
				+ customerName + "%'";

		try {
			tx = session.beginTransaction();
			
			@SuppressWarnings("rawtypes")
			Query query1 = session.createQuery(hql1);
			results.addAll(query1.list());
			
			ArrayList<ReciptPO> temp1 = new ArrayList<ReciptPO>();
			ArrayList<ReciptPO> temp2 = new ArrayList<ReciptPO>();
			@SuppressWarnings("rawtypes")
			Query query21 = session.createQuery(hql21);
			temp1.addAll(query21.list());
			@SuppressWarnings("rawtypes")
			Query query22 = session.createQuery(hql22);
			temp2.addAll(query22.list());
			for(int i = 0; i < temp2.size(); i++) {
				boolean isSame = false;
				for(int j = 0; j < temp1.size(); j++) {
					if(temp1.get(j).getId().equals(temp2.get(i).getId())) {
						isSame = true;
						break;
					}
				}
				if(!isSame) {
					temp1.add(temp2.get(i));
				}
			}
			results.addAll(temp1);
			
			ArrayList<ReciptPO> temp3 = new ArrayList<ReciptPO>();
			ArrayList<ReciptPO> temp4 = new ArrayList<ReciptPO>();
			@SuppressWarnings("rawtypes")
			Query query31 = session.createQuery(hql31);
			temp3.addAll(query31.list());
			@SuppressWarnings("rawtypes")
			Query query32 = session.createQuery(hql32);
			temp4.addAll(query32.list());
			for(int i = 0; i < temp4.size(); i++) {
				boolean isSame = false;
				for(int j = 0; j < temp3.size(); j++) {
					if(temp3.get(j).getId().equals(temp4.get(i).getId())) {
						isSame = true;
						break;
					}
				}
				if(!isSame) {
					temp3.add(temp4.get(i));
				}
			}
			results.addAll(temp3);
			
			int p = 0;
			while(p < results.size()) {
//				String type = results.get(p).getId().split("-")[0];
				String tmpDate = results.get(p).getId().split("-")[1].substring(0, 8);
				if(tmpDate.compareTo(startTime) < 0 || tmpDate.compareTo(endTime) > 0 ) {
					results.remove(results.get(p));
				}
//				else if(type.equals("SKD")) {
//					CollectionOrderPO po = (CollectionOrderPO)results.get(p);
//					if(po.getRetailer() != customerName && po.getSupplier() != customerName) {
//						results.remove(results.get(p));
//					}
//				}
//				else if(type.equals("FKD")) {
//					PaymentOrderPO po = (PaymentOrderPO)results.get(p);
//					if(po.getRetailer() != customerName && po.getSupplier() != customerName) {
//						results.remove(results.get(p));
//					}
//				}
				else p++;
			}
			
			tx.commit();
		} catch (HibernateException e) {
			if(tx != null) 
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return results;
	}

	@SuppressWarnings({ "unchecked" })
	@Override
	public ArrayList<ReciptPO> getAllRecipts() {
		ArrayList<ReciptPO> results = new ArrayList<ReciptPO>();
		
		Session session = HibernateHelper.getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			
			Criteria cr = session.createCriteria(PaymentOrderPO.class);
			results.addAll(cr.list());
			
			cr = session.createCriteria(CollectionOrderPO.class);
			results.addAll(cr.list());
			
			cr = session.createCriteria(CashBillPO.class);
			results.addAll(cr.list());
			
			tx.commit();
		} catch (HibernateException e) {
			if(tx != null) 
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return results;
	}

	@SuppressWarnings("unchecked")
	@Override
	public CollectionOrderPO getACollectionOrder(String id) {
		Session session = HibernateHelper.getSession();
		Transaction tx = null;
		CollectionOrderPO result = null;
		
		try {
			tx = session.beginTransaction();
			
			Criteria cr = session.createCriteria(CollectionOrderPO.class);
			cr.add(Restrictions.eq("id", id));
			
			List<CollectionOrderPO> results = new ArrayList<CollectionOrderPO>();
			results.addAll(cr.list());
			
			if(!results.isEmpty()) 
				result = results.get(0);
			tx.commit();
		} catch (HibernateException e) {
			if(tx != null) 
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PaymentOrderPO getAPaymentOrder(String id) {
		Session session = HibernateHelper.getSession();
		Transaction tx = null;
		PaymentOrderPO result = null;
		
		try {
			tx = session.beginTransaction();
			
			Criteria cr = session.createCriteria(PaymentOrderPO.class);
			cr.add(Restrictions.eq("id", id));
			
			List<PaymentOrderPO> results = new ArrayList<PaymentOrderPO>();
			results.addAll(cr.list());
			
			if(!results.isEmpty()) 
				result = results.get(0);
			tx.commit();
		} catch (HibernateException e) {
			if(tx != null) 
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public CashBillPO getACashBill(String id) {
		Session session = HibernateHelper.getSession();
		Transaction tx = null;
		CashBillPO result = null;
		
		try {
			tx = session.beginTransaction();
			
			Criteria cr = session.createCriteria(CashBillPO.class);
			cr.add(Restrictions.eq("id", id));
			
			List<CashBillPO> results = new ArrayList<CashBillPO>();
			results.addAll(cr.list());
			
			if(!results.isEmpty()) 
				result = results.get(0);
			tx.commit();
		} catch (HibernateException e) {
			if(tx != null) 
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<CollectionOrderPO> getCheckedCollectionOrderList(String startTime, String endTime,
			String customerName, String operator) {
		Session session = HibernateHelper.getSession();
		Transaction tx = null;
		ArrayList<CollectionOrderPO> results = new ArrayList<CollectionOrderPO>();
		
		String hql21 = "from CollectionOrderPO where operator like '%" + operator + "%' and supplier like '%" 
				+ customerName + "%'";
		String hql22 = "from CollectionOrderPO where operator like '%" + operator + "%' and retailer like '%" 
				+ customerName + "%'";

		try {
			tx = session.beginTransaction();
			
			ArrayList<CollectionOrderPO> temp1 = new ArrayList<CollectionOrderPO>();
			ArrayList<CollectionOrderPO> temp2 = new ArrayList<CollectionOrderPO>();
			@SuppressWarnings("rawtypes")
			Query query21 = session.createQuery(hql21);
			temp1.addAll(query21.list());
			@SuppressWarnings("rawtypes")
			Query query22 = session.createQuery(hql22);
			temp2.addAll(query22.list());
			for(int i = 0; i < temp2.size(); i++) {
				boolean isSame = false;
				for(int j = 0; j < temp1.size(); j++) {
					if(temp1.get(j).getId().equals(temp2.get(i).getId())) {
						isSame = true;
						break;
					}
				}
				if(!isSame) {
					temp1.add(temp2.get(i));
				}
			}
			results.addAll(temp1);
			
			int p = 0;
			while(p < results.size()) {
				String tmpDate = results.get(p).getId().split("-")[1].substring(0, 8);
				if(tmpDate.compareTo(startTime) < 0 || tmpDate.compareTo(endTime) > 0 ) {
					results.remove(results.get(p));
				}
				else p++;
			}
			
			tx.commit();
		} catch (HibernateException e) {
			if(tx != null) 
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return results;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<PaymentOrderPO> getCheckedPaymentOrderList(String startTime, String endTime, String customerName,
			String operator) {
		Session session = HibernateHelper.getSession();
		Transaction tx = null;
		ArrayList<PaymentOrderPO> results = new ArrayList<PaymentOrderPO>();
		
		String hql31 = "from PaymentOrderPO where operator like '%" + operator + "%' and supplier like '%" 
				+ customerName + "%'";
		String hql32 = "from PaymentOrderPO where operator like '%" + operator + "%' and retailer like '%" 
				+ customerName + "%'";

		try {
			tx = session.beginTransaction();
			
			ArrayList<PaymentOrderPO> temp3 = new ArrayList<PaymentOrderPO>();
			ArrayList<PaymentOrderPO> temp4 = new ArrayList<PaymentOrderPO>();
			@SuppressWarnings("rawtypes")
			Query query31 = session.createQuery(hql31);
			temp3.addAll(query31.list());
			@SuppressWarnings("rawtypes")
			Query query32 = session.createQuery(hql32);
			temp4.addAll(query32.list());
			for(int i = 0; i < temp4.size(); i++) {
				boolean isSame = false;
				for(int j = 0; j < temp3.size(); j++) {
					if(temp3.get(j).getId().equals(temp4.get(i).getId())) {
						isSame = true;
						break;
					}
				}
				if(!isSame) {
					temp3.add(temp4.get(i));
				}
			}
			results.addAll(temp3);
			
			int p = 0;
			while(p < results.size()) {
				String tmpDate = results.get(p).getId().split("-")[1].substring(0, 8);
				if(tmpDate.compareTo(startTime) < 0 || tmpDate.compareTo(endTime) > 0 ) {
					results.remove(results.get(p));
				}
				else p++;
			}
			
			tx.commit();
		} catch (HibernateException e) {
			if(tx != null) 
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return results;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<CashBillPO> getCheckedCashBillList(String startTime, String endTime, String customerName,
			String operator) {
		Session session = HibernateHelper.getSession();
		Transaction tx = null;
		ArrayList<CashBillPO> results = new ArrayList<CashBillPO>();
		
		String hql1 = "from CashBillPO where operator like '%" + operator + "%'";

		try {
			tx = session.beginTransaction();
			
			@SuppressWarnings("rawtypes")
			Query query1 = session.createQuery(hql1);
			results.addAll(query1.list());
			
			int p = 0;
			while(p < results.size()) {
				String tmpDate = results.get(p).getId().split("-")[1].substring(0, 8);
				if(tmpDate.compareTo(startTime) < 0 || tmpDate.compareTo(endTime) > 0 ) {
					results.remove(results.get(p));
				}
				else p++;
			}
			
			tx.commit();
		} catch (HibernateException e) {
			if(tx != null) 
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return results;
	}
	
}
