package models.dao;

import com.j256.ormlite.dao.Dao;
import models.Booking;

import java.sql.SQLException;

/**
 * Created by troels on 11/14/16.
 */
public interface BookingDao extends Dao<Booking, Integer> {

    @Override
    int create(Booking booking) throws SQLException;
}
