package br.com.dominio.controllers;

import br.com.dominio.services.UsuarioService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "UsuarioController", urlPatterns = {"/usuarios"})
public class UsuarioController extends HttpServlet {

   private final UsuarioService usuarioService;

    public UsuarioController() {
        this.usuarioService = new UsuarioService();
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        String action = req.getParameter("action");
        
        if (action.equalsIgnoreCase("list")) {
            usuarioService.list(req, resp);
        } else if (action.equalsIgnoreCase("add")) {
            usuarioService.add(req, resp);
        } else if (action.equalsIgnoreCase("edit")) {
            usuarioService.edit(req, resp);
        } else if (action.equalsIgnoreCase("del")) {
            usuarioService.del(req, resp);
        }
    }
}
