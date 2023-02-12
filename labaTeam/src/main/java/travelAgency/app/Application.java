package travelAgency.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.mybatis.AirportService;
import travelAgency.airport.Airport;

import java.util.List;

public class Application {
    private static final Logger LOGGER = LogManager.getLogger(Application.class);
    private AirportService airportService;
    private List<Airport> destinations;

    public Application() {
        this.airportService = new AirportService();
        this.destinations = airportService.findAll();
    }

    public void run() {
        LOGGER.info("Welcome to FlyAgency¿¿?? \n Please select your place of departure: ");
        int i = 1;
        for (Airport a : destinations) {
            LOGGER.info("[" + destinations.indexOf(a) + "]. " + a.getName() + ", " + a.getCity() + ", " + a.getCountry());
        }
    }

}
