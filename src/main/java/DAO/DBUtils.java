package DAO;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;

public class DBUtils {

    static <T> T localJndiSearch(String name, Class<T> aClass) {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            Object o = envContext.lookup(name);
            if (aClass.isInstance(o))
                return (T) o;
            else
                throw new NamingException("Object, founded by " + name + " name is not an instance of class " + aClass.getName());
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getCon() {
        System.out.println("IN GET CONNECTION");
        Connection connection = null;
        try {
            connection = DBUtils.<DataSource>localJndiSearch("jdbc/TestDB", DataSource.class).getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
    public static String getPassword(String login){
        Connection con = DBUtils.getCon();
        try (PreparedStatement ps = con.prepareStatement("SELECT password FROM testdb.users WHERE login=?;")) {
            ps.setString(1, login);
            ResultSet resultSet = ps.executeQuery();
            resultSet.next();
            String password = resultSet.getString("password");
            return password;

        } catch (SQLException e) {
            e.printStackTrace();
            return "exception";
        }
    }

    public static String getRole(String login){
        Connection con = DBUtils.getCon();
        try (PreparedStatement ps = con.prepareStatement("SELECT role FROM testdb.roles WHERE id in (SELECT role FROM users WHERE login=?);")){
            ps.setString(1, login);
            ResultSet resultSet = ps.executeQuery();
            resultSet.next();
            String role = resultSet.getString("role");
            System.out.println("ROLE IS " + role);
            return role;
        } catch (SQLException e) {
            e.printStackTrace();
            return "exception";
        }
    }

    public static boolean validate(String login, String password) {
        if (login != null || password != null){
            String dbPassword = DBUtils.getPassword(login);
            System.out.println("entered pass is " + password + " pass in db = " + dbPassword);
            return password.equals(dbPassword);
        }else {
            return false;
        }
    }

}
