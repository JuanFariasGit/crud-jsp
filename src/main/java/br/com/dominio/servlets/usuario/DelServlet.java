package br.com.dominio.servlets.usuario;

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

@WebServlet(name = "DelServlet", urlPatterns = {"/usuarios/Del"})
public class DelServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UsuarioDao usuarioDao = new UsuarioDao();
        try {
            usuarioDao.excluir(Integer.parseInt(req.getParameter("id")));
        } catch (UsuarioDaoException ex) {
            Logger.getLogger(DelServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
