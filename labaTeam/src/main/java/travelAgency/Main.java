package travelAgency;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.mybatis.AirlineService;
import service.mybatis.AirportService;
import service.mybatis.FlightService;
import utils.ConnectionPool;

import java.sql.SQLException;

/**
 * Hello world!
 */
public class Main {
    public static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws SQLException {
        ConnectionPool.getInstance();

        //Services to test if the mappers are working

//        AirportService airportService = new AirportService();
//        LOGGER.info(airportService.getAirportById(4));

        FlightService flightService = new FlightService();
        LOGGER.info(flightService.getFlightById(2));

        AirlineService airlineService = new AirlineService();
        LOGGER.info(airlineService.getAirlineById(2));
    }


}
