package com.example.tp_java_fx_jdbc;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class AddUserController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField adresseTextField;

    @FXML
    private Button btnSave;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField functionTextField;

    @FXML
    private TextField nomTextField;

    @FXML
    private TextField prenomTextField;

    @FXML
    private TextField telTextField;

    @FXML
    void save(MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    // la connection avec la base de donnes :
    public Connection getConnection() {
        Connection connection ;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_users",
                    "root","");
            return connection ;
        } catch (Exception e) {
            e.printStackTrace();
            return null ;
        }
    }
    // la declaration de la fonction save to add data to the database :
    @FXML
    public void save(ActionEvent event) {
        try {

            String nom = nomTextField.getText();
            String prenom = prenomTextField.getText();
            String adresse = adresseTextField.getText();
            String tel = telTextField.getText();
            String email = emailTextField.getText();
            String fonction = functionTextField.getText() ;

            if(nom.isEmpty() || prenom.isEmpty() || adresse.isEmpty() || tel.isEmpty() || email.isEmpty() || fonction.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR) ;
                alert.setContentText("Please Fill in the blanks !");
                alert.showAndWait();
            } else {
                try {
                    String query = "INSERT INTO Users VALUES (?,?,?,?,?,?)";
                    PreparedStatement preparedStatement = getConnection().prepareStatement(query) ;
                    preparedStatement.setString(1, nom);
                    preparedStatement.setString(2, prenom);
                    preparedStatement.setString(3, adresse);
                    preparedStatement.setString(4, tel);
                    preparedStatement.setString(5, email);
                    preparedStatement.setString(6, fonction);
                    int i=preparedStatement.executeUpdate();
                    System.out.println(i+" records inserted");
                } catch(Exception e ) {
                    e.printStackTrace();
                }

            }

        } catch(Exception e ) {
            e.printStackTrace();
        }

    }

}
