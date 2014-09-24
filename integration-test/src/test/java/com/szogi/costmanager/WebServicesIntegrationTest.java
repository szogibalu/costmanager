package com.szogi.costmanager;


import com.szogi.costmanager.core.rest.RestIntegrationTest;
import com.szogi.costmanager.services.model.CostObject;
import com.szogi.costmanager.services.model.CostObjectList;
import com.szogi.costmanager.services.model.TagObject;
import org.apache.http.HttpResponse;
import org.junit.Test;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.math.BigDecimal;

import static javax.ws.rs.client.Entity.entity;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class WebServicesIntegrationTest extends RestIntegrationTest {

    private static final String COST_SERVICE_URL = "/json/costservice";

    private static final String SAVE_PATH = "/save";

    private static final String LOAD_ALL_COST_PATH = "/loadAll";

    private static CostObject getCost() {
        return new CostObject.Builder().setDescription(randomAlphabetic(15)).setAmount(BigDecimal.ONE).addTag(new TagObject(randomAlphabetic(10))).build();
    }

    @Test
    public void testLoadAllWithJerseyClient() {
        final CostObjectList result = getWebTarget(COST_SERVICE_URL, LOAD_ALL_COST_PATH).request(MediaType.APPLICATION_JSON).get(CostObjectList.class);
        assertThat(result.getCostObjects().size(), greaterThan(0));
    }

    @Test
    public void testSave() throws IOException {
        CostObject costObject = getCost();
        final HttpResponse response = createPostClientResponse(COST_SERVICE_URL + SAVE_PATH, OBJECT_MAPPER.writeValueAsString(costObject));
        assertThat(response.getStatusLine().getStatusCode(), is(201));
        assertThat(getEntityContent(response), allOf(containsString("Cost saved"), containsString(costObject.getDescription())));
    }

    @Test
    public void testSaveWithJerseyClient() throws IOException {
        CostObject costObject = getCost();
        final Response response = getWebTarget(COST_SERVICE_URL, SAVE_PATH)
                .request(MediaType.APPLICATION_JSON)
                .post(entity(costObject, MediaType.APPLICATION_JSON_TYPE));
        assertThat(response.readEntity(String.class), allOf(containsString("Cost saved"), containsString(costObject.getDescription())));
    }

    @Override
    protected String getRestServiceUrl() {
        return "http://localhost:8080/cost-manager-services/rest";
    }
}