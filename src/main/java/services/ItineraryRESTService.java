package services;

import models.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/travelagency")
public class ItineraryRESTService {

    private TravelAgencyService travelAgencyService = new TravelAgencyService();

    @GET
    @Path("/flights?from={from}&destination={destination}&year={year}&month={month}&day={day}")
    @Produces(MediaType.APPLICATION_JSON)
    public flight.FlightReservation[] flights(@PathParam("from") String from,
                                       @PathParam("destination") String destination,
                                       @PathParam("day") int day,
                                       @PathParam("month") int month,
                                       @PathParam("year") int year) {
        return travelAgencyService.getFlights(from, destination, new CustomDate(year, month, day));
    }

    @GET
    @Path("/hotels?city={city}&arrivalDateYear={arrivalDateYear}&arrivalDateMonth={arrivalDateMonth}&arrivalDateDay={arrivalDateDay}&departureDateYear={departureDateYear}&departureDateMonth={departureDateMonth}&departureDateDay={departureDateDay}")
    @Produces(MediaType.APPLICATION_JSON)
    public hotel.Hotel[] hotels(@PathParam("city") String city,
                          @PathParam("arrivalDateYear") int arrivalDateYear,
                          @PathParam("arrivalDateMonth") int arrivalDateMonth,
                          @PathParam("arrivalDateDay") int arrivalDateDay,
                          @PathParam("departureDateYear") int departureDateYear,
                          @PathParam("departureDateMonth") int departureDateMonth,
                          @PathParam("departureDateDay") int departureDateDay) {
        return travelAgencyService.getHotels(
                city,
                new CustomDate(arrivalDateYear, arrivalDateMonth, arrivalDateDay),
                new CustomDate(departureDateYear, departureDateMonth, departureDateDay)
        );
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
