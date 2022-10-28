package com.training.db;

import java.util.List;

import com.training.api.Course;
import com.training.api.Professor;
import com.training.api.Student;
import com.training.api.sid_cid_gradeClass;



public interface ProfessorDAO {

	public int insertCourse(int pidtoBeInserted, int cidtoBeInserted);
	public List<Student> findStudentList(int pid,int cid);
	public boolean updateGrade(sid_cid_gradeClass toBeUpdated);
	public List<Course> viewCourses();
	public int insertProfessor(Professor prof);
	
}
