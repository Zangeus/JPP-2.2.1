package hiber.model;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    private final String defaultValue = "";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @OneToOne
    @JoinColumn(name = "car_id")
    private Car car;

    public User() {
        firstName = defaultValue;
        lastName = defaultValue;
        email = defaultValue;
    }

    public User(String firstName, String lastName, String email) {
        this.firstName = firstName.equals("") ? defaultValue : firstName;
        this.lastName = lastName.equals("") ? defaultValue : lastName;

        if (email.contains("!")) {
            this.email = email.replaceAll(
                    "!", String.valueOf((int)(Math.random() * 100)));
        } else this.email = email.equals("") ? defaultValue : email;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        if ((firstName + lastName + email).equals(defaultValue))
            return "Пользователь отсутствует";

        return "Пользователь: " + firstName + ", " + lastName +
                ", email - " + email + ", "
                + (car == null ? "машина отсутствует" : car);
    }
}
