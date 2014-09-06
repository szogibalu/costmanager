package com.szogi.costmanager.core.test;

import com.szogi.costmanager.core.config.ServicesConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:config/services.properties")
@Import(ServicesConfiguration.class)
public class TestConfiguration {
}
