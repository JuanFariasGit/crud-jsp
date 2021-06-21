package br.com.dominio.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("")
public class IndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public IndexController() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String sessionLogin = (String) session.getAttribute("login-crud-jsp");
	
		if (sessionLogin != null) {
			resp.sendRedirect("index.jsp");
		} else {
			resp.sendRedirect("login.jsp");
		}
	}
}
