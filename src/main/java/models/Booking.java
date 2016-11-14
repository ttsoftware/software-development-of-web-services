package models;

import java.util.Date;

/**
 * Created by troels on 11/14/16.
 */
public class Booking {

    private int id;
    private BookingStatus bookingStatus;
    private BookingType bookingType;
    private String bookingNumber;
    private float price;
    private Date start;

    public Booking(BookingStatus bookingStatus, BookingType bookingType, String bookingNumber, float price, Date start) {
        this.bookingStatus = bookingStatus;
        this.bookingType = bookingType;
        this.bookingNumber = bookingNumber;
        this.price = price;
        this.start = start;
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

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }
}
