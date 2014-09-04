package com.szogi.costmanager.core.test;

import com.szogi.costmanager.core.config.ApplicationConfiguration;
import org.springframework.test.context.ContextConfiguration;


@ContextConfiguration(classes = {ApplicationConfiguration.class})
public class SpringIntegrationTest {

    static {
        System.setProperty("cm.config.file", "classpath:config/application.properties");
    }

}
