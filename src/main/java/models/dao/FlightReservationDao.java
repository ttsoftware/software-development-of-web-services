package models.dao;

import com.j256.ormlite.dao.Dao;
import models.FlightReservation;

import java.sql.SQLException;


public interface FlightReservationDao extends Dao<FlightReservation, Integer> {

    @Override
    int create(FlightReservation flightReservation) throws SQLException;
}
