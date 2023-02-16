package travelAgency.flight;

import helpers.DistanceCalculator;
import travelAgency.airport.AirportLocation;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "flight")
@XmlAccessorType(XmlAccessType.FIELD)
public class Flight {
    @XmlAttribute(name = "id")
    private int id;
    @XmlElement(name = "airline")
    private String airline;
    @XmlElement(name = "price")
    private double price;
    @XmlElement(name = "distance")
    private double distance;
    @XmlElement(name = "flightStart")
    private AirportLocation start = new AirportLocation();
    @XmlElement(name = "flightDestination")
    private AirportLocation destination = new AirportLocation();


    public Flight(int id, AirportLocation start, AirportLocation finalDestination, double price) {
        this.id = id;
        this.start = start;
        this.destination = finalDestination;
        this.price = price;
        setDistance();
    }

    public Flight() {

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

    public AirportLocation getDestination() {
        return destination;
    }

    public void setDestination(AirportLocation destination) {
        this.destination = destination;
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
                this.destination.getLatitude(),
                this.destination.getLongitude(),
                'K');
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    @Override
    public String toString() {
        return "Flight{" + "id=" + id +
                ", \n\t\tairline=" + airline +
                ", \n\t\tstart=" + start.getName() + " " + start.getCity() +
                ", \n\t\tfinalDestination=" + destination.getName() + " " + destination.getCity() +
                ", \n\t\tprice=" + price +
                ", \n\t\tdistance=" + Math.round(distance * 100.0) / 100.0 + " Km" +
                '}';
    }

}
