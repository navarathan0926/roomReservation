package com.example.roomreservation.Controller;


import com.example.roomreservation.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class AdminController {


        @FXML
        private Label settinnloginlable;

        @FXML
        private PasswordField pass;

        @FXML
        private TextField uname;

        @FXML
        void settingLogin(ActionEvent event) {

            DatabaseConnection connection = new DatabaseConnection();
            Connection connectDB = connection.getConnection();

            String verifyLogin = "SELECT count(1) FROM admin WHERE user_name = '" + uname.getText() + "' AND password  = '" + pass.getText() + "'";
            try {
                Statement statement = connectDB.createStatement();
                ResultSet queryResult = statement.executeQuery(verifyLogin);
                while (queryResult.next()) {
                    if (queryResult.getInt(1) == 1) {
                        String win = "adminDashboard";
                        Closing.closeWindow(event,win);
                    } else {
                        settinnloginlable.setText("please enter correct user name and password");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();
            }
        }
}



