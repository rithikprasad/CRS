package com.training.api;

public class StudentReg {
	private int sid;
	private int cpid;
	private int grade;
	public StudentReg(int sid, int cpid, int grade) {
		super();
		this.sid = sid;
		this.cpid = cpid;
		this.grade = grade;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public int getCpid() {
		return cpid;
	}
	public void setCpid(int cpid) {
		this.cpid = cpid;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	
	
	

}
