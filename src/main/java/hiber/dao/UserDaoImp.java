package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
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
    public List<User> getUsersByCar(String model, int series) {

        TypedQuery<User> users = sessionFactory.openSession().createQuery(
                "from User u where u.car.model =: model and u.car.series =: series", User.class);
        users.setParameter("model", model);
        users.setParameter("series", series);

        return users.getResultList();
    }



    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }
}
