package models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import models.dao.HotelDaoImpl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@XmlType(name = "Hotel")
@XmlRootElement(name = "Hotel")
@XmlAccessorType(XmlAccessType.FIELD)
@DatabaseTable(tableName = "Hotels", daoClass = HotelDaoImpl.class)
public class Hotel implements Serializable {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(canBeNull = false, unique = true)
    private String name;

    @DatabaseField(canBeNull = false)
    private String city;

    @DatabaseField(canBeNull = false)
    private String bookingNumber;

    @DatabaseField(canBeNull = true)
    private boolean creditCardGuarantee;

    @DatabaseField(canBeNull = false)
    private float price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBookingNumber() {
        return bookingNumber;
    }

    public void setBookingNumber(String bookingNumber) {
        this.bookingNumber = bookingNumber;
    }

    public boolean isCreditCardGuarantee() {
        return creditCardGuarantee;
    }

    public void setCreditCardGuarantee(boolean creditCardGuarantee) {
        this.creditCardGuarantee = creditCardGuarantee;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
