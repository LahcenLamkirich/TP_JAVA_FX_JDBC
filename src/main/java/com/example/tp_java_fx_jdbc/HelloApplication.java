package com.example.tp_java_fx_jdbc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("sceneBuilderUsers.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("FX_JDBC");
            stage.setScene(scene);
            stage.show();
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch();
    }
}