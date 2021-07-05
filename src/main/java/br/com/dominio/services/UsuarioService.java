package br.com.dominio.services;

import br.com.dominio.dao.UsuarioDao;
import br.com.dominio.model.Telefone;
import br.com.dominio.model.Usuario;
import br.com.dominio.util.CriptografiaUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UsuarioService {

    private final UsuarioDao usuarioDao;
    private final CriptografiaUtil cript;
    private Usuario usuario;
    private List<Telefone> phones;

    public UsuarioService() {
        this.cript = new CriptografiaUtil();
        this.usuarioDao = new UsuarioDao();
        this.usuario = new Usuario();
    }

    public void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        try {
            List<String> usuarios = new ArrayList<>();
            List<Usuario> todos = usuarioDao.pegarTodos();
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
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    public void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        phones = new Gson().fromJson(req.getParameter("phones"), new TypeToken<List<Telefone>>() {
        }.getType());

        usuario.setNome(req.getParameter("name").replaceAll("[^ A-Za-zÀ-ú]", ""));
        usuario.setEmail(req.getParameter("email").replaceAll("[^a-zA-Z0-9@._-]", ""));
        usuario.setSenha(cript.md5(req.getParameter("password")));
        usuario.setTelefones(phones);

        try {
            if (!usuarioDao.emailExistePorEmail(usuario.getEmail())) {
                usuarioDao.criar(usuario);
                resp.setContentType("text/html;charset=UTF-8");
                resp.getWriter().write("Usuário cadastrado com successo!");
            } else {
                resp.setContentType("text/html;charset=UTF-8");
                resp.getWriter().write("Já existe um usuário cadastrado com esse e-mail!");
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
        clean();
    }

    public void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        phones = new Gson().fromJson(req.getParameter("phones"), new TypeToken<List<Telefone>>() {
        }.getType());

        usuario.setId(Integer.parseInt(req.getParameter("id")));
        usuario.setNome(req.getParameter("name").replaceAll("[^ A-Za-zÀ-ú]", ""));
        usuario.setEmail(req.getParameter("email").replaceAll("[^a-zA-Z0-9@._-]", ""));
        usuario.setSenha(cript.md5(req.getParameter("password")));
        usuario.setTelefones(phones);

        try {
            if (usuarioDao.emailExistePorIdEEmail(Integer.parseInt(req.getParameter("id")),
                    usuario.getEmail()) || !usuarioDao.emailExistePorEmail(usuario.getEmail())) {
                usuarioDao.atualizar(usuario);
                resp.setContentType("text/html;charset=UTF-8");
                resp.getWriter().write("Usuário atualizado com successo!");
            } else {
                resp.setContentType("text/html;charset=UTF-8");
                resp.getWriter().write("Já existe um usuário cadastrado com esse e-mail!");
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
        clean();
    }

    public void del(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        try {
            usuarioDao.excluir(Integer.parseInt(req.getParameter("id")));
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void clean() {
        usuario = new Usuario();
    }
}
