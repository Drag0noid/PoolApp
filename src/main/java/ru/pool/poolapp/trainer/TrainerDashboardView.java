package ru.pool.poolapp.trainer;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.Getter;

public class TrainerDashboardView extends BorderPane {
    private final Stage stage;

    @Getter
    private Button editAppointmentButton;
    @Getter
    private Button logoutButton;

    public TrainerDashboardView(Stage stage) {
        this.stage = stage;
        initializeComponents();
    }

    private void initializeComponents() {
        stage.setTitle("Тренер");

        GridPane grid = new GridPane();

        // Sidebar Buttons
        editAppointmentButton = createButton("Редактировать прием");
        logoutButton = createButton("Выйти из аккаунта");

        // Sidebar Container
        VBox sidebar = new VBox(editAppointmentButton, logoutButton);
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