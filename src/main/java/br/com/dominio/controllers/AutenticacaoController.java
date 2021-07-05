package br.com.dominio.controllers;

import br.com.dominio.services.AutenticacaoService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AutenticacaoController", urlPatterns = {
    "/autenticacao/login",
    "/autenticacao/logout"
})
public class AutenticacaoController extends HttpServlet {

    private AutenticacaoService autenticacaoService;

    public AutenticacaoController() {
        this.autenticacaoService = new AutenticacaoService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String uri = req.getRequestURI();
        
        if (uri.endsWith("login")) {
            autenticacaoService.Login(req, resp);
        } else if (uri.endsWith("logout")) {
            autenticacaoService.Logout(req, resp);
        }
    }
}
