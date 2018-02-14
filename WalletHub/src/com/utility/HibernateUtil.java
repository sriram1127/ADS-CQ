package com.utility;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.hib.entities.Agent;
import com.hib.entities.Log;
import com.hib.entities.Report;
import com.hib.entities.Request;
import com.hib.entities.Status;

public class HibernateUtil {

	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		try {
			Configuration conf = new Configuration().configure();
			conf.addAnnotatedClass(Request.class);
			conf.addAnnotatedClass(Status.class);
			conf.addAnnotatedClass(Agent.class);
			conf.addAnnotatedClass(Log.class);
			conf.addAnnotatedClass(Report.class);

			ServiceRegistry sr = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();

			return conf.buildSessionFactory(sr);
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}