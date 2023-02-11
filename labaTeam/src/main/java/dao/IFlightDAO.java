package dao;

import travelAgency.flight.Flight;

import java.util.List;

public interface IFlightDAO extends IBaseDAO {
    List<Flight> findAll();
}
