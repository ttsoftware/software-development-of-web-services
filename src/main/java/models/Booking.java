package models;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import models.dao.BookingDaoImpl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import java.util.Date;

/**
 * Created by troels on 11/14/16.
 */
@XmlType(name = "Booking")
@XmlAccessorType(XmlAccessType.FIELD)
@DatabaseTable(tableName = "Booking", daoClass = BookingDaoImpl.class)
public class Booking {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(dataType = DataType.ENUM_INTEGER)
    private BookingStatus bookingStatus;

    @DatabaseField(dataType = DataType.ENUM_INTEGER)
    private BookingType bookingType;

    @DatabaseField(canBeNull = false)
    private String bookingNumber;

    @DatabaseField(canBeNull = false)
    private float price;

    @DatabaseField(canBeNull = false)
    private Date date;

    @DatabaseField(
            foreign = true,
            columnName = "fk_itinerary")
    private Itinerary itinerary;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Itinerary getItinerary() {
        return itinerary;
    }

    public void setItinerary(Itinerary itinerary) {
        this.itinerary = itinerary;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public BookingType getBookingType() {
        return bookingType;
    }

    public void setBookingType(BookingType bookingType) {
        this.bookingType = bookingType;
    }

    public String getBookingNumber() {
        return bookingNumber;
    }

    public void setBookingNumber(String bookingNumber) {
        this.bookingNumber = bookingNumber;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
