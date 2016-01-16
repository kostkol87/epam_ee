package DAO;

import java.sql.*;

public class DBUtils {
    public static Connection getCon() {
        Connection connection = null;
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:src\\main\\resources\\db.sqlite3");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }
    public static String getPassword(String login){
        Connection con = DBUtils.getCon();
        try (PreparedStatement ps = con.prepareStatement("SELECT password FROM users WHERE login=?")) {

            ps.setString(1, login);
            ResultSet resultSet = ps.executeQuery();

            return resultSet.getString("password");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean validate(String login, String password){ {
            String dbPassword = DBUtils.getPassword(login);
            System.out.println("entered pass is " + password + " pass in db = " + dbPassword);
            return password == dbPassword;
        }
    }

    public static void main(String[] args) {
        System.out.println(DBUtils.getPassword("admin"));
    }

}
