package com.szogi.costmanager.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("file:${cm.config.dir}/services.properties")
@Import(ServicesConfiguration.class)
class CostManagerServicesConfiguration {
}
