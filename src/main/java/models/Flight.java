package models;

import java.util.Date;

/**
 * Created by troels on 10/31/16.
 */
public class Flight {
    private String startAirport, destinationAripor, carrier;

    private Date start;

    private Date end;

    public Flight(String startAirport, String destinationAripor, String carrier, Date start, Date end) {
        this.startAirport = startAirport;
        this.destinationAripor = destinationAripor;
        this.carrier = carrier;
        this.start = start;
        this.end = end;
    }

    public String getStartAirport() {
        return startAirport;
    }

    public void setStartAirport(String startAirport) {
        this.startAirport = startAirport;
    }

    public String getDestinationAripor() {
        return destinationAripor;
    }

    public void setDestinationAripor(String destinationAripor) {
        this.destinationAripor = destinationAripor;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }
}
