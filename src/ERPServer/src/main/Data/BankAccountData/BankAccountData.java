package main.Data.BankAccountData;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import main.Data.hibernateHelper.HibernateHelper;
import main.DataService.BankAccountDataService.BankAccountDataService;
import main.PO.BankAccountPO;
import main.utility.ResultMessage;

@SuppressWarnings("deprecation")
public class BankAccountData implements BankAccountDataService {
	
//	private static SessionFactory factory;
//	
//	public BankAccountData() {
//		try {
//			factory = new Configuration().configure().buildSessionFactory();
//	    } catch (Throwable ex) { 
//	    	System.err.println("Failed to create sessionFactory object." + ex);
//	    	throw new ExceptionInInitializerError(ex); 
//	    }
//	}

	@Override
	public ResultMessage add(BankAccountPO po) {
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
	public ResultMessage delete(String id) {
		Session session = HibernateHelper.getSession();
		Transaction tx = null;
		ResultMessage result = ResultMessage.SUCCESS;
		
		try {
			tx = session.beginTransaction();
			session.delete(find(id));
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
	public ResultMessage modify(BankAccountPO po) {
		Session session = HibernateHelper.getSession();
		Transaction tx = null;
		ResultMessage result = ResultMessage.SUCCESS;
		
		try {
			tx = session.beginTransaction();
			session.update(po);
			tx.commit();
		} catch (HibernateException e) {
			if(tx != null) 
				tx.rollback();
			e.printStackTrace();
			result = ResultMessage.FAIL;
		} finally {
			session.close();
		}
		
		return result;
	}

	@Override
	public BankAccountPO find(String id) {
		Session session = HibernateHelper.getSession();
		Transaction tx = null;
		BankAccountPO bankAccount = null;
		
		try {
			tx = session.beginTransaction();
			
			Criteria cr = session.createCriteria(BankAccountPO.class);
			cr.add(Restrictions.eq("id", id));    
			@SuppressWarnings("unchecked")
			List<BankAccountPO> results = cr.list();
			
			if(!results.isEmpty()) 
				bankAccount = results.get(0);
			tx.commit();
		} catch (HibernateException e) {
			if(tx != null) 
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return bankAccount;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<BankAccountPO> getList() {
		Session session = HibernateHelper.getSession();
		Transaction tx = null;
		ArrayList<BankAccountPO> results = null;
		
		try {
			tx = session.beginTransaction();
			results = (ArrayList<BankAccountPO>) session.createQuery("from BankAccountPO").list();
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

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public ArrayList<BankAccountPO> getList(String part) {
		Session session = HibernateHelper.getSession();
		Transaction tx = null;
		ArrayList<BankAccountPO> bankAccounts = null;
		String hql = "from BankAccountPO where id like '%" + part + "%'";
		
		try {
			tx = session.beginTransaction();
			
			Query query = session.createQuery(hql);
			List<BankAccountPO> results = query.list();
			bankAccounts = new ArrayList<BankAccountPO>(results);
			
			tx.commit();
		} catch (HibernateException e) {
			if(tx != null) 
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return bankAccounts;
	}

}
