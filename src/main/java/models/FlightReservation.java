package models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import models.dao.FlightInformationDaoImpl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by troels on 10/31/16.
 */


@XmlType(name = "FlightReservation")
@XmlRootElement(name = "FlightReservation")
@XmlAccessorType(XmlAccessType.FIELD)
@DatabaseTable(tableName = "FlightReservations", daoClass = FlightInformationDaoImpl.class)
public class FlightReservation {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(canBeNull = false, unique = true)
    private String bookingNumber;

    @DatabaseField(canBeNull = false)
    private String airlineName;

    @DatabaseField(canBeNull = false)
    private int price;

    @DatabaseField(
            canBeNull = false,
            foreign = true,
            foreignAutoCreate = true,
            columnName = "fk_flight")
    private Flight flight;

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public String getBookingNumber() {
        return bookingNumber;
    }

    public void setBookingNumber(String bookingNumber) {
        this.bookingNumber = bookingNumber;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
