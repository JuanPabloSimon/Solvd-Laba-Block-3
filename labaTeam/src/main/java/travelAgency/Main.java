package travelAgency;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.mybatis.AirportService;
import travelAgency.airport.Airport;
import travelAgency.app.Application;
import travelAgency.trip.Trip;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Main {
    public static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws SQLException {

        AirportService airportService = new AirportService();
        //LOGGER.info(airportService.findAll());
        List<Airport> airports = airportService.findAll();

        ArrayList<Trip> tripFromBsAs = airports.get(8).searchRoute(airports.get(0));
        //tripFromBsAs.forEach(LOGGER::info);

        Optional<Airport> buenosAires = airports.stream()
                .filter(a -> a.getCity().equals("Buenos Aires"))
                .findFirst();

        ArrayList<String> possibleDestinys = buenosAires.isPresent() ? buenosAires.get().getPossibleDestinys() : null;

        airports.stream().filter(a -> possibleDestinys.contains(a.getCity())).forEach(a -> LOGGER.info(a.getCity()));

        Application app = new Application();
        app.run();

//        FlightService flightService = new FlightService();
//        LOGGER.info(flightService.getFlightById(2));
//
//        AirlineService airlineService = new AirlineService();
//        LOGGER.info(airlineService.getAirlineById(2));
    }


}
