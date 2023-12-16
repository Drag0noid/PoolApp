package ru.pool.poolapp.clientMagic;

import ru.pool.poolapp.database.SQLiteConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBase {
    SQLiteConnection sqLiteConnection = new SQLiteConnection();

    protected List<String> getClinics() {
        List<String> clinics = new ArrayList<>();
        String query = "SELECT region_name FROM Region";

        try {
            Connection connection = sqLiteConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String clinicName = resultSet.getString("region_name");
                clinics.add(clinicName);
            }

            resultSet.close();
            statement.close();
            sqLiteConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clinics;
    }

    protected List<String> getDoctor(String clinic) {
        List<String> doctors = new ArrayList<>();
        String query = "SELECT fio FROM Trainers t join region r\n" +
                        "on t.id_region = r.id_region\n" +
                        "where r.region_name = ?" ;

        try (Connection connection = sqLiteConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            // Устанавливаем значение параметра
            statement.setString(1, clinic);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String doctorName = resultSet.getString("fio");
                    doctors.add(doctorName);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return doctors;
    }


    protected void createOrder(Integer userId, String doctor, String time) {
        String query = "INSERT INTO Visits (id_visiter, id_trainers, visit_date) " +
                "VALUES (?, ?, ?)";

        try (Connection connection = sqLiteConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {


            int doctorId = getDoctorId(doctor);


            statement.setInt(1, userId);
            statement.setInt(2, doctorId);
            statement.setString(3, time);


            statement.executeUpdate();

            System.out.println("Заявка успешно создана!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int getDoctorId(String doctor) {
        String query = "SELECT id_trainers FROM Trainers WHERE fio = ?";

        try (Connection connection = sqLiteConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {


            statement.setString(1, doctor);


            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("id_trainers");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    protected List<String> getAppointments(Integer user_id) {
        List<String> appointments = new ArrayList<>();

        String sqlQuery = "SELECT " +
                "vi.fio AS visiter_name, " +
                "vi.ticket_number AS visiter_ticket, " +
                "tr.fio AS trainer_name, " +
                "r.region_name AS pool, " +
                "v.visit_date AS visit_time, " +
                "v.room AS visiter_gym " +
                "FROM " +
                "Visits v " +
                "INNER JOIN Visiters vi ON v.id_visiter = vi.id_visiter " +
                "INNER JOIN Trainers tr ON v.id_trainers = tr.id_trainers " +
                "INNER JOIN Region r ON tr.id_region = r.id_region " +
                "INNER JOIN users u ON vi.id_visiter = u.id_visiter " +
                "WHERE u.id_user = ?";

        try (Connection connection = sqLiteConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {

            statement.setInt(1, user_id);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String appointment = resultSet.getString("visiter_name") + ", " +
                        resultSet.getString("visiter_ticket") + ", " +
                        resultSet.getString("trainer_name") + ", " +
                        resultSet.getString("pool") + ", " +
                        resultSet.getString("visit_time") + ", " +
                        resultSet.getString("visiter_gym");

                appointments.add(appointment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return appointments;
    }
}
