package models.dao;

import com.j256.ormlite.dao.Dao;
import models.Itinerary;

import java.sql.SQLException;

/**
 * Created by troels on 11/14/16.
 */
public interface ItineraryDao extends Dao<Itinerary, Integer> {

    @Override
    int create(Itinerary itinerary) throws SQLException;
}

