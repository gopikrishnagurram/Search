package com.wavelabs.utility;

import org.apache.solr.client.solrj.beans.Field;

public class Dump {
	@Field
	private String id;
	@Field
	private String name;
	@Field
	private int i;
	
	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
