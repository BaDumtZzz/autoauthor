package ru.rsreu.autoauthor.filter;

import ru.rsreu.autoauthor.command.factory.ActionFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ServletSecurityFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();

        ActionFactory actionFactory = new ActionFactory();
        Set<String> requiredRoleSet = actionFactory.defineRole(req);
        List<String> roleList = (List<String>) session.getAttribute("role");

        if (roleList == null){
            roleList = new ArrayList<>();
            roleList.add("unloggedUser");
        }

        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/jsp/login.jsp");;
        boolean flag = false;
        for (String requiredRole:requiredRoleSet) {
            if (!roleList.contains(requiredRole)&&(!flag)) {
                if (roleList.contains("unloggedUser")) {
                    dispatcher = request.getServletContext().getRequestDispatcher("/jsp/login.jsp");
                } else {
                    dispatcher = request.getServletContext().getRequestDispatcher("/jsp/profile.jsp");
                }

            } else {
                flag = true;
            }
        }

        if (!flag) {
            dispatcher.forward(req, resp);
            return;
        }

        chain.doFilter(request, response);
    }

    public void init(FilterConfig fConfig) throws ServletException {
    }
}
