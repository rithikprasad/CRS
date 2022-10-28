package com.training.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CourseProfGrade {
	private String course;
	private String prof;
	private int grade;
	public CourseProfGrade(String course, String prof, int grade) {
		super();
		this.course = course;
		this.prof = prof;
		this.grade = grade;
	}
	@JsonProperty
	public String getCourse() {
		return course;
	}
	@JsonProperty
	public void setCourse(String course) {
		this.course = course;
	}
	@JsonProperty
	public String getProf() {
		return prof;
	}
	@JsonProperty
	public void setProf(String prof) {
		this.prof = prof;
	}
	@JsonProperty
	public int getGrade() {
		return grade;
	}
	@JsonProperty
	public void setGrade(int grade) {
		this.grade = grade;
	}
	
	

}
