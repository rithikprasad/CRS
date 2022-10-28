package com.training.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Professor {
	int pid;
	String name;
	public Professor(int pid, String name) {
		super();
		this.pid = pid;
		this.name = name;
	}
	public Professor() {
		super();
	}
	@JsonProperty
	public int getPid() {
		return pid;
	}
	@JsonProperty
	public void setPid(int pid) {
		this.pid = pid;
	}
	@JsonProperty
	public String getName() {
		return name;
	}
	@JsonProperty
	public void setName(String name) {
		this.name = name;
	}
	

}
