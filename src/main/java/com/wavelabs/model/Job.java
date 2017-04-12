package com.wavelabs.model;

import java.sql.Time;

public class Job {

	private int id;
	private String area;
	private Time fromTime;
	private Time toTime;
	private Double salary;
	private JobType type;
	private String phoneNumber;
	private Long zipCode;
	private String description;
	private User user;

	public Job() {

	}
	public Job(int id, String area, Time fromTime, Time toTime, Double salary, JobType type, String phoneNumber,
			Long zipCode, String description, User user) {
		this.id = id;
		this.area = area;
		this.fromTime = fromTime;
		this.toTime = toTime;
		this.type = type;
		this.phoneNumber = phoneNumber;
		this.zipCode = zipCode;
		this.description = description;
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Time getFromTime() {
		return fromTime;
	}

	public void setFromTime(Time fromTime) {
		this.fromTime = fromTime;
	}

	public Time getToTime() {
		return toTime;
	}

	public void setToTime(Time toTime) {
		this.toTime = toTime;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public JobType getType() {
		return type;
	}

	public void setType(JobType type) {
		this.type = type;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Long getZipCode() {
		return zipCode;
	}

	public void setZipCode(Long zipCode) {
		this.zipCode = zipCode;
	}
}
