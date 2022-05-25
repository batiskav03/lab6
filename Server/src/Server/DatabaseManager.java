package Server;

import data.*;

import java.util.Date;

import java.sql.*;
import java.util.HashMap;
import java.util.LinkedList;

public class DatabaseManager {

    private static String PASSWORD = "3361";
    private static String USER = "postgres";
    private static String URL = "jdbc:postgresql://localhost:5432/postgres";


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
    public static boolean updDragonInDatabase(Dragon dragon) {

        try {
            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("-- Opened");
            Statement statement = c.createStatement();

            String sql = "UPDATE dragons SET " +
                    "owner = " + dragon.getOwner() + ", " +
                    "name = " + "\'" + dragon.getName() + "\'"+ ", " +
                    "x = " + dragon.getX() + ", " +
                    "y = " + dragon.getY() + ", " +
                    "creationDate = " + "\'" + dragon.getCreationDate() + "\'" + ", " +
                    "age = " + dragon.getAge() + ", " +
                    "color = " + "\'" + dragon.getColor() + "\'" + ", " +
                    "type = " + "\'" + dragon.getType() + "\'" + ", " +
                    "character = " + "\'" + dragon.getCharacter() + "\'" + ", " +
                    "eyes = " + dragon.getEyes() + ", " +
                    "tooth = " + dragon.getTooth() +
                    "WHERE id = " + dragon.getId() + ";";
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
    public static LinkedList<Dragon> getFromDatabaseDragons() {
        LinkedList<Dragon> dragon = new LinkedList<Dragon>();
        try {
            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            PreparedStatement sql = c.prepareStatement("SELECT * FROM dragons");
            ResultSet rs = sql.executeQuery();
            while (rs.next()) {

                Coordinates coordinates = new Coordinates(Long.parseLong(rs.getString(4)),
                        Float.parseFloat(rs.getString(5)));
                DragonHead dragonHead = new DragonHead(Long.parseLong(rs.getString(11)),
                        Float.parseFloat(rs.getString(12)));
                Date date = new Date();
                dragon.add(new Dragon(Integer.valueOf(rs.getString(2)),rs.getString(13),
                        rs.getString(3),
                        coordinates,
                        date,
                        Integer.parseInt(rs.getString(7)),
                        Color.valueOf(rs.getString(8)),
                        DragonType.valueOf(rs.getString(9)) ,
                        DragonCharacter.valueOf(rs.getString(10)) ,
                        dragonHead));
            }
            c.commit();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return dragon;
    }
}


