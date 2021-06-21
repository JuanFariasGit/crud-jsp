package br.com.dominio.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.dominio.dao.UsuarioDao;

@WebServlet("/login")
public class AutenticacaoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final UsuarioDao usuarioDao = new UsuarioDao();

	public AutenticacaoController() {
		super();
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String senha = req.getParameter("password");
		HttpSession session = req.getSession();

		if (usuarioDao.usuarioExiste(email, senha)) {
			session.setAttribute("login-crud-jsp", email);
			resp.sendRedirect("index.jsp");
		} else {
			resp.sendRedirect("login.jsp");
		}
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		HttpSession session = req.getSession();

		if (action.equals("logout")) {
			session.invalidate();
			resp.sendRedirect("login.jsp");
		}
	}
}
