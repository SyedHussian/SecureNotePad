package sample;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Login {

    public void logInVerification(Stage logStage) {
        Main main = new Main();
        JDBC jdbc = new JDBC();

        logStage.setTitle("Login");
        logStage.setMinWidth(300);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        //email label
        Label emailLabel = new Label("Email");
        GridPane.setConstraints(emailLabel, 0, 1);

        //email input
        TextField emailInput = new TextField();
        GridPane.setConstraints(emailInput, 1, 1);

        //password label
        Label passLabel = new Label("Password");
        GridPane.setConstraints(passLabel, 0, 2);

        //password input
        PasswordField passInput = new PasswordField();
        GridPane.setConstraints(passInput, 1, 2);

        Button signInBtn = new Button("Sign In");
        GridPane.setConstraints(signInBtn, 1, 4);
        signInBtn.setOnAction(e -> {
            //verify if the email and password match the database
            if (jdbc.verifyForAcc(emailInput.getText(), passInput.getText())){
                System.out.println("successful!");
                NotePad notePad = new NotePad(logStage, emailInput.getText(), jdbc);
            }
        });

        Button backBtn = new Button("Back to Main");
        GridPane.setConstraints(backBtn, 1, 5);
        backBtn.setOnAction(e -> {
            try {
                main.start(logStage);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        grid.getChildren().addAll(emailLabel, emailInput, passLabel, passInput, signInBtn, backBtn);

        Scene scene = new Scene(grid, 300, 250);
        logStage.setScene(scene);
        logStage.show();

    }

}
