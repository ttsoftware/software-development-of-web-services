package models.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;
import models.HotelReservation;

import java.sql.SQLException;

public class HotelReservationDaoImpl extends BaseDaoImpl<HotelReservation, Integer> implements HotelReservationDao {

    public HotelReservationDaoImpl(Class<HotelReservation> uClass) throws SQLException {
        super(uClass);
    }

    public HotelReservationDaoImpl(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, HotelReservation.class);
    }

    public HotelReservationDaoImpl(ConnectionSource connectionSource, Class<HotelReservation> uClass) throws SQLException {
        super(connectionSource, uClass);
    }

    public HotelReservationDaoImpl(ConnectionSource connectionSource, DatabaseTableConfig<HotelReservation> tableConfig) throws SQLException {
        super(connectionSource, tableConfig);
    }

    /**
     * @param hotelReservation
     * @return
     * @throws SQLException
     */
    @Override
    public int create(HotelReservation hotelReservation) throws SQLException {
        return super.create(hotelReservation);
    }
}
