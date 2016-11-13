package models;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import models.dao.FlightDaoImpl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by troels on 10/31/16.
 */
@XmlType(name = "Flight")
@XmlAccessorType(XmlAccessType.FIELD)
@DatabaseTable(tableName = "Flight", daoClass = FlightDaoImpl.class)
public class Flight {
    @DatabaseField(generatedId = true)
    private int id;

    @XmlElement(name="startAirport", required=true)
    @DatabaseField(canBeNull = false)
    private String startAirport;

    @XmlElement(name="destinationAirport", required=true)
    @DatabaseField(canBeNull = false)
    private String destinationAirport;

    @XmlElement(name="carrier", required=true)
    @DatabaseField(canBeNull = false)
    private String carrier;

    @XmlElement(name="start", required=true)
    @DatabaseField(canBeNull = false, dataType = DataType.DATE_STRING, format = "yyyy/MM/dd HH:mm:ss")
    private Date start;

    @XmlElement(name="end", required=true)
    @DatabaseField(canBeNull = false, dataType = DataType.DATE_STRING, format = "yyyy/MM/dd HH:mm:ss")
    private Date end;


    public String dateToString(Date date) {
        // remember that SimpleDateFormat is not reentrant
        return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
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
