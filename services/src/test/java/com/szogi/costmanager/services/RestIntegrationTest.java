package com.szogi.costmanager.services;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.codehaus.jackson.map.ObjectMapper;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

import static com.google.common.base.Throwables.propagate;
import static javax.ws.rs.client.ClientBuilder.newClient;

public abstract class RestIntegrationTest {

    protected static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private final HttpClient client = HttpClientBuilder.create().build();

    abstract String getRestServiceUrl();

    protected String getEntityContent(final HttpResponse response) {
        try {
            return IOUtils.toString(response.getEntity().getContent());
        } catch (IllegalStateException | IOException e) {
            throw propagate(e);
        }
    }

    private Client getJerseyClient() {
        final ClientConfig clientConfig = new ClientConfig();
        clientConfig.register(JacksonFeature.class);
        return newClient(clientConfig);
    }

    protected WebTarget getWebTarget(final String uri, final String path) {
        return getJerseyClient().target(getRestServiceUrl() + uri).path(path);

    }

    protected HttpResponse createGetClientResponse(final String serviceUrl) {
        final HttpGet getRequest = new HttpGet(getRestServiceUrl() + serviceUrl);
        getRequest.addHeader("accept", MediaType.APPLICATION_JSON);
        try {
            return client.execute(getRequest);
        } catch (final IOException e) {
            throw propagate(e);
        }
    }

    protected HttpResponse createPostClientResponse(final String serviceUrl, final String input) {
        final HttpPost postRequest = new HttpPost(getRestServiceUrl() + serviceUrl);
        try {
            final StringEntity entity = new StringEntity(input);
            entity.setContentType(MediaType.APPLICATION_JSON);
            postRequest.setEntity(entity);
            return client.execute(postRequest);
        } catch (final Exception e) {
            throw propagate(e);
        }
    }
}
