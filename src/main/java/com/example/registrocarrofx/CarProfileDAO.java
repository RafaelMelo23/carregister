package com.example.registrocarrofx;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CarProfileDAO {
    private Connection conn;


    public void createCarProfile(CarProfile car) throws SQLException {
        String sql = "INSERT INTO carprofile (modelName, modelClass, modelYear) VALUES(?, ?, ?)";

        try (Connection conn = MySQLConnection.getMySqlConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, car.getModelName());
            pstmt.setString(2, car.getModelClass());
            pstmt.setString(3, car.getModelYear());
            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    car.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    public void createTrim(Trim trim, int carProfileId) throws SQLException {
        String sql = "INSERT INTO trim (carProfileId, trimType, modelColor, modelHp, modelTransmission, modelOptionals, selectedEngineFuel, " +
                "selectedEngineLitre, selectedPowertrain, selectedEngineCylinders) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = MySQLConnection.getMySqlConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, carProfileId);
            pstmt.setString(2, trim.getTrimType());
            pstmt.setString(3, trim.getModelColor());
            pstmt.setString(4, trim.getModelHp());
            pstmt.setString(5, trim.getModelTransmission());
            pstmt.setString(6, trim.getModelOptionals());
            pstmt.setString(7, trim.getSelectedEngineFuel());
            pstmt.setString(8, trim.getSelectedEngineLitre());
            pstmt.setString(9, trim.getSelectedPowertrain());
            pstmt.setString(10, trim.getSelectedEngineCylinders());
            pstmt.executeUpdate();
        }
    }

    public void updateCarProfile(CarProfile car) throws SQLException {
        String sql = "UPDATE CarProfile SET modelName = ?, modelClass = ?, modelYear = ? WHERE id = ?";
        try (Connection conn = MySQLConnection.getMySqlConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, car.getModelName());
            pstmt.setString(2, car.getModelClass());
            pstmt.setString(3, car.getModelYear());
            pstmt.setInt(4, car.getId());
            pstmt.executeUpdate();
        }
    }

    public void deleteCarProfile(int id) throws SQLException {
        String sql = "DELETE FROM CarProfile WHERE id = ?";
        try (Connection conn = MySQLConnection.getMySqlConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    public List<CarProfile> getAllCarProfilesWithTrims() throws SQLException {
        List<CarProfile> carProfiles = new ArrayList<>();
        String sql = "SELECT cp.id AS carProfileId, cp.modelName, cp.modelClass, cp.modelYear, " +
                "t.trimType, t.modelColor, t.modelHp, t.modelTransmission, t.modelOptionals, " +
                "t.selectedEngineFuel, t.selectedEngineLitre, t.selectedPowertrain, t.selectedEngineCylinders " +
                "FROM carprofile cp " +
                "LEFT JOIN trim t ON cp.id = t.carProfileId";

        try (Connection conn = MySQLConnection.getMySqlConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            Map<Integer, CarProfile> carProfileMap = new HashMap<>();

            while (rs.next()) {
                int carProfileId = rs.getInt("carProfileId");
                CarProfile carProfile = carProfileMap.get(carProfileId);

                if (carProfile == null) {
                    String modelName = rs.getString("modelName");
                    String modelClass = rs.getString("modelClass");
                    String modelYear = rs.getString("modelYear");
                    carProfile = new CarProfile(carProfileId, modelName, modelClass, modelYear);
                    carProfileMap.put(carProfileId, carProfile);
                    carProfiles.add(carProfile);
                }

                String trimType = rs.getString("trimType");
                if (trimType != null) {
                    Trim trim = new Trim();
                    trim.setTrimType(trimType);
                    trim.setModelColor(rs.getString("modelColor"));
                    trim.setModelHp(rs.getString("modelHp"));
                    trim.setModelTransmission(rs.getString("modelTransmission"));
                    trim.setModelOptionals(rs.getString("modelOptionals"));
                    trim.setSelectedEngineFuel(rs.getString("selectedEngineFuel"));
                    trim.setSelectedEngineLitre(rs.getString("selectedEngineLitre"));
                    trim.setSelectedPowertrain(rs.getString("selectedPowertrain"));
                    trim.setSelectedEngineCylinders(rs.getString("selectedEngineCylinders"));

                    carProfile.addTrim(trim);
                }
            }
        }
        return carProfiles;
    }
}
