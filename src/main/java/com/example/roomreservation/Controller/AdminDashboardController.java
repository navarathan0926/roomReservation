package com.example.roomreservation.Controller;

import com.example.roomreservation.DAO.BookingsDAO;
import com.example.roomreservation.DAO.RoomsDAO;
import com.example.roomreservation.DAO.usersDAO;
import com.example.roomreservation.entity.BookingsEntity;
import com.example.roomreservation.entity.RoomsEntity;
import com.example.roomreservation.entity.UserEntity;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class AdminDashboardController implements Initializable {

    public static String name;

    @FXML
    private Label pendinglbl, bookingslbl, guestlbl, roomslbl;


    ObservableList<UserEntity> uList;
    ObservableList<RoomsEntity> rList;

    ObservableList<BookingsEntity> BookList;

    DatabaseConnection connection= new DatabaseConnection();
    Connection connectDB= connection.getConnection();
    Statement statement = connectDB.createStatement();

    public AdminDashboardController() throws SQLException {
    }

    @FXML
    void pendingAmounts(ActionEvent event) throws IOException {
    }

    @FXML
    void showTotalGuests(ActionEvent event) throws IOException{
        String win = "showGuest";
        Closing.closeWindow(event,win);
    }

    @FXML
    void totalBookings(ActionEvent event) throws IOException {
        String win = "showReservation";
        Closing.closeWindow(event,win);
    }

    @FXML
    void totalRooms(ActionEvent event) throws IOException{
        String win = "addRooms";
        Closing.closeWindow(event,win);
    }

    @FXML
    void Logout(ActionEvent event) throws IOException {
        String win = "login";
        Closing.closeWindow(event,win);
    }

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            String user= ("SELECT totalBill FROM reservation WHERE paid_Status='Not Paid';");
            ResultSet rs = statement.executeQuery(user);
            Double total=0.00;
            while(rs.next()) {
                Double price= rs.getDouble("totalBill");
                total=total+price;
            }
            pendinglbl.setText("Rs "+String.valueOf(total));
        }catch(Exception e) {
            e.printStackTrace();
            e.getCause();
        }

        usersDAO dao2 = new usersDAO();
        uList = dao2.getAll();
        int c=0;
        c=uList.size();
        guestlbl.setText(String.valueOf(c));


        RoomsDAO dao3=new RoomsDAO();
        rList=dao3.getAll();
        int r=0;
        r= rList.size();
        roomslbl.setText(String.valueOf(r));

        BookingsDAO dao4=new BookingsDAO();
        BookList=dao4.getting();
        int book=0;
        book=BookList.size();
        bookingslbl.setText(String.valueOf(book));
    }
}
