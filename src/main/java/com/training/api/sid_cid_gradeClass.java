package com.training.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class sid_cid_gradeClass {
	
	int sid;
	int cid;
	String grade;
	
	public sid_cid_gradeClass() {
		super();
	}
	public sid_cid_gradeClass(int sid, int cid, String grade) {
		super();
		this.sid = sid;
		this.cid = cid;
		this.grade = grade;
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
	public int getCid() {
		return cid;
	}
	@JsonProperty
	public void setCid(int cid) {
		this.cid = cid;
	}
	@JsonProperty
	public String getGrade() {
		return grade;
	}
	@JsonProperty
	public void setGrade(String grade) {
		this.grade = grade;
	}
 
	
	
	
	

}
