package com.chaika.servlet.listener;

import com.chaika.dao.UserDAO;
import com.chaika.model.User;
import com.chaika.model.enums.Role;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by echaika on 30.11.2018
 */
@WebListener
public class ContextListener implements ServletContextListener {

    /**
     * Fake database connector.
     */
    private AtomicReference<UserDAO> userDao;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        userDao = new AtomicReference<>(new UserDAO());

        userDao.get().add(new User(1, "Evgeniy", "1", Role.ADMIN));
        userDao.get().add(new User(2, "John", "1", Role.USER));

        final ServletContext servletContext = sce.getServletContext();
        servletContext.setAttribute("dao", userDao);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        userDao = null;
    }
}
