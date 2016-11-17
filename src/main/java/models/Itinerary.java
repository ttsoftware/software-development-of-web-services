package models;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
import models.dao.ItineraryDaoImpl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by troels on 11/14/16.
 */
@XmlType(name = "Itinerary")
@XmlRootElement(name = "Itinerary")
@XmlAccessorType(XmlAccessType.FIELD)
@DatabaseTable(tableName = "Itinerary", daoClass = ItineraryDaoImpl.class)
public class Itinerary {

    @DatabaseField(generatedId = true)
    private int id;

    @ForeignCollectionField(eager = true)
    private ForeignCollection<Booking> bookings;

    public ForeignCollection<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(ForeignCollection<Booking> bookings) {
        this.bookings = bookings;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
