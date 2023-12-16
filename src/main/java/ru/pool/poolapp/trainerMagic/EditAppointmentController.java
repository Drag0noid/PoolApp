package ru.pool.poolapp.trainerMagic;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ru.pool.poolapp.trainer.TrainerDashboardController;
import ru.pool.poolapp.models.UserModel;

import java.util.List;

public class EditAppointmentController {
    private final Stage stage;
    private final VBox root;
    private final UserModel userModel;
    private final DataBase dataBase;

    private String user_id;

    public EditAppointmentController(Stage stage, UserModel userModel) {
        this.stage = stage;
        this.root = new VBox();
        this.userModel = userModel;
        this.dataBase = new DataBase();

        initializeComponents();
    }

    private void initializeComponents() {
        stage.setTitle("Редактирование занятий");

        Label appointmentLabel = new Label("Выберите занятие:");
        List<String> orderTitle = dataBase.getTitleAppointment(userModel.getId_paramedic());
        ComboBox<String> appointmentComboBox = new ComboBox<>();
        appointmentComboBox.getItems().addAll(orderTitle);

        Label editLabel = new Label("Редактируйте занятия:");

        Label patientNameTextField = new Label();
        Label patientPolisTextField = new Label();
        Label paramedicNameTextField = new Label();
        Label clinicTextField = new Label();
        Label visitTimeTextField = new Label();
        Label dia = new Label("Введите зал");
        TextField patientDiagnosisTextField = new TextField();

        Button selectButton = new Button("Выбрать");
        selectButton.setOnAction(event -> {
            String selectedAppointment = appointmentComboBox.getValue();
            List<List> appointments = dataBase.getAppointments(userModel.getId_paramedic());
            for (List appointment : appointments) {
                if (appointment.get(0).equals(selectedAppointment)) {
                    patientNameTextField.setText((String) appointment.get(0));
                    patientPolisTextField.setText((String) appointment.get(1));
                    paramedicNameTextField.setText((String) appointment.get(2));
                    clinicTextField.setText((String) appointment.get(3));
                    visitTimeTextField.setText((String) appointment.get(4));
                    patientDiagnosisTextField.setText((String) appointment.get(5));
                    user_id = ((String) appointment.get(6));
                    break;
                }
            }
        });

        Button saveButton = new Button("Сохранить");
        saveButton.setOnAction(event -> {
            String patientDiagnosis = patientDiagnosisTextField.getText();

            dataBase.saveAppointment(Integer.valueOf(user_id), patientDiagnosis);
        });

        Button backButton = new Button("Назад");
        backButton.setOnAction(event -> handleBackButtonClicked());

        root.getChildren().addAll(appointmentLabel, appointmentComboBox, selectButton, editLabel,
                                  patientNameTextField, patientPolisTextField,
                                  paramedicNameTextField, clinicTextField,
                                  visitTimeTextField, dia, patientDiagnosisTextField, saveButton, backButton);

        Scene scene = new Scene(root, 400, 300);
        scene.getStylesheets().add(0, "dashboard.css");
        stage.setScene(scene);
    }

    public void show() {
        stage.show();
    }

    private void handleBackButtonClicked() {
        TrainerDashboardController doctorDashboardController = new TrainerDashboardController(stage, userModel);
        doctorDashboardController.show();
    }
}