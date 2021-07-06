package sample;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class NotePad {
    public NotePad(Stage padStage, String email, JDBC jdbc){
        MenuBar menuBar = new MenuBar();

        padStage.setTitle("NotePad");

        Menu file = new Menu("File");
        Menu edit = new Menu("Edit");
        Menu format = new Menu("Format");
        Menu view = new Menu("View");
        Menu help = new Menu("Help");

        MenuItem New = new MenuItem("New");
        MenuItem Open = new MenuItem("Open");
        MenuItem Save = new MenuItem("Save");

        file.getItems().addAll(New, Open, Save);

        menuBar.getMenus().addAll(file, edit, format, view, help);

        TextField titleInput = new TextField();
        titleInput.setPromptText("Title");

        TextArea textArea = new TextArea();
        textArea.setMaxWidth(600);
        textArea.setMaxHeight(400);

        New.setOnAction(e -> { newBtn(textArea);});

        Save.setOnAction(e -> { jdbc.saveDoc(textArea.getText(), titleInput.getText(), email);});

        GridPane gridPane = new GridPane();
        gridPane.add(menuBar, 0 ,0);
        gridPane.add(titleInput, 0, 1);
        gridPane.add(textArea, 0, 2);

        Group group = new Group();
        group.getChildren().add(gridPane);

        Scene scene = new Scene(group);
        padStage.setMaxWidth(600);
        padStage.setMaxHeight(500);
        padStage.setScene(scene);
        padStage.show();

    }

    public void newBtn(TextArea textArea){
        textArea.clear();
    }

}
