package com.entity.dao;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StandardBasicTypes;

import com.hib.entities.Log;
import com.hib.entities.Report;
import com.utility.HibernateUtil;

public class ReportDao implements ReportInterface {
	private SessionFactory sf = null;
	private Session session = null;
	private Transaction tx = null;

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<String> check(Timestamp start, Timestamp end, int threshold) {
		List<String> list = null;
		try {
			sf = HibernateUtil.getSessionFactory();
			session = sf.openSession();

			Criteria criteria = session.createCriteria(Log.class);
			criteria.add(Restrictions.between("date_time", start, end));
			criteria.setProjection(Projections.sqlGroupProjection("ip", "ip having count(ip) > " + threshold,
					new String[] { "ip" }, new org.hibernate.type.Type[] { StandardBasicTypes.STRING }));
			list = criteria.list();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			if (session.isOpen())
				session.close();
		}

		return list;
	}

	@Override
	public void insertErrorMsg(List<Report> reports) {
		// TODO Auto-generated method stub
		try {
			sf = HibernateUtil.getSessionFactory();
			session = sf.openSession();
			tx = session.beginTransaction();
			for (Report report : reports) {
				session.save(report);
			}
			session.flush();
			session.clear();
			tx.commit();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			if (session.isOpen())
				session.close();
		}
	}

}
