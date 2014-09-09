package com.szogi.costmanager.services.service;


import com.szogi.costmanager.services.model.Cost;
import com.szogi.costmanager.services.model.CostList;
import com.szogi.costmanager.services.repository.CostRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/json/costservice")
public class CostService {

    @Autowired
    private CostRepository costRepository;


    @POST
    @Path("/save")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveCost(final Cost cost) {
        costRepository.save(cost);
        return Response.status(201).entity("Cost saved: " + cost.getDescription()).build();
    }

    @GET
    @Path("/loadAll")
    @Produces(MediaType.APPLICATION_JSON)
    public CostList loadAll() {
        return new CostList(costRepository.findAll());
    }
}
