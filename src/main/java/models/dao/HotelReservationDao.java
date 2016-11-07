package models.dao;

import com.j256.ormlite.dao.Dao;
import models.HotelReservation;

import java.sql.SQLException;

public interface HotelReservationDao extends Dao<HotelReservation, Integer> {

    @Override
    int create(HotelReservation hotelReservation) throws SQLException;
}
