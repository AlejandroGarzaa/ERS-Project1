package com.revature.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.models.Reimbursement;
import com.revature.models.Users;
import com.revature.repos.ReimbursementDao;
import com.revature.repos.UsersDao;
import com.revature.util.HtmlTemplate;

/**
 * Servlet implementation class ViewServlet
 */
public class ViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewServlet() {
    }
        
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = HtmlTemplate.getHtmlWriter(response);
		
				// we will create an html page on the fly
//		pw.println("<h3 style='color:red'>Denied</h3>");
//		pw.println("<p>Username or password is incorrect</p>");
		
		
		//List<Reimbursement> list = ReimbursementDao.findAll();
		pw.println("<table border=2px><tr>");
	//	for (Reimbursement h : list) {
		List<Users> all = UsersDao.findAll();

		for (Users u : all) {
			pw.println("<th>" + u + "</th>");
		}for (int x= 0; x<all.get(x).userId; x++ ) {
			pw.println(all.get(x));
		}
			pw.println("</tr></table>");
		}
		
	}


