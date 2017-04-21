package com.wavelabs.utility;

import java.io.IOException;
import java.util.List;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import com.wavelabs.model.Job;
public class DataFromSolr {

	
	public static void main(String[] args) throws SolrServerException, IOException {

		SolrClient client = new HttpSolrClient("http://localhost:8983/solr/job");
		SolrQuery query = new SolrQuery("*:*");
		QueryResponse response = client.query(query);
		List<Job> jobs = response.getBeans(Job.class);
		System.out.println(jobs.size());
	}
}
