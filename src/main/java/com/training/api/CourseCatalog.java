package com.training.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CourseCatalog {
	private int courseId;
	private String profName;
	private String courseName;
	private int cost;
	
	public CourseCatalog() {
		super();
	}
	public CourseCatalog(int courseId, String courseName, String profName, int cost) {
		super();
		this.courseId = courseId;
		this.profName = profName;
		this.courseName = courseName;
		this.cost = cost;
	}
	@JsonProperty
	public int getCourseId() {
		return courseId;
	}
	@JsonProperty
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	@JsonProperty
	public String getProfName() {
		return profName;
	}
	@JsonProperty
	public void setProfName(String profName) {
		this.profName = profName;
	}
	@JsonProperty
	public String getCourseName() {
		return courseName;
	}
	@JsonProperty
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	@JsonProperty
	public int getCost() {
		return cost;
	}
	@JsonProperty
	public void setCost(int cost) {
		this.cost = cost;
	}
	
}
