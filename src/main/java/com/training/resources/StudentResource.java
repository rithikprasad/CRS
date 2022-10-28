package com.training.resources;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.training.api.Bill;
import com.training.api.Course;
import com.training.api.CourseCatalog;
import com.training.api.Courses;
import com.training.api.Report;
import com.training.db.RegistrationDAO;


@Path("/students")
public class StudentResource {
	RegistrationDAO dao;

	public StudentResource(RegistrationDAO dao) {
		super();
		this.dao = dao;
	}
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{sid}/register")
	public Response addCourse(@PathParam("sid") int sid, Courses courses) {
		
		List<Integer> courseList = new ArrayList<Integer>();
		
		courseList.add(courses.getC1());
		courseList.add(courses.getC2());
		courseList.add(courses.getC3());
		courseList.add(courses.getC4());
		courseList.add(courses.getC5());
		courseList.add(courses.getC6());
		
		System.out.println(courseList);
		List<Integer> alredyRegisteredCourses = new ArrayList<Integer>();
		for(int i=0; i<courseList.size(); i++) {
			int checkIfReg = dao.checkIfRegistered(sid, courseList.get(i));
			if(checkIfReg == 1) {
				alredyRegisteredCourses.add(courseList.get(i));
			}
		}
		if(alredyRegisteredCourses.size() != 0) {
			String errmsg = "You have alredy registered for these "+alredyRegisteredCourses.toString();
			return Response.status(400).entity(errmsg).build();
		}
		
		List<Integer> maxRegisteredCourses = new ArrayList<>();
		for(int i=0; i<courseList.size(); i++) {
				int count = dao.getNumberOfStudentsInCourse(courseList.get(i));
				if(count >= 10) {
					maxRegisteredCourses.add(courseList.get(i));
				}
		}
		if(maxRegisteredCourses.size() != 0) {
			String errmsg = "These course reached maximum number of registrations !!! "+maxRegisteredCourses.toString();
			return Response.status(400).entity(errmsg).build();
		}
		
		
		int id = 0;
		for(int i=0; i<courseList.size(); i++) {
			try{
				dao.addCourse(sid, courseList.get(i));
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}

		return Response.status(201).entity( "Registration Successful").build();

	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{sid}/courses/{cpid}")
	public Response addCourse(@PathParam("sid") int sid, @PathParam("cpid")int cpid) {
		
		int checkIfReg = dao.checkIfRegistered(sid, cpid);
		if(checkIfReg == 1) {
			String errmsg = "You have alredy registered for this course";
			return Response.status(400).entity(errmsg).build();
		}
		
		int count = dao.getNumberOfStudentsInCourse(cpid);
		if(count >= 10) {
			String errmsg = "this course reached maximum number of registrations !!! ";
			return Response.status(400).entity(errmsg).build();
		}
		
		int id = dao.addCourse(sid, cpid);
		if(id != 0) {
			return Response.status(201).entity("added Successsfully").build();
		} else {
			return Response.status(400).build();
		}
	}
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{sid}/courses/{cpid}")
	public Response dropCourse(@PathParam("sid") int sid, @PathParam("cpid")int cpid) {
		
		int checkIfReg = dao.checkIfRegistered(sid, cpid);
		if(checkIfReg == 0) {
			String errmsg = "This is not present in you for this course";
			return Response.status(400).entity(errmsg).build();
		}
		
		int count = dao.dropCourse(sid, cpid);
		if(count != 0) {
			return Response.status(201).entity("deleted Successsfully").build();
		} else {
			return Response.status(400).build();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{sid}/report")
	public Response getReportById(@PathParam("sid") int sid) throws SQLException {
		
		Report rep = dao.getReport(sid);
		System.out.println("reached resource");
		return Response.ok(rep).build();
//		if(rep != null) {
//			
//		}else {
//			throw new WebApplicationException(404);
//		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{sid}/bill")
	public Response getStudentBill(@PathParam("sid") int sid) {
		
		Bill bill = dao.getBill(sid);
		
		if(bill != null) {
			return Response.ok(bill).build();
		}else {
			throw new WebApplicationException(404);
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/coursecatalog")
	public Response getCourseCatalog() {
           
		List<CourseCatalog> list = dao.getcatalog();
		if(list != null) {
			return Response.ok(list).build();
		}else {
			throw new WebApplicationException(404);
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{sid}/myregisteredcourses")
	public Response getRegisteredCourses(@PathParam("sid")int sid) {
        
		List<CourseCatalog> list = dao.getRegisteredList(sid);
		if(list != null) {
			return Response.ok(list).build();
		}else {
			throw new WebApplicationException(404);
		}
	}
	
}
