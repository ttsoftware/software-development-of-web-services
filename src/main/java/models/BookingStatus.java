package models;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "BookingStatus")
@XmlEnum(String.class)
public enum BookingStatus {
    @XmlEnumValue("CONFIRMED")
    CONFIRMED("CONFIRMED"),
    @XmlEnumValue("UNCONFIRMED")
    UNCONFIRMMED("UNCONFIRMED"),
    @XmlEnumValue("CANCELLED")
    CANCELLED("CANCELLED");

    private String value;

    BookingStatus(String v) {
        value = v;
    }


    BookingStatus() {

    }

    public String value() {
        return value;
    }
}
