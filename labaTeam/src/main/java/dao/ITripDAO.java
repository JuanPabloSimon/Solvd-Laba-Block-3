package dao;

import travelAgency.trip.Trip;

import java.util.List;

public interface ITripDAO extends IBaseDAO {
    List<Trip> findAll();
}
