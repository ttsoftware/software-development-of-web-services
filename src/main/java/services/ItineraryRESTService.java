package services;

import models.CustomDate;
import models.Itinerary;
import models.ItineraryChangeRequest;
import services.exceptions.BookingFaultException;
import services.exceptions.CancleBookingException;
import services.exceptions.ItineraryDoesNotExistException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlElement;

@Path("/")
public class ItineraryRESTService {

    private TravelAgencyService travelAgencyService = new TravelAgencyService();

    @GET
    @Path("/flights")
    @Produces(MediaType.APPLICATION_XML)
    public flight.FlightReservation[] flights(@QueryParam("from") String from,
                                              @QueryParam("destination") String destination,
                                              @QueryParam("day") int day,
                                              @QueryParam("month") int month,
                                              @QueryParam("year") int year) {

        return travelAgencyService.getFlights(from, destination, new CustomDate(year, month, day));
    }

    @GET
    @Path("/hotels")
    @Produces(MediaType.APPLICATION_XML)
    public hotel.Hotel[] hotels(@QueryParam("city") String city,
                                 @QueryParam("arrivalDateYear") int arrivalDateYear,
                                 @QueryParam("arrivalDateMonth") int arrivalDateMonth,
                                 @QueryParam("arrivalDateDay") int arrivalDateDay,
                                 @QueryParam("departureDateYear") int departureDateYear,
                                 @QueryParam("departureDateMonth") int departureDateMonth,
                                 @QueryParam("departureDateDay") int departureDateDay) {

        return travelAgencyService.getHotels(
                city,
                new CustomDate(arrivalDateYear, arrivalDateMonth, arrivalDateDay),
                new CustomDate(departureDateYear, departureDateMonth, departureDateDay)
        );
    }

    @POST
    @Path("/itinerary")
    @Produces(MediaType.TEXT_PLAIN)
    public Integer create() {
        return travelAgencyService.createItinerary();
    }

    @GET
    @Path("/itinerary/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Itinerary show(@PathParam("id") int id) throws ItineraryDoesNotExistException {
        return travelAgencyService.getItinerary(id);
    }

    @PUT
    @Path("/itinerary/{id}/cancel")
    @Consumes({MediaType.APPLICATION_XML})
    @Produces(MediaType.TEXT_PLAIN)
    public Boolean cancel(@XmlElement ItineraryChangeRequest changeRequest) {
        try {
            return travelAgencyService.cancelItinerarie(
                    changeRequest.getItinerary().getId(),
                    changeRequest.getCardInformation().getBankCreditCardInfoType()
            );
        } catch (CancleBookingException e) {
            e.printStackTrace();
        }
        return false;
    }

    @PUT
    @Path("/itinerary/{id}/book")
    @Consumes({MediaType.APPLICATION_XML})
    @Produces(MediaType.APPLICATION_XML)
    public boolean book(@XmlElement ItineraryChangeRequest changeRequest) {
        try {
            return travelAgencyService.bookItinerarie(
                    changeRequest.getItinerary().getId(),
                    changeRequest.getCardInformation().getBankCreditCardInfoType()
            );
        } catch (BookingFaultException e) {
            e.printStackTrace();
        }
        return false;
    }
}
