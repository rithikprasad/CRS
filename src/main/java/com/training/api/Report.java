package com.training.api;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Report {
	int sid;
	List<CourseProfGrade> cpgList;
	float sgpa;
	public Report(int sid) {
		super();
		this.sid = sid;
		this.sgpa = 0;
		this.cpgList = new ArrayList<CourseProfGrade>();
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
	public List<CourseProfGrade> getCpgList() {
		return cpgList;
	}
	@JsonProperty
	public void addToCpgList(CourseProfGrade cpg) {
		this.cpgList.add(cpg);
	}
	@JsonProperty
	public float getSgpa() {
		return sgpa;
	}
	@JsonProperty
	public void setSgpa(float sgpa) {
		this.sgpa = sgpa;
	}
	
}
