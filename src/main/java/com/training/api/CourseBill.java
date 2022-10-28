package com.training.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CourseBill {
	private int cid;
	private String cname;
	private int cost;
	public CourseBill(int cid, String cname, int cost) {
		super();
		this.cid = cid;
		this.cname = cname;
		this.cost = cost;
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
	public String getCname() {
		return cname;
	}
	@JsonProperty
	public void setCname(String cname) {
		this.cname = cname;
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
