package main.Data.PromotionData;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import main.Data.hibernateHelper.HibernateHelper;
import main.DataService.PromotionDataService.PromotionDataService;
import main.PO.PromotionPO;
import main.utility.PromotionType;
import main.utility.ResultMessage;

public class PromotionData implements PromotionDataService {
	
//	private static SessionFactory factory;
//
//	public PromotionData() {
//		try {
//			factory = new Configuration().configure().buildSessionFactory();
//	    } catch (Throwable ex) { 
//	    	System.err.println("Failed to create sessionFactory object." + ex);
//	    	throw new ExceptionInInitializerError(ex); 
//	    }
//	}

	@Override
	public PromotionPO find(String id) {
		Session session = HibernateHelper.getSession();
		Transaction tx = null;
		PromotionPO promotion = null;
		
		try {
			tx = session.beginTransaction();
			
			@SuppressWarnings("deprecation")
			Criteria cr = session.createCriteria(PromotionPO.class);
			cr.add(Restrictions.eq("id", id));    
			@SuppressWarnings("unchecked")
			List<PromotionPO> results = cr.list();
			
			if(!results.isEmpty()) 
				promotion = results.get(0);
			tx.commit();
		} catch (HibernateException e) {
			if(tx != null) 
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return promotion;
	}

	@Override
	public ResultMessage insert(PromotionPO po) {
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
		
		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage delete(String id) {
		Session session = HibernateHelper.getSession();
		Transaction tx = null;
		PromotionPO po = this.find(id);
		
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
		
		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage update(PromotionPO po) {
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
		
		return ResultMessage.SUCCESS;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<PromotionPO> getPromotionList(PromotionType type) {
		
		Session session = HibernateHelper.getSession();
		Transaction tx = null;
		ArrayList<PromotionPO> list = null;
		
		try {
			tx = session.beginTransaction();
			if(type == PromotionType.ALL) {
				list = (ArrayList<PromotionPO>) session.createQuery("from PromotionPO").list();
			}
			else if(type == PromotionType.LEVEL) {
				list = (ArrayList<PromotionPO>) session.createQuery("from LevelPromotionPO").list();
			}
			else if(type == PromotionType.PACKAGE) {
				list = (ArrayList<PromotionPO>) session.createQuery("from PackagePromotionPO").list();
			}
			else if(type == PromotionType.TOTAL) {
				list = (ArrayList<PromotionPO>) session.createQuery("from TotalPromotionPO").list();
			}
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
	
}
