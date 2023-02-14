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

    public ArrayList<Trip> searchAllTrips(){
        ArrayList<Trip> trips = new ArrayList<>();
        trips.addAll(searchDirectTrip());//search direct flight
        trips.addAll(searchOneStopTrip());
        return trips;
    }

    private ArrayList<Trip> searchDirectTrip(){
        return departure.searchRoute(fDestination);
    }

    private ArrayList<Trip> searchOneStopTrip(){
        Set<String> possibleFirstStop = departure.getPossibleDestinations();//search possible first stop
        possibleFirstStop.remove(fDestination.getCity());
        ArrayList<Trip> completeTrip = new ArrayList<Trip>();
        //search for a trip with a stop
        destinations.stream()
                .filter(d -> possibleFirstStop.contains(d.getCity()))
                .forEach(d -> {//You can also go to Your final Destination from:
                    ArrayList<Trip> possibleTripSecondPart = d.searchRoute(fDestination);

                    if (!possibleTripSecondPart.isEmpty()) {
                        ArrayList<Trip> possibleTripFirstPart = departure.searchRoute(d);
                        possibleTripFirstPart.forEach(t -> {
                            possibleTripSecondPart.forEach(t2-> {
                                completeTrip.add(new Trip(t, t2));
                            });
                        });
                    }
                });
        return completeTrip;
    }

    public void selectTypeOfFilter(int choice) {

        ArrayList<Trip> possiblesTrips = searchAllTrips();
        if (!possiblesTrips.isEmpty()) {
            LOGGER.info("\n");
            LOGGER.info("You have this trips options:" + possiblesTrips.size());
            possiblesTrips.forEach(t -> {
                if (t.getFlights().size() < 2){
                    LOGGER.info("Direct");
                } else if (t.getFlights().size() == 2) {
                    LOGGER.info((t.getFlights().size() - 1) + "Stop");
                } else {
                    LOGGER.info("It must not have more that 2 flights, but there's a problem");
                }
            });
            possiblesTrips.forEach(t -> {
                LOGGER.info("\n"+t);
            });
        }else {
            LOGGER.info("No flights founded");
        }

        switch (choice) {
            case 0:
                LOGGER.info("Cheapest Flight Logic");
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
