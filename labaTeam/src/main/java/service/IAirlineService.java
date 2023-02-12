package service;

import travelAgency.airline.Airline;

import java.sql.SQLException;

public interface IAirlineService {
    Airline getAirlineById(int airlineId) throws SQLException;

    Airline createAirline(Airline newAirline) throws SQLException;
}
