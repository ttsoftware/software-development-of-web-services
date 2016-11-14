package models.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;
import models.Booking;

import java.sql.SQLException;

/**
 * Created by troels on 11/14/16.
 */
public class BookingDaoImpl extends BaseDaoImpl<Booking, Integer> implements  BookingDao {
    protected BookingDaoImpl(Class<Booking> dataClass) throws SQLException {
        super(dataClass);
    }

    protected BookingDaoImpl(ConnectionSource connectionSource, Class<Booking> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    protected BookingDaoImpl(ConnectionSource connectionSource, DatabaseTableConfig<Booking> tableConfig) throws SQLException {
        super(connectionSource, tableConfig);
    }

    public BookingDaoImpl(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, Booking.class);
    }

    /**
     * @param booking
     * @return
     * @throws SQLException
     */
    @Override
    public int create(Booking booking) throws SQLException {
        return super.create(booking);
    }
}
