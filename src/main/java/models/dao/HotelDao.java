package models.dao;

import com.j256.ormlite.dao.Dao;
import models.Hotel;

import java.sql.SQLException;

public interface HotelDao extends Dao<Hotel, Integer> {

    @Override
    int create(Hotel hotel) throws SQLException;
}
