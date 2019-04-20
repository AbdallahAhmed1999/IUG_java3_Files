/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Q4;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Abdallah_Ahmed
 */
public class Main extends Application {

    @FXML
    private TextField noTextFiled;

    @FXML
    private TextField nameTextFiled;

    @FXML
    private TextField balanceTextField;

    @FXML
    private TextArea textArea;

    private Account account;


    public class AppendableObjectOutputStream extends ObjectOutputStream {

        public AppendableObjectOutputStream(OutputStream out) throws IOException {
            super(out);
        }

        @Override
        protected void writeStreamHeader() throws IOException {

        }
    }
    
    class AppendableObjectInputStream extends ObjectInputStream{

        public AppendableObjectInputStream(InputStream in) throws IOException {
            super(in);
        }

        @Override
        protected void readStreamHeader() throws IOException, StreamCorruptedException {

        }
    }

    @FXML
    void writeHandler() {
        if (!noTextFiled.getText().equals("")
                && !nameTextFiled.getText().equals("") && !balanceTextField.getText().equals("")) {

            try {
//                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("./src/Q4/Account.ser"));
                AppendableObjectOutputStream oos = new AppendableObjectOutputStream(new FileOutputStream("./src/Q4/Account.ser", true));
                account = new Account();
                account.setNo(Integer.parseInt(noTextFiled.getText()));
                account.setName(nameTextFiled.getText());
                account.setBalance(Double.parseDouble(balanceTextField.getText()));
                oos.writeObject(account);
                oos.flush();
                oos.close();
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText("The Object was Written Successfully");
                a.show();
                noTextFiled.setText("");
                nameTextFiled.setText("");
                balanceTextField.setText("");
            } catch (Exception e) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setContentText("Invalid Data");
                a.show();
            }
        } else {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Please Fill the Forms");
            a.show();
        }

    }

    @FXML
    void readHandler() {
        try {
//            ObjectInputStream ois = new ObjectInputStream(
//                    new FileInputStream("./src/Q4/Account.ser"));
            AppendableObjectInputStream ois = new AppendableObjectInputStream(new FileInputStream("./src/Q4/Account.ser"));
            String s1 = String.format("Account : \n%-10s %-10s %-10s\n","NO","Name","Balance");
            textArea.setText(s1);
            while (true) {
                try {
                    Account a1 = (Account) ois.readObject();
                    int x = a1.getName().length();
                    String s = String.format("%06d | %-15s | %2.2f",a1.getNo(),a1.getName(),a1.getBalance());
                    textArea.appendText(s+"\n");
                } catch (EOFException e) {
                    break;
                }
            }
            ois.close();
        } catch (ClassNotFoundException | IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLReadWriteObject.fxml"));
        loader.setController(this);
        Scene scene = new Scene(loader.load());
        primaryStage.setTitle("Read / Write Objects");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
