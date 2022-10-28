package com.training.db;

import java.util.List;

import com.training.api.Bill;
import com.training.api.Course;
import com.training.api.CourseCatalog;
import com.training.api.Report;
import com.training.api.Student;

public interface RegistrationDAO {
	
	public int addCourse(int sid, int cpid);
	public int dropCourse(int sid, int cpid);
	public int checkIfRegistered(int sid, int cpid);
	public List<Course> register(int sid);
	public Report getReport(int sid);
	public Bill getBill(int sid);
	public List<CourseCatalog> getcatalog();
	public int deleteCourse(int cpid);
	public List<Student> getStudentsHavingCoursesLessthanTresh(int tresh);
	public int getNumberOfStudentsInCourse(int cpid);
	public List<Integer> getCpidWhereNumberOfStudentsLessthanN(int thres);
	public List<CourseCatalog> getRegisteredList(int sid);
	

}
