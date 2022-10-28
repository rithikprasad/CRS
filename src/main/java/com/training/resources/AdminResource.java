package com.training.resources;

import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.training.api.Student;
import com.training.api.Professor;
import com.training.db.ProfessorDAO;
import com.training.db.RegistrationDAO;
import com.training.db.StudentDAO;
import com.training.exception.CourseNotFoundException;

@Path("/admin")
public class AdminResource {
	RegistrationDAO dao;
	ProfessorDAO pdao;
	StudentDAO sdao;
	
	public AdminResource(RegistrationDAO dao, ProfessorDAO pdao, StudentDAO sdao) {
		super();
		this.dao = dao;
		this.pdao = pdao;
		this.sdao = sdao;
	}
	
//	public AdminResource(RegistrationDAO dao) {
//		super();
//		this.dao = dao;
//	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/professor")
	public Response addProfessor( Professor prof) {
		System.out.println(prof.getName()+"----------------------");
		int id=pdao.insertProfessor(prof);
		return Response.created(URI.create("/professor/"+prof.getPid())).build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/student")
	public Response addStudent( Student student) {
		
		int id=sdao.insertStudent(student);
		return Response.created(URI.create("/student/"+student.getSid())).build();
	}
	
	@DELETE
	@Path("/dcourses")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteCourse() throws CourseNotFoundException {
		   
			List<Integer> cpidLessthanThreeStudents = dao.getCpidWhereNumberOfStudentsLessthanN(4);
			System.out.println(cpidLessthanThreeStudents);
			
			for(int i=0; i<cpidLessthanThreeStudents.size(); i++) {
				int val = dao.deleteCourse(cpidLessthanThreeStudents.get(i));
			}
		    
			List<Student> effectedStudents = dao.getStudentsHavingCoursesLessthanTresh(4);
			
			return Response.status(200).entity(effectedStudents).build();

	}
}
