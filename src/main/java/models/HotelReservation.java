package models;

import bank.CreditCardInfoType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import models.dao.HotelReservationDaoImpl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "HotelReservation")
@XmlRootElement(name = "HotelReservation")
@XmlAccessorType(XmlAccessType.FIELD)
@DatabaseTable(tableName = "HotelReservation", daoClass = HotelReservationDaoImpl.class)
public class HotelReservation {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(canBeNull = false)
    private String bookingNumber;

    /*@DatabaseField(
            foreign = true,
            columnName = "fk_creditCardInfoType")*/
    private CreditCardInfoType cardInformation;

    @DatabaseField(
            foreign = true,
            columnName = "fk_hotel")
    private Hotel hotel;

    public HotelReservation() {
    }

    public String getBookingNumber() {
        return bookingNumber;
    }

    public void setBookingNumber(String bookingNumber) {
        this.bookingNumber = bookingNumber;
    }

    public CreditCardInfoType getCardInformation() {
        return cardInformation;
    }

    public void setCardInformation(CreditCardInfoType cardInformation) {
        this.cardInformation = cardInformation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}
