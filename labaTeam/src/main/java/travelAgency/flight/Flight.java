package travelAgency.flight;

import helpers.DistanceCalculator;
import travelAgency.airport.Airport;
import travelAgency.airport.AirportLocation;

public class Flight {
    private int id;
    private AirportLocation start;
    private AirportLocation finalDestination;
    private double price;
    private double distance;

    public Flight(int id, AirportLocation start, AirportLocation finalDestination, double price) {
        this.id = id;
        this.start = start;
        this.finalDestination = finalDestination;
        this.price = price;
        setDistance();
    }

    public Flight(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id > 0) {
            this.id = id;
        }
    }

    public AirportLocation getStart() {
        return start;
    }

    public void setStart(AirportLocation start) {
        this.start = start;
        setDistance();
    }

    public AirportLocation getFinalDestination() {
        return finalDestination;
    }

    public void setFinalDestination(AirportLocation finalDestination) {
        this.finalDestination = finalDestination;
        setDistance();
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price > 0) {
            this.price = price;
        }
    }

    public double getDistance() {
        return distance;
    }

    private void setDistance() {
        this.distance = DistanceCalculator.distance(this.start.getLatitude(),
                this.start.getLongitude(),
                this.finalDestination.getLatitude(),
                this.finalDestination.getLongitude(),
                'K');
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", \nstart=" + start.getName() + " " + start.getCity() +
                ", \nfinalDestination=" + finalDestination.getName() + " " + finalDestination.getCity() +
                ", \nprice=" + price +
                ", \ndistance=" + distance +
                '}';
    }
}
