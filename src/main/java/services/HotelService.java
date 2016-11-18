package services;

import com.j256.ormlite.stmt.QueryBuilder;
import models.Hotel;
import models.HotelBookingRequest;
import models.HotelReservation;
import models.dao.HotelReservationDao;

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

        QueryBuilder<Hotel, ?> queryBuilder = DatabaseService.getDao(Hotel.class).queryBuilder();
        queryBuilder.
                where()
                .eq("city", city)
                .and().le("opens", arrivalDate.getTime())
                .and().ge("closes", departureDate.getTime());

        List<Hotel> hotels = DatabaseService.getDao(Hotel.class).query(queryBuilder.prepare());

        return hotels.toArray(new Hotel[hotels.size()]);
    }

    @Override
    public boolean bookHotel(HotelBookingRequest hotelBookingRequest) {

        Hotel hotel = null;
        try {
            hotel = DatabaseService.getDao(Hotel.class)
                    .queryForEq("bookingNumber", hotelBookingRequest.getBookingNumber())
                    .get(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        HotelReservation hotelReservation = new HotelReservation();
        hotelReservation.setHotel(hotel);
        hotelReservation.setBookingNumber(hotelBookingRequest.getBookingNumber());
        hotelReservation.setCardInformation(hotelBookingRequest.getCardInformation());

        try {
            HotelReservationDao hotelReservationDao = DatabaseService.getDao(HotelReservation.class);
            hotelReservationDao.create(hotelReservation);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public void cancelHotel(String bookingNumber) {
        try {
            DatabaseService.getDao(HotelReservation.class)
                    .delete(
                            DatabaseService.getDao(HotelReservation.class)
                                    .queryForEq("bookingNumber", bookingNumber)
                    );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
