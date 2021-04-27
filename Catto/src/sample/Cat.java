package sample;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Cat {
    public int getMoney() {
        return money;
    }

    public int getLevel() {
        return level;
    }

    public int getPoints() {
        return points;
    }

    public int getHunger() {
        return hunger;
    }

    public int getEnergy() {
        return energy;
    }

    public int getMood() {
        return mood;
    }

    public int getHygiene() {
        return hygiene;
    }

    public int getGold() {
        return gold;
    }

    public int getSilver() {
        return silver;
    }

    public int getBronze() {
        return bronze;
    }

    public int getObedience() {
        return obedience;
    }

    public int getDiscipline() {
        return discipline;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public String getName() {
        return name;
    }

    private int money;
    private int level;
    private int points;
    private int hunger;
    private int energy;
    private int mood;
    private int hygiene;
    private int gold;
    private int silver;
    private int bronze;
    private int obedience;
    private int discipline;
    private int intelligence;
    private String name;

    public Cat() {
        Connection con = DatabaseHelper.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "Select * from cat where name = 'kitek'";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            name = rs.getString(1);
            money = rs.getInt(2);
            level = rs.getInt(3);
            points = rs.getInt(4);
            hunger = rs.getInt(5);
            hygiene = rs.getInt(6);
            energy = rs.getInt(7);
            mood = rs.getInt(8);
            gold = rs.getInt(9);
            silver = rs.getInt(10);
            bronze = rs.getInt(11);
            obedience = rs.getInt(12);
            discipline = rs.getInt(13);
            intelligence = rs.getInt(14);
        } catch (
                SQLException e) {
            System.out.println(e.toString());
        } finally {
            try {
                rs.close();
                ps.close();
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }

}
