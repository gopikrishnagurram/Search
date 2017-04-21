package com.wavelabs.utility;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class DumpCreation {

	public static void main(String[] args) {

		Configuration cfg = new Configuration();
		@SuppressWarnings("deprecation")
		SessionFactory factory = cfg.configure("com/wavelabs/utility/Hibernate.cfg.xml").buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		for (int i = 0; i < 500; i++) {
			Dump dump = new Dump();
			dump.setI(i);
			if (i % 2 == 0) {
				dump.setName("nbos");
			} else {
				dump.setName("wave labs");
			}
			session.save(dump);
		}
		tx.commit();
		session.close();
		factory.close();
	}
}
