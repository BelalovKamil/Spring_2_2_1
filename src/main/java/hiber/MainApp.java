package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        User user1 = new User("Ivan", "Ivanov", "ivanovivan@mail.ru");
        User user2 = new User("Egor", "Egorov", "egorovegor@mail.ru");
        User user3 = new User("Andrey", "Maximov", "andreika@mail.ru");
        User user4 = new User("Petr", "Gerasimov", "gerasim@mail.ru");

        user1.setCar(new Car("BMW", 5));
        user2.setCar(new Car("LADA", 7));
        user3.setCar(new Car("LADA", 7));
        user4.setCar(new Car("LADA", 7));

        UserService userService = context.getBean(UserService.class);

        userService.add(user1);
        userService.add(user2);
        userService.add(user3);
        userService.add(user4);

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Model of car = " + user.getCar().getModel());
            System.out.println("Series of car = " + user.getCar().getSeries());
            System.out.println();
        }

        List<User> userList = userService.getUser("LADA", 7);
        for (User user : userList){
            System.out.println("Found user:");
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
        }

        context.close();
    }
}
