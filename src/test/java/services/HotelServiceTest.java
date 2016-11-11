package services;

import models.Hotel;
import org.junit.Before;
import org.junit.Test;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;

public class HotelServiceTest {

    private HotelInterface hotelService;

    @Before
    public void setup() {
        try {
            URL url = new URL("http://localhost:8080/web_services_war_exploded/HotelService?wsdl");
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

        Arrays.stream(hotels).forEach(hotel -> {
            System.out.println(hotel.getName());
        });
    }
}
