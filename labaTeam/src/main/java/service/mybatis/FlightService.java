package service.mybatis;

import dao.IFlightDAO;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.IFlightService;
import travelAgency.flight.Flight;
import utils.MyBatisDaoFactory;

import java.sql.SQLException;

public class FlightService implements IFlightService {

    private final static Logger LOGGER = LogManager.getLogger(FlightService.class);
    private static final SqlSessionFactory SESSION_FACTORY = MyBatisDaoFactory.getSqlSessionFactory();

    @Override
    public Flight getFlightById(int flightId) throws SQLException {
        Flight flight;
        try (SqlSession sqlSession = SESSION_FACTORY.openSession()) {
            IFlightDAO flightDAO = sqlSession.getMapper(IFlightDAO.class);
            flight = flightDAO.getEntityById(flightId);
        }
        return flight;

    }

    @Override
    public Flight createFlight(Flight newFlight) throws SQLException {
        try (SqlSession sqlSession = SESSION_FACTORY.openSession()) {
            IFlightDAO flightDAO = sqlSession.getMapper(IFlightDAO.class);

            try {
                flightDAO.createEntity(newFlight);

                sqlSession.commit();
            } catch (Exception e) {
                sqlSession.rollback();
                LOGGER.error(e.getMessage(), e);
            }

        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return newFlight;
    }
}
