package models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import models.dao.FlightDaoImpl;

import javax.xml.bind.annotation.*;

/**
 * Created by troels thomsen on stalin.
 */
@XmlType(name = "Flight")
@XmlRootElement(name = "Flight")
@XmlAccessorType(XmlAccessType.FIELD)
@DatabaseTable(tableName = "Flight", daoClass = FlightDaoImpl.class)
public class Flight {

    @DatabaseField(generatedId = true)
    private int id;

    @XmlElement(name = "startAirport", required = true)
    @DatabaseField(canBeNull = false)
    private String startAirport;

    @XmlElement(name = "destinationAirport", required = true)
    @DatabaseField(canBeNull = false)
    private String destinationAirport;

    @XmlElement(name = "carrier", required = true)
    @DatabaseField(canBeNull = false)
    private String carrier;

    @XmlElement(name = "start", required = true)
    @DatabaseField(canBeNull = false)
    private long start;

    @XmlElement(name = "end", required = true)
    @DatabaseField(canBeNull = false)
    private long end;

    public Flight() {
    }

    public Flight(flight.Flight flight) {
        id = flight.getId();
        startAirport = flight.getStartAirport();
        destinationAirport = flight.getDestinationAirport();
        carrier = flight.getCarrier();
        start = flight.getStart();
        end = flight.getEnd();
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStartAirport() {
        return startAirport;
    }

    public void setStartAirport(String startAirport) {
        this.startAirport = startAirport;
    }

    public String getDestinationAirport() {
        return destinationAirport;
    }

    public void setDestinationAirport(String destinationAirport) {
        this.destinationAirport = destinationAirport;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }
}
