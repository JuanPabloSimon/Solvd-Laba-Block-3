package travelAgency.trip;

import travelAgency.airport.Airport;
import travelAgency.airport.AirportLocation;
import travelAgency.flight.Flight;

import java.util.ArrayList;

public class Trip {
    private int id;
    private AirportLocation start;
    private AirportLocation finalDestination;
    private ArrayList<Flight> flights;

    public Trip(int id, Airport start, Airport finalDestination, ArrayList<Flight> flights) {
        this.id = id;
        this.start = new AirportLocation(start);
        this.finalDestination = new AirportLocation(finalDestination);
        this.flights = flights;
    }

    public Trip(int id, Airport start, Airport finalDestination) {
        this.id = id;
        this.start = new AirportLocation(start);
        this.finalDestination = new AirportLocation(finalDestination);
        this.flights = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id > 0){
            this.id = id;
        }
    }

    public AirportLocation getStart() {
        return start;
    }

    public void setStart(AirportLocation start) {
        this.start = start;
    }

    public AirportLocation getFinalDestination() {
        return finalDestination;
    }

    public void setFinalDestination(AirportLocation finalDestination) {
        this.finalDestination = finalDestination;
    }

    public ArrayList<Flight> getFlights() {
        return flights;
    }

    public void setFlights(ArrayList<Flight> flights) {
        this.flights = flights;
    }

    public void addFlight(Flight flight) {
        this.flights.add(flight);
    }

    public void removeFlight(Flight flight) {
        this.flights.remove(flight);
    }

    public double getPrice(){
        return this.flights.stream().mapToDouble(Flight::getPrice).sum();
    }

    public double getDistance(){
        return this.flights.stream().mapToDouble(Flight::getDistance).sum();
    }

    public void addFlightsOfThisTrip(Trip previousTrip){
        this.flights.addAll(previousTrip.getFlights());
    }

    @Override
    public String toString() {
        return "Trip{" +
                "id=" + id +
                ", start=" + start +
                ", finalDestination=" + finalDestination +
                ", flights=" + flights +
                '}';
    }
}
