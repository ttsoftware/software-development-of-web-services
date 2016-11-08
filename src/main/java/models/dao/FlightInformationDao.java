package models.dao;

import com.j256.ormlite.dao.Dao;
import models.FlightReservation;

import java.sql.SQLException;


public interface FlightInformationDao extends Dao<FlightReservation, Integer> {

    @Override
    int create(FlightReservation flightInformation) throws SQLException;
}
