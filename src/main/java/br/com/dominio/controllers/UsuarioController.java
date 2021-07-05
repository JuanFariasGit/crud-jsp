package br.com.dominio.controllers;

import br.com.dominio.services.UsuarioService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "UsuarioController", urlPatterns = {
        "/usuarios/list",
        "/usuarios/add",
        "/usuarios/edit",
        "/usuarios/del"
})
public class UsuarioController extends HttpServlet {

    private final UsuarioService usuarioService;

    public UsuarioController() {
        this.usuarioService = new UsuarioService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String uri = req.getRequestURI();

        if (uri.endsWith("list")) {
            usuarioService.list(req, resp);
        } else if (uri.endsWith("add")) {
            usuarioService.add(req, resp);
        } else if (uri.endsWith("edit")) {
            usuarioService.edit(req, resp);
        } else if (uri.endsWith("del")) {
            usuarioService.del(req, resp);
        }
    }
}
