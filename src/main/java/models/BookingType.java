package models;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

/**
 * Created by troels on 11/14/16.
 */
@XmlEnum
public enum BookingType {
    @XmlEnumValue("FLIGHT")
    FLIGHT("FLIGHT"),
    @XmlEnumValue("HOTEL")
    HOTEL("HOTEL");

    private final String value;


    BookingType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static BookingType fromValue(String v) {
        for (BookingType c: BookingType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v.toString());
    }
}
