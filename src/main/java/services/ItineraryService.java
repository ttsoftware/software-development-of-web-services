package services;

import models.Booking;
import models.Itinerary;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ItineraryService implements ItineraryInterface {
    @Override
    public Itinerary getItinerary(int id) {
        List<Itinerary> itinerary = null;
        try {
            itinerary = DatabaseService.getDao(Itinerary.class).queryForEq("id", 1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(itinerary.size() == 0) return null;
        return itinerary.get(0);
    }

    @Override
    public int createItinerary() {
        try {
            return DatabaseService.getDao(Itinerary.class).create(new Itinerary());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public List<Itinerary> getItineraries() {
        try {
            return DatabaseService.getDao(Itinerary.class).queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<Itinerary>();
    }

    @Override
    public boolean addBookingToItinerary(int itineraryId, Booking booking) {
        Itinerary itinerary = getItinerary(itineraryId);
        if(itinerary == null) return false;
        itinerary.getBookings().add(booking);
        try {
            DatabaseService.getDao(Itinerary.class).update(itinerary);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateBooking(int itineraryId, Booking booking) {
        Itinerary itinerary = getItinerary(itineraryId);
        if(itinerary == null) return false;
        Collection<Booking> bookings = itinerary.getBookings();
        Booking bookingDb = bookings.stream().filter(b -> b.getId() == (booking.getId())).findFirst().get();
        if(bookingDb == null) return false;
        bookingDb = booking;
        bookingDb.setBookingNumber(booking.getBookingNumber());
        bookingDb.setBookingStatus(booking.getBookingStatus());
        bookingDb.setBookingType(booking.getBookingType());
        bookingDb.setPrice(booking.getPrice());
        bookingDb.setDate(booking.getDate());
        try {
            DatabaseService.getDao(Booking.class).update(bookingDb);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
