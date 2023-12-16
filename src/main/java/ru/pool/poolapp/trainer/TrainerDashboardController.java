package ru.pool.poolapp.trainer;

import javafx.scene.control.Button;
import javafx.stage.Stage;
import ru.pool.poolapp.trainerMagic.EditAppointmentController;
import ru.pool.poolapp.login.LoginController;
import ru.pool.poolapp.models.UserModel;

public class TrainerDashboardController {
    private final TrainerDashboardView view;
    private final Stage stage;
    private final UserModel userModel;

    public TrainerDashboardController(Stage stage, UserModel userModel) {
        this.stage = stage;
        this.userModel = userModel;
        view = new TrainerDashboardView(stage);
        bindEvents();
    }

    private void bindEvents() {
        Button editAppointmentButton = view.getEditAppointmentButton();
        Button logoutButton = view.getLogoutButton();

        editAppointmentButton.setOnAction(event -> openEditAppointmentGUI());
        logoutButton.setOnAction(event -> logout());
    }

    public void show() {
        view.show();
    }

    private void openEditAppointmentGUI() {
        EditAppointmentController editAppointmentController = new EditAppointmentController(stage, userModel);
        editAppointmentController.show();
    }

    private void logout() {
        stage.close();
        LoginController loginController = new LoginController(new Stage());
        loginController.show();
    }
}