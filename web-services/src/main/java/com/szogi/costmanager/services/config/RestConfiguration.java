package com.szogi.costmanager.services.config;

import com.szogi.costmanager.services.service.CostService;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

class RestConfiguration extends ResourceConfig {

    public RestConfiguration() {
        register(RequestContextFilter.class);
        register(JacksonFeature.class);
        register(CostService.class);
    }
}
