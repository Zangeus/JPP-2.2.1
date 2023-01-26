package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        userService.add(
                new User("Bonjin", "Unknown", "user!@mail.net"),
                new Car("UWU_the_one", 1));
        userService.add(
                new User("Bonjin", "Unknown", "user!@mail.net"),
                new Car("URU_the_two", 0));
        userService.add(
                new User("Bonjin", "Unknown", "user!@mail.net"),
                new Car("UNU_the_three", 0));
        userService.add(
                new User("Bonjin", "Unknown", "user!@mail.net"),
                new Car("UBU_the_four", 0));


        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Пользователь под номером " + user.getId());
            System.out.println("Имя: " + user.getFirstName());
            System.out.println("Фамилия: " + user.getLastName());
            System.out.println("Email: " + user.getEmail());
            System.out.println("Машина: " + user.getCar().getModel() +
                    ", серии - " + user.getCar().getSeries());
            System.out.println("-----------------------------------------------");
        }

        for (User u: userService.getUsersByModelAndSeries("UWU_the_one", 1)) {
            System.out.println(u);
        }

        context.close();
    }
}
