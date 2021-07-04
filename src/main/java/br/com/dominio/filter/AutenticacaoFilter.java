package br.com.dominio.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(filterName = "AutenticacaoFilter", urlPatterns= {"/usuarios", "*.jsp"})
public class AutenticacaoFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) req;
        String uri = httpServletRequest.getRequestURI();
        HttpSession session = httpServletRequest.getSession();

        if (session.getAttribute("login-crud-jsp") != null || uri.endsWith("login.jsp")
                || uri.endsWith("cadastro.jsp") || uri.endsWith("usuarios?action=add")) {
            chain.doFilter(req, resp);
        } else {
            ((HttpServletResponse) resp).sendRedirect("/login.jsp");
        }
    }
}
