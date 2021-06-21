package br.com.dominio.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import br.com.dominio.dao.UsuarioDao;
import br.com.dominio.model.Telefone;
import br.com.dominio.model.Usuario;
import br.com.dominio.util.CriptografiaUtil;

@WebServlet("/usuarios")
public class UsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final UsuarioDao usuarioDao = new UsuarioDao();
	private static final CriptografiaUtil cript = new CriptografiaUtil();
	private Usuario usuario = new Usuario();
	private List<Telefone> phones = new ArrayList<>();
	
	public UsuarioController() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String sessionLogin = (String) session.getAttribute("login-crud-jsp");

		if (sessionLogin != null) {
			List<String> usuarios = new ArrayList<>();
			Gson gson = new Gson();
			List<Usuario> todos = usuarioDao.pegarTodos();

			for (Usuario u : todos) {
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

				usuarios.add(gson.toJson(mapUsuario));
			}
			clean();
			resp.setContentType("text/html;charset=UTF-8");
			resp.getWriter().write("{\"data\":" + usuarios + "}");
		} else {
			resp.sendRedirect("login.jsp");
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String sessionLogin = (String) session.getAttribute("login-crud-jsp");

		String action = req.getParameter("action");

		switch (action) {
			case "add":
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
				} catch (Exception e) {
					e.printStackTrace();
				}
	
				break;
	
			case "edit":
				if (sessionLogin != null) {
					phones = new Gson().fromJson(req.getParameter("phones"), new TypeToken<List<Telefone>>() {
					}.getType());
					
					usuario.setId(Integer.parseInt(req.getParameter("id")));
					usuario.setNome(req.getParameter("name").replaceAll("[^ A-Za-zÀ-ú]", ""));
					usuario.setEmail(req.getParameter("email").replaceAll("[^a-zA-Z0-9@._-]", ""));
					usuario.setSenha(cript.md5(req.getParameter("password")));
					usuario.setTelefones(phones);
					
					try {
						usuarioDao.atualizar(usuario);
						resp.setContentType("text/html;charset=UTF-8");
						resp.getWriter().write("Usuario atualizado com sucesso");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				break;
	
			case "del":
				if (sessionLogin != null) {
					usuarioDao.excluir(Integer.parseInt(req.getParameter("id")));
				}
				break;
		}
	}

	private void clean() {
		usuario = new Usuario();
	}
}
