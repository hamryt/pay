package com.daehwapay.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class DummyMembershipGenerator {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/membership?useSSL=false&allowPublicKeyRetrieval=true";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "riahn";
    private static final String[] ADDRESSES = {"강남구", "관악구", "서초구", "영등포구"};

    public static void main(String[] args) throws SQLException {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            generateDummy(connection);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            assert connection != null;
            connection.close();
        }
    }

    private static void generateDummy(Connection connection) throws SQLException {
        PreparedStatement statement = null;

        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            String sql = "INSERT INTO membership(membership_id, corporation, valid, address, email, name) value (?, ?, ?, ?, ?, ?)";
            Random random = new Random();

            statement = connection.prepareStatement(sql);

            int dummySize = 10000;

            for (int i = 1; i <= dummySize; i++) {
                statement.setLong(1, i);
                statement.setBoolean(2, random.nextBoolean());
                statement.setBoolean(3, random.nextBoolean());
                statement.setString(4, ADDRESSES[random.nextInt(ADDRESSES.length)]);
                statement.setString(5, "test_" + i + "@gmail.com");
                statement.setString(6, "user_" + i);
                statement.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            statement.close();
        }
    }
}
