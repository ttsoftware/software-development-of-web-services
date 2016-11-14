package services;

import models.Hotel;
import models.HotelBookingRequest;
import models.HotelReservation;
import org.junit.Before;
import org.junit.Test;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HotelServiceTest {

    private HotelInterface hotelService;

    @Before
    public void setup() {
        try {
            URL url = new URL("http://localhost:8080/webservices/HotelService?wsdl");
            QName qname = new QName("http://services/", "HotelService");
            Service service = Service.create(url, qname);
            hotelService = service.getPort(HotelInterface.class);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getHotelsTest() throws SQLException {
        Hotel[] hotels = hotelService.getHotels("copenhagen", new Date(), new Date());

        assertEquals(hotels[0].getName(), "Danglen");
    }

    @Test
    public void bookHotelTest() throws SQLException {

        HotelBookingRequest bookingRequest = new HotelBookingRequest();
        bookingRequest.setBookingNumber("penis");

        boolean isBooked = hotelService.bookHotel(bookingRequest);

        assertTrue(isBooked);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void cancelHotel() throws SQLException {

        HotelBookingRequest bookingRequest = new HotelBookingRequest();
        bookingRequest.setBookingNumber("penis");

        boolean isBooked = hotelService.bookHotel(bookingRequest);

        assertTrue(isBooked);

        hotelService.cancelHotel("penis");

        HotelReservation hotelReservation = DatabaseService.getDao(HotelReservation.class)
                .queryForEq("bookingNumber", "penis")
                .get(0);
    }
}
