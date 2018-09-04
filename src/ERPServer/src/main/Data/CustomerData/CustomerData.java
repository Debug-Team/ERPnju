package main.Data.CustomerData;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import main.Data.hibernateHelper.HibernateHelper;
import main.DataService.CustomerDataService.CustomerDataService;
import main.PO.CustomerPO;

@SuppressWarnings("deprecation")
public class CustomerData implements CustomerDataService{

	public CustomerData() {
	}
	
	@Override
	public boolean customerAdd(CustomerPO po) {
		Session session = HibernateHelper.getSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			session.save(po);
			tx.commit();
		} catch (HibernateException e) {
			if(tx != null) 
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return true;
	}

	@Override
	public boolean customerDelete(CustomerPO po) {
		Session session = HibernateHelper.getSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			session.delete(po);
			tx.commit();
		} catch (HibernateException e) {
			if(tx != null) 
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return true;
	}

	@Override
	public boolean customerModify(CustomerPO po) {
		Session session = HibernateHelper.getSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			session.update(po);
			tx.commit();
		} catch (HibernateException e) {
			if(tx != null) 
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<CustomerPO> customerAll() {
		Session session = HibernateHelper.getSession();
		Transaction tx = null;
		ArrayList<CustomerPO> list = null;
		
		try {
			tx = session.beginTransaction();
			list = (ArrayList<CustomerPO>) session.createQuery("from CustomerPO").list();
			tx.commit();
		} catch (HibernateException e) {
			if(tx != null) 
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return list;
	}

	@Override
	public CustomerPO customerFind(String id) {
		Session session = HibernateHelper.getSession();
		Transaction tx = null;
		CustomerPO customer = null;
		
		try {
			tx = session.beginTransaction();

			Criteria cr = session.createCriteria(CustomerPO.class);
			cr.add(Restrictions.eq("ID", id));    
			@SuppressWarnings("unchecked")
			List<CustomerPO> results = cr.list();
			
			if(!results.isEmpty()) 
				customer = results.get(0);
			tx.commit();
		} catch (HibernateException e) {
			if(tx != null) 
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return customer;
	}
	
	@Override
	public int getCustomerID(String category){
		Session session = HibernateHelper.getSession();
		Transaction tx = null;
		int ret = 0;
		CustomerPO po = null;

		try {
			tx = session.beginTransaction();

			Criteria cr = session.createCriteria(CustomerPO.class);
			cr.add(Restrictions.eq("category", category));    
			@SuppressWarnings("unchecked")
			List<CustomerPO> results = cr.list();
			if(!results.isEmpty()){
				po = results.get(results.size()-1);
				String s = po.getID().substring(3);
				ret = Integer.parseInt(s);
			}else{
				ret = 0;
			}
			
			tx.commit();
		} catch (HibernateException e) {
			if(tx != null) 
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return ret+1;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public ArrayList<CustomerPO> customerFind(String keyword, String type) {
		Session session = HibernateHelper.getSession();
		Transaction tx = null;
		ArrayList<CustomerPO> customers = null;
		String hql = "from CustomerPO where " + type + " like '%" + keyword + "%'";
		
		try {
			tx = session.beginTransaction();
			
			Query query = session.createQuery(hql);
			List<CustomerPO> results = query.list();
			customers = new ArrayList<>(results);
			
			tx.commit();
		} catch (HibernateException e) {
			if(tx != null) 
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return customers;
	}

}
