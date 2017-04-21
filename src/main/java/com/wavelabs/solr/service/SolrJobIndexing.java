package com.wavelabs.solr.service;


import java.util.List;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import com.wavelabs.model.Job;
import com.wavelabs.model.JobType;
import com.wavelabs.model.UserType;

public class SolrJobIndexing implements org.quartz.Job {

	private final static Logger logger = Logger.getLogger(SolrJobIndexing.class);
	private static SolrClient client;
	private static SessionFactory factory;
	public static Boolean doIndex() {
		Criteria cr =getJobCritiera();
		boolean flag = listOfJobs(cr);
		return flag;
	}

	@SuppressWarnings("unchecked")
	private static boolean listOfJobs(Criteria cr) {
		logger.setLevel(Level.INFO);
		return addDocumentsToSolr(cr.list());
	}

	public static boolean addDocumentsToSolr(List<Job> listOfJobs) {
		int i = 0;
		createSolrInstance();
		logger.debug("adding documents into solr is started");
		try {
			for (Job job : listOfJobs) {
				SolrInputDocument docuemnt = new SolrInputDocument();
				docuemnt.addField("id", job.getId());
				docuemnt.addField("area", job.getArea());
				docuemnt.addField("fromTime", job.getFromTime());
				docuemnt.addField("toTime", job.getToTime());
				docuemnt.addField("salary", job.getSalary());
				if (job.getType() == JobType.Child) {
					docuemnt.addField("type", "Child");
				} else if (job.getType() == JobType.Home) {
					docuemnt.addField("type", "Home");
				} else {
					docuemnt.addField("type", "Pet");
				}
				docuemnt.addField("phoneNumber", job.getPhoneNumber());
				docuemnt.addField("zipCode", job.getZipCode());
				docuemnt.addField("description", job.getDescription());
				docuemnt.addField("user_id", job.getUser().getId());
				docuemnt.addField("name", job.getUser().getName());
				docuemnt.addField("email", job.getUser().getEmail());
				docuemnt.addField("phone", job.getUser().getPhone());
				if (job.getUser().getType() == UserType.Provider) {
					docuemnt.addField("userType", "Provider");
				} else {
					docuemnt.addField("userType", "Seeker");
				}
				if (job.getType() == JobType.Child) {
					docuemnt.addField("jobType", "Child");
				} else if (job.getType() == JobType.Home) {
					docuemnt.addField("jobType", "Home");
				} else {
					docuemnt.addField("jobType", "Pet");
				}
				client.add(docuemnt);
				logger.info((++i) + "Document added, not commited");
			}
			client.commit();
			logger.info("All documents commited");
			client.close();
			logger.info("Indexing completed and connection closed");
			factory.close();
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
	}

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {

		boolean flag = SolrJobIndexing.doIndex();
		if (flag) {
			logger.info("Solr  re-indexing successfully completed");
		} else {
			logger.error("Solr re-indexing failed");
		}
	}
	private static void createSolrInstance() {
		client = new HttpSolrClient("http://localhost:8983/solr/Search");
		logger.info("client connection opend");
	}
	@SuppressWarnings("deprecation")
	private static Criteria getJobCritiera() {
		Configuration cfg = new Configuration();
		 factory = cfg.configure().buildSessionFactory();
		Session session = factory.openSession();
		return session.createCriteria(Job.class);
	}
	public static void main(String[] args) {
		doIndex();
	}
}
