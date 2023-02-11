package travelAgency;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.mybatis.FlightService;
import utils.ConnectionPool;

import java.sql.SQLException;

/**
 * Hello world!
 */
public class Main {
    public static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws SQLException {
        LOGGER.info("Hello Team!!!! Good Change");
        ConnectionPool.getInstance();

        //Test Service
        FlightService flightService = new FlightService();
        LOGGER.info(flightService.getFlightById(2));
    }


}
