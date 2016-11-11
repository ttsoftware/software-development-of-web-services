package models.dao;

import bank.CreditCardInfoType;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;

import java.sql.SQLException;

public class CreditCardInfoTypeDaoImpl extends BaseDaoImpl<CreditCardInfoType, Integer> implements CreditCardInfoTypeDao {

    public CreditCardInfoTypeDaoImpl(Class<CreditCardInfoType> uClass) throws SQLException {
        super(uClass);
    }

    public CreditCardInfoTypeDaoImpl(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, CreditCardInfoType.class);
    }

    public CreditCardInfoTypeDaoImpl(ConnectionSource connectionSource, Class<CreditCardInfoType> uClass) throws SQLException {
        super(connectionSource, uClass);
    }

    public CreditCardInfoTypeDaoImpl(ConnectionSource connectionSource, DatabaseTableConfig<CreditCardInfoType> tableConfig) throws SQLException {
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
