package com.game.depths;

import java.sql.*;

public class DataBaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/juego_poo"; // Cambia a tu URL de conexión
    private static final String USER = "root"; // Cambia a tu usuario de MySQL
    private static final String PASSWORD = "agusbenja29"; // Cambia a tu contraseña de MySQL
    private static Connection connection = null;

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");

                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Conexión a la base de datos establecida.");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                throw new SQLException("Controlador JDBC de MySQL no encontrado.");
            }
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Conexión a la base de datos cerrada.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static ResultSet executeQuery(String query) throws SQLException {
        Connection conn = getConnection();
        PreparedStatement statement = conn.prepareStatement(query);
        return statement.executeQuery();
    }

    public static int executeUpdate(String query) throws SQLException {
        Connection conn = getConnection();
        PreparedStatement statement = conn.prepareStatement(query);
        return statement.executeUpdate();
    }

}

