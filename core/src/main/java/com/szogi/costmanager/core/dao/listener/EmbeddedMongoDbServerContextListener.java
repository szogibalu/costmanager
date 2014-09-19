package com.szogi.costmanager.core.dao.listener;


import com.szogi.costmanager.core.dao.EmbeddedMongoDbServer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class EmbeddedMongoDbServerContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        EmbeddedMongoDbServer.start();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        EmbeddedMongoDbServer.stop();
    }
}
