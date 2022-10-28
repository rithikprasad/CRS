package com.training.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.training.api.Course;
import com.training.api.Product;
import com.training.api.Professor;
import com.training.api.Student;
import com.training.api.sid_cid_gradeClass;
import com.training.exception.dbAccessException;

public class ProfessorDAOjdbcimpl implements ProfessorDAO{
	ConnectionUtil connectionUtil;
	
	//create a constructor
	


	public ProfessorDAOjdbcimpl(ConnectionUtil connectionUtil) {
		super();
		this.connectionUtil = connectionUtil;
	}

	

	
	public int insertCourse(int pidtoBeInserted, int cidtoBeInserted) {

		int id=0;
		try(Connection c=connectionUtil.getConnection();){
			
			String sql="insert into jedi.courseprof(cid,pid) values(?,?)";
			PreparedStatement s=c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			s.setInt(1, cidtoBeInserted);			
			s.setInt(2, pidtoBeInserted);
			System.out.println("--------------------------Done1");
			s.executeUpdate();
			ResultSet keys=s.getGeneratedKeys();
			if(keys.next())
			{
			id=keys.getInt(1);
			System.out.println("--------------------------Done2");}
			//automatically does c.close in finally because of what is written in the brackets
			
		}
		catch(SQLException e) {
			e.printStackTrace();
			
		}
		return id;
	}
	

	
	public List<Student> findStudentList(int pid,int cid) {
		// TODO Auto-generated method stub
		List<Student> all = new ArrayList<>();
		
		try(Connection c = connectionUtil.getConnection();){
			String sql = "SELECT s.sid, s.name \n"
					+ "FROM jedi.courseprof AS cp, jedi.studentreg AS sg, jedi.student AS s\n"
					+ "WHERE cp.cpid = sg.cpid AND s.sid = sg.sid AND cp.cid = ? AND cp.pid = ?";
			PreparedStatement s = c.prepareStatement(sql);
			s.setInt(1, cid);
			s.setInt(2, pid);
			ResultSet rs = s.executeQuery();
			while(rs.next()) {
				Student st = mapRowToObjectStudent(rs);
				all.add(st);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return all;
	}

	

public boolean updateGrade(sid_cid_gradeClass toBeUpdated) {
		try (Connection c = connectionUtil.getConnection();) {
			String sql = "update jedi.studentreg set grade=? where sid=? and cpid=?";
			PreparedStatement s = c.prepareStatement(sql);
			s.setString(1, toBeUpdated.getGrade());
			s.setInt(2, toBeUpdated.getSid());
			s.setInt(3, toBeUpdated.getCid());
			
			int rs=s.executeUpdate();
			boolean update=true;
			return update;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new dbAccessException(e);
		}
	}

public List<Course> viewCourses() {
		
		List<Course> courseList = new ArrayList<>();
		try (Connection c = connectionUtil.getConnection()) {
			
			String sql = "select cid, name, cost from jedi.course";
			PreparedStatement s = c.prepareStatement(sql);
			//statement.setInt(1, catalogId);
			ResultSet resultSet = s.executeQuery();
			
			while(resultSet.next()) {
				
				Course course = mapRowToObjectProf(resultSet);
				
				courseList.add(course);
				
			}
			
			
		}catch(SQLException se) {
			
			se.printStackTrace();
			
		}
		System.out.println(courseList);
		return courseList; 
		
	}
private Course mapRowToObjectProf(ResultSet resultSet) throws SQLException {
	Course p = new Course();
	p.setCid(resultSet.getInt(1));
	p.setName(resultSet.getString(2));
	p.setCost(resultSet.getInt(3));
	
	return p;
}


private Student mapRowToObjectStudent(ResultSet resultSet) throws SQLException {
	Student s = new Student();
	s.setSid(resultSet.getInt(1));
	s.setSname(resultSet.getString(2));
	
	
	return s;
}




@Override
public int insertProfessor(Professor prof) {
	int id = 0;
	try (Connection c = connectionUtil.getConnection();) {
		String sql = "INSERT INTO `jedi`.`prof` (`pid`, `name`) VALUES (?, ?)";
		PreparedStatement s = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		s.setInt(1, prof.getPid());
		s.setString(2, prof.getName());
		s.executeUpdate();
		
		id = 1;
	} catch (SQLException e) {
		e.printStackTrace();
	}

	return id;
}



}