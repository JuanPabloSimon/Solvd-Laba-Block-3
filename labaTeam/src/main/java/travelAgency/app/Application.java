package travelAgency.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.mybatis.AirportService;
import travelAgency.airport.Airport;

import java.util.List;
import java.util.Scanner;

public class Application {
    private static final Logger LOGGER = LogManager.getLogger(Application.class);
    private AirportService airportService;
    private List<Airport> destinations;
    private Airport departure;
    private Scanner scanner = new Scanner(System.in);

    public Application() {
        this.airportService = new AirportService();
        this.destinations = airportService.findAll();
    }

    public void run() {
        LOGGER.info("Welcome to FlyAgency¿¿?? \n Please select your place of departure: ");
        for (Airport a : destinations) {
            LOGGER.info("[" + destinations.indexOf(a) + "]. " + a.getName() + ", " + a.getCity() + ", " + a.getCountry());
        }
        int choice = scanner.nextInt();
        LOGGER.info("Select your destination: ");
        selectDeparture(choice);
    }

    public void selectDeparture(int choice) {
        switch (choice) {
            case 0:
                destinations.stream().filter(d -> destinations.indexOf(d) != 0).forEach(e -> LOGGER.info("[" + destinations.indexOf(e) + "]. " + e.getCity()));
                setDeparture(destinations.stream().filter(d -> destinations.indexOf(d) == 0).findAny().orElse(null));
                break;
            case 1:
                destinations.stream().filter(d -> destinations.indexOf(d) != 1).forEach(e -> LOGGER.info("[" + destinations.indexOf(e) + "]. " + e.getCity()));
                setDeparture(destinations.stream().filter(d -> destinations.indexOf(d) == 1).findAny().orElse(null));
                break;
            case 2:
                destinations.stream().filter(d -> destinations.indexOf(d) != 2).forEach(e -> LOGGER.info("[" + destinations.indexOf(e) + "]. " + e.getCity()));
                setDeparture(destinations.stream().filter(d -> destinations.indexOf(d) == 2).findAny().orElse(null));
                break;
            case 3:
                destinations.stream().filter(d -> destinations.indexOf(d) != 3).forEach(e -> LOGGER.info("[" + destinations.indexOf(e) + "]. " + e.getCity()));
                setDeparture(destinations.stream().filter(d -> destinations.indexOf(d) == 3).findAny().orElse(null));
                break;
            case 4:
                destinations.stream().filter(d -> destinations.indexOf(d) != 4).forEach(e -> LOGGER.info("[" + destinations.indexOf(e) + "]. " + e.getCity()));
                setDeparture(destinations.stream().filter(d -> destinations.indexOf(d) == 4).findAny().orElse(null));
                break;
            case 5:
                destinations.stream().filter(d -> destinations.indexOf(d) != 5).forEach(e -> LOGGER.info("[" + destinations.indexOf(e) + "]. " + e.getCity()));
                setDeparture(destinations.stream().filter(d -> destinations.indexOf(d) == 5).findAny().orElse(null));
                break;
            case 6:
                destinations.stream().filter(d -> destinations.indexOf(d) != 6).forEach(e -> LOGGER.info("[" + destinations.indexOf(e) + "]. " + e.getCity()));
                setDeparture(destinations.stream().filter(d -> destinations.indexOf(d) == 6).findAny().orElse(null));
                break;
            case 7:
                destinations.stream().filter(d -> destinations.indexOf(d) != 7).forEach(e -> LOGGER.info("[" + destinations.indexOf(e) + "]. " + e.getCity()));
                setDeparture(destinations.stream().filter(d -> destinations.indexOf(d) == 7).findAny().orElse(null));
                break;
            case 8:
                destinations.stream().filter(d -> destinations.indexOf(d) != 8).forEach(e -> LOGGER.info("[" + destinations.indexOf(e) + "]. " + e.getCity()));
                setDeparture(destinations.stream().filter(d -> destinations.indexOf(d) == 8).findAny().orElse(null));
                break;
            case 9:
                destinations.stream().filter(d -> destinations.indexOf(d) != 9).forEach(e -> LOGGER.info("[" + destinations.indexOf(e) + "]. " + e.getCity()));
                setDeparture(destinations.stream().filter(d -> destinations.indexOf(d) == 9).findAny().orElse(null));
                break;
            default:
                throw new IllegalArgumentException("Parameter out of bounds");
        }
    }

    public Airport getDeparture() {
        return this.departure;
    }

    public void setDeparture(Airport a) {
        this.departure = a;
    }
}
