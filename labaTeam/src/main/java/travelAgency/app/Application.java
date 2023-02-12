package travelAgency.app;

import service.mybatis.AirportService;
import travelAgency.airport.Airport;

import java.util.List;

public class Application {

    private AirportService airportService;
    private List<Airport> destinations;

    public Application() {
        this.airportService = new AirportService();
        this.destinations = airportService.findAll();
    }

}
