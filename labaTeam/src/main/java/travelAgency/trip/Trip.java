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

    public Trip(int id, AirportLocation start, AirportLocation finalDestination, ArrayList<Flight> flights) {
        this.id = id;
        this.start = start;
        this.finalDestination = finalDestination;
        this.flights = flights;
    }

    public Trip(int id, Airport start, Airport finalDestination) {
        this.id = id;
        this.start = new AirportLocation(start);
        this.finalDestination = new AirportLocation(finalDestination);
        this.flights = new ArrayList<>();
    }

    public Trip(Trip t, Trip t2) {
        this.id = t.getId();
        this.start = t.getStart();
        this.finalDestination = t2.getFinalDestination();
        this.flights = new ArrayList<>();
        this.flights.addAll(t.getFlights());
        this.flights.addAll(t2.getFlights());
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
        return Math.round(this.flights.stream().mapToDouble(Flight::getDistance).sum()*100.0)/100.0;
    }

    public void addFlightsOfThisTrip(Trip previousTrip){
        this.flights.addAll(previousTrip.getFlights());
    }

    @Override
    public String toString() {
        return "Trip{" +
                "id=" + id +
                ", \nstart=" + start +
                ", \nfinalDestination=" + finalDestination +
                ", \nflights=" + flights +
                ", \nprice=" + getPrice() +
                ", \ntotal distance=" + getDistance() +
                '}';
    }
}
