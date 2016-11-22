package models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
import models.dao.ItineraryDaoImpl;

import javax.xml.bind.annotation.*;
import java.util.Collection;

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
    @XmlElement(name = "bookings")
    private Collection<Booking> bookings;

    public Collection<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(Collection<Booking> bookings) {
        this.bookings = bookings;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
