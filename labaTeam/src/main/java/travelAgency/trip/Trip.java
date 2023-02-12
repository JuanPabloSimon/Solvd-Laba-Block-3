package travelAgency.trip;

import travelAgency.airport.Airport;
import travelAgency.flight.Flight;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class Trip {
    private int id;
    private Airport start;
    private Airport finalDestination;
    private ArrayList<Flight> flights;

    public Trip(int id, Airport start, Airport finalDestination, ArrayList<Flight> flights) {
        this.id = id;
        this.start = start;
        this.finalDestination = finalDestination;
        this.flights = flights;
    }

    public Trip(int id, Airport start, Airport finalDestination) {
        this.id = id;
        this.start = start;
        this.finalDestination = finalDestination;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id > 0){
            this.id = id;
        }
    }

    public Airport getStart() {
        return start;
    }

    public void setStart(Airport start) {
        this.start = start;
    }

    public Airport getFinalDestination() {
        return finalDestination;
    }

    public void setFinalDestination(Airport finalDestination) {
        this.finalDestination = finalDestination;
    }

    public ArrayList<Flight> getFlights() {
        return flights;
    }

    public void setFlights(ArrayList<Flight> flights) {
        this.flights = flights;
    }

    public void addFlight(Flight flight) {
        if (!flights.contains(flight)) {
            this.flights.add(flight);
        }
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
}
