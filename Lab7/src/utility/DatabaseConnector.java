package utility;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

public class DatabaseConnector {
  private static final String DB_URL = "jdbc:mysql://localhost:3306/customerdb";
  private static final String DB_USERNAME = "root";
  private static final String DB_PASSWORD = "my-secret-pw";
  private static final Logger logger = Logger.getLogger(DatabaseConnector.class.getName());

  // C - Create: add user
  public static void addUser(User user) {
    String query = "INSERT INTO student(first_name, age, college, hobbies) VALUES (?, ?, ?, ?)";
    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
    PreparedStatement stmt = conn.prepareStatement(query)) {

    stmt.setString(1, user.getName());
    stmt.setInt(2, user.getAge());
    stmt.setString(3, user.getCollege());
    stmt.setString(4, user.getHobby());

    int rows = stmt.executeUpdate();
    logger.log(Level.INFO, "Rows inserted: {0}", rows);
    } catch (SQLException sqle) {
      logger.log(Level.SEVERE, "SQL Exception Occurred", sqle);
    }
  }

  // R - Read: read user info
  public static ArrayList<User> getUsers() {
    ArrayList<User> users = new ArrayList<>();
    String query = "SELECT * FROM student";
    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
    Statement stmt = conn.createStatement();
    ResultSet rs = stmt.executeQuery(query)) {
      while (rs.next()) {
        User user = new User();
        user.setUserId(rs.getInt("student_id"));
        user.setName(rs.getString("first_name"));
        user.setCollege(rs.getString("college"));
        user.setAge(rs.getInt("age"));
        user.setHobby(rs.getString("hobbies"));
        users.add(user);
      }
    } catch (SQLException sqle) {
      logger.log(Level.SEVERE, "SQL Exception Occurred", sqle);
    }
    return users;
  }

  // U - Update: update user info
  public static void updateUser(User oldUser, User updatedUser) {
    String query = "UPDATE student SET first_name = ?, college = ?, age = ?, hobbies = ? WHERE student_id = ?";
    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
    PreparedStatement stmt = conn.prepareStatement(query)) {
      stmt.setString(1, updatedUser.getName());
      stmt.setString(2, updatedUser.getCollege());
      stmt.setInt(3, updatedUser.getAge());
      stmt.setString(4, updatedUser.getHobby());
      stmt.setInt(5, oldUser.getUserId());

      int rows = stmt.executeUpdate();
      logger.log(Level.INFO, "Rows updated: {0}", rows);
    } catch (SQLException sqle) {
      logger.log(Level.SEVERE, "SQL Exception Occurred", sqle);
    }
  }

  // D - Delete: delete user
  public static void deleteUser(User user) {
    String query = "DELETE FROM student WHERE student_id = ?";
    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
    PreparedStatement stmt = conn.prepareStatement(query)) {
      stmt.setInt(1, user.getUserId());
      int rows = stmt.executeUpdate();
      logger.log(Level.INFO, "Rows deleted: {0}", rows);
    } catch (SQLException sqle) {
      logger.log(Level.SEVERE, "SQL Exception Occurred", sqle);
    }
  }
}
