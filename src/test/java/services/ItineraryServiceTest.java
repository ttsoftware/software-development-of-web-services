package services;

import models.Booking;
import models.BookingStatus;
import models.BookingType;
import models.Itinerary;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ItineraryServiceTest {
    private ItineraryService is;
    @Before
    public void setup(){
         is = new ItineraryService();
    }

    @Test
    public void insertItinerary(){
        List<Itinerary> itineraryList = is.getItineraries();
        int beforeInsertSize = itineraryList.size();
        is.createItinerary();
        List<Itinerary> itineraryList2 = is.getItineraries();
        int afterInsertSize = itineraryList2.size();

        Assert.assertEquals(beforeInsertSize + 1, afterInsertSize);
    }

    @Test
    public void getItineraryTest(){
        List<Itinerary> itineraryList = is.getItineraries();
        int id = is.createItinerary();
        Itinerary i = itineraryList.get(0);
        int iId = i.getId();
        Itinerary i2 = is.getItinerary(iId);

        Assert.assertEquals(i.getId(), i2.getId());
    }


    @Test
    public void getItinerariesTest(){
        List<Itinerary> itineraryList = is.getItineraries();
        //...
    }

    @Test
    public void addBookingToItineraryTest(){
        List<Itinerary> itineraryList = is.getItineraries();
        is.createItinerary();
        Itinerary i = itineraryList.get(0);
        int itineraryId = i.getId();
        Booking b = new Booking();
        b.setBookingNumber("qdwqwe");
        b.setBookingStatus(BookingStatus.UNCONFIRMMED);
        b.setBookingType(BookingType.FLIGHT);
        b.setPrice(100);
        b.setDate(new Date());
        b.setItinerary(i);
        is.addBookingToItinerary(itineraryId, b);

        Itinerary itineraryWithBooking = is.getItinerary(itineraryId);

        Collection<Booking> bookings = itineraryWithBooking.getBookings();
        Assert.assertTrue(bookings.contains(b));

    }


    @Test
    public void updateBookingTest(){
        List<Itinerary> itineraryList = is.getItineraries();
        is.createItinerary();
        Itinerary i = itineraryList.get(0);
        int itineraryId = i.getId();
        Collection<Booking> bookings = i.getBookings();
        Iterator<Booking> iterator = bookings.iterator();
        if(!iterator.hasNext()) Assert.fail();
        //Booking for update
        Booking booking = iterator.next();
        if(!booking.getBookingStatus().equals(BookingStatus.CONFIRMED)) {
            booking.setBookingStatus(BookingStatus.CONFIRMED);
        }else {
            booking.setBookingStatus(BookingStatus.CANCELLED);
        }

        booking.setBookingNumber("w");
        is.updateBooking(itineraryId, booking);



    }
}
