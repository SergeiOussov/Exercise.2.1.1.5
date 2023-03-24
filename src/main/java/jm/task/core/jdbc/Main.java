package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {

    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Иван", "Иванов", (byte) 25);
        userService.saveUser("Пётр", "Петров", (byte) 22);
        userService.saveUser("Сидор", "Сидоров", (byte) 44);
        userService.saveUser("Кузьма", "Кузьмин", (byte) 31);
        userService.getAllUsers().forEach(System.out::println);
        userService.cleanUsersTable();
        userService.dropUsersTable();
        Util.closeFactory();
        Util.closeConnection();
    }

}
