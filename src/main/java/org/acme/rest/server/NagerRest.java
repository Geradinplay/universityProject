package org.acme.rest.server;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.model.Country;
import org.acme.model.Holiday;
import org.acme.model.Item;
import org.acme.rest.client.NagerClientRest;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

@Path("/nager")
public class NagerRest {
    @RestClient
    @Inject
    NagerClientRest nagerClientRest;

    @GET
    @Path("/AvailableCountries")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAvailableCountries() {
        List<Country> countries= nagerClientRest.getAvailableCountries();
        return Response.ok().entity(countries).build();
    }
    @GET
    @Path("/NextPublicHolidays/{countryCode}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNextPublicHolidays(@PathParam("countryCode") String countryCode) {
        List<Holiday> publicHolidays= nagerClientRest.getNextPublicHolidays(countryCode);
        return Response.ok().entity(publicHolidays).build();
    }
}
