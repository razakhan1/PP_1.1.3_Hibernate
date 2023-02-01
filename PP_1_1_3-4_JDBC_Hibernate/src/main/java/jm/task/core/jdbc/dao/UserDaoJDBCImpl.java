package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        //language=MySQL
        Class<Driver> driverClass = Driver.class;
        String sql = """
                CREATE TABLE IF NOT EXISTS Users (
                    id SERIAL PRIMARY KEY,
                    name VARCHAR(100),
                    lastName VARCHAR(100),
                    age INT
                    );
                """;
        try (Connection connection = Util.connectionJDBC();
             Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        Class<Driver> driverClass = Driver.class;
        String sql = """
                DROP TABLE IF EXISTS Users;
                """;
        try (Connection connection = Util.connectionJDBC();
             Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        Class<Driver> driverClass = Driver.class;
        String sql = """
                INSERT INTO Users(name, lastName, age)
                VALUES (?, ?, ?);
                """;
        try (Connection connection = Util.connectionJDBC();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User с именем - " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void removeUserById(long id) {
        Class<Driver> driverClass = Driver.class;
        String sql = "DELETE FROM Users WHERE id = ?;";
        try (Connection connection = Util.connectionJDBC();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> listUser = new ArrayList<>();
        Class<Driver> driverClass = Driver.class;
        String sql = "SELECT * FROM Users;";
        try (Connection connection = Util.connectionJDBC();
             Statement statement = connection.createStatement();
             ResultSet executeResultUsers = statement.executeQuery(sql)) {
            while (executeResultUsers.next()) {
                User user = new User();
                user.setId(executeResultUsers.getLong("id"));
                user.setName(executeResultUsers.getString("name"));
                user.setLastName(executeResultUsers.getString("lastName"));
                user.setAge(executeResultUsers.getByte("age"));
                listUser.add(user);
            }
            return listUser;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void cleanUsersTable() {
        Class<Driver> driverClass = Driver.class;
        String sql = "DELETE FROM Users;";
        try (Connection connection = Util.connectionJDBC();
             Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
