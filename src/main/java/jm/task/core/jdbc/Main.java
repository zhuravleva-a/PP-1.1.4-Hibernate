package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // Создание таблицы User(ов)
        System.out.println("---Создание таблицы User(ов)---");
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        System.out.println(userService.getAllUsers());
        System.out.println("---Добавление 4 User(ов)---");
        //Добавление 4 User(ов)
        userService.saveUser("Tom", "Kruz", (byte) 59);
        System.out.println(userService.getAllUsers());
        System.out.printf("User с именем – %s добавлен в базу данных\n",
                userService.getAllUsers().get(0).getName());
        userService.saveUser("Will", "Smith", (byte) 53);
        System.out.printf("User с именем – %s добавлен в базу данных\n",
                userService.getAllUsers().get(1).getName());
        userService.saveUser("Robert", "Downey", (byte) 56);
        System.out.printf("User с именем – %s добавлен в базу данных\n",
                userService.getAllUsers().get(2).getName());
        userService.saveUser("Johnny", "Depp", (byte) 58);
        System.out.printf("User с именем – %s добавлен в базу данных\n",
                userService.getAllUsers().get(3).getName());

        //Получение всех User из базы и вывод в консоль
        System.out.println(userService.getAllUsers());
        System.out.println("---Очистка таблицы User(ов)---");

        //Очистка таблицы User(ов)
        userService.cleanUsersTable();
        System.out.println(userService.getAllUsers());
        System.out.println("---Удаление таблицы---");

        //Удаление таблицы
        userService.dropUsersTable();

   }
}
