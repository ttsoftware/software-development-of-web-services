package services;

import models.CustomDate;
import models.Itinerary;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Path("/")
public class ItineraryRESTService {

    private TravelAgencyService travelAgencyService = new TravelAgencyService();

    @GET
    @Path("/flights")
    @Produces(MediaType.APPLICATION_XML)
    public models.FlightReservation[] flights(@QueryParam("from") String from,
                                       @QueryParam("destination") String destination,
                                       @QueryParam("day") int day,
                                       @QueryParam("month") int month,
                                       @QueryParam("year") int year) {

        flight.FlightReservation[] flightReservations = travelAgencyService.getFlights(from, destination, new CustomDate(year, month, day));

        List<models.FlightReservation> flightReservationList = new ArrayList<>();
        Arrays.stream(flightReservations).forEach(flightReservation -> {
            flightReservationList.add(new models.FlightReservation(flightReservation));
        });

        return flightReservationList.toArray(new models.FlightReservation[flightReservationList.size()]);
    }

    @GET
    @Path("/hotels")
    @Produces(MediaType.APPLICATION_XML)
    public models.Hotel[] hotels(@QueryParam("city") String city,
                          @QueryParam("arrivalDateYear") int arrivalDateYear,
                          @QueryParam("arrivalDateMonth") int arrivalDateMonth,
                          @QueryParam("arrivalDateDay") int arrivalDateDay,
                          @QueryParam("departureDateYear") int departureDateYear,
                          @QueryParam("departureDateMonth") int departureDateMonth,
                          @QueryParam("departureDateDay") int departureDateDay) {

        hotel.Hotel[] hotelServiceHotels = travelAgencyService.getHotels(
                city,
                new CustomDate(arrivalDateYear, arrivalDateMonth, arrivalDateDay),
                new CustomDate(departureDateYear, departureDateMonth, departureDateDay)
        );

        List<models.Hotel> hotels = new ArrayList<>();
        Arrays.stream(hotelServiceHotels).forEach(hotel -> {
            hotels.add(new models.Hotel(hotel));
        });

        return hotels.toArray(new models.Hotel[hotels.size()]);
    }

    @GET
    @Path("/itinerary/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Itinerary show() {
        return new Itinerary();
    }

    @POST
    @Path("/itinerary")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces(MediaType.APPLICATION_XML)
    public int create(Itinerary itinerary) {
        return new Itinerary().getId();
    }

    @PUT
    @Path("/itinerary/{id}/cancel")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces(MediaType.APPLICATION_XML)
    public boolean cancel(Itinerary itinerary) {
        return false;
    }

    @PUT
    @Path("/itinerary/{id}/book")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces(MediaType.APPLICATION_XML)
    public boolean book(Itinerary itinerary) {
        return false;
    }
}
