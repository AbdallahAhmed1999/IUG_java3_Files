/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Q3;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author Abdallah_Ahmed
 */
public class FXMLbinaryController implements Initializable {

    private File file;

    @FXML
    private Label fileLable;

    @FXML
    private void selectedHandler(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        file = fileChooser.showOpenDialog(null);
        fileLable.setText("selecte file : " + file.getName());
    }

    @FXML
    private void binaryToTextHandler(ActionEvent event) {
        try {
            StringBuilder sb = new StringBuilder();
            DataInputStream input = new DataInputStream(new FileInputStream(file));
            try {
                while (true) {
                    sb.append((char) input.readByte());
                }
            } catch (EOFException eof) {
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            StringBuilder sb2 = new StringBuilder();
            for (int i = 0; i < sb.length(); i += 7) {
                try {
                    sb2.append((char) Long.parseLong(sb.toString().substring(i, i + 7), 2));
                } catch (IndexOutOfBoundsException | NumberFormatException e) {
                    System.out.println(e.getMessage());
                    break;
                }
            }
            File savedFile = null;
            try{
                FileChooser chooser2 = new FileChooser();
                chooser2.setTitle("Save");
                savedFile = chooser2.showSaveDialog(null);
            }catch(Exception e){
            
            }
            if (savedFile != null) {
                try {
                    FileWriter fileWriter = new FileWriter(savedFile);
                    PrintWriter printWriter = new PrintWriter(fileWriter);
                    printWriter.write(sb2.toString());
                    printWriter.close();
                    Alert a1 = new Alert(Alert.AlertType.INFORMATION);
                    a1.setContentText("The File " + file.getName() + " Converted to text Successfully");
                    a1.show();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (FileNotFoundException | NullPointerException e2) {
            Alert a1 = new Alert(Alert.AlertType.WARNING);
            a1.setContentText("Please Select the File");
            a1.show();
        }
    }

    @FXML
    private void textToBinaryHandler(ActionEvent event) {
        try {
            StringBuilder sb = new StringBuilder();
            DataInputStream input = new DataInputStream(new FileInputStream(file));
            try {
                while (true) {
                    sb.append(Integer.toBinaryString(input.readByte()));
                }
            } catch (EOFException eof) {
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            File savedFile = null;
            try {
                FileChooser chooser2 = new FileChooser();
                chooser2.setTitle("Save");
                savedFile = chooser2.showSaveDialog(null);
            } catch (Exception e) {

            }
            if (savedFile != null) {
                try {
                    FileWriter fileWriter = new FileWriter(savedFile);
                    PrintWriter printWriter = new PrintWriter(fileWriter);
                    printWriter.write(sb.toString());
                    printWriter.close();
                    Alert a1 = new Alert(Alert.AlertType.INFORMATION);
                    a1.setContentText("The File " + file.getName() + " Converted to Binary Successfully");
                    a1.show();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }

        } catch (FileNotFoundException | NullPointerException e2) {
            Alert a1 = new Alert(Alert.AlertType.WARNING);
            a1.setContentText("Please Select the File");
            a1.show();
        }

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
