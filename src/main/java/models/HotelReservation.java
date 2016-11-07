package models;

import bank.CreditCardInfoType;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "HotelReservation")
@XmlRootElement(name = "HotelReservation")
@XmlAccessorType(XmlAccessType.FIELD)
public class HotelReservation {

    private String bookingNumber;
    private CreditCardInfoType cardInformation;

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
