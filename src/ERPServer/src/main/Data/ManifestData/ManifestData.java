package main.Data.ManifestData;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import main.Data.hibernateHelper.HibernateHelper;
import main.DataService.ManifestDataService.ManifestDataService;
import main.PO.ManifestPO;
import main.utility.ResultMessage;

@SuppressWarnings("deprecation")
public class ManifestData implements ManifestDataService {
	
	@Override
	public boolean setManifest(ManifestPO po){
		Session session = HibernateHelper.getSession();;
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
	public boolean updateManifest(ManifestPO po){

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
	
	@Override
	public int newID(String string, String date){
		Session session = HibernateHelper.getSession();
		Transaction tx = null;
		int ret = 0;
		ManifestPO po = null;
		
		try {
			tx = session.beginTransaction();
			
			Criteria cr = session.createCriteria(ManifestPO.class);
			cr.add(Restrictions.eq("type", string));
			cr.add(Restrictions.eq("createDate",date));
			@SuppressWarnings("unchecked")
			List<ManifestPO> results = cr.list();
			if(!results.isEmpty()){
				po = results.get(results.size()-1);
				String s = po.getID().split("-")[2];
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
	
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ArrayList<ManifestPO> find(String keyword, String type){
		Session session = HibernateHelper.getSession();
		Transaction tx = null;
		ArrayList<ManifestPO> recipts = null;
		String hql = "from ManifestPO where " + type + " like '%" + keyword + "%'";
		
		try {
			tx = session.beginTransaction();
			
			Query query = session.createQuery(hql);
			List<ManifestPO> results = query.list();
			recipts = new ArrayList<>(results);
			
			tx.commit();
		} catch (HibernateException e) {
			if(tx != null) 
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return recipts;
	}
	
	@Override
	public ResultMessage modify(ArrayList<ManifestPO> list){
		Session session = HibernateHelper.getSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			for(ManifestPO po : list){
				session.update(po);
			}
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
	public boolean deleteManifest(ManifestPO po){
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
	@SuppressWarnings("unchecked")
	public ArrayList<ManifestPO> getSalesHistoryList(String startTime, String endTime,
			String customerName,String operator) {
		Session session = HibernateHelper.getSession();
		Transaction tx = null;
		ArrayList<ManifestPO> recipts = null;
		String hql = "from ManifestPO where createDate between " + startTime + " and " +  endTime +
				" and customerName  like '%" + customerName + "%'" +
				" and operator like '%" + operator + "%'";

		try {
			tx = session.beginTransaction();
			
			@SuppressWarnings("rawtypes")
			Query query = session.createQuery(hql);
			List<ManifestPO> results = query.list();
			recipts = new ArrayList<>(results);
			
			tx.commit();
		} catch (HibernateException e) {
			if(tx != null) 
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return recipts;
	}

	@Override
	public ResultMessage modifyManifest(ManifestPO po) {
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
	
	@Override
	@SuppressWarnings("unchecked")
	public ArrayList<ManifestPO> findByTime(String startTime, String endTime){
		Session session = HibernateHelper.getSession();
		Transaction tx = null;
		ArrayList<ManifestPO> recipts = null;
		String hql = "from ManifestPO where createDate between " + startTime + " and " +  endTime;

		try {
			tx = session.beginTransaction();
			
			@SuppressWarnings("rawtypes")
			Query query = session.createQuery(hql);
			List<ManifestPO> results = query.list();
			recipts = new ArrayList<>(results);
			
			tx.commit();
		} catch (HibernateException e) {
			if(tx != null) 
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return recipts;	
	}
}
