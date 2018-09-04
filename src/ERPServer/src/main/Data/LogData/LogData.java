package main.Data.LogData;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import main.Data.hibernateHelper.HibernateHelper;
import main.PO.LogPO;

@SuppressWarnings("deprecation")
public class LogData {
	
	public LogData() {
	}
	
	public boolean addLog(LogPO po){
		
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
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ArrayList<LogPO> findLog(String keyword, String type){
		Session session = HibernateHelper.getSession();
		Transaction tx = null;
		ArrayList<LogPO> logs = null;
		String hql = "from LogPO where " + type + " like '%" + keyword + "%'";
		
		try {
			tx = session.beginTransaction();
			
			Query query = session.createQuery(hql);
			List<LogPO> results = query.list();
			logs = new ArrayList<>(results);
			
			tx.commit();
		} catch (HibernateException e) {
			if(tx != null) 
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return logs;		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ArrayList<LogPO> findLogByTime(String startTime, String endTime) {
		Session session = HibernateHelper.getSession();
		Transaction tx = null;
		ArrayList<LogPO> logs = null;
		String hql = "from LogPO where createDate between " +
				startTime + " and " +  endTime;

		try {
			tx = session.beginTransaction();
			
			Query query = session.createQuery(hql);
			List<LogPO> results = query.list();
			logs = new ArrayList<>(results);
			
			tx.commit();
		} catch (HibernateException e) {
			if(tx != null) 
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return logs;
	}
}
