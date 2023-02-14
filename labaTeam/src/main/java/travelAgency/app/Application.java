package travelAgency.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.mybatis.AirportService;
import travelAgency.airport.Airport;
import travelAgency.trip.Trip;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Application {
    private static final Logger LOGGER = LogManager.getLogger(Application.class);
    private AirportService airportService;
    private List<Airport> destinations;
    private Airport departure;
    private Airport destination;
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
        choice = scanner.nextInt();
        selectDestination(choice);
        LOGGER.info("Select how would you like your trip to be calculated: \n" +
                "[0]. Cheapest Trip \n" +
                "[1]. Fastest Trip");
        choice = scanner.nextInt();
        selectTypeOfFilter(choice);
    }

    public void selectDeparture(int choice) {
        if (choice >= 0 && choice < destinations.size()) {
            destinations.stream().filter(d -> destinations.indexOf(d) != choice).forEach(e -> LOGGER.info("[" + destinations.indexOf(e) + "]. " + e.getCity()));
            setDeparture(destinations.stream().filter(d -> destinations.indexOf(d) == choice).findAny().orElse(null));
        } else {
            throw new IllegalArgumentException("Parameter out of bounds");
        }
    }

    public void selectDestination(int choice) {
        if (choice >= 0 && choice < destinations.size()) {
            destinations.stream().filter(d -> destinations.indexOf(d) == choice).forEach(e -> LOGGER.info("Destination Selected: " + e.getCity()));
            setDestination(destinations.stream().filter(d -> destinations.indexOf(d) == choice).findAny().orElse(null));
        } else {
            throw new IllegalArgumentException("Parameter out of bounds");
        }
    }

    public void selectTypeOfFilter(int choice) {
        switch (choice) {
            case 0:
                LOGGER.info("Cheapest Flight Logic");
                ArrayList<Trip> possiblesTrips = departure.searchRoute(destination);
                if (!possiblesTrips.isEmpty()) {
                    possiblesTrips.forEach(t -> {
                        LOGGER.info("\n"+t);
                    });
                }else {
                    LOGGER.info("No direct flight found");
                }
                break;
            case 1:
                LOGGER.info("Shortest Flight Logic");
                break;
            default:
                throw new IllegalArgumentException("Invaled choice");
        }
    }

    public Airport getDestination() {
        return this.destination;
    }

    public void setDestination(Airport a) {
        this.destination = a;
    }

    public Airport getDeparture() {
        return this.departure;
    }

    public void setDeparture(Airport a) {
        this.departure = a;
    }
}
