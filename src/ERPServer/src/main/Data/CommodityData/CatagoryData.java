package main.Data.CommodityData;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import main.Data.hibernateHelper.HibernateHelper;
import main.DataService.CommodityDataService.CatagoryDataService;
import main.PO.CategoryPO;

@SuppressWarnings("deprecation")
public class CatagoryData implements CatagoryDataService{

	@Override
	public boolean catagoryAdd(CategoryPO po) {
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
	public boolean catagoryDelete(CategoryPO po) {
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
	public boolean catagoryModify(CategoryPO po) {
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
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ArrayList<CategoryPO> catagoryFind(String catagoryName) {
		Session session = HibernateHelper.getSession();
		Transaction tx = null;
		ArrayList<CategoryPO> list = null;
		String hql = "from CategoryPO where " + "myvalue" + " = '" + catagoryName + "'";
		if(catagoryName.equals(""))
			hql = "from CategoryPO where " + "myvalue" + " like '%" + catagoryName + "%'";
		
		try {
			tx = session.beginTransaction();
			
			Query query = session.createQuery(hql);
			List<CategoryPO> results = query.list();
			list = new ArrayList<>(results);
			
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
