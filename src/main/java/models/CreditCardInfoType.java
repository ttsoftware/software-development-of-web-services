package models;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import models.dao.CreditCardInfoTypeDaoImpl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "CreditCardInfoType")
@XmlAccessorType(XmlAccessType.FIELD)
@DatabaseTable(tableName = "CreditCardInfoType", daoClass = CreditCardInfoTypeDaoImpl.class)
public class CreditCardInfoType {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(canBeNull = false)
    private String name;

    @DatabaseField(canBeNull = false)
    private String number;

    @DatabaseField(canBeNull = false)
    private int expirationMonth;

    @DatabaseField(canBeNull = false)
    private int expirationYear;

    public CreditCardInfoType() {
    }

    public bank.CreditCardInfoType getBankCreditCardInfoType() {

        bank.CreditCardInfoType creditCardInfoType = new bank.CreditCardInfoType();

        creditCardInfoType.setName(this.getName());
        creditCardInfoType.setNumber(this.getNumber());

        bank.CreditCardInfoType.ExpirationDate expirationDate = new bank.CreditCardInfoType.ExpirationDate();
        expirationDate.setMonth(this.getExpirationMonth());
        expirationDate.setYear(this.getExpirationYear());

        creditCardInfoType.setExpirationDate(expirationDate);

        return creditCardInfoType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getExpirationMonth() {
        return expirationMonth;
    }

    public void setExpirationMonth(int expirationMonth) {
        this.expirationMonth = expirationMonth;
    }

    public int getExpirationYear() {
        return expirationYear;
    }

    public void setExpirationYear(int expirationYear) {
        this.expirationYear = expirationYear;
    }
}
