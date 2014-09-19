package com.szogi.costmanager.core.test;

import com.szogi.costmanager.core.search.EmbeddedElasticsearchServer;
import org.elasticsearch.client.Client;
import org.junit.After;
import org.junit.Before;

public abstract class AbstractElasticsearchIntegrationTest {

    private EmbeddedElasticsearchServer embeddedElasticsearchServer;

    @Before
    public void startEmbeddedElasticsearchServer() {
        embeddedElasticsearchServer = new EmbeddedElasticsearchServer();
    }

    @After
    public void shutdownEmbeddedElasticsearchServer() {
        embeddedElasticsearchServer.shutdown();
    }

    public Client getClient() {
        return embeddedElasticsearchServer.getClient();
    }
}