package services;

import bank.AccountType;
import bank.BankPortType;
import bank.BankService;
import bank.CreditCardFaultMessage;
import com.j256.ormlite.stmt.QueryBuilder;
import models.Hotel;
import models.HotelBookingRequest;
import models.HotelReservation;
import models.PenisDate;
import models.dao.HotelReservationDao;
import services.exceptions.BookingNumberException;

import javax.jws.WebService;
import java.net.MalformedURLException;
import java.net.URL;
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
                             PenisDate arrivalDate,
                             PenisDate departureDate) throws SQLException {

        Date arrivalDatePenisDate = arrivalDate.toDate();
        Date departureDatePenisDate = departureDate.toDate();

        QueryBuilder<Hotel, ?> queryBuilder = DatabaseService.getDao(Hotel.class).queryBuilder();
        queryBuilder.
                where()
                .eq("city", city)
                .and().le("opens", arrivalDatePenisDate.getTime())
                .and().ge("closes", departureDatePenisDate.getTime());

        List<Hotel> hotels = DatabaseService.getDao(Hotel.class).query(queryBuilder.prepare());

        return hotels.toArray(new Hotel[hotels.size()]);
    }

    @Override
    public boolean bookHotel(HotelBookingRequest hotelBookingRequest) throws CreditCardFaultMessage {

        Hotel hotel = null;
        try {
            hotel = DatabaseService.getDao(Hotel.class)
                    .queryForEq("bookingNumber", hotelBookingRequest.getBookingNumber())
                    .get(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (hotel == null) {
            return false;
        }

        URL bankServiceUrl = null;
        try {
            bankServiceUrl = new URL("http://fastmoney.imm.dtu.dk:8080/BankService?wsdl");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        BankService bs = new BankService(bankServiceUrl);
        BankPortType port = bs.getBankPort();

        AccountType hotelAccount = new AccountType();
        hotelAccount.setName("LameDuck");
        hotelAccount.setNumber("50208812");

        port.chargeCreditCard(22, hotelBookingRequest.getCardInformation(), (int) hotel.getPrice() , hotelAccount);

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
    public void cancelHotel(HotelBookingRequest hotelCancleRequest) throws CreditCardFaultMessage, BookingNumberException {
        HotelReservation hotelRes = null;
        List<HotelReservation> hotelResList = null;
        try {
            hotelResList = DatabaseService.getDao(HotelReservation.class)
                    .queryForEq("bookingNumber", hotelCancleRequest.getBookingNumber());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(hotelResList.size() == 0) throw new BookingNumberException("Booking number does not exists");
        hotelRes = hotelResList.get(0);

        float hotelPrice = hotelRes.getHotel().getPrice();

        AccountType hotelAccount = new AccountType();
        hotelAccount.setName("NiceView");
        hotelAccount.setNumber("50308815");

        URL bankServiceUrl = null;
        try {
            bankServiceUrl = new URL("http://fastmoney.imm.dtu.dk:8080/BankService?wsdl");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        BankService bs = new BankService(bankServiceUrl);
        BankPortType port = bs.getBankPort();

        int returnMoney = (int) Math.floor(hotelPrice/2);
        port.refundCreditCard(22, hotelCancleRequest.getCardInformation(), returnMoney, hotelAccount);

        try {
            DatabaseService.getDao(HotelReservation.class)
                    .delete(hotelRes);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
