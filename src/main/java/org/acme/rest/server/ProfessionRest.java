package org.acme.rest.server;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.exception.ItemsException;
import org.acme.model.Profession;
import org.acme.rest.mapper.ProfessionMapper;
import org.acme.rest.userDTO.ProfessionDTO;
import org.acme.service.ProfessionService;

import java.util.List;
@Path("/profession")
public class ProfessionRest {
    @Inject
    private ProfessionService professionService;
    @Inject
    private ProfessionMapper professionMapper;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllProfession() {
        List<Profession> professions = professionService.getAllProfession();
        return Response.ok().entity(professions).build();
    }

    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    public Response createProfession(@Valid ProfessionDTO professionDTO) {
        if (professionDTO == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Profession is null!").build();
        }
        try {
            Profession profession = professionMapper.toModel(professionDTO);
            professionService.createProfession(profession);
        } catch (ItemsException e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
        return Response.status(Response.Status.CREATED).entity(professionDTO).build();
    }
}
