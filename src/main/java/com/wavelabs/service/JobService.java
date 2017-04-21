package com.wavelabs.service;

import com.wavelabs.dao.JobDAO;
import com.wavelabs.model.Job;

public class JobService {

	public static Boolean persistJob(Job job) {
		return JobDAO.persistJob(job);
	}

	public static Job getJob(int id) {
		return JobDAO.getJob(id);
	}

	public static Job deleteJob(int id) {
		return JobDAO.deleteJob(id);
	}

	public static Boolean updateJob(Job job) {

		return JobDAO.updateJob(job);
	}
}
