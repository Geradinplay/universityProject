package org.acme.rest.server;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.exception.ItemsException;
import org.acme.model.User;
import org.acme.service.ItemService;
import org.acme.service.UserService;

import java.util.List;

@Path("/user")
public class UserRest {
    @Inject
    private UserService userService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers() {
        List<User> users = userService.getAllUsers();
        return Response.ok().entity(users).build();
    }

    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    public Response createUser(User user) {
        if (user == null|| user.getPersonalData()==null) {
            return Response.status(Response.Status.BAD_REQUEST).entity(user).build();
        }
        try {
            userService.createUser(user);
        } catch (ItemsException e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
        return Response.status(Response.Status.CREATED).entity(user).build();
    }
}
