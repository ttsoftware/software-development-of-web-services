package services;

import models.Flight;
import models.Hotel;
import models.Itinerary;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/travelagency")
public class ItineraryRESTService {

    @GET
    @Path("/flights?from={from}&destination={destination}&date={date}")
    @Produces(MediaType.APPLICATION_JSON)
    public Flight[] flights() {
        return new Flight[]{};
    }

    @GET
    @Path("/hotels?city={city}&arrivalDate={arrivalDate}&departureDate={departureDate}")
    @Produces(MediaType.APPLICATION_JSON)
    public Hotel[] hotels() {
        return new Hotel[]{};
    }

    @GET
    @Path("/itinerary/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Itinerary show() {
        return new Itinerary();
    }

    @POST
    @Path("/itinerary")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces(MediaType.APPLICATION_JSON)
    public int create(Itinerary itinerary) {
        return new Itinerary().getId();
    }

    @PUT
    @Path("/itinerary/{id}/cancel")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces(MediaType.APPLICATION_JSON)
    public boolean cancel(Itinerary itinerary) {
        return false;
    }

    @PUT
    @Path("/itinerary/{id}/book")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces(MediaType.APPLICATION_JSON)
    public boolean book(Itinerary itinerary) {
        return false;
    }
}
