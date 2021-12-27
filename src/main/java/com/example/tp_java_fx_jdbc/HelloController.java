package com.example.tp_java_fx_jdbc;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class HelloController implements Initializable{

        @FXML
        private Button btnAdd;

        @FXML
        private Button btnDelete;

        @FXML
        private Button btnResearch;

        @FXML
        private Button btnTrie;

        @FXML
        private Button btnGetItems;

        @FXML
        private TableColumn<Users, String> nomTableViewId;

        @FXML
        private TableColumn<Users, String> prenomTableViewId;

        @FXML
        private TableColumn<Users, String> telTableViewId;

        @FXML
        private TableView<Users> tableViewId;

        @FXML
        private TableColumn<Users, String> adresseTableViewId;

        @FXML
        private TableColumn<Users,String> emailTableViewId;

        @FXML
        private TableColumn<Users, String> fonctionTableViewId;

        @FXML
        private TextField textSearch ;

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle){
            showUsers();
        }

        @FXML
        public void Add(ActionEvent event) {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("AddSceneBuilder.fxml"));
            try {
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage() ;
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        @FXML
        public void delete(ActionEvent event) {
            Users user = tableViewId.getSelectionModel().getSelectedItem();
            String query = "DELETE FROM Users WHERE nom = ?";
            try {
                PreparedStatement preparedStatement = getConnection().prepareStatement(query);
                preparedStatement.setString(1, user.getNom());
                int i=preparedStatement.executeUpdate();
                System.out.println(i+" records deleted");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // la declaration de la fonction search :
        @FXML
        public void search(ActionEvent event) {
            String Query = "SELECT * FROM Users WHERE nom = ? " ;
            String textFSearch = textSearch.getText() ;
            Users user ;
            if(textFSearch.isEmpty()) {
                    showUsers();
            } else {
                try {
                        PreparedStatement preparedStatement = getConnection().prepareStatement(Query) ;
                        preparedStatement.setString(1, textFSearch);
                        ResultSet resultSet = preparedStatement.executeQuery() ;
                        ObservableList<Users> UserList = FXCollections.observableArrayList() ;
                        while (resultSet.next()){
                            user = new Users(resultSet.getString("nom"),resultSet.getString("prenom"),
                                    resultSet.getString("adresse"), resultSet.getString("tel"),
                                    resultSet.getString("email"), resultSet.getString("function"));
                            UserList.add(user);
                            tableViewId.setItems(UserList);
                        }

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
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

       public ObservableList<Users> getUserList() {
               ObservableList<Users> UserList = FXCollections.observableArrayList() ;
               Connection connection = getConnection();
               String Query = "SELECT * FROM Users" ;
               Statement statement ;
               ResultSet resultSet ;

       try {
            statement = connection.createStatement();
            resultSet =  statement.executeQuery(Query) ;
            Users user ;
                    while(resultSet.next()) {
                        user = new Users(resultSet.getString("nom"),resultSet.getString("prenom"),
                                resultSet.getString("adresse"), resultSet.getString("tel"),
                                resultSet.getString("email"), resultSet.getString("function"));
                        UserList.add(user);
                        /* show this in the terminal */
    //                    System.out.println("The name is " + resultSet.getString("nom") + " The prenom is : " + resultSet.getString("prenom")+ "the adresse is " +
    //                            resultSet.getString("adresse") + " the tel is " + resultSet.getString("tel") + " the email is :" + resultSet.getString("email")+
    //                            " And the function is " + resultSet.getString("function"));
                        /* And we stopped here */

                        System.out.println("The user have been added sucessfully to the list ") ;
                     }
               }catch(Exception e) {
                        e.printStackTrace();
               }
            return UserList ;
       }

       public void showUsers() {

           System.out.println("I want to make a test here ");
           ObservableList<Users> list = getUserList() ;
           nomTableViewId.setCellValueFactory(new PropertyValueFactory<Users,String>("nom"));
           prenomTableViewId.setCellValueFactory(new PropertyValueFactory<Users, String>("prenom"));
           adresseTableViewId.setCellValueFactory(new PropertyValueFactory<Users, String>("adresse"));
           telTableViewId.setCellValueFactory(new PropertyValueFactory<Users, String>("tel"));
           emailTableViewId.setCellValueFactory(new PropertyValueFactory<Users, String>("email"));
           fonctionTableViewId.setCellValueFactory(new PropertyValueFactory<Users, String>("function"));

           tableViewId.setItems(list);
           System.out.println("M just testing github here !");

       }

}

