package com.wavelabs.utility;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.wavelabs.model.Job;

public class Helper {

	private static Session session = null;
	private static SessionFactory factory = null;
	private static int count = 0;
	private static Configuration cfg = null;

	public static Session getSession() {
		if (count == 0) {
			setFactory();
		}
		if (!session.isOpen()) {
			session = factory.openSession();
		}
		return session;
	}
	public static SessionFactory getFactory() {
		if (count == 0) {
			setFactory();
		}
		return factory;
	}
	@SuppressWarnings("deprecation")
	private static void setFactory() {
		cfg = new Configuration();
		factory = cfg.configure().buildSessionFactory();
		session = factory.openSession();
		count++;
	}
	public static Criteria getJobCritiera() {
		session = getSession();
		return session.createCriteria(Job.class);
	}
}
