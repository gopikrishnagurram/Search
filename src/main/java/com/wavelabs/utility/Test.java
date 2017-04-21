package com.wavelabs.utility;


import java.io.IOException;
import java.util.List;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import test.Job;
public class Test {
	@SuppressWarnings({ "unchecked", "deprecation" })
	public static void main(String[] args) throws IOException, SolrServerException {
		Configuration cfg = new Configuration();
		SessionFactory factory = cfg.configure("test/Hibernate.cfg.xml").buildSessionFactory();
		Session session = factory.openSession();
		SolrClient client = new HttpSolrClient("http://localhost:8983/solr/jobtest");
		Criteria cr = session.createCriteria(Job.class);
		List<Job> jobs = cr.list();
		client.addBeans(jobs);
		client.commit();
		client.close();
		session.close();
		factory.close();
	}
}
