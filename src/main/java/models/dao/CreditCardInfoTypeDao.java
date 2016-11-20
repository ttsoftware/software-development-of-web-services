package models.dao;

import models.CreditCardInfoType;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

public interface CreditCardInfoTypeDao extends Dao<CreditCardInfoType, Integer> {

    @Override
    int create(CreditCardInfoType creditCardInfoType) throws SQLException;
}
