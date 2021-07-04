package br.com.dominio.controllers;

import br.com.dominio.services.AutenticacaoService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AutenticacaoController", urlPatterns = {"/autenticacao"})
public class AutenticacaoController extends HttpServlet {

    private AutenticacaoService autenticacaoService;

    public AutenticacaoController() {
        this.autenticacaoService = new AutenticacaoService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        String action = req.getParameter("action");
        
        if (action.equalsIgnoreCase("login")) {
            autenticacaoService.Login(req, resp);
        } else if (action.equalsIgnoreCase("logout")) {
            autenticacaoService.Logout(req, resp);
        }
    }
}
