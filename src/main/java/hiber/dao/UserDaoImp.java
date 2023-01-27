package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
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
    @SuppressWarnings("unchecked")
    public List<User> getUsersByCar(String model, int series) {

        TypedQuery<Car> cars = sessionFactory.openSession().createQuery(
                "from Car where model =: model and series =: series ");
        cars.setParameter("model", model);
        cars.setParameter("series", series);

        List<User> users = new ArrayList<>();
        for (Car car: cars.getResultList()) {
            users.add(car.getUser());
        }

        return users;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

}
