package models.dao;

import com.j256.ormlite.dao.Dao;
import models.Flight;

import java.sql.SQLException;

public interface FlightDao extends Dao<Flight, Integer> {

    @Override
    int create(Flight flight) throws SQLException;
}