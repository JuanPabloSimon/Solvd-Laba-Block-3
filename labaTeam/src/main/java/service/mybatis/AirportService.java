package service.mybatis;

import dao.IAirportDAO;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.IAirportService;
import travelAgency.airport.Airport;
import utils.MyBatisDaoFactory;

import java.sql.SQLException;

public class AirportService implements IAirportService {
    private final static Logger LOGGER = LogManager.getLogger(AirportService.class);
    private static final SqlSessionFactory SESSION_FACTORY = MyBatisDaoFactory.getSqlSessionFactory();

    @Override
    public Airport getAirportById(int airportId) throws SQLException {
        Airport airport;
        try (SqlSession sqlSession = SESSION_FACTORY.openSession()) {
            IAirportDAO airportDAO = sqlSession.getMapper(IAirportDAO.class);
            airport = airportDAO.getEntityById(airportId);
        }
        return airport;
    }

    @Override
    public Airport createAirport(Airport newAirport) throws SQLException {
        return null;
    }
}
