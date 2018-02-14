package com.entity.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.hib.entities.Agent;
import com.hib.entities.Log;
import com.hib.entities.Request;
import com.hib.entities.Status;
import com.utility.HibernateUtil;

public class LogDao implements LogInterface {

	private SessionFactory sf = null;
	private Session session = null;
	private Transaction tx = null;

	@Override
	public void insert(List<Log> logs) {
		// TODO Auto-generated method stub
		try {
			sf = HibernateUtil.getSessionFactory();
			session = sf.openSession();
			tx = session.beginTransaction();
			for (Log log : logs) {
				session.save(log);
			}
			session.flush();
			session.clear();
			tx.commit();
		} catch (Exception e) {
			System.out.println(e);
		}finally {
			if (session.isOpen())
				session.close();
		}
	}

	@Override
	public Integer insertStatus(Status status) {
		Integer id = null;
		// TODO Auto-generated method stub
		try {
			sf = HibernateUtil.getSessionFactory();
			session = sf.openSession();
			tx = session.beginTransaction();
			id = (Integer) session.save(status);
			tx.commit();
		} catch (Exception e) {
			System.out.println(e);
		}finally {
			if (session.isOpen())
				session.close();
		}
		return id;
	}

	@Override
	public Integer insertAgent(Agent agent) {
		Integer id = null;
		// TODO Auto-generated method stub
		try {
			sf = HibernateUtil.getSessionFactory();
			session = sf.openSession();
			tx = session.beginTransaction();
			id = (Integer) session.save(agent);
			tx.commit();
		} catch (Exception e) {
			System.out.println(e);
		}finally {
			if (session.isOpen())
				session.close();
		}
		return id;
	}

	@Override
	public Integer insertRequest(Request request) {
		Integer id = null;
		// TODO Auto-generated method stub
		try {
			sf = HibernateUtil.getSessionFactory();
			session = sf.openSession();
			tx = session.beginTransaction();
			id = (Integer) session.save(request);
			tx.commit();
			session.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return id;
	}
}
