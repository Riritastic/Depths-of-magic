package com.game.depths;

import java.sql.*;
import java.util.*;

public class DataBaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/depths of magic"; // Cambia a tu URL de conexión
    private static final String USER = "root"; // Cambia a tu usuario de MySQL
    private static final String PASSWORD = ""; // Cambia a tu contraseña de MySQL
    private static Connection connection = null;

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Conexión a la base de datos establecida.");
            } catch (ClassNotFoundException e) {
                System.out.println("Controlador JDBC de MySQL no encontrado.");
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
                System.out.println("Error al cerrar la conexión");
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

    public static ResultSet query(String query, String nombre) {
        ResultSet rs = null;
        try {
            Connection conn = getConnection();
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setString(1,nombre);
            rs = stm.executeQuery();

        } catch (SQLException e) {
            System.out.println("Error durante query");


        }
        return rs;
    }
    public static List<Weapon> getWeapons() throws SQLException {
        List<Weapon> armas = new ArrayList<>();
        String query = "SELECT * FROM weapon";
        Connection conn = getConnection();
        try (Statement stmt = conn.createStatement(); ResultSet resultado = stmt.executeQuery(query)) {
            while (resultado.next()) {
                String nombre = resultado.getString("nombre");
                String tipo = resultado.getString("tipo");
                int daño = resultado.getInt("daño");
                int rango = resultado.getInt("rango");
                Weapon weapon = new Weapon(nombre, tipo, daño, rango,10,10,2,4);
                armas.add(weapon);
            }
        }
        return armas;
    }

    public static Weapon getRandomWeapon() throws SQLException {
        List<Weapon> armas = getWeapons();
        Random random = new Random();
        return armas.get(random.nextInt(armas.size()));
    }
}

