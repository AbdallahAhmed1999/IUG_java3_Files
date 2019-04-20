/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Q5;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Abdallah_Ahmed
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    void cancelHandler(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void okHandler(ActionEvent event) {
        if (checkPassword(usernameTextField.getText(), passwordTextField.getText())) {
            try {
                Parent optionParent = FXMLLoader.load(getClass().getResource("Option.fxml"));
                Scene optionScene = new Scene(optionParent);
                Stage optionStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                optionStage.setTitle("Option Page");
                optionStage.setScene(optionScene);
                optionStage.show();
            } catch (IOException ex) {
            }
        }else{
            Alert a1 = new Alert(Alert.AlertType.ERROR);
            a1.setContentText("Invalid username or password");
            a1.show();
        }

    }

    private boolean checkPassword(String username, String password) {
        FileReader fr;
        try {
            File file = new File("./src/Q5/login.txt");
            fr = new FileReader(file);
            Scanner scanner = new Scanner(fr);
            String s[] = null;
            while (scanner.hasNext()) {
                s = scanner.next().split("#");
            }
            fr.close();
            for (int i = 0; i < s.length; i++) {
                if (username.equalsIgnoreCase(s[i]) && getMd5(password).equalsIgnoreCase(s[i+1])) {
                    return true;
                }
            }
        } catch (FileNotFoundException ex) {

        } catch (IOException ex) {

        }
        return false;
    }

    private static String getMd5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
