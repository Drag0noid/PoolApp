package ru.pool.poolapp.clientMagic;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ru.pool.poolapp.client.UserDashboardController;
import ru.pool.poolapp.models.UserModel;

import java.util.List;


public class TrainerAppointmentController {
    private final Stage stage;
    private final VBox root;
    private final DataBase dataBase = new DataBase();

    private UserModel userModel;




    public TrainerAppointmentController(Stage stage, UserModel userModel) {
        this.stage = stage;
        this.root = new VBox();
        this.userModel = userModel;

        initializeComponents();
    }

    private void initializeComponents() {
        stage.setTitle("Запись к тренеру");

        // Add GUI components for doctor appointment
        Label clinicLabel = new Label("Выберите бассейн:");
        ComboBox<String> clinicComboBox = new ComboBox<>();
        List<String> clinics = dataBase.getClinics();
        clinicComboBox.getItems().addAll(clinics);

        Label doctorLabel = new Label("Выберите тренера:");
        ComboBox<String> doctorComboBox = new ComboBox<>();

        Button showDoc = new Button("Показать тренеров");
        showDoc.setOnAction(event -> {
            String selectedClinic = clinicComboBox.getValue();
            List<String> doctors = dataBase.getDoctor(selectedClinic);
            doctorComboBox.getItems().clear();
            doctorComboBox.getItems().addAll(doctors);
        });

        Label timeLabel = new Label("Выберите время:");
        ComboBox<String> timeComboBox = new ComboBox<>();
        timeComboBox.getItems().addAll("10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00");

        Button saveButton = new Button("Сохранить");
        saveButton.setOnAction(event -> handleSaveButtonClicked(userModel.getId_patient() ,doctorComboBox.getValue(), timeComboBox.getValue()));

        Button backButton = new Button("Назад");
        backButton.setOnAction(event -> handleBackButtonClicked());

        root.getChildren().addAll(clinicLabel, clinicComboBox, doctorLabel, doctorComboBox, showDoc, timeLabel, timeComboBox, saveButton, backButton);

        Scene scene = new Scene(root, 400, 300);
        scene.getStylesheets().add(0, "dashboard.css");
        stage.setScene(scene);
    }

    private void handleSaveButtonClicked(Integer userId, String selectedDoctor, String selectedTime) {
        dataBase.createOrder(userId, selectedDoctor, selectedTime);
    }

    public void show() {
        stage.show();
    }

    private void handleBackButtonClicked() {
        UserDashboardController userDashboardController = new UserDashboardController(stage, userModel);
        userDashboardController.show();
    }
}