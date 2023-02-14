package service.mybatis;

import dao.IAirlineDAO;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import travelAgency.airline.Airline;
import utils.MyBatisDaoFactory;

import java.util.List;

public class AirlineService implements IAirlineDAO {
    private final static Logger LOGGER = LogManager.getLogger(AirlineService.class);
    private static final SqlSessionFactory SESSION_FACTORY = MyBatisDaoFactory.getSqlSessionFactory();

    @Override
    public List<Airline> findAll() {
        return null;
    }

    @Override
    public Airline getEntityById(int id) {
        Airline airline;
        try (SqlSession sqlSession = SESSION_FACTORY.openSession()) {
            IAirlineDAO airlineDAO = sqlSession.getMapper(IAirlineDAO.class);
            airline = airlineDAO.getEntityById(id);
        }
        return airline;
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
