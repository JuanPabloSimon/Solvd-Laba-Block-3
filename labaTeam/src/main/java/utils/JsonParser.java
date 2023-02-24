package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import travelAgency.trip.Trip;

import java.io.FileWriter;
import java.io.IOException;

public class JsonParser {
    private static final Logger LOGGER = LogManager.getLogger(JsonParser.class);

    public static void getTripInJsonFormat(Trip trip, String filePath) {
        try {
            ObjectMapper om = new ObjectMapper();
            om.enable(SerializationFeature.INDENT_OUTPUT);
            // serialize the trip object to JSON string
            String json = om.writeValueAsString(trip);
            // save the JSON string to a file
            FileWriter fileWriter = new FileWriter(filePath);
            fileWriter.write(json);
            fileWriter.close();
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

}
