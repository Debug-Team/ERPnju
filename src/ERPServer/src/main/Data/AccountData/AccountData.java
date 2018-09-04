package main.Data.AccountData;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import main.Data.hibernateHelper.HibernateHelper;
import main.DataService.AccountDataService.AccountDataService;
import main.PO.AccountPO;
import main.utility.ResultMessage;

public class AccountData implements AccountDataService {
	
//	private static SessionFactory factory;
	
//	public AccountData() {
//		try {
//			factory = new Configuration().configure().buildSessionFactory();
//	    } catch (Throwable ex) { 
//	    	System.err.println("Failed to create sessionFactory object." + ex);
//	    	throw new ExceptionInInitializerError(ex); 
//	    }
//	}

	@Override
	public AccountPO get() {
		Session session = HibernateHelper.getSession();
		Transaction tx = null;
		AccountPO account = null;
		
		try {
			tx = session.beginTransaction();
			
			@SuppressWarnings("unchecked")
			ArrayList<AccountPO> results = (ArrayList<AccountPO>)session.createQuery("from AccountPO").list();
			
			if(!results.isEmpty()) 
				account = results.get(results.size()-1);
			tx.commit();
		} catch (HibernateException e) {
			if(tx != null) 
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return account;
	}

	@Override
	public ResultMessage update(AccountPO po) {
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
	public ResultMessage set(AccountPO po) {
		Session session = HibernateHelper.getSession();
		Transaction tx = null;
		ResultMessage result = ResultMessage.SUCCESS;
		
		try {
			tx = session.beginTransaction();
			session.save(po);
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

}
