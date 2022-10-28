package com.training.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.training.api.Student;

public class StudentDAOImpl implements StudentDAO {
	ConnectionUtil connectionUtil;


	public StudentDAOImpl(ConnectionUtil connectionUtil) {
		super();
		this.connectionUtil = connectionUtil;
	}


	@Override
	public int insertStudent(Student student) {
		int id = 0;
		try (Connection c = connectionUtil.getConnection();) {
			String sql = "INSERT INTO `jedi`.`student` (`sid`, `name`) VALUES (?, ?)";
			PreparedStatement s = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			s.setInt(1, student.getSid());
			s.setString(2, student.getSname());
			s.executeUpdate();
			// following code is for retrieving generated PK
			ResultSet keys = s.getGeneratedKeys();
			keys.next();
			id = 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return id;
	}

}
