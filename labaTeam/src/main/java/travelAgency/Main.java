package travelAgency;

import travelAgency.app.Application;

import java.io.IOException;
import java.sql.SQLException;

public class Main {


    public static void main(String[] args) throws SQLException, IOException {
        Application app = new Application();
        app.run();
    }


}
