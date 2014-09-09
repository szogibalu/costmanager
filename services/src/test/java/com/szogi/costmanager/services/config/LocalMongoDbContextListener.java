package com.szogi.costmanager.services.config;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class LocalMongoDbContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        LocalMongoDb.start();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        LocalMongoDb.stop();
    }
}
