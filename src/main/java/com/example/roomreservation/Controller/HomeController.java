package com.example.roomreservation.Controller;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;


public class HomeController implements Initializable {

    public static String name;

    @FXML
    void booking(ActionEvent event) throws IOException {
        String win = "booking";
        Closing.closeWindow(event,win);
    }


    @FXML
    void cancelBookings(ActionEvent event) throws IOException {
        String win = "cancelBooking";
        Closing.closeWindow(event,win);
    }


    @FXML
    void showRooms(ActionEvent event) throws IOException {
        String win = "reserve";
        Closing.closeWindow(event,win);
    }


    @FXML
    void Logout(ActionEvent event) throws IOException {
        String win = "login";
        Closing.closeWindow(event,win);
    }


    @FXML
    void adminLog(ActionEvent event) throws IOException {
        String win = "adminLogin";
        Closing.closeWindow(event,win);
    }



    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


}
