package com.wavelabs.service.SolrService;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrServerException;
import org.testng.annotations.Test;

import com.wavelabs.solr.service.SolrJobSearchService;

public class SolrServiceTest {

	
	
	@Test
	public void getJobsTest() throws SolrServerException, IOException {

		
		SolrJobSearchService.getJobs("gachibowli");
		
		
	}
}
