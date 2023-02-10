package travelAgency;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.ConnectionPool;

/**
 * Hello world!
 */
public class Main {
    public static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        LOGGER.info("Hello Team!!!! Good Change");
        ConnectionPool.getInstance();
    }


}