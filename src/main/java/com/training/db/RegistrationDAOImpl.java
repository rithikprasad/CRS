package com.training.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.training.api.Bill;
import com.training.api.Course;
import com.training.api.CourseBill;
import com.training.api.CourseCatalog;
import com.training.api.CourseProfGrade;
import com.training.api.Report;
import com.training.api.Student;

public class RegistrationDAOImpl implements RegistrationDAO {
	ConnectionUtil connectionUtil;
	

	public RegistrationDAOImpl(ConnectionUtil connectionUtil) {
		super();
		this.connectionUtil = connectionUtil;
	}

	@Override
	public int addCourse(int sid, int cpid) {
		
		int id = 0;
		try (Connection c = connectionUtil.getConnection();) {
			String sql = "INSERT INTO `jedi`.`studentreg` (`sid`, `cpid`) VALUES (?, ?)";
			PreparedStatement s = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			s.setInt(1, sid);
			s.setInt(2, cpid);
			s.executeUpdate();
			// following code is for retrieving generated PK
			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return id;
	}

	@Override
	public int dropCourse(int sid, int cpid) {
		
		int rowCount = 0;
		try (Connection c = connectionUtil.getConnection();) {
			String sql = "DELETE FROM `jedi`.`studentreg` WHERE sid = ? AND cpid = ?";
			PreparedStatement s = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			s.setInt(1, sid);
			s.setInt(2, cpid);
			rowCount = s.executeUpdate();
			return rowCount;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rowCount;
		
	}

	@Override
	public Report getReport(int sid) {
		Report report = new Report(sid);
		try (Connection c = connectionUtil.getConnection();) {
			int sgpa = 0, count = 0;
			String sql = "SELECT c.cid, p.pid, sup.grade \n"
					+ "FROM jedi.studentreg  AS sup, jedi.courseprof AS cp, jedi.course as c , jedi.prof AS p\n"
					+ "WHERE sup.cpid = cp.cpid AND cp.cid = c.cid AND cp.pid = p.pid AND sid = ? ";
			PreparedStatement s = c.prepareStatement(sql);
			s.setInt(1, sid);
			
			ResultSet rs = s.executeQuery();
			while (rs.next()) {
				report.addToCpgList(new CourseProfGrade(rs.getString(1), rs.getString(2), rs.getInt(3)));
				sgpa += rs.getInt(3);
				count++;
			}
			report.setSgpa((float)sgpa/count);
			
			return report;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return report;
	}

	@Override
	public Bill getBill(int sid) {
		
		Bill bill = new Bill(sid);
		try (Connection c = connectionUtil.getConnection();) {
			String sql = "SELECT c.cid, c.name, c.cost \n"
					+ "FROM jedi.studentreg  AS sup, jedi.courseprof AS cp, jedi.course as c \n"
					+ "WHERE sup.cpid = cp.cpid AND cp.cid = c.cid AND sid = ? ";
			PreparedStatement s = c.prepareStatement(sql);
			s.setInt(1, sid);
			
			float total = 0;
			ResultSet rs = s.executeQuery();
			while (rs.next()) {
				bill.addToCourses(new CourseBill(rs.getInt(1), rs.getString(2), rs.getInt(3)));
				total += rs.getInt(3);
			}
			bill.setTotal(total);
			
			return bill;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bill;
	}

	@Override
	public List<Course> register(int sid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CourseCatalog> getcatalog() {
		List<CourseCatalog> list = new ArrayList<CourseCatalog>();
		try (Connection c = connectionUtil.getConnection();) {
			String sql = "SELECT c.cid, c.name, p.name, c.cost \n"
					+ "FROM jedi.courseprof AS cp, jedi.course as c, jedi.prof AS p \n"
					+ "WHERE cp.cid = c.cid AND cp.pid = p.pid ";
			PreparedStatement s = c.prepareStatement(sql);
			
			ResultSet rs = s.executeQuery();
			while (rs.next()) {
				list.add(new CourseCatalog(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
			}
			return list;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int checkIfRegistered(int sid, int cpid) {
		int count = 0;
		try (Connection c = connectionUtil.getConnection();) {
			String sql = "SELECT COUNT(*) FROM jedi.studentreg WHERE sid = ? AND cpid = ?";
			PreparedStatement s = c.prepareStatement(sql);
			s.setInt(1, sid);
			s.setInt(2, cpid);
			ResultSet rs = s.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
			return count;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public int deleteCourse(int cpid) {
		int row = 0;

		try (Connection connection = connectionUtil.getConnection()) {
			String sql = "DELETE FROM jedi.courseprof WHERE cpid = ?";
			PreparedStatement s= connection.prepareStatement(sql);
			s.setInt(1, cpid);
			row = s.executeUpdate();
		}catch(SQLException se) {
			se.printStackTrace();	
		}
		return row;
	}

	@Override
	public List<Student> getStudentsHavingCoursesLessthanTresh(int tresh) {
		List<Student> studentshavinglessthanTresh=new ArrayList<>();
		
		try (Connection c = connectionUtil.getConnection()) {
			
			String sql = "SELECT s.sid,s.name\n"
					+ "FROM jedi.student AS s, (SELECT sid, COUNT(*) AS count \n"
					+ "						FROM jedi.studentreg \n"
					+ "						GROUP BY sid) AS tab\n"
					+ "WHERE s.sid = tab.sid AND tab.count < ?";
			PreparedStatement s = c.prepareStatement(sql);
			s.setInt(1, tresh);
			ResultSet rs = s.executeQuery();
			
			while(rs.next()) {				
				studentshavinglessthanTresh.add(new Student(rs.getInt(1), rs.getString(2)));
			}
		}catch(SQLException se) {
			se.printStackTrace();
		}
		System.out.println(studentshavinglessthanTresh);
		return studentshavinglessthanTresh; 
	}

	@Override
	public int getNumberOfStudentsInCourse(int cpid) {
		int count = 0;
		try (Connection c = connectionUtil.getConnection();) {
			String sql = "SELECT COUNT(*) FROM jedi.studentreg WHERE cpid = ?";
			PreparedStatement s = c.prepareStatement(sql);
			s.setInt(1, cpid);
			ResultSet rs = s.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
			return count;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public List<Integer> getCpidWhereNumberOfStudentsLessthanN(int thres) {
		List<Integer> list = new ArrayList<Integer>();
		try (Connection c = connectionUtil.getConnection();) {
			String sql = "SELECT cpid \n"
					+ "FROM (SELECT cpid, COUNT(*) AS count \n"
					+ "		FROM jedi.studentreg \n"
					+ "        GROUP BY cpid) AS tab\n"
					+ "WHERE tab.count < ?;";
			PreparedStatement s = c.prepareStatement(sql);
			s.setInt(1, thres);
			
			ResultSet rs = s.executeQuery();
			while (rs.next()) {
				list.add(rs.getInt(1));
			}
			return list;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<CourseCatalog> getRegisteredList(int sid) {
		// TODO Auto-generated method stub
		List<CourseCatalog> list=new ArrayList<>();
		try (Connection c = connectionUtil.getConnection()) {
			
			String sql = "SELECT c.cid, c.name, p.name, c.cost\n"
					+ "FROM jedi.student AS s, jedi.studentreg AS sr, jedi.courseprof AS cp, jedi.course as c, jedi.prof AS p \n"
					+ "WHERE s.sid = sr.sid AND sr.cpid = cp.cpid AND cp.pid = p.pid AND cp.cid = c.cid AND s.sid = ?";
			PreparedStatement s = c.prepareStatement(sql);
			s.setInt(1, sid);
			//s.setInt(1, tresh);
			
			ResultSet resultSet = s.executeQuery();
			
			while(resultSet.next()) {
				
				CourseCatalog st = mapRowToObjectCourseCatalog(resultSet);
				
				list.add(st);
				
			}
			
			
		}catch(SQLException se) {
			
			se.printStackTrace();
			
		}
		System.out.println("okay");
		return list; 
		
	}
	private CourseCatalog mapRowToObjectCourseCatalog(ResultSet resultSet) throws SQLException {
		CourseCatalog s = new CourseCatalog();
		s.setCourseId(resultSet.getInt(1));
		s.setCourseName(resultSet.getString(2));
		
		
		return s;
	}

}
