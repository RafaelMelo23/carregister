package com.example.registrocarrofx;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.sql.SQLException;
import java.util.*;

public class MainPageController implements Initializable {

    private boolean areFieldsInitialized() {
        return modelNameField != null &&
                engineFuelChoiceBox != null &&
                powertrainChoiceBox != null &&
                modelClassField != null &&
                optionalsField != null &&
                transmissionField != null &&
                yearField != null &&
                engineHpField != null &&
                colorOptionsField != null &&
                trimChoiceBox != null &&
                engineCylindersChoiceBox != null &&
                engineLitreChoiceBox != null;
    }

    @FXML
    private Button registerModelButton;
    @FXML
    private Label trimMessageLabel;
    @FXML
    private Label optionalsLabelMessage;
    @FXML
    private Label optionalsCountLabel;
    @FXML
    private TextField modelNameField;
    @FXML
    private TextField modelClassField;
    @FXML
    private TextField yearField;
    @FXML
    private TextField transmissionField;
    @FXML
    private TextField colorOptionsField;
    @FXML
    private TextField optionalsField;
    @FXML
    private TextField engineHpField;
    @FXML
    private Label colorsCountLabel;

    private String modelName;
    private String modelClass;
    private String modelYear;
    private String modelTransmission;
    private String modelColor;
    private String modelOptionals;
    private String modelHp;
    private String selectedTrim;
    private String selectedEngineLitre;
    private String selectedEngineFuel;
    private String selectedPowertrain;
    private String selectedEngineCylinders;
    private int id;
    @FXML
    private ChoiceBox<String> engineFuelChoiceBox;
    @FXML
    private ChoiceBox<String> trimChoiceBox;
    @FXML
    private ChoiceBox<String> powertrainChoiceBox;
    @FXML
    private ChoiceBox<String> engineLitreChoiceBox;
    @FXML
    private ChoiceBox<String> engineCylindersChoiceBox;

    private Map<String, Trim> trimMap = new HashMap<>();
    private List<CarProfile> carProfiles = new ArrayList<>();
    private List<String> optionalsList = new ArrayList<>();
    private List<String> colorsList = new ArrayList<>();

    private final String[] engineFuel = {"Gas", "Diesel"};
    private final String[] engineLitre = {"1.0 NA", "1.2 NA", "1.3 NA ", "1.4 NA", "1.6 NA", "1.8 NA", "2.0 NA", "2.5 NA", "1.0T", "1.2T", "1.3T", "1.4T", "1.6T", "1.8T", "2.0T", "2.5T", "3.0 NA", "5.0 NA", "6.3 NA", };
    private final String[] trims = {"Standard", "Comfort", "Premium"};
    private final String[] powertrains = {"FWD", "RWD", "AWD", "4WD"};
    private final String[] engineCylinders = {"I3", "I4", "I5", "I6", "V6", "V8", "V10", "V12"};

    private static final int MAX_OPTIONALS = 99;
    private static final int MAX_COLORS = 99;
    Gson gson = new Gson();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        engineFuelChoiceBox.getItems().addAll(engineFuel);
        trimChoiceBox.getItems().addAll(trims);
        powertrainChoiceBox.getItems().addAll(powertrains);
        engineLitreChoiceBox.getItems().addAll(engineLitre);
        engineCylindersChoiceBox.getItems().addAll(engineCylinders);

        yearField.textProperty().addListener((observableValue, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                yearField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        engineHpField.textProperty().addListener((observableValue, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                engineHpField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        trimChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> selectedTrim = newValue);
        engineFuelChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            selectedEngineFuel = newValue;
        });
        powertrainChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> selectedPowertrain = newValue);
        engineLitreChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> selectedEngineLitre = newValue);
        engineCylindersChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> selectedEngineCylinders = newValue);

        optionalsField.setOnKeyPressed(keyEvent -> {

            if (keyEvent.getCode() == KeyCode.ENTER) {


                if (optionalsList.size() > MAX_OPTIONALS) {
                    optionalsLabelMessage.setText("Error: Maximum amount of optionals!");
                    return;
                }
                String optional = optionalsField.getText();
                if (optional != null && !optional.trim().isEmpty()) {
                    optionalsList.add(optional);
                    optionalsField.clear();
                    optionalsLabelMessage.setText("Optional added: " + optional);
                    optionalsLabelCounter();

                }
            }

        });
        colorOptionsField.setOnKeyPressed(keyEvent -> {

            if (keyEvent.getCode() == KeyCode.ENTER) {


                if (colorsList.size() > MAX_COLORS) {
                    optionalsLabelMessage.setText("Error: Maximum amount of colors!");
                    return;
                }
                String colors = colorOptionsField.getText();
                if (colors != null && !colors.trim().isEmpty()) {
                    colorsList.add(colors);
                    colorOptionsField.clear();
                    optionalsLabelMessage.setText("Color added: " + colors);
                    colorsLabelCounter();

                }
            }

        });

    }

    public void colorsLabelCounter() {
        StringBuilder colorsText = new StringBuilder();
        for (String colors : colorsList) {
            colorsText.append(colors).append("\n");
        }
        colorsCountLabel.setText(colorsText.toString());
    }
    public void optionalsLabelCounter() {
        StringBuilder optionalsText = new StringBuilder();
        for (String optional : optionalsList) {
            optionalsText.append(optional).append("\n");
        }
        optionalsCountLabel.setText(optionalsText.toString());
    }

    public void modelNameTextField(KeyEvent typeListener) {
        if (areFieldsInitialized()) {
            modelName = modelNameField.getText();

        }
    }

    public void modelClassTextField(KeyEvent typeListener) {
        if (areFieldsInitialized()) {
            modelClass = modelClassField.getText();

        }
    }

    public void yearTextField(KeyEvent typeListener) {
        if (areFieldsInitialized()) {
            modelYear = yearField.getText();
        }
    }

    public void trimSelector() {
        if (areFieldsInitialized()) {
            selectedTrim = trimChoiceBox.getSelectionModel().getSelectedItem();


        }
    }

    public void engineCylindersSelector() {
        if (areFieldsInitialized()) {
            selectedEngineCylinders = engineCylindersChoiceBox.getSelectionModel().getSelectedItem();


        }
    }


    public void registerTrimButton(ActionEvent buttonListener) {

        if (modelTransmission == null || modelColor == null || modelOptionals == null || modelHp == null || selectedEngineFuel == null || selectedEngineLitre == null || selectedPowertrain == null) {
            trimMessageLabel.setText("Error: all fields must be filled out");
            return;
        }
        modelOptionals = gson.toJson(optionalsList);
        modelColor = gson.toJson(optionalsList);
        if ("Standard".equals(selectedTrim)) {
            Trim standardValues = new Trim();
            standardValues.setModelTransmission(modelTransmission);
            standardValues.setModelColor(modelColor);
            standardValues.setModelOptionals(modelOptionals);
            standardValues.setModelHp(modelHp);
            standardValues.setSelectedEngineFuel(selectedEngineFuel);
            standardValues.setSelectedEngineLitre(selectedEngineLitre);
            standardValues.setSelectedPowertrain(selectedPowertrain);
            standardValues.setSelectedEngineCylinders(selectedEngineCylinders);
            trimMap.put(selectedTrim, standardValues);
            trimMessageLabel.setText("Standard trim set successfully!");
            clearAllFields();

        } else if ("Comfort".equals(selectedTrim)) {
            Trim comfortValues = new Trim();
            comfortValues.setModelTransmission(modelTransmission);
            comfortValues.setModelColor(modelColor);
            comfortValues.setModelOptionals(modelOptionals);
            comfortValues.setModelHp(modelHp);
            comfortValues.setSelectedEngineFuel(selectedEngineFuel);
            comfortValues.setSelectedEngineLitre(selectedEngineLitre);
            comfortValues.setSelectedPowertrain(selectedPowertrain);
            comfortValues.setSelectedEngineCylinders(selectedEngineCylinders);
            trimMap.put(selectedTrim, comfortValues);
            trimMessageLabel.setText("Comfort trim set successfully!");
            clearAllFields();

        } else if ("Premium".equals(selectedTrim)) {
            Trim premiumValues = new Trim();
            premiumValues.setModelTransmission(modelTransmission);
            premiumValues.setModelColor(modelColor);
            premiumValues.setModelOptionals(modelOptionals);
            premiumValues.setModelHp(modelHp);
            premiumValues.setSelectedEngineFuel(selectedEngineFuel);
            premiumValues.setSelectedEngineLitre(selectedEngineLitre);
            premiumValues.setSelectedPowertrain(selectedPowertrain);
            premiumValues.setSelectedEngineCylinders(selectedEngineCylinders);
            trimMap.put(selectedTrim, premiumValues);
            trimMessageLabel.setText("Premium trim set successfully!");
            clearAllFields();

        }
    }

    public void clearAllFields() {
        optionalsField.clear();
        engineHpField.clear();
        transmissionField.clear();
        colorOptionsField.clear();
        engineCylindersChoiceBox.setValue(null);
        engineLitreChoiceBox.setValue(null);
        engineFuelChoiceBox.setValue(null);
        trimChoiceBox.setValue(null);
        powertrainChoiceBox.setValue(null);
    }

    public void clearOptionalsMessages() {
        optionalsCountLabel.setText(null);
        optionalsLabelMessage.setText(null);
    }


    public void engineFuelSelector() {
        if (areFieldsInitialized()) {
            selectedEngineFuel = engineFuelChoiceBox.getSelectionModel().getSelectedItem();
        }
    }

    public void engineLitreSelector() {
        if (areFieldsInitialized()) {
            selectedEngineLitre = engineLitreChoiceBox.getSelectionModel().getSelectedItem();
        }
    }

    public void powertrainSelector() {
        if (areFieldsInitialized()) {
            selectedPowertrain = powertrainChoiceBox.getSelectionModel().getSelectedItem();
        }
    }

    public void engineHpTextField(KeyEvent typeListener) {
        if (areFieldsInitialized()) {
            modelHp = engineHpField.getText();

        }
    }

    public void transmissionTextField(KeyEvent typeListener) {
        if (areFieldsInitialized()) {
            modelTransmission = transmissionField.getText();
        }
    }

    public void colorOptionsTextField(KeyEvent typeListener) {
        if (areFieldsInitialized()) {
            modelColor = colorOptionsField.getText();
        }
    }

    public void optionalsTextField(KeyEvent EnterListener) {
        if (areFieldsInitialized()) {
            modelOptionals = optionalsField.getText();
        }
    }

    @FXML
    public void modelsDatabaseButton(ActionEvent buttonListener) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("carlistview.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((javafx.scene.Node) buttonListener.getSource()).getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void createCarProfile(ActionEvent buttonListener) {
        if (areFieldsInitialized()) {
            if (modelNameField.getText().isEmpty() || modelClassField.getText().isEmpty() || yearField.getText().isEmpty()) {
                registerModelButton.setText("Please fill in all fields!");
                PauseTransition pause = new PauseTransition(Duration.seconds(3));
                pause.setOnFinished(event -> registerModelButton.setText("Register Model"));
                pause.play();
                return;
            }
            CarProfileDAO dao = new CarProfileDAO();
            try {

                CarProfile car = new CarProfile(id, modelName, modelClass, modelYear);
                dao.createCarProfile(car);


                Trim standardValues = trimMap.get("Standard");
                if (standardValues != null) {
                    standardValues.setTrimType("Standard");
                    dao.createTrim(standardValues, car.getId());

                }

                Trim comfortValues = trimMap.get("Comfort");
                if (comfortValues != null) {
                    comfortValues.setTrimType("Comfort");
                    dao.createTrim(comfortValues, car.getId());
                }

                Trim premiumValues = trimMap.get("Premium");
                if (premiumValues != null) {
                    premiumValues.setTrimType("Premium");
                    dao.createTrim(premiumValues, car.getId());
                }

                carProfiles.add(car);
                String originalText = registerModelButton.getText();
                registerModelButton.setText("Model Registered Successfully!");
                clearOptionalsMessages();

                PauseTransition pause = new PauseTransition(Duration.seconds(3));
                pause.setOnFinished(event -> registerModelButton.setText(originalText));
                pause.play();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}