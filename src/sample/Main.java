package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Main extends Application{

    Button login;
    Button registration;
    Stage window;

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;

        window.setTitle("SecureNotePad");

        Registration reg = new Registration();
        Login log = new Login();

        Label label = new Label("Welcome to SecureNotePad");

        login = new Button();
        login.setText("Login");
        login.setOnAction(event -> log.logInVerification(window));


//        login.setOnAction(event -> t.print("1"));

        registration = new Button();
        registration.setText("Registration");
        registration.setOnAction(event -> reg.register(window));

        VBox layout = new VBox(20);
        layout.getChildren().addAll(label, login, registration);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 300, 250);
        window.setScene(scene);
        window.show();

    }

}
