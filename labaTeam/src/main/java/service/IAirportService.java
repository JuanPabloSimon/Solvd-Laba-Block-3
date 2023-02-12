package service;

import travelAgency.airport.Airport;

import java.sql.SQLException;

public interface IAirportService {
    Airport getAirportById(int airportId) throws SQLException;

    Airport createAirport(Airport newAirport) throws SQLException;
}
