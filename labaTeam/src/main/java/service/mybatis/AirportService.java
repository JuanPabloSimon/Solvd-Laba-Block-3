package service.mybatis;

import dao.IAirportDAO;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import travelAgency.airport.Airport;
import utils.MyBatisDaoFactory;

import java.util.List;

public class AirportService implements IAirportDAO {
    private final static Logger LOGGER = LogManager.getLogger(AirportService.class);
    private static final SqlSessionFactory SESSION_FACTORY = MyBatisDaoFactory.getSqlSessionFactory();


    @Override
    public List<Airport> findAll() {
        try (SqlSession sqlSession = SESSION_FACTORY.openSession()) {
            IAirportDAO airportDAO = sqlSession.getMapper(IAirportDAO.class);
            List<Airport> airports = airportDAO.findAll();
            return airports;
        }
    }

    @Override
    public Airport getEntityById(int id) {
        Airport airport;
        try (SqlSession sqlSession = SESSION_FACTORY.openSession()) {
            IAirportDAO airportDAO = sqlSession.getMapper(IAirportDAO.class);
            airport = airportDAO.getEntityById(id);
        }
        return airport;
    }

    @Override
    public void createEntity(Airport entity) {

    }

    @Override
    public void updateEntity(Airport entity) {

    }

    @Override
    public void removeById(int id) {

    }
}
