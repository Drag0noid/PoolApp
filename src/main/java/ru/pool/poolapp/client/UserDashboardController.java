package ru.pool.poolapp.client;

import javafx.scene.control.Button;
import javafx.stage.Stage;
import lombok.Getter;
import ru.pool.poolapp.clientMagic.AppointmentsController;
import ru.pool.poolapp.clientMagic.TrainerAppointmentController;
import ru.pool.poolapp.login.LoginController;
import ru.pool.poolapp.models.UserModel;


public class UserDashboardController {

    private final UserDashboardView view;
    @Getter
    private Stage stage;
    private UserModel userModel = new UserModel();


    public UserDashboardController(Stage stage, UserModel userModel) {
        setStage(stage);
        view = new UserDashboardView(stage);
        bindEvents();
        this.userModel = userModel;
    }

    private void bindEvents() {
        Button doctorAppointmentButton = view.getDoctorAppointmentButton();
        Button viewAppointmentsButton = view.getViewAppointmentsButton();
        Button logoutButton = view.getLogoutButton();

        doctorAppointmentButton.setOnAction(event -> openDoctorAppointmentGUI());
        viewAppointmentsButton.setOnAction(event -> openViewAppointmentsGUI());
        logoutButton.setOnAction(event -> logout());
    }

    public void show() {
        view.show();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    private void openDoctorAppointmentGUI() {
        TrainerAppointmentController doctorAppointmentController = new TrainerAppointmentController(stage, userModel);
        doctorAppointmentController.show();
    }

    private void openViewAppointmentsGUI() {
        AppointmentsController appointmentsController = new AppointmentsController(stage, userModel);
        appointmentsController.show();
    }

    private void logout() {
        getStage().close();
        LoginController loginController = new LoginController(new Stage());
        loginController.show();
    }
}