package dao;

import travelAgency.airport.Airport;

import java.util.List;

public interface IAirportDAO extends IBaseDAO {
    List<Airport> findAll();
}
