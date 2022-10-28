package com.training.db;

import com.training.api.StudentReg;

public interface StudentRegDAO {
	
	public int insertStudentReg(StudentReg sg);
	public int deleteStudentReg(StudentReg sg);
	public int countStudentRegById(int sid);

}
