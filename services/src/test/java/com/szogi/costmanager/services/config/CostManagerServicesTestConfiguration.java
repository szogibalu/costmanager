package com.szogi.costmanager.services.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:config/services.properties")
@Import(ServicesConfiguration.class)
public class CostManagerServicesTestConfiguration {
}
