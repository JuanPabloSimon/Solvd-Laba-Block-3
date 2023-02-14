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
        try (SqlSession sqlSession = SESSION_FACTORY.openSession()) {
            IAirportDAO airportDAO = sqlSession.getMapper(IAirportDAO.class);

            try {
                airportDAO.createEntity(entity);

                sqlSession.commit();
            } catch (Exception e) {
                sqlSession.rollback();
                LOGGER.error(e.getMessage(), e);
            }

        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public void updateEntity(Airport entity) {
        try (SqlSession sqlSession = SESSION_FACTORY.openSession()) {
            IAirportDAO airportDAO = sqlSession.getMapper(IAirportDAO.class);
            try {
                airportDAO.updateEntity(entity);
                sqlSession.commit();
                LOGGER.info("Airport Updated successfully");
            } catch (Exception e) {
                LOGGER.info("Error Updating");
                sqlSession.rollback();
                LOGGER.info("Session rollback");
            }
        }
    }

    @Override
    public void removeById(int id) {
        try (SqlSession sqlSession = SESSION_FACTORY.openSession()) {
            if (id > 0) {
                IAirportDAO airportDAO = sqlSession.getMapper(IAirportDAO.class);
                airportDAO.removeById(id);
                LOGGER.info("Airport Deleted");
                sqlSession.commit();
            }
        } catch (Exception e) {
            LOGGER.info("Error Deleting");
        }
    }
}
