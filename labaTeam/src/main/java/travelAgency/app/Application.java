package travelAgency.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.mybatis.AirportService;
import travelAgency.airport.Airport;
import travelAgency.trip.Trip;

import java.util.*;

public class Application {
    private static final Logger LOGGER = LogManager.getLogger(Application.class);
    private AirportService airportService;
    private List<Airport> destinations;
    private Airport departure;
    private Airport fDestination;
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
            setfDestination(destinations.stream().filter(d -> destinations.indexOf(d) == choice).findAny().orElse(null));
        } else {
            throw new IllegalArgumentException("Parameter out of bounds");
        }
    }

    public void selectTypeOfFilter(int choice) {
        switch (choice) {
            case 0:
                LOGGER.info("Cheapest Flight Logic");
                ArrayList<Trip> possiblesTrips = departure.searchRoute(fDestination);//search direct flight


                Set<String> possibleFirstStop = departure.getPossibleDestinations();//search possible first stop
                possibleFirstStop.remove(fDestination.getCity());

                //search for a trip with a stop
                destinations.stream()
                        .filter(d -> possibleFirstStop.contains(d.getCity()))
                        .forEach(d -> {
//                            LOGGER.info("You can also go to Paris from:");
//                            LOGGER.info(d.getCity());
                            ArrayList<Trip> possiblesTripsOneStop = d.searchRoute(fDestination);
                            if (!possiblesTripsOneStop.isEmpty()) {
                                ArrayList<Trip> possiblesPreviousTripsOneStop = departure.searchRoute(d);
                                possiblesPreviousTripsOneStop.forEach(t -> {
                                    possiblesTripsOneStop.forEach(t2-> {
                                        t.addFlightsOfThisTrip(t2);
                                        t.setFinalDestination(t2.getFinalDestination());
                                    });
                                });
                                possiblesTrips.addAll(possiblesPreviousTripsOneStop);
                            }
                        });

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

    public Airport getfDestination() {
        return this.fDestination;
    }

    public void setfDestination(Airport a) {
        this.fDestination = a;
    }

    public Airport getDeparture() {
        return this.departure;
    }

    public void setDeparture(Airport a) {
        this.departure = a;
    }
}
