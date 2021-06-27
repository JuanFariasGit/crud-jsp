package br.com.dominio.servlets.usuario;

import br.com.dominio.dao.UsuarioDao;
import br.com.dominio.model.Usuario;
import com.google.gson.Gson;
import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ListServlet", urlPatterns = {"/usuarios/List"})
public class ListServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UsuarioDao usuarioDao = new UsuarioDao();
        java.util.List<String> usuarios = new ArrayList<>();
        java.util.List<Usuario> todos = usuarioDao.pegarTodos();
        Gson gson = new Gson();

        todos.stream().map(u -> {
            Map<String, Object> mapUsuario = new HashMap<>();
            mapUsuario.put("DT_RowId", u.getId());
            mapUsuario.put("nome", u.getNome());
            mapUsuario.put("email", u.getEmail());
            mapUsuario.put("senha", u.getSenha());
            mapUsuario.put("telefones", u.getTelefones().toString().replace("[", "").replace("]", "").replace(",", "<br>"));
            mapUsuario.put("excluir", "<button class=\"btn btn-primary\" onclick=\"openModalFormEdit(" + "'"
                    + u.getId() + "\')\""
                    + "><i class=\"far fa-edit fa-lg\"></i></button> <button class=\"btn btn-danger\" onclick=\"openModal("
                    + "'" + u.getId() + "','" + u.getNome() + "'"
                    + ")\"><i class=\"far fa-trash-alt fa-lg\"></i></button>");
            return mapUsuario;
        }).forEachOrdered(mapUsuario -> {
            usuarios.add(gson.toJson(mapUsuario));
        });
        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().write("{\"data\":" + usuarios + "}");
    }
}
