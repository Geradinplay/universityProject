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
import java.util.Optional;

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

        Optional<ItemDTO> optionalItemDTO = Optional.ofNullable(itemDTO);

        try{
            optionalItemDTO.orElseThrow(NullPointerException::new);
        }catch (NullPointerException e){
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

        Optional<Long> optionalId = Optional.ofNullable(id)
                .filter(a -> a != 0L);

        try {
            optionalId.orElseThrow(IllegalArgumentException::new);
        }catch (IllegalArgumentException e){
            return Response.status(Response.Status.BAD_REQUEST).entity("Item is null!").build();
        }

        Item deleted = itemService.deleteItemById(id);
        if (deleted == null) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.status(Response.Status.OK).entity(deleted).build();
    }

    @Consumes(MediaType.APPLICATION_JSON)
    @PUT
    @Path("/{id}")
    public Response updateItemById(@PathParam("id") Long id, Item updatedItem) {
        Item item =itemService.updateItemById(id, updatedItem);

        Optional<Item> optionalItem = Optional.ofNullable(item);
        try{
            optionalItem.orElseThrow(NullPointerException::new);
        }catch (NullPointerException e){
            return Response.status(Response.Status.BAD_REQUEST).entity("Item is null!").build();
        }



        return Response.status(Response.Status.OK).entity(item).build();
    }

}
