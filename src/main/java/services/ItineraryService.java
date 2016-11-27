package services;

import models.Booking;
import models.Itinerary;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class ItineraryService implements ItineraryInterface {

    //Allan Nielsen - s162874
    @Override
    public Itinerary getItinerary(int id) {
        List<Itinerary> itinerary = null;
        try {
            itinerary = DatabaseService.getDao(Itinerary.class).queryForEq("id", id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(itinerary.size() == 0) return null;
        return itinerary.get(0);
    }

    //Dennis Olesen - s155996
    @Override
    public int createItinerary() {
        try {
            Itinerary i = new Itinerary();
            DatabaseService.getDao(Itinerary.class).create(i);
            return i.getId();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    //Troels Thomsen - s152165
    @Override
    public List<Itinerary> getItineraries() {
        try {
            return DatabaseService.getDao(Itinerary.class).queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<Itinerary>();
    }

    //Troels Hessner Hansen - s123136
    @Override
    public boolean addBookingToItinerary(int itineraryId, Booking booking) {
        Itinerary itinerary = getItinerary(itineraryId);
        if(itinerary == null) return false;
        itinerary.getBookings().add(booking);
        try {
            DatabaseService.getDao(Itinerary.class).update(itinerary);
            Iterator<Booking> iterator = itinerary.getBookings().iterator();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Allan Nielsen - s162874
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
