package travelAgency;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.mybatis.AirportService;

import travelAgency.airport.Airport;
import travelAgency.trip.Trip;
import utils.ConnectionPool;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws SQLException {
        ConnectionPool.getInstance();

        //Services to test if the mappers are working

        AirportService airportService = new AirportService();
        LOGGER.info(airportService.findAll());
        List<Airport> airports = airportService.findAll();

        ArrayList<Trip> tripFromBsAs = airports.get(8).searchRoute(airports.get(0));
        tripFromBsAs.forEach(LOGGER::info);


//        FlightService flightService = new FlightService();
//        LOGGER.info(flightService.getFlightById(2));
//
//        AirlineService airlineService = new AirlineService();
//        LOGGER.info(airlineService.getAirlineById(2));
    }


}
