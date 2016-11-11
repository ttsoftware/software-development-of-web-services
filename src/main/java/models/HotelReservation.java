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

    @DatabaseField(canBeNull = false)
    private CreditCardInfoType cardInformation;

    @DatabaseField(
            canBeNull = false,
            foreign = true,
            foreignAutoCreate = true,
            columnName = "fk_hotel")
    private Hotel hotel;

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
}
