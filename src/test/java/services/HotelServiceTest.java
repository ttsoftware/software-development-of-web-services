package services;

import bank.CreditCardFaultMessage;
import models.*;
import org.junit.Before;
import org.junit.Test;
import services.exceptions.BookingNumberException;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;

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

        CustomDate arrivalDate = new CustomDate(2015, 12, 27);
        CustomDate depatureDate = new CustomDate(2018, 12, 31);

        Hotel[] hotels = hotelService.getHotels("Copenhagen", arrivalDate, depatureDate);

        assertEquals(hotels.length, 0);

        arrivalDate = new CustomDate(2016, 12, 28);
        depatureDate = new CustomDate(2016, 12, 30);

        hotels = hotelService.getHotels("Copenhagen", arrivalDate, depatureDate);

        assertEquals(hotels[0].getName(), "Slumpen");
    }

    @Test
    public void bookHotelTest() throws SQLException {

        CreditCardInfoType creditCard = new CreditCardInfoType();

        creditCard.setExpirationMonth(9);
        creditCard.setExpirationYear(10);
        creditCard.setNumber("50408823");
        creditCard.setName("Tobiasen Inge");

        HotelBookingRequest bookingRequest = new HotelBookingRequest();
        bookingRequest.setBookingNumber("2457");
        bookingRequest.setCardInformation(creditCard);

        try {
            boolean isBooked = hotelService.bookHotel(bookingRequest);
            assertTrue(isBooked);
        } catch (CreditCardFaultMessage e) {
            e.printStackTrace();
        }
    }

    @Test
    public void bookHotelFailTest() throws SQLException {

        CreditCardInfoType creditCard = new CreditCardInfoType();
        creditCard.setNumber("nej");

        HotelBookingRequest bookingRequest = new HotelBookingRequest();
        bookingRequest.setBookingNumber("nej");
        bookingRequest.setCardInformation(creditCard);

        try {
            boolean isBooked = hotelService.bookHotel(bookingRequest);
            assertTrue(!isBooked);
        } catch (CreditCardFaultMessage e) {
            e.printStackTrace();
        }
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void cancelHotel() throws SQLException {

        CreditCardInfoType creditCard = new CreditCardInfoType();

        creditCard.setExpirationMonth(9);
        creditCard.setExpirationYear(10);
        creditCard.setNumber("50408823");
        creditCard.setName("Tobiasen Inge");

        HotelBookingRequest bookingRequest = new HotelBookingRequest();
        bookingRequest.setBookingNumber("2457");
        bookingRequest.setCardInformation(creditCard);

        try {
            boolean isBooked = hotelService.bookHotel(bookingRequest);
            assertTrue(isBooked);
        } catch (CreditCardFaultMessage e) {
            e.printStackTrace();
        }

        try {
            hotelService.cancelHotel("2457");
        } catch (CreditCardFaultMessage | BookingNumberException e) {
            e.printStackTrace();
        }

        HotelReservation hotelReservation = DatabaseService.getDao(HotelReservation.class)
                .queryForEq("bookingNumber", "penis")
                .get(0);
    }
}
