package ru.pool.poolapp.trainerMagic;

import ru.pool.poolapp.database.SQLiteConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DataBase {
    SQLiteConnection sqLiteConnection = new SQLiteConnection();

    protected List<List> getAppointments(Integer user_id) {

        List<List> docAppointments = new ArrayList<>();

        String sqlQuery = "SELECT " +
                "vi.fio AS visiter_name, " +
                "vi.ticket_number AS visiter_ticket, " +
                "tr.fio AS trainer_name, " +
                "r.region_name AS pool, " +
                "v.visit_date AS visit_time, " +
                "v.room AS visiter_room, " +
                "vi.id_visiter AS id_user " +
                "FROM " +
                "Visits v " +
                "INNER JOIN Visiters vi ON v.id_visiter = vi.id_visiter " +
                "INNER JOIN Trainers tr ON v.id_trainers = tr.id_trainers " +
                "INNER JOIN Region r ON tr.id_region = r.id_region " +
                "INNER JOIN users u ON vi.id_visiter = u.id_visiter " +
                "WHERE tr.id_trainers = ?;";

        try (Connection connection = sqLiteConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {

            statement.setInt(1, user_id);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                List<String> order = new ArrayList<>();
                order.add(resultSet.getString("visiter_name"));
                order.add(resultSet.getString("visiter_ticket"));
                order.add(resultSet.getString("trainer_name"));
                order.add(resultSet.getString("pool"));
                order.add(resultSet.getString("visit_time"));
                order.add(resultSet.getString("visiter_room"));
                order.add(resultSet.getString("id_user"));

                docAppointments.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return docAppointments;
    }

    protected List<String> getTitleAppointment(Integer user_id) {

        List<String> orderTitle = new ArrayList<>();

        String sqlQuery = "SELECT " +
                "vi.fio AS visiter_name " +
                "FROM " +
                "Visits v " +
                "INNER JOIN Visiters vi ON v.id_visiter = vi.id_visiter " +
                "INNER JOIN Trainers tr ON v.id_trainers = tr.id_trainers " +
                "WHERE tr.id_trainers = ?";

        try (Connection connection = sqLiteConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {

            statement.setInt(1, user_id);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                orderTitle.add(resultSet.getString("visiter_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderTitle;
    }

    protected void saveAppointment(Integer user_id, String patientDiagnosis) {
        String sqlQuery = "UPDATE visits SET room = ? WHERE id_visiter = ?";

        try (Connection connection = sqLiteConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setString(1, patientDiagnosis);
            statement.setInt(2, user_id);

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error occurred while saving the appointment.");
            e.printStackTrace();
        }
    }
}
