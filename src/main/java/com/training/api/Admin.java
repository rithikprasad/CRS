package com.training.api;

import com.training.constant.GenderConstant;
import com.training.constant.RoleConstant;

public class Admin extends User{
	private int adminID;

	public Admin(String userId, String name, String password, String newPassword, RoleConstant role,
			GenderConstant gender, String address, int adminID) {
		super(userId, name, password, newPassword, role, gender, address);
		this.adminID = adminID;
	}

}
