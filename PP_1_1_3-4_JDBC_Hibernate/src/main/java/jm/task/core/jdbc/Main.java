package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        User user1 = new User("Razakhan", "Kazanbekov", (byte) 24);
        User user2 = new User("Rinat", "Shokirov", (byte) 25);
        User user3 = new User("Maksim", "Nikitin", (byte) 25);
        User user4 = new User("Anna", "Romanova", (byte) 24);
        userService.saveUser(user1.getName(), user1.getLastName(), user1.getAge());
        userService.saveUser(user2.getName(), user2.getLastName(), user2.getAge());
        userService.saveUser(user3.getName(), user3.getLastName(), user3.getAge());
        userService.saveUser(user4.getName(), user4.getLastName(), user4.getAge());
        List<User> users = userService.getAllUsers();
        System.out.println(users.toString());
        userService.cleanUsersTable();
        userService.dropUsersTable();


    }
}
