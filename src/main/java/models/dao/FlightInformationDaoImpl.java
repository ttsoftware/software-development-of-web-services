package models.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;
import models.FlightReservation;

import java.sql.SQLException;

/**
 * Created by troels on 11/7/16.
 */
public class FlightInformationDaoImpl extends BaseDaoImpl<FlightReservation, Integer> implements FlightInformationDao  {
    protected FlightInformationDaoImpl(Class<FlightReservation> dataClass) throws SQLException {
        super(dataClass);
    }

    protected FlightInformationDaoImpl(ConnectionSource connectionSource, Class<FlightReservation> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    protected FlightInformationDaoImpl(ConnectionSource connectionSource, DatabaseTableConfig<FlightReservation> tableConfig) throws SQLException {
        super(connectionSource, tableConfig);
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
