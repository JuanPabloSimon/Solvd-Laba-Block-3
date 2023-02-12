package service.mybatis;

import dao.IAirlineDAO;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import travelAgency.airline.Airline;
import utils.MyBatisDaoFactory;

import java.sql.SQLException;
import java.util.List;

public class AirlineService implements IAirlineDAO {
    private final static Logger LOGGER = LogManager.getLogger(AirlineService.class);
    private static final SqlSessionFactory SESSION_FACTORY = MyBatisDaoFactory.getSqlSessionFactory();


    public Airline getAirlineById(int airlineId) throws SQLException {
        Airline airline;
        try (SqlSession sqlSession = SESSION_FACTORY.openSession()) {
            IAirlineDAO airlineDAO = sqlSession.getMapper(IAirlineDAO.class);
            airline = airlineDAO.getEntityById(airlineId);
        }
        return airline;
    }


    public Airline createAirline(Airline newAirline) throws SQLException {
        return null;
    }

    @Override
    public List<Airline> findAll() {
        return null;
    }

    @Override
    public Airline getEntityById(int id) {
        return null;
    }

    @Override
    public void createEntity(Airline entity) {

    }

    @Override
    public void updateEntity(Airline entity) {

    }

    @Override
    public void removeById(int id) {

    }
}
