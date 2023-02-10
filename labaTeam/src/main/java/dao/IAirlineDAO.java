package dao;

import travelAgency.airline.Airline;

import java.util.List;

public interface IAirlineDAO extends IBaseDAO {
    List<Airline> findAll();
}
