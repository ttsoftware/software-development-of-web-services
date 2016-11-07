package services;

import models.Hotel;
import models.HotelBookingRequest;
import models.HotelReservation;

import javax.jws.WebService;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@WebService(
        name = "HotelService",
        serviceName = "HotelService",
        endpointInterface = "services.HotelInterface"
)
public class HotelService implements HotelInterface {

    @Override
    public Hotel[] getHotels(String city,
                             Date arrivalDate,
                             Date departureDate) throws SQLException {

        List<Hotel> hotels = DatabaseService.getDao(Hotel.class).queryForEq("city", city);

        return (Hotel[]) hotels.toArray();
    }

    @Override
    public boolean bookHotel(HotelBookingRequest hotelBookingRequest) throws SQLException {

        Hotel hotel = DatabaseService.getDao(Hotel.class)
                .queryForEq("bookingNumber", hotelBookingRequest.getBookingNumber())
                .get(0);

        HotelReservation hotelReservation = new HotelReservation();
        hotelReservation.setBookingNumber(hotelBookingRequest.getBookingNumber());
        hotelReservation.setCardInformation(hotelBookingRequest.getCardInformation());

        //

        return true;
    }

    @Override
    public void cancelHotel(String bookingNumber) {

    }
}
