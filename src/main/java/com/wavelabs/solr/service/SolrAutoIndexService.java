package com.wavelabs.solr.service;

import com.wavelabs.utility.SolrIndexJobScheduler;

public class SolrAutoIndexService {

	
	public static Boolean startAutoIndex(String timeIntervel) {
		return SolrIndexJobScheduler.startSchedule(timeIntervel);
	
	}
	public static Boolean stopAutoIndex() {
		return SolrIndexJobScheduler.stopSchedule();
	}
	public static Boolean getStatus() {
		return SolrIndexJobScheduler.getFlag();
	}
}
