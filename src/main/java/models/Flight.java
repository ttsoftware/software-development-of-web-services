package models;

import javax.xml.bind.annotation.*;
import java.util.Date;

/**
 * Created by troels on 10/31/16.
 */
@XmlType(name = "Flight")
@XmlAccessorType(XmlAccessType.FIELD)
public class Flight {

    @XmlElement(name="startAirport", required=true)
    private String startAirport;

    @XmlElement(name="destinationAripor", required=true)
    private String destinationAripor;

    @XmlElement(name="carrier", required=true)
    private String carrier;

    @XmlElement(name="start", required=true)
    private Date start;

    @XmlElement(name="end", required=true)
    private Date end;

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
