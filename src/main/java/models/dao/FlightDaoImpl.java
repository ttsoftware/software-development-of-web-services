package models.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;
import models.Flight;

import java.sql.SQLException;

/**
 * Created by troels on 11/7/16.
 */
public class FlightDaoImpl extends BaseDaoImpl<Flight, Integer> implements FlightDao {

    protected FlightDaoImpl(Class<Flight> dataClass) throws SQLException {
        super(dataClass);
    }

    protected FlightDaoImpl(ConnectionSource connectionSource, Class<Flight> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    protected FlightDaoImpl(ConnectionSource connectionSource, DatabaseTableConfig<Flight> tableConfig) throws SQLException {
        super(connectionSource, tableConfig);
    }

    public FlightDaoImpl(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, Flight.class);
    }

    /**
     * @param flight
     * @return
     * @throws SQLException
     */
    @Override
    public int create(Flight flight) throws SQLException {
        return super.create(flight);
    }
}
