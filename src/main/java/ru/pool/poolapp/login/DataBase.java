package ru.pool.poolapp.login;

import ru.pool.poolapp.database.SQLiteConnection;
import ru.pool.poolapp.models.UserModel;

import java.sql.*;

public class DataBase {
    SQLiteConnection sqLiteConnection = new SQLiteConnection();

    protected UserModel authenticateUser(String username, String password) {
        boolean isAuthenticated = false;
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        UserModel userModel = new UserModel();

        try {
            Connection connection = sqLiteConnection.getConnection();

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                userModel.setId(resultSet.getInt("id_user"));
                userModel.setUsername(resultSet.getString("username"));
                userModel.setPassword(resultSet.getString("password"));
                userModel.setEmail(resultSet.getString("email"));
                userModel.setRole(resultSet.getString("role"));
                userModel.setId_paramedic(resultSet.getInt("id_trainers"));
                userModel.setId_patient(resultSet.getInt("id_visiter"));
            }

            resultSet.close();
            statement.close();
            sqLiteConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userModel;
    }
}
