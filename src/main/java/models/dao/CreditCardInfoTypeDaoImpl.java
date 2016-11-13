package models.dao;

import bank.CreditCardInfoType;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;

import java.sql.SQLException;

public class CreditCardInfoTypeDaoImpl extends BaseDaoImpl<CreditCardInfoType, Integer> implements CreditCardInfoTypeDao {

    protected CreditCardInfoTypeDaoImpl(Class<CreditCardInfoType> dataClass) throws SQLException {
        super(dataClass);
    }

    protected CreditCardInfoTypeDaoImpl(ConnectionSource connectionSource, Class<CreditCardInfoType> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    protected CreditCardInfoTypeDaoImpl(ConnectionSource connectionSource, DatabaseTableConfig<CreditCardInfoType> tableConfig) throws SQLException {
        super(connectionSource, tableConfig);
    }

    /**
     * @param creditCardInfoType
     * @return
     * @throws SQLException
     */
    @Override
    public int create(CreditCardInfoType creditCardInfoType) throws SQLException {
        return super.create(creditCardInfoType);
    }
}
