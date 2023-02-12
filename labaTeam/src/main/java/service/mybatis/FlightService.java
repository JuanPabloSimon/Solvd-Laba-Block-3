package service.mybatis;

import dao.IFlightDAO;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import travelAgency.flight.Flight;
import utils.MyBatisDaoFactory;

import java.sql.SQLException;
import java.util.List;

public class FlightService implements IFlightDAO {

    private final static Logger LOGGER = LogManager.getLogger(FlightService.class);
    private static final SqlSessionFactory SESSION_FACTORY = MyBatisDaoFactory.getSqlSessionFactory();


    public Flight getFlightById(int flightId) throws SQLException {
        Flight flight;
        try (SqlSession sqlSession = SESSION_FACTORY.openSession()) {
            IFlightDAO flightDAO = sqlSession.getMapper(IFlightDAO.class);
            flight = flightDAO.getEntityById(flightId);
        }
        return flight;

    }


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

    @Override
    public Flight getEntityById(int id) {
        return null;
    }

    @Override
    public void createEntity(Flight entity) {

    }

    @Override
    public void updateEntity(Flight entity) {

    }

    @Override
    public void removeById(int id) {

    }

    @Override
    public List<Flight> findAll() {
        return null;
    }
}
