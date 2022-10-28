package com.training.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Course {
		int cid;
		String name;
		int cost;
		
		public Course() {
			super();
		}
		public Course(int cid, String name, int cost) {
			super();
			this.cid = cid;
			this.name = name;
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
		public String getName() {
			return name;
		}
		@JsonProperty
		public void setName(String name) {
			this.name = name;
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
