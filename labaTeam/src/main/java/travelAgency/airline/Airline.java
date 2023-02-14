package travelAgency.airline;

import travelAgency.airport.Airport;
import travelAgency.flight.Flight;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Airline {
    private int id;
    private String name;
    private ArrayList<Flight> flights;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Flight> getFlights() {
        return flights;
    }

    public void setFlights(ArrayList<Flight> flights) {
        this.flights = flights;
    }

    public ArrayList<Flight> canFlyMeTo(Airport destination) {//All the flights that can fly you to the destination
        ArrayList<Flight> flights = new ArrayList<>();
        for (Flight flight: this.flights) {
            if (flight.getFinalDestination().getCity().equals(destination.getCity()) &&
                    flight.getFinalDestination().getName().equals(destination.getName()))   {
                flights.add(flight);
            }
        }
        return flights;
    }


    public Set<String> getPossibleDestinations() {
        Set<String> possibleDestinations = new HashSet<>();
        for (Flight flight: this.flights) {//This looks for direct flights
            possibleDestinations.add(flight.getFinalDestination().getCity());
        }
        return possibleDestinations;
    }




    @Override
    public String toString() {
        return "Airline{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", flights=" + flights +
                '}';
    }



}
