package travelAgency.flight;

import helpers.DistanceCalculator;
import travelAgency.airport.Airport;

public class Flight {
    private int id;
    private Airport start;
    private Airport finalDestination;
    private double price;
    private double distance;

    public Flight(int id, Airport start, Airport finalDestination, double price) {
        this.id = id;
        this.start = start;
        this.finalDestination = finalDestination;
        this.price = price;
        setDistance();
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
        setDistance();
    }

    public Airport getFinalDestination() {
        return finalDestination;
    }

    public void setFinalDestination(Airport finalDestination) {
        this.finalDestination = finalDestination;
        setDistance();
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price > 0){
            this.price = price;
        }
    }

    public double getDistance() {
        return distance;
    }

    private void setDistance(){
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
                ", start=" + start +
                ", finalDestination=" + finalDestination +
                ", price=" + price +
                '}';
    }
}
