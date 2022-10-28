package com.training;

import com.training.db.ConnectionUtil;
import com.training.db.ProductDAOJdbcImpl;
import com.training.db.ProfessorDAOjdbcimpl;
import com.training.db.RegistrationDAOImpl;
import com.training.db.StudentDAOImpl;
import com.training.resources.AdminResource;
import com.training.resources.ProductResource;
import com.training.resources.ProfessorResource;
import com.training.resources.StudentResource;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class App extends Application<AppConfiguration>{

	public static void main(String[] args) throws Exception {
		new App().run(args);
	}
	
	@Override
	public void run(AppConfiguration configuration, Environment environment) throws Exception {
		
		ConnectionUtil connectionUtil = new ConnectionUtil(configuration.getUrl(), configuration.getUsername(), configuration.getPassword());
		
		ProductDAOJdbcImpl pdao = new ProductDAOJdbcImpl(connectionUtil);
		RegistrationDAOImpl rdao = new RegistrationDAOImpl(connectionUtil);
		ProfessorDAOjdbcimpl profdao = new ProfessorDAOjdbcimpl(connectionUtil);
		StudentDAOImpl sdao = new StudentDAOImpl(connectionUtil);
		
		ProductResource pr = new ProductResource(pdao);
		StudentResource sr = new StudentResource(rdao);
		AdminResource ar = new AdminResource(rdao, profdao, sdao);
		ProfessorResource profr = new ProfessorResource(profdao);
		
		environment.jersey().register(pr);
		environment.jersey().register(sr);
		environment.jersey().register(ar);
		environment.jersey().register(profr);
		
	}

}
