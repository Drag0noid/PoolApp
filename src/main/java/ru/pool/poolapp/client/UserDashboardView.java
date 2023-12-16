package ru.pool.poolapp.client;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.Getter;

public class UserDashboardView extends BorderPane {
    private final Stage stage;

    @Getter
    private Button doctorAppointmentButton;
    @Getter
    private Button viewAppointmentsButton;
    @Getter
    private Button logoutButton;

    public UserDashboardView(Stage stage) {
        this.stage = stage;
        initializeComponents();
    }

    private void initializeComponents() {
        stage.setTitle("Возможности пользователя");

        GridPane grid = new GridPane();

        doctorAppointmentButton = createButton("Записаться к тренеру");
        viewAppointmentsButton = createButton("Посмотреть записи");
        logoutButton = createButton("Выйти из аккаунта");

        VBox sidebar = new VBox(doctorAppointmentButton, viewAppointmentsButton, logoutButton);
        sidebar.setSpacing(10);
        sidebar.setPadding(new Insets(10));
        sidebar.setAlignment(Pos.TOP_LEFT);

        // Set Sidebar
        setLeft(sidebar);
        setMargin(sidebar, new Insets(10));

        grid.add(sidebar, 0, 0);

        Scene scene = new Scene(grid, 420, 310);
        scene.getStylesheets().add(0, "dashboard.css");
        stage.setScene(scene);

    }

    private Button createButton(String buttonText) {
        Button button = new Button(buttonText);
        button.setPrefWidth(180);
        return button;
    }

    public void show() {
        stage.show();
    }
}