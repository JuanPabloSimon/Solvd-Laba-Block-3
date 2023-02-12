package travelAgency;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import travelAgency.app.Application;

import java.sql.SQLException;

public class Main {
    public static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws SQLException {

        //Services to test if the mappers are working

//        AirportService airportService = new AirportService();
//        LOGGER.info(airportService.findAll());

        Application app = new Application();
        app.run();

//        FlightService flightService = new FlightService();
//        LOGGER.info(flightService.getFlightById(2));
//
//        AirlineService airlineService = new AirlineService();
//        LOGGER.info(airlineService.getAirlineById(2));
    }


}
