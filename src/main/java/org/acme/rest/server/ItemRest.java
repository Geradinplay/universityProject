package org.acme.rest.server;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.exception.ItemsException;
import org.acme.model.Item;
import org.acme.rest.mapper.ItemMapper;
import org.acme.rest.userDTO.ItemDTO;
import org.acme.service.ItemService;

import java.util.List;

@Path("/items")
public class ItemRest {
//    @Inject
//    @RestClient
//    private ItemsClient itemsClient;

    @Inject
    private ItemService itemService;
    @Inject
    private ItemMapper itemMapper;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllItems() {
        List<Item> items = itemService.getAllItems();
        return Response.ok().entity(items).build();
    }

    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    public Response createItem(@Valid ItemDTO itemDTO) {
        if (itemDTO == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Item is null!").build();
        }
        try {
            Item item = itemMapper.toModel(itemDTO);
            itemService.createItem(item);
        } catch (ItemsException e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
        return Response.status(Response.Status.CREATED).entity(itemDTO).build();
    }

    @Consumes(MediaType.APPLICATION_JSON)
    @DELETE
    @Path("/{id}")
    public Response deleteItemById(@PathParam("id") Long id) {
        if (id == 0) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Item is null!").build();
        }
        Item item = itemService.deleteItemById(id);
        return Response.status(Response.Status.OK).entity(item).build();
    }

    @Consumes(MediaType.APPLICATION_JSON)
    @PUT
    @Path("/{id}")
    public Response updateItemById(@PathParam("id") Long id, Item updatedItem) {
        Item item =itemService.updateItemById(id, updatedItem);
        if (item == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Item not found!").build();
        }

        return Response.status(Response.Status.OK).entity(item).build();
    }

}
