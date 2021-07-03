package br.com.dominio.servlets.usuario;

import br.com.dominio.dao.UsuarioDao;
import br.com.dominio.dao.UsuarioDaoException;
import br.com.dominio.model.Telefone;
import br.com.dominio.model.Usuario;
import br.com.dominio.util.CriptografiaUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AddServlet", urlPatterns = {"/usuarios/Add"})
public class AddServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UsuarioDao usuarioDao = new UsuarioDao();
        CriptografiaUtil cript = new CriptografiaUtil();
        Usuario usuario = new Usuario();
        List<Telefone> phones;

        phones = new Gson().fromJson(req.getParameter("phones"), new TypeToken<List<Telefone>>() {
        }.getType());

        usuario.setNome(req.getParameter("name").replaceAll("[^ A-Za-zÀ-ú]", ""));
        usuario.setEmail(req.getParameter("email").replaceAll("[^a-zA-Z0-9@._-]", ""));
        usuario.setSenha(cript.md5(req.getParameter("password")));
        usuario.setTelefones(phones);

        try {
            usuarioDao.criar(usuario);
            resp.setContentType("text/html;charset=UTF-8");
            resp.getWriter().write("Usuário cadastrado com successo!");
        } catch (UsuarioDaoException ex) {
            Logger.getLogger(AddServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
