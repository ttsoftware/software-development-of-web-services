package services;

import models.Booking;
import models.Itinerary;

import java.util.List;


public interface ItineraryInterface {

    Itinerary getItinerary(int id);

    int createItinerary();

    List<Itinerary> getItineraries();

    boolean addBookingToItinerary(int itineraryId, Booking booking);

    boolean updateBooking(int itineraryId, Booking booking);

}
