package pl.coderslab.entity;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.Arrays;

public class UserDao {

    private static final String CREATE_USER = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";
    private static final String READ_USER = "SELECT * FROM users where id = ?";
    private static final String UPDATE_USER = "UPDATE users SET username = ?, email = ?, password = ? where id = ?";
    private static final String DELETE_USER = "DELETE FROM users WHERE id = ?";
    private static final String DISPLAY_USERS = "SELECT * FROM users";
    private static final String SHOW_PASSWORD = "SELECT password FROM users WHERE id = ?";

    // hash method
    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    // checking if hashed password is same to given by user
    public boolean verifyHash(String password, String hash){
        return BCrypt.checkpw(password,hash);
    }


    // create user
    public User createUser(User user) {
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(CREATE_USER, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getEmail());
            statement.setString(3, hashPassword(user.getPassword()));
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // display user by id
    public User readUser(int userId) {
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(READ_USER);
            statement.setInt(1, userId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUserName(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // update user
    public void updateUser(User user) {
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(UPDATE_USER);
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getEmail());
            statement.setString(3, hashPassword(user.getPassword()));
            statement.setInt(4, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // delete user
    public User deleteUser(int userId) {
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DELETE_USER);
            statement.setInt(1, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // additional method enlarging array list if necessary
    private User[] addToArray(User u, User[] users) {
        User[] newArraysOfUsers = Arrays.copyOf(users, users.length + 1);
        newArraysOfUsers[users.length] = u;
        return newArraysOfUsers;
    }

    public User[] displayAll() {
        try (Connection connection = DbUtil.getConnection()) {
            User[] usersNewTab = new User[0];
            PreparedStatement statement = connection.prepareStatement(DISPLAY_USERS);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUserName(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                usersNewTab = addToArray(user, usersNewTab);
            }
            return usersNewTab;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}









