/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Q2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;
import java.util.Scanner;
import javafx.application.Application;  
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author Abdallah_Ahmed
 */
public class FileView2 extends Application {

    @FXML
    private TextArea textArea;

    @FXML
    private void openHandler() {
        textArea.setEditable(true);
        FileChooser chooser = new FileChooser();
        File selectedFile = chooser.showOpenDialog(null);
        Scanner s;
        try {
            s = new Scanner(selectedFile);
            while (s.hasNext()) {
                textArea.appendText(s.nextLine() + "\n");
            }
        } catch (Exception ex) {
            System.out.println("Exception");
        }

    }

    @FXML
    private void saveHandler() {
        FileChooser chooser2 = new FileChooser();
        chooser2.setTitle("Save");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("text files (*.txt)", "*.txt");
        chooser2.getExtensionFilters().add(extFilter);
        File savedFile = chooser2.showSaveDialog(null);
        if (savedFile != null) {
            try {
                FileWriter fileWriter = new FileWriter(savedFile);
                PrintWriter printWriter = new PrintWriter(fileWriter);
                printWriter.write(textArea.getText());
                printWriter.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    @FXML
    private void closeHandler() {
        textArea.setEditable(false);
        textArea.setText("");
    }

    @FXML
    private void exitHandler() {
        System.exit(0);
    }

    @FXML
    private void fontHandler() {
        Integer[] fonts = {12, 14, 18, 24, 30, 36, 48, 60, 72, 84};
        ChoiceDialog<Integer> cs = new ChoiceDialog<>(fonts[1], fonts);
        cs.setTitle("Font Selection");
        cs.setHeaderText("Select the font size");
        cs.setContentText("font size : ");
        Optional<Integer> fontSize = cs.showAndWait();
        try {
            textArea.setFont(new Font(fontSize.get()));
        } catch (Exception e) {
            System.out.println("Font Error");
        }
    }

    @FXML
    private void colorHandler() {
        ChoiceDialog<String> cs = new ChoiceDialog<>("Red", "Green", "Blue", "Yellow", "Black", "Cyan", "Gray", "Brown");
        cs.setTitle("Color Selection");
        cs.setHeaderText("Select the Font Color");
        cs.setContentText("font color : ");
        Optional<String> color = cs.showAndWait();
        try{
            textArea.setStyle("-fx-text-inner-color:" + color.get());
        }catch(Exception e){
            System.out.println("color Error");
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FileViewFXML2.fxml"));
        loader.setController(this);
        Parent parent = loader.load();
        Scene scene = new Scene(parent, 593, 393);
        primaryStage.setTitle("File View");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
