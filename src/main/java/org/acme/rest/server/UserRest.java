package org.acme.rest.server;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.exception.ItemsException;
import org.acme.model.User;
import org.acme.rest.mapper.UserMapper;
import org.acme.rest.userDTO.UserDTO;
import org.acme.service.UserService;

import java.util.List;

@Path("/user")
public class UserRest {
    @Inject
    private UserService userService;
    @Inject
    private UserMapper userMapper;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers() {
        List<User> users = userService.getAllUsers();
        return Response.ok().entity(users).build();
    }

    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    public Response createUser(@Valid UserDTO userDTO) {
        if (userDTO == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("User is null!").build();
        }
        try {
            User user = userMapper.toModel(userDTO);
            userService.createUser(user);
        } catch (ItemsException e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
        return Response.status(Response.Status.CREATED).entity(userDTO).build();
    }
}
