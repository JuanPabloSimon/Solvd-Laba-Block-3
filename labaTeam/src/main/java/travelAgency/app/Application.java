package travelAgency.app;

import helpers.Graph;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.mybatis.AirportService;
import travelAgency.airport.Airport;
import travelAgency.trip.Trip;
import utils.JsonParser;
import utils.XmlParser;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Application {
    private static final Logger LOGGER = LogManager.getLogger(Application.class);
    private static final int MAX_TRIP_STEPS = 4;
    private static final int CLOSE_APP = 99;
    private AirportService airportService;
    private List<Airport> destinations;
    private Airport departure;
    private Airport fDestination;
    private Scanner scanner = new Scanner(System.in);

    public Application() {
        this.airportService = new AirportService();
        this.destinations = airportService.findAll();
    }

    public void run() throws IOException {
        int choice = 0;
        departure = null;
        fDestination = null;
        LOGGER.info("Welcome to Cosmic Obelisk \n Please select your place of departure: ");
        for (Airport a : destinations) {
            LOGGER.info("[" + destinations.indexOf(a) + "]. " + a.getName() + ", " + a.getCity() + ", " + a.getCountry());
        }
        LOGGER.info("\n[" + CLOSE_APP + "]. " + "To close the app");
        choice = scanner.nextInt();
        if (choice == CLOSE_APP)
            return;
        LOGGER.info("Select your destination: ");
        selectDeparture(choice);
        choice = scanner.nextInt();
        if (choice == CLOSE_APP)
            return;
        selectDestination(choice);
        if (departure != null && departure != fDestination & fDestination != null) {
            LOGGER.info("Select how would you like your trip to be calculated: \n" +
                    "[0]. Cheapest Trip \n" +
                    "[1]. Fastest Trip\n" +
                    "[99]. To close the app");
            choice = scanner.nextInt();
            if (choice == CLOSE_APP)
                return;
            selectTypeOfFilter(choice);
        }
        LOGGER.info("Do you want to search another trip?\n" +
                "[0]. Yes\n[99]. No");
        choice = scanner.nextInt();
        if (choice == 0) {
            run();
        }

    }

    private ArrayList<Trip> searchAllTripsWithGraph() {
        ArrayList<List<String>> paths = createPossibleTripsBetweenTwoCities(departure.getCity(), fDestination.getCity());
        paths = paths.stream().filter(p -> p.size() <= MAX_TRIP_STEPS).collect(Collectors.toCollection(ArrayList::new));
        return buildAllTrips(paths);
    }

    public ArrayList<List<String>> createPossibleTripsBetweenTwoCities(String city1, String city2) {
        Graph<String> graph = new Graph<>();
        destinations.forEach(d -> {
            graph.addVertex(d.getCity());
            d.getPossibleDestinations().forEach(c -> {
                graph.addEdge(d.getCity(), c);
            });
        });

        ArrayList<List<String>> paths = new ArrayList<>();
        List<String> path = new ArrayList<>();
        // Call recursive utility
        Set<String> visited = new HashSet<>();
        printAllPathsUtil(city1, city2, visited, paths, path, graph);
        return paths;
    }

    private void printAllPathsUtil(String city1, String city2, Set<String> visited, ArrayList<List<String>> paths, List<String> path, Graph<String> graph) {
        if (city1.equals(city2)) {
            LinkedList<String> path1 = new LinkedList<>(path);
            path1.addFirst(departure.getCity());
            paths.add(path1);
            return;
        }
        visited.add(city1);
        for (Object c : graph.getAdjVertices(city1)) {
            String d = (String) c;
            if (!visited.contains(d)) {
                path.add(d);
                printAllPathsUtil(d, city2, visited, paths, path, graph);
                path.remove(d);
            }
        }
        visited.remove(city1);
    }

    public ArrayList<Trip> buildAllTrips(ArrayList<List<String>> paths) {
        ArrayList<Trip> completeTrips = new ArrayList<>();
        paths.forEach(p -> {
            ArrayList<Trip> trip = getAirport(p.get(0)).searchRoute(getAirport(p.get(1))); //All direct flights
            if (p.size() > 2) {
                p.forEach(t -> {
                    if (p.indexOf(t) != (p.size() - 1) && p.indexOf(t) != 0) {
                        ArrayList<Trip> possibleTripNextPart = getAirport(p.get(p.indexOf(t))).searchRoute(getAirport(p.get(p.indexOf(t) + 1)));
                        if (!possibleTripNextPart.isEmpty()) {
                            ArrayList<Trip> temp = new ArrayList<Trip>();
                            trip.forEach(ts -> {
                                possibleTripNextPart.forEach(t2 -> {
                                    temp.add(new Trip(ts, t2));
                                });
                            });
                            trip.clear();
                            trip.addAll(temp);
                        }
                    }
                });
                completeTrips.addAll(trip);
            } else {
                completeTrips.addAll(trip);
            }
        });
        return completeTrips;
    }

    public void selectDeparture(int choice) throws IOException {
        if (choice >= 0 && choice < destinations.size()) {
            destinations.stream().filter(d -> destinations.indexOf(d) != choice).forEach(e -> LOGGER.info("[" + destinations.indexOf(e) + "]. " + e.getCity()));
            LOGGER.info("\n[" + 99 + "]. " + "To close the app");
            setDeparture(destinations.stream().filter(d -> destinations.indexOf(d) == choice).findAny().orElse(null));
        } else {
            LOGGER.info("\n~~~~~~Illegal destination choice, restarting the app~~~~~~\n");
            run();
        }
    }

    public void selectDestination(int choice) throws IOException {
        if (choice >= 0 && choice < destinations.size() && choice != destinations.indexOf(departure)) {
            destinations.stream().filter(d -> destinations.indexOf(d) == choice).forEach(e -> LOGGER.info("Destination Selected: " + e.getCity()));
            setfDestination(destinations.stream().filter(d -> destinations.indexOf(d) == choice).findAny().orElse(null));
        } else {
            LOGGER.info("\n~~~~~~Illegal destination choice, restarting the app~~~~~~\n");
            run();
        }
    }

    public void selectTypeOfFilter(int choice) throws IOException {
        ArrayList<Trip> possiblesGraphTrips = searchAllTripsWithGraph();
        if (possiblesGraphTrips.isEmpty()) {
            LOGGER.info("No flights founded");
        } else {
            LOGGER.info("______________________________");
            switch (choice) {
                case 0:
                    possiblesGraphTrips.sort(Comparator.comparing(Trip::getPrice));
                    possiblesGraphTrips.get(0).getFlights().forEach(f -> {
                        LOGGER.info("- Take flight " + f.getId() + " of " + f.getAirline() + " at " + f.getStart().getName() + " to get to " +
                                f.getDestination().getName());
                    });
                    LOGGER.info("- You achieved your final destination :)");
                    JsonParser.getTripInJsonFormat(possiblesGraphTrips.get(0));
                    XmlParser.marshall(possiblesGraphTrips.get(0), "labaTeam/src/main/resources/xml/trip.xml");
                    break;
                case 1:
                    possiblesGraphTrips.sort(Comparator.comparing(Trip::getDistance));
                    possiblesGraphTrips.get(0).getFlights().forEach(f -> {
                        LOGGER.info("- Take flight" + f.getId() + " of " + f.getAirline() + " at " + f.getStart().getName() + " to get to " +
                                f.getDestination().getName());
                    });
                    LOGGER.info("- You achieved your final destination :)");
                    JsonParser.getTripInJsonFormat(possiblesGraphTrips.get(0));
                    XmlParser.marshall(possiblesGraphTrips.get(0), "labaTeam/src/main/resources/xml/trip.xml");
                    break;
                default:
                    LOGGER.info("\n~~~~~~Illegal Filter choice, restarting the app~~~~~~\n");
            }
            LOGGER.info("______________________________");
        }
    }

    private ArrayList<Trip> searchOneStopTrip() {
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
                            possibleTripSecondPart.forEach(t2 -> {
                                completeTrip.add(new Trip(t, t2));
                            });
                        });
                    }
                });
        return completeTrip;
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

    public Airport getAirport(String airport) {
        return destinations.stream().filter(d -> airport.equals(d.getCity())).findFirst().orElse(null);
    }

}
