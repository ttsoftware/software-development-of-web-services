package models;

import bank.CreditCardInfoType;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by troels on 10/31/16.
 */
@XmlType(name="HotelBookingRequest")
public class HotelBookingRequest {

    @XmlElement(name="bookingNumber", required=true)
    private String bookingNumber;
    @XmlElement(name="creditCardInformation", required=false)
    private CreditCardInfoType cardInformation;
}
