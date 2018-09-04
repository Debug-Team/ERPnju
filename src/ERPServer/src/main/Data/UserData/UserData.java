package main.Data.UserData;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import main.DataService.UserDataService.UserDataService;
import main.PO.UserPO;

public class UserData implements UserDataService {
	
	private static SessionFactory factory;
	
	public UserData() {
		try {
			factory = new Configuration().configure().buildSessionFactory();
	    } catch (Throwable ex) { 
	    	System.err.println("Failed to create sessionFactory object." + ex);
	    	throw new ExceptionInInitializerError(ex); 
	    }
	}

	@Override
	public boolean findUser(String jobNum) {
		Session session = factory.openSession();
		Transaction tx = null;
		boolean result = true;
		
		try {
			tx = session.beginTransaction();
			
			@SuppressWarnings("deprecation")
			Criteria cr = session.createCriteria(UserPO.class);
			cr.add(Restrictions.eq("jobNum", jobNum));    
			@SuppressWarnings("unchecked")
			List<UserPO> results = cr.list();
			
			if(results.isEmpty()) {
				result = false;
			}
			else {
				result = true;
			}
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

	@Override
	public UserPO getUser(String jobNum) {
		Session session = factory.openSession();
		Transaction tx = null;
		UserPO result = null;
		
		try {
			tx = session.beginTransaction();
			
			@SuppressWarnings("deprecation")
			Criteria cr = session.createCriteria(UserPO.class);
			cr.add(Restrictions.eq("jobNum", jobNum));    
			@SuppressWarnings("unchecked")
			List<UserPO> results = cr.list();
			
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
	
	@Override
	public ArrayList<UserPO> getUserList() {
		Session session = factory.openSession();
		Transaction tx = null;
		ArrayList<UserPO> results = new ArrayList<UserPO>();
		
		try {
			tx = session.beginTransaction();
			
			@SuppressWarnings("deprecation")
			Criteria cr = session.createCriteria(UserPO.class);  
			@SuppressWarnings("unchecked")
			List<UserPO> temp = cr.list();
			
			results.addAll(temp);
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
	public boolean addNewUser(UserPO po) {
		Session session = factory.openSession();
		Transaction tx = null;
		boolean result = true;
		
		try {
			tx = session.beginTransaction();
			session.save(po);
			tx.commit();
		} catch (HibernateException e) {
			if(tx!=null) 
				tx.rollback();
			e.printStackTrace();
			result = false;
		} finally {
			session.close();
		}
		
		return result;
	}

	@Override
	public boolean modifyUser(UserPO po) {
		Session session = factory.openSession();
		Transaction tx = null;
		boolean result = true;
		
		try {
			tx = session.beginTransaction();
			session.update(po);
			tx.commit();
		} catch (HibernateException e) {
			if(tx!=null) 
				tx.rollback();
			e.printStackTrace();
			result = false;
		} finally {
			session.close();
		}
		
		return result;
	}

	@Override
	public boolean deleteUser(UserPO po) {
		Session session = factory.openSession();
		Transaction tx = null;
		boolean result = true;
		
		try {
			tx = session.beginTransaction();
			session.delete(po);
			tx.commit();
		} catch (HibernateException e) {
			if(tx!=null) 
				tx.rollback();
			e.printStackTrace();
			result = false;
		} finally {
			session.close();
		}
		
		return result;
	}
	
}
