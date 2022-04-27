package Server;

import data.Dragon;

import java.sql.*;
import java.util.HashMap;

public class DatabaseManager {

    private static String PASSWORD = "****";
    private static String USER = "***";
    private static String URL = "jdbc:postgresql://pg:5432/studs";


    public static boolean addDragonToDatabase(Integer key, Dragon dragon) {

        try {
            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("-- Opened");
            Statement statement = c.createStatement();

            String sql = "INSERT INTO dragons (key,owner,id,name,x,y,creationDate,age,color,type,character,eyes,tooth) VALUES" + "(" + key + ", " + "\'" + dragon.getOwner() + "\'" + ", " + dragon.getId() + ", " +
                    "\'" + dragon.getName() + "\'" + ", " + dragon.getX() + ", " + dragon.getY() + ", " + "\'" + dragon.getCreationDate() + "\'" + ", " + dragon.getAge() + ", " +
                    "\'" + dragon.getColor() + "\'" + ", " + "\'" + dragon.getType() + "\'" + ", " + "\'" + dragon.getCharacter() + "\'" + ", " + dragon.getEyes() + ", " + dragon.getTooth() + " );";
            System.out.println(sql);
            statement.executeUpdate(sql);
            statement.close();
            c.commit();
            System.out.println("record succ");


        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean clearDatabase() {
        try {
            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            Statement statement = c.createStatement();
            String sql = "TRUNCATE dragons";
            statement.executeUpdate(sql);
            statement.close();
            c.commit();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean removeDragonByKey(Integer key) {
        try {
            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            Statement statement = c.createStatement();
            String sql = "DELETE FROM dragons" +
                    "\n WHERE key = " + key;
            statement.executeUpdate(sql);
            statement.close();
            c.commit();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean savePasswords(String login, String password) {
        try {
            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            Statement statement = c.createStatement();
            String sql = "INSERT INTO users (login,password) VALUES (" + "\'" + login + "\'" + ", " + "\'" + password +
                    "\');";
            statement.executeUpdate(sql);
            statement.close();
            c.commit();
        } catch (SQLException | ClassNotFoundException throwables) {

            throwables.printStackTrace();
            return false;
        }
        return true;
    }
    public static HashMap<String, String> getFromDatabase(){
        HashMap<String, String> users1 = new HashMap<>();
        try {
            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            PreparedStatement sql = c.prepareStatement("SELECT * FROM users");
            ResultSet rs = sql.executeQuery();
            while (rs.next()) {
                users1.put(rs.getString(2), rs.getString(3));
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return users1;
    }


}