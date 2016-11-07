package models.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;
import models.Hotel;

import java.sql.SQLException;

public class HotelDaoImpl extends BaseDaoImpl<Hotel, Integer> implements HotelDao {

    public HotelDaoImpl(Class<Hotel> uClass) throws SQLException {
        super(uClass);
    }

    public HotelDaoImpl(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, Hotel.class);
    }

    public HotelDaoImpl(ConnectionSource connectionSource, Class<Hotel> uClass) throws SQLException {
        super(connectionSource, uClass);
    }

    public HotelDaoImpl(ConnectionSource connectionSource, DatabaseTableConfig<Hotel> tableConfig) throws SQLException {
        super(connectionSource, tableConfig);
    }

    /**
     * @param hotel
     * @return
     * @throws SQLException
     */
    @Override
    public int create(Hotel hotel) throws SQLException {
        return super.create(hotel);
    }
}
