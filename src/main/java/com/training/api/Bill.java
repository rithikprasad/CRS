package com.training.api;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Bill {
	
	int sid;
	List<CourseBill> courses;
	float total;
	public Bill(int sid) {
		super();
		this.sid = sid;
		this.courses = new ArrayList<CourseBill>();
		this.total = 0;
	}
	@JsonProperty
	public int getSid() {
		return sid;
	}
	@JsonProperty
	public void setSid(int sid) {
		this.sid = sid;
	}
	@JsonProperty
	public List<CourseBill> getCourses() {
		return courses;
	}
	@JsonProperty
	public void addToCourses(CourseBill cb) {
		this.courses.add(cb);
	}
	@JsonProperty
	public float getTotal() {
		return total;
	}
	@JsonProperty
	public void setTotal(float total) {
		this.total = total;
	}
	
	

}
