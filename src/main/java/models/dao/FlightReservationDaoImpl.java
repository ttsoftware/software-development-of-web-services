package models.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;
import models.FlightReservation;

import java.sql.SQLException;

/**
 * Created by troels on 11/7/16.
 */
public class FlightReservationDaoImpl extends BaseDaoImpl<FlightReservation, Integer> implements FlightReservationDao {

    public FlightReservationDaoImpl(Class<FlightReservation> uClass) throws SQLException {
        super(uClass);
    }


    protected FlightReservationDaoImpl(ConnectionSource connectionSource, Class<FlightReservation> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    protected FlightReservationDaoImpl(ConnectionSource connectionSource, DatabaseTableConfig<FlightReservation> tableConfig) throws SQLException {
        super(connectionSource, tableConfig);
    }


    public FlightReservationDaoImpl(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, FlightReservation.class);
    }


    /**
     * @param flightReservation
     * @return
     * @throws SQLException
     */
    @Override
    public int create(FlightReservation flightReservation) throws SQLException {
        return super.create(flightReservation);
    }
}
