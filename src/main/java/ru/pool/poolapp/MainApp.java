package ru.pool.poolapp;

import javafx.application.Application;
import javafx.stage.Stage;
import ru.pool.poolapp.login.LoginController;


public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        LoginController loginController = new LoginController(primaryStage);
        loginController.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}