package br.com.dominio.services;

import br.com.dominio.dao.UsuarioDao;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AutenticacaoService {

    private final UsuarioDao usuarioDao;

    public AutenticacaoService() {
        this.usuarioDao = new UsuarioDao();
    }
    
    public void Login(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        String email = req.getParameter("email");
        String senha = req.getParameter("password");
        HttpSession session = req.getSession();

        try {
            if (usuarioDao.usuarioExiste(email, senha)) {
                session.setAttribute("login-crud-jsp", email);
            } else {
                resp.getWriter().write("E-mail e/ou senha invalido(s)!");
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
    
    public void Logout(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        session.invalidate();
    }
}
