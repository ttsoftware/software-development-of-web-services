package services;

import models.Itinerary;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

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
    }


}
