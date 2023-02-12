package travelAgency.airline;

import travelAgency.airport.Airport;
import travelAgency.flight.Flight;

import java.util.ArrayList;

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

    public Flight canFlyMeTo(Airport destination) {
        for (Flight flight: this.flights) {
            if (flight.getFinalDestination().getCity().equals(destination.getCity()) &&
                    flight.getFinalDestination().getName().equals(destination.getName()))   {
                return flight;
            }
        }
        return null;
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
