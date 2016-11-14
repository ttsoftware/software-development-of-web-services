package models.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;
import models.Itinerary;

import java.sql.SQLException;

/**
 * Created by troels on 11/14/16.
 */
public class ItineraryDaoImpl extends BaseDaoImpl<Itinerary, Integer> implements ItineraryDao {

    protected ItineraryDaoImpl(Class<Itinerary> dataClass) throws SQLException {
        super(dataClass);
    }

    protected ItineraryDaoImpl(ConnectionSource connectionSource, Class<Itinerary> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    protected ItineraryDaoImpl(ConnectionSource connectionSource, DatabaseTableConfig<Itinerary> tableConfig) throws SQLException {
        super(connectionSource, tableConfig);
    }

    public ItineraryDaoImpl(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, Itinerary.class);
    }


    /**
     * @param itinerary
     * @return
     * @throws SQLException
     */
    @Override
    public int create(Itinerary itinerary) throws SQLException {
        return super.create(itinerary);
    }
}
