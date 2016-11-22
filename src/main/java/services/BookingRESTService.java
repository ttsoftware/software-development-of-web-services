package services;

import models.Booking;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/")
public class BookingRESTService {

    @POST
    @Path("/booking")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces(MediaType.APPLICATION_JSON)
    public boolean create(Booking booking) {
        return false;
    }

    @PUT
    @Path("/booking/cancel")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces(MediaType.APPLICATION_JSON)
    public boolean cancel(Booking booking) {
        return false;
    }
}
