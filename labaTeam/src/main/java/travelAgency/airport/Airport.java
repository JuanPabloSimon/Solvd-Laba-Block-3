package travelAgency.airport;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import travelAgency.airline.Airline;
import travelAgency.app.Application;
import travelAgency.flight.Flight;
import travelAgency.trip.Trip;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Airport {
    private static final Logger LOGGER = LogManager.getLogger(Airport.class);

    private int id;
    private String name;
    private String country;
    private String city;
    private ArrayList<Airline> airlines;
    private double latitude;
    private double longitude;


    public Airport() {
    }


    public Airport(int id, String name, String country, String city, double latitude, double longitude) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id > 0) {
            this.id = id;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public ArrayList<Airline> getAirlines() {
        return airlines;
    }

    public void setAirlines(ArrayList<Airline> airlines) {
        this.airlines = airlines;
    }

    public void addAirline(Airline airline) {
        if (!airlines.contains(airline)) {
            this.airlines.add(airline);
        }
    }

    public void removeAirline(Airline airline) {
        this.airlines.remove(airline);
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public ArrayList<Trip> searchRoute(Airport destination){//gives you a direct trip to the destination
        ArrayList<Trip> possibleTrips = new ArrayList<>();
        for (Airline a: this.airlines) {
            for (Flight f : a.canFlyMeTo(destination)) {
                possibleTrips.add(new Trip(this, destination));
                possibleTrips.get(possibleTrips.size() - 1).addFlight(f);
            }
        }
        return possibleTrips;
    }

    public Set<String> getPossibleDestinations(){//This looks for direct flights
        Set<String> possibleDestinations2 = new HashSet<>();
        for (Airline a: this.airlines) {
            possibleDestinations2.addAll(a.getPossibleDestinations());
        }
        return possibleDestinations2;
    }



    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Airport{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", airlines=[");
        if (this.airlines != null) {
            for (Airline a : this.airlines) {
                result.append("\n");
                result.append(a.toString()).append(", ");
            }
        }
        result.append("\n], \nlatitude=").append(latitude);
        result.append("\n], \nlongitude=").append(longitude).append("\n}");
        return result.toString();
    }
}
