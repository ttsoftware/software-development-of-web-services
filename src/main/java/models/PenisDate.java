package models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Date;


@XmlType(name = "PenisDate")
@XmlRootElement(name = "PenisDate")
@XmlAccessorType(XmlAccessType.FIELD)
public class PenisDate {

    private int year;
    private int month;
    private int day;

    public PenisDate() {
    }

    public PenisDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public Date toDate() {
        return new Date(this.getYear()-1900, this.getMonth()-1, this.getDay());
    }
}