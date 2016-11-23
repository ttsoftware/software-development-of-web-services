package services;

import bank.AccountType;
import bank.BankPortType;
import bank.BankService;
import bank.CreditCardFaultMessage;
import com.j256.ormlite.stmt.QueryBuilder;
import models.*;
import models.dao.HotelReservationDao;
import services.exceptions.BookingNumberException;

import javax.jws.WebService;
import javax.xml.ws.soap.SOAPFaultException;
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
                             CustomDate arrivalDate,
                             CustomDate departureDate) {

        Date arrivalDatePenisDate = arrivalDate.toDate();
        Date departureDatePenisDate = departureDate.toDate();

        QueryBuilder<Hotel, ?> queryBuilder = null;
        try {
            queryBuilder = DatabaseService.getDao(Hotel.class).queryBuilder();

            queryBuilder.
                    where()
                    .eq("city", city)
                    .and().le("opens", arrivalDatePenisDate.getTime())
                    .and().ge("closes", departureDatePenisDate.getTime());

            List<Hotel> hotels = DatabaseService.getDao(Hotel.class).query(queryBuilder.prepare());

            return hotels.toArray(new Hotel[hotels.size()]);
        } catch (SQLException e) {
            return new Hotel[]{};
        }

    }

    @Override
    public boolean bookHotel(HotelBookingRequest hotelBookingRequest) throws CreditCardFaultMessage {

        Hotel hotel = null;
        try {
            List<Hotel> hotels = DatabaseService.getDao(Hotel.class)
                    .queryForEq("bookingNumber", hotelBookingRequest.getBookingNumber());

            if (hotels.isEmpty()) return false;
            hotel = hotels.get(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        HotelReservation hotelReservation = new HotelReservation();
        hotelReservation.setHotel(hotel);
        hotelReservation.setBookingNumber(hotelBookingRequest.getBookingNumber());

        if (hotel.isCreditCardGuarantee()) {
            models.CreditCardInfoType customerCreditCardInfoType = null;
            try {
                List<models.CreditCardInfoType> creditCards = DatabaseService
                        .getDao(models.CreditCardInfoType.class)
                        .queryForEq("number", hotelBookingRequest.getCardInformation().getNumber());

                if (creditCards.isEmpty()) return false;
                customerCreditCardInfoType = creditCards.get(0);
            } catch (SQLException e) {
                e.printStackTrace();
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

            bank.CreditCardInfoType creditCardInfoType = customerCreditCardInfoType.getBankCreditCardInfoType();
            boolean success = port.chargeCreditCard(22, creditCardInfoType, (int) hotel.getPrice(), hotelAccount);

            hotelReservation.setCardInformation(customerCreditCardInfoType);
        }

        try {
            HotelReservationDao hotelReservationDao = DatabaseService.getDao(HotelReservation.class);
            hotelReservationDao.create(hotelReservation);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public void cancelHotel(String bookingNumber) throws CreditCardFaultMessage, BookingNumberException {
        HotelReservation hotelRes = null;
        List<HotelReservation> hotelResList = null;
        try {
            hotelResList = DatabaseService.getDao(HotelReservation.class)
                    .queryForEq("bookingNumber", bookingNumber);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (hotelResList.size() == 0) throw new BookingNumberException("Booking number does not exists");
        hotelRes = hotelResList.get(0);

        float hotelPrice = hotelRes.getHotel().getPrice();

        AccountType hotelAccount = new AccountType();
        hotelAccount.setName("NiceView");
        hotelAccount.setNumber("50308815");

        URL bankServiceUrl = null;
        try {
            bankServiceUrl = new URL("http://fastmoney.imm.dtu.dk:8080/BankService?wsdl");
        } catch (MalformedURLException | SOAPFaultException e) {
            e.printStackTrace();
        }

        BankService bs = new BankService(bankServiceUrl);
        BankPortType port = bs.getBankPort();

        int returnMoney = (int) Math.floor(hotelPrice);

        if (hotelRes.getCardInformation() != null) {
            bank.CreditCardInfoType creditCardInfoType = hotelRes.getCardInformation().getBankCreditCardInfoType();
            port.refundCreditCard(22, creditCardInfoType, returnMoney, hotelAccount);
        }

        try {
            DatabaseService.getDao(HotelReservation.class)
                    .delete(hotelResList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
