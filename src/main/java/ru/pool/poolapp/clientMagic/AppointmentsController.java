package ru.pool.poolapp.clientMagic;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ru.pool.poolapp.client.UserDashboardController;
import ru.pool.poolapp.models.AppointmentModel;
import ru.pool.poolapp.models.UserModel;

import java.util.ArrayList;
import java.util.List;

public class AppointmentsController {
    private final Stage stage;
    private final VBox root;
    private final DataBase dataBase = new DataBase();
    private final UserModel userModel;

    private TableView<AppointmentModel> appointmentsTable = new TableView<>();

    public AppointmentsController(Stage stage, UserModel userModel) {
        this.stage = stage;
        this.root = new VBox();
        this.userModel = userModel;

        initializeComponents();
    }

    private void initializeComponents() {
        stage.setTitle("Просмотр занятий");

        // Add GUI components for viewing appointments
        Label appointmentsLabel = new Label("Список ваших занятий:");

        TableColumn<AppointmentModel, String> patientNameColumn = new TableColumn<>("Имя клиента");
        patientNameColumn.setCellValueFactory(data -> data.getValue().patientNameProperty());

        TableColumn<AppointmentModel, String> patientPolisColumn = new TableColumn<>("Номер абонемента");
        patientPolisColumn.setCellValueFactory(data -> data.getValue().patientPolisProperty());

        TableColumn<AppointmentModel, String> paramedicNameColumn = new TableColumn<>("Имя тренера");
        paramedicNameColumn.setCellValueFactory(data -> data.getValue().paramedicNameProperty());

        TableColumn<AppointmentModel, String> clinicColumn = new TableColumn<>("Бассейн");
        clinicColumn.setCellValueFactory(data -> data.getValue().clinicProperty());

        TableColumn<AppointmentModel, String> visitTimeColumn = new TableColumn<>("Время занятия");
        visitTimeColumn.setCellValueFactory(data -> data.getValue().visitTimeProperty());

        TableColumn<AppointmentModel, String> patientDiagnosisColumn = new TableColumn<>("Зал для занятия");
        patientDiagnosisColumn.setCellValueFactory(data -> data.getValue().patientDiagnosisProperty());

        appointmentsTable.getColumns().addAll(
                patientNameColumn,
                patientPolisColumn,
                paramedicNameColumn,
                clinicColumn,
                visitTimeColumn,
                patientDiagnosisColumn
        );

        List<AppointmentModel> appointmentModels = getAppointmentModels();

        // Добавить все объекты AppointmentModel в таблицу
        appointmentsTable.getItems().addAll(appointmentModels);

        Button backButton = new Button("Назад");
        backButton.setOnAction(event -> handleBackButtonClicked());

        root.getChildren().addAll(appointmentsLabel, appointmentsTable, backButton);

        Scene scene = new Scene(root, 600, 400);
        stage.setScene(scene);
    }

    private List<AppointmentModel> getAppointmentModels() {
        List<String> appointments = dataBase.getAppointments(userModel.getId());
        List<AppointmentModel> appointmentModels = new ArrayList<>();
        for (String appointment : appointments) {
            // Разбить строку записи на отдельные поля
            String[] fields = appointment.split(", ");

            // Создать объект AppointmentModel с помощью полей
            AppointmentModel appointmentModel = new AppointmentModel(fields[0], fields[1], fields[2], fields[3], fields[4], fields[5]);

            // Добавить объект AppointmentModel в список
            appointmentModels.add(appointmentModel);
        }
        return appointmentModels;
    }

    public void show() {
        stage.show();
    }

    private void handleBackButtonClicked() {
        UserDashboardController userDashboardController = new UserDashboardController(stage, userModel);
        userDashboardController.show();
    }
}