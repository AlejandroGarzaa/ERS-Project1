package com.revature.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dto.UsersDto;
import com.revature.models.Users;
import com.revature.repos.UsersDao;

/**
 * Servlet implementation class UserServlet
 */
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	// an Object Mapper allows us to convert a Java object to JSON and vice versa
	private static ObjectMapper om = new ObjectMapper();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		List<Users> all = UsersDao.findAll();
		List<UsersDto> allDTO = new ArrayList<>();
		
		for(Users e : all) {
			allDTO.add(UsersDao.convertToDto(e));
		}
		
		String json = om.writeValueAsString(allDTO);
		
		PrintWriter pw = response.getWriter();
		pw.println(json);
	}



}

