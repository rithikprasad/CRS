package com.training.api;

import com.training.constant.GenderConstant;
import com.training.constant.RoleConstant;

public class User {
	
	String userId;
	String name;
	String password;
	String newPassword;
	RoleConstant role;
	GenderConstant gender;
	String address;
	public User(String userId, String name, String password, String newPassword, RoleConstant role,
			GenderConstant gender, String address) {
		super();
		this.userId = userId;
		this.name = name;
		this.password = password;
		this.newPassword = newPassword;
		this.role = role;
		this.gender = gender;
		this.address = address;
	}
	public User() {
		super();
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public RoleConstant getRole() {
		return role;
	}
	public void setRole(RoleConstant role) {
		this.role = role;
	}
	public GenderConstant getGender() {
		return gender;
	}
	public void setGender(GenderConstant gender) {
		this.gender = gender;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	

}
