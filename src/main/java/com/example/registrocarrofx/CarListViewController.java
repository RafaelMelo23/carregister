package com.example.registrocarrofx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CarListViewController {

    @FXML
    private VBox rootVBox;
    @FXML
    private Button carlistBackButton;

    private CarProfileService carProfileService = new CarProfileService();

    @FXML
    public void initialize() {
        try {
            List<CarProfile> carProfiles = carProfileService.getAllCarProfilesWithTrims();
            TabPane carTabPane = new TabPane();

            for (CarProfile carProfile : carProfiles) {
                Tab carTab = new Tab(carProfile.getModelName());
                VBox vbox = new VBox();

                TabPane trimTabPane = new TabPane();
                trimTabPane.getTabs().add(createTrimTab("Standard", carProfile));
                trimTabPane.getTabs().add(createTrimTab("Comfort", carProfile));
                trimTabPane.getTabs().add(createTrimTab("Premium", carProfile));

                vbox.getChildren().add(trimTabPane);
                carTab.setContent(vbox);
                carTabPane.getTabs().add(carTab);
            }

            rootVBox.getChildren().add(carTabPane);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Tab createTrimTab(String trimLevel, CarProfile carProfile) {
        Tab trimTab = new Tab(trimLevel);
        VBox vbox = new VBox();

        vbox.getChildren().add(new Label("Class: " + carProfile.getModelClass()));
        vbox.getChildren().add(new Label("Year: " + carProfile.getModelYear()));

        for (Trim trim : carProfile.getTrims()) {
            String trimType = trim.getTrimType();
            if (trimType != null && trimType.equalsIgnoreCase(trimLevel)) {
                if (trim.getModelHp() != null) {
                    vbox.getChildren().add(new Label("HP: " + trim.getModelHp()));
                }
                if (trim.getModelOptionals() != null) {
                    vbox.getChildren().add(new Label("Optionals: " + trim.getModelOptionals()));
                }
                if (trim.getModelColor() != null) {
                    vbox.getChildren().add(new Label("Color: " + trim.getModelColor()));
                }
                if (trim.getModelTransmission() != null) {
                    vbox.getChildren().add(new Label("Transmission: " + trim.getModelTransmission()));
                }
                if (trim.getSelectedPowertrain() != null) {
                    vbox.getChildren().add(new Label("Powertrain: " + trim.getSelectedPowertrain()));
                }
                if (trim.getSelectedEngineCylinders() != null) {
                    vbox.getChildren().add(new Label("Cylinders: " + trim.getSelectedEngineCylinders()));
                }
                if (trim.getSelectedEngineLitre() != null) {
                    vbox.getChildren().add(new Label("Engine Litre: " + trim.getSelectedEngineLitre()));
                }
                if (trim.getSelectedEngineFuel() != null) {
                    vbox.getChildren().add(new Label("Fuel: " + trim.getSelectedEngineFuel()));
                }
            }
        }
        trimTab.setContent(vbox);
        return trimTab;
    }
    public void homepageButton(ActionEvent buttonListener) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("mainpagelayout.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((javafx.scene.Node) buttonListener.getSource()).getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}