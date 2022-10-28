package com.training.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.training.api.StudentReg;

public class StudentRegImpl implements StudentRegDAO {
	
	ConnectionUtil connectionUtil;
	
	

	public StudentRegImpl(ConnectionUtil connectionUtil) {
		super();
		this.connectionUtil = connectionUtil;
	}

	@Override
	public int insertStudentReg(StudentReg sg) {
		int id = 0;
		try (Connection c = connectionUtil.getConnection();) {
			String sql = "INSERT INTO `jedi`.`supertable` (`sid`, `cpid`) VALUES (?, ?)";
			PreparedStatement s = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			s.setInt(1, sg.getSid());
			s.setInt(2, sg.getCpid());
			s.executeUpdate();
			ResultSet keys = s.getGeneratedKeys();
			keys.next();
			id = keys.getInt(1);
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return id;
	}

	@Override
	public int deleteStudentReg(StudentReg sg) {
		
		int rowCount = 0;
		
		try (Connection c = connectionUtil.getConnection();) {
			String sql = "DELETE FROM `jedi`.`supertable` WHERE sid = ? AND cpid = ?";
			PreparedStatement s = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			s.setInt(1, sg.getSid());
			s.setInt(2, sg.getCpid());
			rowCount = s.executeUpdate();
			return rowCount;
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rowCount;
	}

	@Override
	public int countStudentRegById(int sid) {
		int rowCount = 0;
		
		try (Connection c = connectionUtil.getConnection();) {
			String sql = "SELECT COUNT(*) FROM `jedi`.`supertable` WHERE sid = ?";
			PreparedStatement s = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			s.setInt(1, sid);
			rowCount = s.executeUpdate();
			return rowCount;
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rowCount;
	}

}
