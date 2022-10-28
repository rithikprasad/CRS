package com.training.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Student {
	
	private int sid;
	private String sname;
	
	public Student(int sid, String sname) {
		super();
		this.sid = sid;
		this.sname = sname;
	}
	public Student() {
		// TODO Auto-generated constructor stub
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
	public String getSname() {
		return sname;
	}
	@JsonProperty
	public void setSname(String sname) {
		this.sname = sname;
	}

	

}
