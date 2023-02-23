package travelAgency.trip;

import com.fasterxml.jackson.annotation.JsonProperty;
import travelAgency.airport.Airport;
import travelAgency.airport.AirportLocation;
import travelAgency.flight.Flight;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement(name = "trip")
public class Trip {
    private static int tripId = 0;

    @JsonProperty("tripId")
    private int id;
    @JsonProperty("start")
    private AirportLocation start;
    @JsonProperty("finalDestination")
    private AirportLocation finalDestination;

    @JsonProperty("flights")
    private ArrayList<Flight> flights;

    public Trip() {
        this.id = ++tripId;
    }


    public Trip(Airport start, Airport finalDestination, ArrayList<Flight> flights) {
        this.id = ++tripId;
        this.start = new AirportLocation(start);
        this.finalDestination = new AirportLocation(finalDestination);
        this.flights = flights;
    }

    public Trip(AirportLocation start, AirportLocation finalDestination, ArrayList<Flight> flights) {
        this.id = ++tripId;
        this.start = start;
        this.finalDestination = finalDestination;
        this.flights = flights;
    }

    public Trip(Airport start, Airport finalDestination) {
        this.id = ++tripId;
        this.start = new AirportLocation(start);
        this.finalDestination = new AirportLocation(finalDestination);
        this.flights = new ArrayList<>();
    }

    public Trip(Trip t, Trip t2) {
        this.id = ++tripId;
        this.start = t.getStart();
        this.finalDestination = t2.getFinalDestination();
        this.flights = new ArrayList<>();
        this.flights.addAll(t.getFlights());
        this.flights.addAll(t2.getFlights());
    }

    @XmlAttribute(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id > 0) {
            this.id = id;
        }
    }

    @XmlElement(name = "startLocation")
    public AirportLocation getStart() {
        return start;
    }

    public void setStart(AirportLocation start) {
        this.start = start;
    }

    @XmlElement(name = "finalDestination")
    public AirportLocation getFinalDestination() {
        return finalDestination;
    }

    public void setFinalDestination(AirportLocation finalDestination) {
        this.finalDestination = finalDestination;
    }

    @XmlElementWrapper(name = "flights")
    @XmlElement(name = "flight")
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

    @XmlElement(name = "totalPrice")
    public double getPrice() {
        return this.flights.stream().mapToDouble(Flight::getPrice).sum();
    }

    @XmlElement(name = "totalDistance")
    public double getDistance() {
        return Math.round(this.flights.stream().mapToDouble(Flight::getDistance).sum() * 100.0) / 100.0;
    }

    public void addFlightsOfThisTrip(Trip previousTrip) {
        this.flights.addAll(previousTrip.getFlights());
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Trip{" +
                "id=" + id +
                ", \nstart = " + start +
                ", \nfinalDestination = " + finalDestination +
                ", \nflights = [");
        for (Flight f : this.flights) {
            result.append("\n");
            result.append("\t- ").append(f.toString()).append(", ");
        }
        result.append("], \nprice=" + getPrice());
        result.append(", \ntotal distance=" + getDistance());
        result.append('}');
        return result.toString();
    }
}
