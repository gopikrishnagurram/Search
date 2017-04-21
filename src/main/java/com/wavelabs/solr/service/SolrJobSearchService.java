
package com.wavelabs.solr.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.hibernate.Query;
import org.hibernate.Session;

import com.wavelabs.model.Job;
import com.wavelabs.utility.Helper;

public class SolrJobSearchService {

	private static SolrClient client = null;
	private static List<Integer> searchIds = new ArrayList<Integer>();

	@SuppressWarnings("unchecked")
	public static Job[] getJobs(String searchTerm) throws SolrServerException, IOException {
		Session dbSession = null;
		try {
			intillization();
			SolrQuery query = new SolrQuery("q=" + searchTerm);
			QueryResponse resp = client.query(query);
			SolrDocumentList documentList = resp.getResults();
			getSearchIdList(documentList);
			dbSession = Helper.getSession();
			Query dbQuery = dbSession.createQuery("from " + Job.class.getName() + " where id in (:idList)");
			dbQuery.setParameterList("idList", searchIds);
			Job job[] = arrayConverter(dbQuery.list());
			dbSession.clear();
			return job;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			client.close();
			try {
				dbSession.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private static void intillization() {
		client = new HttpSolrClient("http://localhost:8983/solr/job");
	}

	private static List<Integer> getSearchIdList(SolrDocumentList documentList) {
		searchIds.removeAll(searchIds);
		for (SolrDocument document : documentList) {
			searchIds.add(Integer.parseInt(document.getFieldValue("id").toString()));
		}
		return searchIds;
	}

	private static Job[] arrayConverter(List<Job> list) {
		Job job[] = new Job[list.size()];
		for (int i = 0; i < list.size(); i++) {
			job[i] = list.get(i);
		}
		return job;
	}
}
