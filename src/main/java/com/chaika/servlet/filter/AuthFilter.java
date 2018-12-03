package com.chaika.servlet.filter;

import com.chaika.dao.UserDAO;
import com.chaika.model.enums.Role;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

import static java.util.Objects.nonNull;

/**
 * Created by echaika on 03.12.2018
 */
@WebFilter("/")
public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse res = (HttpServletResponse) response;

        final String login = req.getParameter("login");
        final String password = req.getParameter("password");

        @SuppressWarnings("unchecked") final AtomicReference<UserDAO> dao = (AtomicReference<UserDAO>) req.getServletContext().getAttribute("dao");

        final HttpSession session = req.getSession();

        if (nonNull(session) && nonNull(session.getAttribute("login")) && nonNull(session.getAttribute("password"))) {
            final Role role = (Role) session.getAttribute("role");
            moveToMenu(req, res, role);
        } else if (dao.get().userIsExist(login, password)) {
            final Role role = dao.get().getRoleByLoginPassword(login, password);

            req.getSession().setAttribute("password", password);
            req.getSession().setAttribute("login", login);
            req.getSession().setAttribute("role", role);

            moveToMenu(req, res, role);
        } else {
            moveToMenu(req, res, Role.UNKNOWN);
        }
    }


    @Override
    public void destroy() {

    }

    private void moveToMenu(final HttpServletRequest req, final HttpServletResponse res, final Role role) throws ServletException, IOException {
        if (role.equals(Role.ADMIN)) {
            req.getRequestDispatcher("/view/admin_main.jsp").forward(req, res);
        } else if (role.equals(Role.USER)) {
            req.getRequestDispatcher("/view/user_main.jsp").forward(req, res);
        } else {
            req.getRequestDispatcher("/view/login.jsp").forward(req, res);
        }
    }
}
