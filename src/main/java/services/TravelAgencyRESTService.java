package services;

import models.Booking;
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
public class TravelAgencyRESTService {

    private TravelAgencyService travelAgencyService = new TravelAgencyService();

    // Dennis Olesen - s155996
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

    //Troels Hessner Hansen - s123136
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

    //Dennis Olesen - s155996
    @POST
    @Path("/itinerary")
    @Produces(MediaType.TEXT_PLAIN)
    public Integer create() {
        return travelAgencyService.createItinerary();
    }

    // Allan Nielsen - s162874
    @GET
    @Path("/itinerary")
    @Produces(MediaType.APPLICATION_XML)
    public Itinerary[] showAll() {
        return travelAgencyService.getItineraries();
    }

    // Troels Thomsen - s152165
    @GET
    @Path("/itinerary/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Itinerary show(@PathParam("id") int id) throws ItineraryDoesNotExistException {
        return travelAgencyService.getItinerary(id);
    }

    // Troels Thomsen - s152165
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

    // Troels Hessner Hansen - s123136
    @PUT
    @Path("/itinerary/{id}/booking")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.TEXT_PLAIN)
    public Boolean create(@PathParam("id") int id,
                          @XmlElement Booking booking) {
        return travelAgencyService.createBooking(id, booking);
    }

    // Allan Nielsen - s162874
    @PUT
    @Path("/itinerary/{id}/book")
    @Consumes({MediaType.APPLICATION_XML})
    @Produces(MediaType.TEXT_PLAIN)
    public Boolean book(@XmlElement ItineraryChangeRequest changeRequest) {
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
