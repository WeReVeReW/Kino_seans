package com.example.kino_seans;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ControllerLogin {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button loginAuthButton;

    @FXML
    private TextField login_field;

    @FXML
    private PasswordField password_field;

    @FXML
    private AnchorPane signIn_form;

    @FXML
    private Button signin_close_button;

    @FXML
    private Button signin_minimaze;

    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    public void signin() throws SQLException, ClassNotFoundException {

        String sql = "SELECT * FROM user_admin WHERE username = ? and password = ?";

        connect = Database.connectDb();

        try{

            prepare = connect.prepareStatement(sql);
            prepare.setString(1,login_field.getText());
            prepare.setString(2,password_field.getText());

            result = prepare.executeQuery();

            Alert alert;

            if(login_field.getText().isEmpty() || password_field.getText().isEmpty()){

                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Сообщение об ошибке");
                alert.setHeaderText(null);
                alert.setContentText("Пожалуйста, заполните все поля!");
                alert.showAndWait();

            } else{
                if(result.next()){

                    getData.username =login_field.getText();

                    loginAuthButton.getScene().getWindow().hide();

                    Parent root = FXMLLoader.load(getClass().getResource("app.fxml"));

                    Stage stage = new Stage();
                    Scene scene = new Scene(root);

                    stage.initStyle(StageStyle.TRANSPARENT);


                    AtomicReference<Double> x = new AtomicReference<>((double) 0);
                    AtomicReference<Double> y = new AtomicReference<>((double) 0);
                    root.setOnMousePressed((MouseEvent event) ->{

                        x.set(event.getSceneX());
                        y.set(event.getSceneY());

                    });

                    root.setOnMouseDragged((MouseEvent event) -> {

                        stage.setX(event.getScreenX() - x.get());
                        stage.setY(event.getScreenY() - y.get());
                    });

                    stage.setScene(scene);
                    stage.show();

                } else {

                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Сообщение об ошибке");
                    alert.setHeaderText(null);
                    alert.setContentText("Неправильное Имя пользователя или Пароль");
                    alert.showAndWait();

                }
            }
        }catch (Exception e){e.printStackTrace();}
    }

    public void signIn_close(){
        System.exit(0);
    }

    public void signIn_minimaze(){
        Stage stage = (Stage)signIn_form.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    void initialize() {

    }
}
