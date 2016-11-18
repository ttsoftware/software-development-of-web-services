package services;

import models.Booking;
import models.Itinerary;

import java.sql.SQLException;
import java.util.List;


public class Daotest {

    public static void main(String[] args) throws SQLException {
        List<Itinerary> itinerary = DatabaseService.getDao(Itinerary.class).queryForEq("id", 1);

        System.out.println(itinerary.size());

        Object[] bookings = itinerary.get(0).getBookings().toArray();
        Booking test = (Booking) bookings[0];
        System.out.println(test.getBookingStatus());
    }
}
