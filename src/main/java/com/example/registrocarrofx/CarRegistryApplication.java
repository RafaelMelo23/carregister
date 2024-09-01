package com.example.registrocarrofx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class CarRegistryApplication extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(CarRegistryApplication.class.getResource("mainpagelayout.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        primaryStage.setTitle("Car Registry System");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch();
    }


}