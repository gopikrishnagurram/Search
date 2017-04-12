package com.wavelabs.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import com.wavelabs.model.Job;
import com.wavelabs.utility.Helper;

public class JobDAO {

	public static Boolean PersisetJob(Job job) {
		Session session = null;
		try {
			session = Helper.getSession();
			Transaction tx = session.beginTransaction();
			session.save(job);
			tx.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			session.close();
		}
	}

	public static Job getJob(int id) {
		Session session = Helper.getSession();
		Job job = null;
		try {
			job = (Job) session.get(Job.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return job;
	}

	public static Job deleteJob(int id) {
		Job job = null;
		Session session = Helper.getSession();
		try {
			job = (Job) session.get(Job.class, id);
			Transaction tx = session.beginTransaction();
			session.delete(job);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return job;
	}

	public static Boolean updateJob(Job job) {
		boolean flag = false;
		Session session = Helper.getSession();
		try {
			Transaction tx = session.beginTransaction();
			session.update(job);
			tx.commit();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		} finally {
			session.close();
		}
		return flag;
	}
}
