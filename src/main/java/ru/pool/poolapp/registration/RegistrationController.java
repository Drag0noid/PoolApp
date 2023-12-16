package ru.pool.poolapp.registration;

import javafx.stage.Stage;
import lombok.Getter;

import java.util.prefs.Preferences;

public class RegistrationController {

    private final Preferences prefs;
    private final DataBase dataBase = new DataBase();

    private final RegistrationView view;

    @Getter
    private Stage stage;

    public RegistrationController(Stage stage) {
        setStage(stage);
        prefs = Preferences.userNodeForPackage(RegistrationController.class);
        view = new RegistrationView(stage);
        view.setRegisterButtonAction(this::onRegisterButtonClicked);
    }

    public void show() {
        view.show();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    private void onRegisterButtonClicked() {
        String fio = String.valueOf(view.getTxtFio().getText());
        String addr = String.valueOf(view.getTxtAddr().getText());
        String polis = String.valueOf(view.getTxtAbon().getText());
        String gender = String.valueOf(view.getGenderComboBox().getValue());
        String email = String.valueOf(view.getTxtEmail().getText());
        String username = String.valueOf(view.getTxtUsername().getText());
        String password = String.valueOf(view.getTxtPassword().getText());
        String confirmPassword = String.valueOf(view.getTxtConfirmPassword().getText());
        System.out.println(fio + addr + polis + gender + email + username + password);

        if (password.equals(confirmPassword) && validatePassword(password)) {
            boolean isRegistered = registerUser(fio, addr, polis, gender, email, username, password);

            if (isRegistered) {
                rememberLoginCredentials(username, password);

                view.showInformationAlert("Успешная регистрация", "Вы успешно зарегистрировались в системе.");
                getStage().close();

            } else {
                view.showErrorAlert("Ошибка регистрации", "Не удалось зарегистрироваться в системе. Пожалуйста, попробуйте снова.");
            }
        } else {
            view.showErrorAlert("Ошибка ввода", "Пароль и подтверждение пароля не совпадают. Пожалуйста, попробуйте снова.");
        }
    }

    private boolean registerUser(String fio, String addr, String polis, String gender, String email, String username, String password) {
        return dataBase.registerUser(fio, addr, polis, gender, email, username, password);
    }

    private void rememberLoginCredentials(String username, String password) {
        prefs.put("username", username);
        prefs.put("password", password);
    }

    public static boolean validatePassword(String password) {
        // Проверяем длину пароля
        if (password.length() < 4 || password.length() > 16) {
            return false;
        }

        // Проверяем наличие запрещенных символов
        if (!password.matches(".*[\\*\\&\\{\\}\\|\\+].*")) {
            return false;
        }

        // Проверяем наличие заглавных букв
        if (!password.matches(".*[A-Z].*")) {
            return false;
        }

        // Проверяем наличие цифр
        if (!password.matches(".*\\d.*")) {
            return false;
        }

        // Если все проверки пройдены, возвращаем true
        return true;
    }
}
