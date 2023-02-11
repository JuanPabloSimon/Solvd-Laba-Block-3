package service;

import travelAgency.flight.Flight;

import java.sql.SQLException;

public interface IFlightService {
    Flight getFlightById(int flightId) throws SQLException;

    Flight createFlight(Flight newFlight) throws SQLException;


}
