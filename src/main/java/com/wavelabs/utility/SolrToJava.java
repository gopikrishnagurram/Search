package com.wavelabs.utility;


import java.io.IOException;
import java.util.List;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;


public class SolrToJava {

	public static void main(String[] args) throws SolrServerException, IOException {
		SolrClient client = new HttpSolrClient("http://localhost:8983/solr/jobtest");
		SolrQuery query = new SolrQuery("*:*");
		String json = "{\"id\":\"8a8989ae5b8484aa015b8484ae650000\",\"name\":\"nbos\",\"i\":0}";
		query.setParam("fl", "id,name,i");
		query.setRows(1);
		QueryResponse resp = client.query(query);
		/*SolrDocumentList documentList = resp.getResults();
		ObjectMapper mapper = new ObjectMapper();
		Dump d = mapper.readValue(json, Dump.class);
		System.out.println(d.getName());
		System.out.println(d.getI());
		System.out.println(d.getId());
		for (SolrDocument document : documentList) {
			String dumpObj = JSONUtil.toJSON(document);
			dumpObj = dumpObj.replace("\"name\":[", "\"name\":");
			dumpObj = dumpObj.replace("\"i\":[", "\"i\":");
			int index = dumpObj.indexOf("\"name\":");
			System.out.println(index);
			System.out.println(dumpObj);
		}*/
		List<test.Job> list = resp.getBeans(test.Job.class);
		System.out.println(list.size());
		client.close();
	}
}
