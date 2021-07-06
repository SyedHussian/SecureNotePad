package sample;

import javafx.css.Match;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

import java.util.jar.Attributes;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Registration {

    public void register(Stage regStage) {
        Main main = new Main();

        regStage.setTitle("Registration");
        regStage.setMinWidth(300);


        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        Label label = new Label();
        label.setText("Please fill up all the boxes.");
        GridPane.setConstraints(label, 1, 0);

        //First Name label
        Label fnameLabel = new Label("First Name");
        GridPane.setConstraints(fnameLabel, 0, 1);

        //First Name input
        TextField fnameInput = new TextField();
        fnameInput.setPromptText("First Name");
        GridPane.setConstraints(fnameInput, 1, 1);

        //Last Name label
        Label lnameLabel = new Label("Last Name");
        GridPane.setConstraints(lnameLabel, 0, 2);

        //Name input
        TextField lnameInput = new TextField();
        lnameInput.setPromptText("Last Name");
        GridPane.setConstraints(lnameInput, 1, 2);

        //email label
        Label emailLabel = new Label("Email");
        GridPane.setConstraints(emailLabel, 0, 3);

        //email input
        TextField emailInput = new TextField();
        emailInput.setPromptText("abc@abc.abc");
        GridPane.setConstraints(emailInput, 1, 3);

        //Pass label
        Label passLabel = new Label("Password");
        GridPane.setConstraints(passLabel, 0, 4);

        //Pass input
        PasswordField passInput = new PasswordField();
        passInput.setPromptText("Exactly 4 digit");
        GridPane.setConstraints(passInput, 1, 4);

        Button signUpBtn = new Button("Sign Up");
        GridPane.setConstraints(signUpBtn, 1, 6);
        signUpBtn.setOnAction(e -> {
            if (isName(fnameInput, lnameInput) && isEmail(emailInput) && isPassword(passInput)) {
                // save the values in database
                JDBC jdbc = new JDBC();

                if (!jdbc.checkforDup(emailInput.getText())) {
                    try {
                        main.start(regStage);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }

                jdbc.insert(fnameInput.getText(), lnameInput.getText(), emailInput.getText(), passInput.getText());

                try {
                    main.start(regStage);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });


        Button backBtn = new Button("Back to Main");
        GridPane.setConstraints(backBtn, 1, 7);
        backBtn.setOnAction(e -> {
            try {
                main.start(regStage);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });


        grid.getChildren().addAll(label, fnameLabel, fnameInput, lnameLabel, lnameInput, emailLabel, emailInput, passLabel, passInput, signUpBtn, backBtn);

        Scene scene = new Scene(grid, 300, 250);
        regStage.setScene(scene);
        regStage.show();
    }

    public boolean isName(TextField fname, TextField lname){
        String regex = "^[A-Za-z]\\w{2,29}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher fmatcher = pattern.matcher(fname.getText());
        Matcher lmatcher = pattern.matcher(lname.getText());
        if(fmatcher.matches() && lmatcher.matches()) {
            return true;
        }
        else {
            AlertBox.display("Incorrect Name!", "Please type in both the first and last name. " +
                    "Minimum 3 characters and only letters are allowed! Please try again.");
            return false;
        }
    }

    public boolean isEmail(TextField email){
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email.getText());
        if(matcher.matches()) {
            return true;
        }
        else {
            AlertBox.display("Incorrect Email!", "Email address has to be in the form of abc@abc.abc! Please try again.");
            return false;
        }
    }

    public boolean isPassword(TextField password){
        String regex = "\\d{4}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password.getText());
        if (matcher.matches()) {
            return true;
        }
        else {
            AlertBox.display("Incorrect Password!", "Password has to be exactly 4 digit! Please try again.");
            return false;
        }
    }

}

