package br.com.dominio.servlets.autenticacao;

import br.com.dominio.dao.UsuarioDao;
import br.com.dominio.dao.UsuarioDaoException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "LoginServlet", urlPatterns = {"/Login"})
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final UsuarioDao usuarioDao = new UsuarioDao();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        String email = req.getParameter("email");
        String senha = req.getParameter("password");
        HttpSession session = req.getSession();

        try {
            if (usuarioDao.usuarioExiste(email, senha)) {
                session.setAttribute("login-crud-jsp", email);
                resp.sendRedirect("index.jsp");
            } else {
                resp.sendRedirect("login.jsp");
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
