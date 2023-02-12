package dao;

import travelAgency.airline.Airline;

import java.util.List;

public interface IAirlineDAO extends IBaseDAO<Airline> {
    List<Airline> findAll();
}
