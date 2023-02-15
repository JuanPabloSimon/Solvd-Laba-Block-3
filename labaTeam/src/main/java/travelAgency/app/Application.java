package travelAgency.app;

import helpers.Graph;
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
        LOGGER.info("Would you like to see all possible trip options and compare them manually?: \n" +
                "[0]. No \n" +
                "[1]. Yes");
        choice = scanner.nextInt();
        if (choice == 1) {
            printAllTrips();
        }

    }

    private ArrayList<Trip> searchAllTripsWithGraph() {
        ArrayList<ArrayList<String>> paths = createPathWithGraph();
        return buildAllTrips(paths);
    }

    private ArrayList<ArrayList<String>> createPathWithGraph() {
        Graph<String> graph = new Graph<>();

        destinations.forEach(d -> {
            graph.addVertex(d.getCity());
            d.getPossibleDestinations().forEach(c -> {
                graph.addEdge(d.getCity(), c);
            });
        });

        ArrayList<ArrayList<String>> paths = new ArrayList<>();

        Set<String> possibleFirstStop = departure.getPossibleDestinations();//search possible first stop
        if (possibleFirstStop.contains(fDestination.getCity())) {
            ArrayList<String> tmp = new ArrayList<>();
            tmp.add(departure.getCity());
            tmp.add(fDestination.getCity());
            paths.add(tmp);
        }

        Set<String> possibleSecondStop = new HashSet<>();
        Set<String> visited = new HashSet<>();
        visited.add(departure.getCity());
        visited.add(fDestination.getCity());

        possibleFirstStop.forEach(pStop -> {
            visited.add(pStop);
            if (graph.getAdjVertices(pStop).contains(fDestination.getCity())) {
                ArrayList<String> tmp = new ArrayList<>();
                tmp.add(departure.getCity());
                tmp.add(pStop);
                tmp.add(fDestination.getCity());
                paths.add(tmp);
            }
            possibleSecondStop.addAll(graph.getAdjVertices(pStop));
            possibleSecondStop.removeAll(visited);
        });

        possibleFirstStop.forEach(pStop -> {
            possibleSecondStop.forEach(pSecondStop -> {
                if (graph.getAdjVertices(pStop).contains(pSecondStop) && graph.getAdjVertices(pSecondStop).contains(fDestination.getCity())) {
                    ArrayList<String> tmp = new ArrayList<>();
                    tmp.add(departure.getCity());
                    tmp.add(pStop);
                    tmp.add(pSecondStop);
                    tmp.add(fDestination.getCity());
                    paths.add(tmp);
                }
            });
        });

        return paths;
    }

    public ArrayList<Trip> buildAllTrips(ArrayList<ArrayList<String>> paths) {
        LOGGER.info(":::::::PATHS::::");
        paths.forEach(LOGGER::info);
        LOGGER.info(":::::::::::");
        ArrayList<Trip> completeTrips = new ArrayList<>();
        paths.forEach(p -> {
            ArrayList<Trip> trip = getAirport(p.get(0)).searchRoute(getAirport(p.get(1)));//All direct flights
            if (p.size() > 2) {

                for (int i = 1; i < (p.size() - 1); i++) {
                    ArrayList<Trip> possibleTripNextPart = getAirport(p.get(i)).searchRoute(getAirport(p.get(i + 1)));

                    if (!possibleTripNextPart.isEmpty()) {
                        ArrayList<Trip> temp = new ArrayList<Trip>();
                        trip.forEach(t -> {
                            possibleTripNextPart.forEach(t2 -> {
                                temp.add(new Trip(t, t2));
                            });
                        });
                        trip.clear();
                        trip.addAll(temp);
                    }
                }
                completeTrips.addAll(trip);
            } else {
                completeTrips.addAll(trip);
            }
        });
        return completeTrips;
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
        ArrayList<Trip> possiblesTrips = searchAllTrips();
        ArrayList<Trip> possiblesGraphTrips = searchAllTripsWithGraph();
        switch (choice) {
            case 0:
                if (!possiblesGraphTrips.isEmpty()) {
                    possiblesGraphTrips.sort(Comparator.comparing(Trip::getPrice));
                    LOGGER.info("______________________________");
                    LOGGER.info("\nCheaper: " + possiblesGraphTrips.get(0));
                    LOGGER.info("______________________________");
                } else {
                    LOGGER.info("No flights founded");
                }
                break;

            case 1:
                if (!possiblesGraphTrips.isEmpty()) {
                    possiblesGraphTrips.sort(Comparator.comparing(Trip::getDistance));
                    LOGGER.info("______________________________");
                    LOGGER.info("\nShortest: " + possiblesGraphTrips.get(0));
                    LOGGER.info("______________________________");
                } else {
                    LOGGER.info("No flights founded");
                }
                break;

            default:
                throw new IllegalArgumentException("Invalid choice");
        }
    }

    public void printAllTrips() {
        //ArrayList<Trip> possiblesTrips = searchAllTrips();
        ArrayList<Trip> possiblesGraphTrips = searchAllTripsWithGraph();

        if (!possiblesGraphTrips.isEmpty()) {
            LOGGER.info("\n");
            LOGGER.info("You have this trips options:" + possiblesGraphTrips.size());
            possiblesGraphTrips.forEach(t -> {
                if (t.getFlights().size() < 2) {
                    LOGGER.info("- Direct");
                } else {
                    LOGGER.info("- With " + (t.getFlights().size() - 1) + " Stop/s");
                }
            });
            possiblesGraphTrips.forEach(t -> {
                LOGGER.info("\n" + t);
            });
        } else {
            LOGGER.info("No flights founded");
        }
    }

    public ArrayList<Trip> searchAllTrips() {
        ArrayList<Trip> trips = new ArrayList<>();
        trips.addAll(searchDirectTrip());//search direct flight
        trips.addAll(searchOneStopTrip());
        return trips;
    }

    private ArrayList<Trip> searchDirectTrip() {
        return departure.searchRoute(fDestination);
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
