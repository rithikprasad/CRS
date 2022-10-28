package com.training.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Courses {
	int c1,c2,c3,c4,c5,c6;

	public Courses() {
		super();
	}

	public Courses(int c1, int c2, int c3, int c4, int c5, int c6) {
		super();
		this.c1 = c1;
		this.c2 = c2;
		this.c3 = c3;
		this.c4 = c4;
		this.c5 = c5;
		this.c6 = c6;
	}
	@JsonProperty
	public int getC1() {
		return c1;
	}
	@JsonProperty
	public void setC1(int c1) {
		this.c1 = c1;
	}
	@JsonProperty
	public int getC2() {
		return c2;
	}
	@JsonProperty
	public void setC2(int c2) {
		this.c2 = c2;
	}
	@JsonProperty
	public int getC3() {
		return c3;
	}
	@JsonProperty
	public void setC3(int c3) {
		this.c3 = c3;
	}
	@JsonProperty
	public int getC4() {
		return c4;
	}
	@JsonProperty
	public void setC4(int c4) {
		this.c4 = c4;
	}
	@JsonProperty
	public int getC5() {
		return c5;
	}
	@JsonProperty
	public void setC5(int c5) {
		this.c5 = c5;
	}
	@JsonProperty
	public int getC6() {
		return c6;
	}
	@JsonProperty
	public void setC6(int c6) {
		this.c6 = c6;
	}
	

}
