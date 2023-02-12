package travelAgency.airport;

public class AirportLocation {
    private String name;
    private String country;
    private String city;
    private double latitude;
    private double longitude;

    public AirportLocation() {

    }

    public AirportLocation(String name, String country, String city, double latitude, double longitude) {
        this.name = name;
        this.country = country;
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public AirportLocation(Airport airport){
        this.name = airport.getName();
        this.country = airport.getCountry();
        this.city = airport.getCity();
        this.latitude = airport.getLatitude();
        this.longitude = airport.getLongitude();
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
        return "AirportLocation{" +
                "name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}









