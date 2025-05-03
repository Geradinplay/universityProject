package org.acme.rest.client;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.acme.model.Country;
import org.acme.model.Holiday;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@RegisterRestClient(configKey = "nager-api")
public interface NagerClientRest {
    @GET
    @Path("/AvailableCountries")
    @Produces(MediaType.APPLICATION_JSON)
    List<Country> getAvailableCountries();

    @GET
    @Path("/NextPublicHolidays/{countryCode}")
    @Produces(MediaType.APPLICATION_JSON)
    List<Holiday> getNextPublicHolidays(@PathParam("countryCode") String countryCode);
}
