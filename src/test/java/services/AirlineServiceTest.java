package services;

import bank.CreditCardFaultMessage;
import bank.CreditCardInfoType;
import models.FlightReservation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

public class AirlineServiceTest {

    private AirlineInterface airlineService;
    
    @Before
    public void setup() {
        try {
            URL url = new URL("http://localhost:8080/webservices/AirlineService?wsdl");
            QName qname = new QName("http://services/", "AirlineService");
            Service service = Service.create(url, qname);
            airlineService = service.getPort(AirlineInterface.class);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getFlightsTest() {
        FlightReservation[] result = airlineService.getFlights("Copenhagen", "Berlin", new Date(2016, 11, 7));

        System.out.println(result.length);
        System.out.println(result[0].getAirlineName());
    }


    @Test
    public void bookFlightEnoguhMoneyTest(){
        CreditCardInfoType creditCard = new CreditCardInfoType();
        CreditCardInfoType.ExpirationDate expDate = new CreditCardInfoType.ExpirationDate();

        expDate.setMonth(9);
        expDate.setYear(10);

        creditCard.setExpirationDate(expDate);
        creditCard.setNumber("50408823");
        creditCard.setName("Tobiasen Inge");
        try {
            boolean succes = airlineService.bookFlight("1234", creditCard);

            Assert.assertEquals(succes, true);
        } catch (CreditCardFaultMessage creditCardFaultMessage) {
            creditCardFaultMessage.printStackTrace();
        } catch (AirlineService.BookingNumberException e) {
            e.printStackTrace();
        }
    }


    @Test(expected=CreditCardFaultMessage.class)
    public void bookFlightNotEnoughtMoneyTest() throws AirlineService.BookingNumberException, CreditCardFaultMessage {
        CreditCardInfoType creditCard = new CreditCardInfoType();
        CreditCardInfoType.ExpirationDate expDate = new CreditCardInfoType.ExpirationDate();

        expDate.setMonth(3);
        expDate.setYear(10);

        creditCard.setExpirationDate(expDate);
        creditCard.setNumber("50408822");
        creditCard.setName("Bech Camilla");
        boolean succes = airlineService.bookFlight("1234", creditCard);

    }


    @Test(expected=AirlineService.BookingNumberException.class)
    public void bookFlightBookingNumberException() throws AirlineService.BookingNumberException, CreditCardFaultMessage {
        CreditCardInfoType creditCard = new CreditCardInfoType();
        CreditCardInfoType.ExpirationDate expDate = new CreditCardInfoType.ExpirationDate();

        expDate.setMonth(5);
        expDate.setYear(9);

        creditCard.setExpirationDate(expDate);
        creditCard.setNumber("50408816");
        creditCard.setName("Anne Strandberg");
        boolean succes = airlineService.bookFlight("NaN", creditCard);

    }

    @Test
    public void cancelFlightTest() throws AirlineService.BookingNumberException, CreditCardFaultMessage {
        CreditCardInfoType creditCard = new CreditCardInfoType();
        CreditCardInfoType.ExpirationDate expDate = new CreditCardInfoType.ExpirationDate();

        expDate.setMonth(5);
        expDate.setYear(9);

        creditCard.setExpirationDate(expDate);
        creditCard.setNumber("50408816");
        creditCard.setName("Anne Strandberg");
        boolean succes = airlineService.cancelFlight("1234", 500, creditCard);
        Assert.assertEquals(succes, true);

    }

    @Test
    public void cancelFlightTestBookingNumber() throws AirlineService.BookingNumberException, CreditCardFaultMessage {
        CreditCardInfoType creditCard = new CreditCardInfoType();
        CreditCardInfoType.ExpirationDate expDate = new CreditCardInfoType.ExpirationDate();

        expDate.setMonth(5);
        expDate.setYear(9);

        creditCard.setExpirationDate(expDate);
        creditCard.setNumber("50408816");
        creditCard.setName("Anne Strandberg");
        boolean succes = airlineService.cancelFlight("5237", 500, creditCard);
        Assert.assertEquals(succes, true);

    }
}
