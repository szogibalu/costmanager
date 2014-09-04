package com.szogi.costmanager.core.config;

import com.szogi.costmanager.core.service.CostService;
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
