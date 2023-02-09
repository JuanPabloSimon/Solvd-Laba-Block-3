package travelAgency.airport;

import travelAgency.airline.Airline;

import java.util.ArrayList;

public class Airport {
    private int id;
    private String country;
    private String city;
    private ArrayList<Airline> airlines;
    private double latitude;
    private double longitude;


    public Airport(int id, String country, String city, double latitude, double longitude) {
        this.id = id;
        this.country = country;
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id > 0){
            this.id = id;
        }
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

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Airport{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", airlines=[");
        for (Airline a : this.airlines) {
            result.append("\n");
            result.append(a.toString()).append(", ");
        }
        result.append("\n], \ncoordinates=").append(latitude).append("\n}");
        return result.toString();
    }
}
