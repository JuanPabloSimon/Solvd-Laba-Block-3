package travelAgency;

import travelAgency.app.Application;

import java.sql.SQLException;

public class Main {


    public static void main(String[] args) throws SQLException {
        Application app = new Application();
        app.run();
        //app.createPossibleTripsBetweenTwoCities("Buenos Aires", "Madrid");
    }


}
