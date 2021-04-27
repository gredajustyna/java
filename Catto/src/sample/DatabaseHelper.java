package sample;

import java.sql.*;

public class DatabaseHelper {
    public static Connection connect(){
        Connection con = null;
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:catbase.db");
            // System.out.println("Connected!");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println(e+"");
        }
        return con;
    }
}
