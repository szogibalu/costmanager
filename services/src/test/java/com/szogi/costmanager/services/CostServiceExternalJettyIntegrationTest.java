package com.szogi.costmanager.services;


import com.szogi.costmanager.core.test.RestIntegrationTest;
import com.szogi.costmanager.services.model.Cost;
import com.szogi.costmanager.services.model.CostList;
import org.apache.http.HttpResponse;
import org.junit.Test;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

import static com.szogi.costmanager.services.util.TestObjectFactory.testCost;
import static javax.ws.rs.client.Entity.entity;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class CostServiceExternalJettyIntegrationTest extends RestIntegrationTest {

    private static final String COST_SERVICE_URL = "/json/costservice";

    private static final String SAVE_PATH = "/save";

    private static final String LOAD_ALL_COST_PATH = "/loadAll";

    @Test
    public void testLoadAllWithJerseyClient() {
        final CostList result = getWebTarget(COST_SERVICE_URL, LOAD_ALL_COST_PATH).request(MediaType.APPLICATION_JSON).get(CostList.class);
        assertThat(result.getCosts().size(), greaterThan(0));
    }

    @Test
    public void testSave() throws IOException {
        Cost cost = testCost();
        final HttpResponse response = createPostClientResponse(COST_SERVICE_URL + SAVE_PATH, OBJECT_MAPPER.writeValueAsString(cost));
        assertThat(response.getStatusLine().getStatusCode(), is(201));
        assertThat(getEntityContent(response), allOf(containsString("Cost saved"), containsString(cost.getDescription())));
    }

    @Test
    public void testSaveWithJerseyClient() throws IOException {
        Cost cost = testCost();
        final Response response = getWebTarget(COST_SERVICE_URL, SAVE_PATH)
                .request(MediaType.APPLICATION_JSON)
                .post(entity(cost, MediaType.APPLICATION_JSON_TYPE));
        assertThat(response.readEntity(String.class), allOf(containsString("Cost saved"), containsString(cost.getDescription())));
    }

    @Override
    protected String getRestServiceUrl() {
        return "http://localhost:8080/cost-manager-services/rest";
    }
}