package com.szogi.costmanager.services.service;


import com.szogi.costmanager.services.model.CostObject;
import com.szogi.costmanager.services.model.CostObjectList;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/json/costservice")
public class CostService {

    @Autowired
    private CostObjectService costObjectService;

    @POST
    @Path("/save")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveCost(final CostObject costObject) {
        costObjectService.save(costObject);
        return Response.status(201).entity("Cost saved: " + costObject.getDescription()).build();
    }

    @GET
    @Path("/loadAll")
    @Produces(MediaType.APPLICATION_JSON)
    public CostObjectList loadAll() {
        return new CostObjectList(costObjectService.findAll());
    }
}
