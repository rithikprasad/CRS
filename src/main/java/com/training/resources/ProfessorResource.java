package com.training.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.training.api.Course;
import com.training.api.Student;
import com.training.api.sid_cid_gradeClass;
import com.training.db.ProfessorDAO;

@Path("/professor")
public class ProfessorResource {
	
	ProfessorDAO dao;

	public ProfessorResource(ProfessorDAO dao) {
		super();
		this.dao = dao;
	}
	
	@POST
	//@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{pid}/course/{cid}")
	public Response addProfCourse(@PathParam("pid")int pid, @PathParam("cid")int cid) {
		
		int id=dao.insertCourse(cid,pid);
		return Response.created(URI.create("/professor/"+pid+"/course/"+cid)).build();
	}
	
	//prof/{id}/cour//se/{cid}/students —-- get
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{pid}/course/{cid}/students")
	public Response getAllStudentsByCid(@PathParam("pid")int pid,@PathParam("cid")int cid){
		
		List<Student> list = dao.findStudentList(pid, cid);
		return Response.ok(list).build();
		
	}
	
	//prof/{id}/course{cid}/student/{sid}/grade —- post
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{pid}/course/{cid}/student/{sid}/grade")
	public Response addGrade(@PathParam("pid")int pid,@PathParam("cid")int cid,@PathParam("sid")int sid, sid_cid_gradeClass s) {
		
		try {
		
		boolean ifupdated=dao.updateGrade(s);
	    
		if(!ifupdated) {
		
		
			throw new WebApplicationException(Status.NOT_FOUND);
		}
		}
		catch(Exception e)
		{
			return Response.status(500).entity("Something went wrong , pls try again").build();
			
		}
		return Response.status(200).entity("Grade Updated for student").build();
	}
	

	@GET
	@Path("/course")
	@Produces(MediaType.APPLICATION_JSON)
	public Response viewCoursesInCatalogue() {
		List<Course> list = new ArrayList<Course>();
		
		try {
			list=dao.viewCourses();
		}
		catch(Exception e)
		{
			return null;
		}
		
		return Response.status(200).entity(list).build();
		
	}
	
//what do we add into entity?	
	

	
	
	

}
