package main.Data.CommodityData;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import main.Data.hibernateHelper.HibernateHelper;
import main.DataService.CommodityDataService.CommodityReciptDataService;
import main.PO.CommodityReciptPO;
import main.utility.ResultMessage;

@SuppressWarnings("deprecation")
public class CommodityReciptData implements CommodityReciptDataService{
	
	@Override
	public ResultMessage add(CommodityReciptPO po) {
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
	public ResultMessage modify(ArrayList<CommodityReciptPO> list){
		Session session = HibernateHelper.getSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			for(CommodityReciptPO po : list){
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
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ArrayList<CommodityReciptPO> find(String keyword, String type){
		Session session = HibernateHelper.getSession();
		Transaction tx = null;
		ArrayList<CommodityReciptPO> recipts = null;
		String hql = "from CommodityReciptPO where " + type + " like '%" + keyword + "%'";
		
		try {
			tx = session.beginTransaction();
			
			Query query = session.createQuery(hql);
			List<CommodityReciptPO> results = query.list();
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
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ArrayList<CommodityReciptPO> find(String startTime, String endTime, String customerName,
			String operator){
		Session session = HibernateHelper.getSession();
		Transaction tx = null;
		ArrayList<CommodityReciptPO> recipts = null;
		String hql = "from CommodityReciptPO where createDate between " +
				startTime + " and " +  endTime;

		try {
			tx = session.beginTransaction();
			
			Query query = session.createQuery(hql);
			List<CommodityReciptPO> results = query.list();
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
