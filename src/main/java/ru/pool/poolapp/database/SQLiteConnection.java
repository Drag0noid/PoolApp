package ru.pool.poolapp.database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteConnection {
    Connection connection;

    public Connection getConnection() {
        try {
            // db parameters
            String url = "jdbc:sqlite:src/main/java/ru/pool/poolapp/database/CourseWork.sqlite3";
            // create a connection to the database
            connection = DriverManager.getConnection(url);

            System.out.println("Соединение с базой данных установленно.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } return connection;
    }


    public void close(){
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Соединение с базой данных закрыто.");
            }
        } catch (SQLException e) {
            System.out.println("Ошибка при закрытии соединения с базой данных.");
            e.printStackTrace();
        }
    }
}
