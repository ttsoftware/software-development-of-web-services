package models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "HotelBookingRequest")
public class HotelBookingRequest {

    private String bookingNumber;
    private CreditCardInfoType cardInformation;

    @XmlElement(name = "bookingNumber", required = true)
    public String getBookingNumber() {
        return bookingNumber;
    }

    public void setBookingNumber(String bookingNumber) {
        this.bookingNumber = bookingNumber;
    }

    @XmlElement(name = "creditCardInformation", required = true)
    public CreditCardInfoType getCardInformation() {
        return cardInformation;
    }

    public void setCardInformation(CreditCardInfoType cardInformation) {
        this.cardInformation = cardInformation;
    }
}
