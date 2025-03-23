package org.acme.rest.client;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.acme.model.Item;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;
@Path("/api")
@RegisterRestClient
public interface ItemClient {
        @GET
        @Path("/items")
        List<Item> getAll();
}
