package org.servlet;

import java.io.IOException;

import javax.sql.DataSource;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.model.user;


@WebServlet("/registra")
public class registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(name = "jdbc/project")
	private DataSource dataSource;
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String page = request.getParameter("page");
		page = page.toLowerCase();

		switch (page) {
		case "register":
			addUserFormLoader(request, response);
			break;
		default:
			errorPage(request, response);
		}

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String operation = request.getParameter("form");
		operation = operation.toLowerCase();
		switch (operation) {
		case "adduseroperation":
			user newUser=new user(request.getParameter("name"),request.getParameter("email"),request.getParameter("password"),request.getParameter("contact"));
			addUserOperation(newUser,request);
			request.getRequestDispatcher("registration.jsp").forward(request, response);
			break;
		default:
			errorPage(request, response);
			break;
		}
			
	}
	public void errorPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("title", "Error page");
		request.getRequestDispatcher("error.jsp").forward(request, response);

	}
	private void addUserOperation(user newUser,HttpServletRequest request) {
		new UsersModel().addUser(dataSource, newUser,request);
		return;
	}
	

	

	public void addUserFormLoader(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("title", "registration");
		request.getRequestDispatcher("registration.jsp").forward(request, response);
	}
}
