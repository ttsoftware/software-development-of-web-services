package services;

import models.CustomDate;
import models.FlightReservation;
import models.Hotel;
import models.Itinerary;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;

/**
 * Created by troels on 11/15/16.
 */
public class TravelAgencySoapServiceTest {

    private TravelAgencySoapInterface travelAgencyInterface;

    @Before
    public void setup() {
        try {
            URL url = new URL("http://localhost:8080/webservices/TravelAgencySoapService?wsdl");
            QName qname = new QName("http://services/", "TravelAgencySoapService");
            Service service = Service.create(url, qname);
            travelAgencyInterface = service.getPort(TravelAgencySoapInterface.class);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getItineraryTest() {
        try {
            int id = travelAgencyInterface.createItinerarie();
            Itinerary itinerary = travelAgencyInterface.getItinerary(id);
            Assert.assertNotNull(itinerary);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public  void getItinerariesTest(){
        try {
            Itinerary[] itinerary = travelAgencyInterface.getItineraries();
            Assert.assertNotNull(itinerary);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void P1(){
       FlightReservation[] flight = travelAgencyInterface.getFlights("Copenhagen", "Berlin", new CustomDate(2016, 11, 7));
        try {
            Hotel[] hotels = travelAgencyInterface.getHotels("copenhagen", new CustomDate(2016, 11, 7), new CustomDate(2016, 11, 7));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
