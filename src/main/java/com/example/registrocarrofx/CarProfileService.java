package com.example.registrocarrofx;

import java.sql.SQLException;
import java.util.List;

public class CarProfileService {

    private CarProfileDAO carProfileDAO = new CarProfileDAO();

    public List<CarProfile> getAllCarProfilesWithTrims() throws SQLException {
        return carProfileDAO.getAllCarProfilesWithTrims();
    }
}
