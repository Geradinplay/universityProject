package org.acme.rest.server;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.exception.ItemsException;
import org.acme.model.Item;
import org.acme.rest.client.ItemsClient;
import org.acme.service.ItemService;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

@Path("/items")
public class ItemsRest {
//    @Inject
//    @RestClient
//    private ItemsClient itemsClient;

    @Inject
    private ItemService itemService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllItems(){
        List<Item>items= itemService.getAllItems();
        return  Response.ok().entity(items).build();
    }

    @Consumes(MediaType.APPLICATION_JSON)
        @POST
        public Response createItem(Item item) {
        if (item == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Item is null!").build();
        }
            try {
                itemService.createItem(item);
            } catch (ItemsException e) {
                return Response.serverError().entity(e.getMessage()).build();
            }
            return Response.status(Response.Status.CREATED).entity(item).build();
        }

    @Consumes(MediaType.APPLICATION_JSON)
    @DELETE
    @Path("/{id}")
    public Response deleteItemById(@PathParam("id")Long id) {
        if (id == 0) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Item is null!").build();
        }
        Item item =itemService.deleteItemById(id);
        return Response.status(Response.Status.OK).entity(item).build();
    }
}
