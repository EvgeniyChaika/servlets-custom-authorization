package com.chaika.servlet.listener;

import com.chaika.dao.UserDAO;

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

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
