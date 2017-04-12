package com.wavelabs.model;

import java.sql.Time;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Test {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {

		Configuration cfg = new Configuration();
		SessionFactory factory = cfg.configure().buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		User user = new User();
		user.setEmail("gopi@gmail.com");
		user.setJobType(JobType.Home);
		user.setName("gopi krishna");
		user.setPhone("9032118864");
		user.setType(UserType.Seeker);
		user.setJobType(JobType.None);
		Job job = new Job();
		job.setArea("A");
		job.setDescription("DESC");
		job.setFromTime(new Time(8, 21, 0));
		job.setToTime(new Time(10, 5, 0));
		job.setPhoneNumber("9133213559");
		job.setSalary(2100d);
		job.setZipCode(521402l);
		job.setType(JobType.Child);
		job.setUser(user);
		session.save(job);
		tx.commit();
		session.close();
		factory.close();
		System.out.println("Export completed");
	}
}
