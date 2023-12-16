package ru.pool.poolapp.login;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class LoginView {

    private final Stage stage;
    private TextField txtUsername;
    private PasswordField txtPassword;
    private Button btnLogin;
    private Button btnRegistration;
    private CheckBox chkRememberMe;

    public LoginView(Stage stage) {
        this.stage = stage;
        initializeComponents();
    }

    private void initializeComponents() {
        stage.setTitle("Авторизация");

        GridPane grid = new GridPane();
        grid.getStyleClass().add("grid-pane");

        Label txtMain = new Label("Авторизация");
        txtMain.setId("txtMain");
        grid.add(txtMain, 0, 0);

        txtUsername = new TextField();
        txtUsername.setId("inputText");
        txtUsername.setPromptText("Логин");
        grid.add(txtUsername, 0, 1);

        txtPassword = new PasswordField();
        txtPassword.setId("inputText");
        txtPassword.setPromptText("Пароль");
        grid.add(txtPassword, 0, 2);

        btnLogin = new Button("Вход");
        btnLogin.setId("Btn");
        grid.add(btnLogin, 0, 3);

        btnRegistration = new Button("Регистрация");
        btnRegistration.setId("Btn");
        grid.add(btnRegistration, 0, 4);

        chkRememberMe = new CheckBox("Запомнить меня");
        grid.add(chkRememberMe, 1, 3);

        Scene scene = new Scene(grid, 500, 500);
        scene.getStylesheets().add(0, "login.css");
        stage.setScene(scene);
    }

    public void show() {
        stage.show();
    }

    public String getUsername() {return txtUsername.getText();}

    public String getPassword() {return txtPassword.getText();}

    public void setUsername(String username) {
        txtUsername.setText(username);
    }

    public void setPassword(String password) {
        txtPassword.setText(password);
    }

    public void setRememberMeSelected(boolean selected) {
        chkRememberMe.setSelected(selected);
    }

    public boolean isRememberMeSelected() {
        return chkRememberMe.isSelected();
    }

    public void setLoginButtonAction(Runnable action) {
        btnLogin.setOnAction(event -> action.run());
    }

    public void setRegistrationButtonAction(Runnable action) {
        btnRegistration.setOnAction(event -> action.run());
    }

    public void showErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}