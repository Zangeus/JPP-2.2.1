package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public UserDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(User user, Car car) {
        user.setCar(car);
        sessionFactory.getCurrentSession().persist(user);
        sessionFactory.getCurrentSession().persist(car);
    }

    @Override
    public List<User> getUserByModelAndSeries(String model, int series) {
        List<Car> cars = sessionFactory.openSession().createQuery(
                "from Car where model = '" + model + "' and series = " + series).getResultList();

        List<User> list = new ArrayList<>();
        for (Car car : cars) {
            list.add(car.getUser());
        }
        return list;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

}
