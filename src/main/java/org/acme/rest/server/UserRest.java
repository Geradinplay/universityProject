package org.acme.rest.server;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.exception.ItemsException;
import org.acme.model.Item;
import org.acme.model.User;
import org.acme.rest.mapper.UserMapper;
import org.acme.rest.userDTO.UserDTO;
import org.acme.service.ItemService;
import org.acme.service.UserService;

import java.util.List;
import java.util.Optional;

@Path("/user")
public class UserRest {
    @Inject
    private UserService userService;
    @Inject
    private UserMapper userMapper;
    @Inject
    private ItemService itemService;

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

    @POST
    @Path("/addItem/{userId}/{itemId}")
    public Response addItemToUser(@PathParam("userId") Long userId, @PathParam("itemId") Long itemId) {
        // Найти пользователя
        User user = userService.findUsersById(userId);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("User not found")
                    .build();
        }

        // Найти предмет
        Item item = itemService.findItemById(itemId);
        if (item == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Item not found")
                    .build();
        }
        if (item == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Item not found")
                    .build();
        }
        // Добавить предмет к пользователю
        user.addItem(item);
        userService.updateUser(user); // если требуется сохранить

        return Response.ok("Item added to user").build();
    }

}
