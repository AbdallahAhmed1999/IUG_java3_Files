/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Q5;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Abdallah_Ahmed
 */
public class MyApp extends Application {
    
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Login Page");
        stage.setScene(scene);
        stage.show();
    }
    
//    private static String getMd5(String input) {
//        try {
//            MessageDigest md = MessageDigest.getInstance("MD5");
//            byte[] messageDigest = md.digest(input.getBytes());
//            BigInteger no = new BigInteger(1, messageDigest);
//            String hashtext = no.toString(16);
//            while (hashtext.length() < 32) {
//                hashtext = "0" + hashtext;
//            }
//            return hashtext;
//        } catch (NoSuchAlgorithmException e) {
//            throw new RuntimeException(e);
//        }
//    }

    
    public static void main(String[] args) {
        launch(args);

//        File file = new File("./src/Q5/login.txt");
//        try {
//            FileWriter fw = new FileWriter(file,true);
//            PrintWriter pw = new PrintWriter(fw);
//            pw.print("ali" + "#" + getMd5("123456")+"#");
//            pw.print("omar"+"#"+getMd5("654321")+"#");
//            pw.print("ahmed" + "#" + getMd5("159357")+"#");
//            pw.print("Abdallah"+"#"+getMd5(""));
//            pw.close();
//        } catch (IOException ex) {
//            System.out.println(ex.getMessage());
//        }

    }
    
}
