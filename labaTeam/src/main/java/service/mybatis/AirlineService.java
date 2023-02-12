package service.mybatis;

import dao.IAirlineDAO;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.IAirlineService;
import travelAgency.airline.Airline;
import utils.MyBatisDaoFactory;

import java.sql.SQLException;

public class AirlineService implements IAirlineService {
    private final static Logger LOGGER = LogManager.getLogger(AirlineService.class);
    private static final SqlSessionFactory SESSION_FACTORY = MyBatisDaoFactory.getSqlSessionFactory();

    @Override
    public Airline getAirlineById(int airlineId) throws SQLException {
        Airline airline;
        try (SqlSession sqlSession = SESSION_FACTORY.openSession()) {
            IAirlineDAO airlineDAO = sqlSession.getMapper(IAirlineDAO.class);
            airline = airlineDAO.getEntityById(airlineId);
        }
        return airline;
    }

    @Override
    public Airline createAirline(Airline newAirline) throws SQLException {
        return null;
    }
}
