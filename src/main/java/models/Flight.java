package models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import models.dao.FlightDaoImpl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Date;

/**
 * Created by troels on 10/31/16.
 */
@XmlType(name = "Flight")
@XmlAccessorType(XmlAccessType.FIELD)
@DatabaseTable(tableName = "Flights", daoClass = FlightDaoImpl.class)
public class Flight {
    @DatabaseField(generatedId = true)
    private int id;

    @XmlElement(name="startAirport", required=true)
    @DatabaseField(canBeNull = false)
    private String startAirport;

    @XmlElement(name="destinationAripor", required=true)
    @DatabaseField(canBeNull = false)
    private String destinationAripor;

    @XmlElement(name="carrier", required=true)
    @DatabaseField(canBeNull = false)
    private String carrier;

    @XmlElement(name="startDate", required=true)
    @DatabaseField(canBeNull = false)
    private Date startDate;

    @XmlElement(name="endDate", required=true)
    @DatabaseField(canBeNull = false)
    private Date endDate;

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
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


}
