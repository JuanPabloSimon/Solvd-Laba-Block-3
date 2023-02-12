package dao;

import travelAgency.airport.Airport;

import java.util.List;

public interface IAirportDAO extends IBaseDAO<Airport> {
    List<Airport> findAll();
}
