package com.revature.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.revature.dto.UsersDto;
import com.revature.models.Users;
import com.revature.repos.ReimbursementDao;
import com.revature.repos.UsersDao;
import com.revature.util.HtmlTemplate;

/**
 * Servlet implementation class ReimServlet
 */
public class ReimServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(ReimServlet.class);  
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReimServlet() {
    }


    	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    		String user = request.getParameter("username");
    		int amount = Integer.parseInt(request.getParameter("amount"));
    		String desc = request.getParameter("description");
    		int type = Integer.parseInt(request.getParameter("type"));
    		
    		
    		int name = UsersDao.userId(user);
    		
    
    		
    		log.info("User entered amount " + amount + ",For " + desc + " , type "+ type+ ", id:" + name);
    		
//    		// create method to insert reim
    		int r = ReimbursementDao.insert(amount, desc, type, name);
//    		
//    		
//    		
    		if ( r==7) {
   			RequestDispatcher rd = request.getRequestDispatcher("success.html");// we want to send our user to the home page!
   			rd.forward(request, response);
//    			log.info(" has successfully inserted reimb");
    		}		
//    		
//  
    		else {
    			
   			RequestDispatcher rd2 = request.getRequestDispatcher("error.html");// we want to send our user to the home page!
   			rd2.forward(request, response);
//    			PrintWriter pw = HtmlTemplate.getHtmlWriter(response);
    			log.info(" has failed to insert reim");
//    					// we will create an html page on the fly
//    			pw.println("<h3 style='color:red'>Denied</h3>");
//    			pw.println("<p>Username or password is incorrect</p>");
    		}
    		
    	}
}
    		
    	

    
