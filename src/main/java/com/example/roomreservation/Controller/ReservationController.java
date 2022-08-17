package com.example.roomreservation.Controller;

import com.example.roomreservation.DAO.BookingsDAO;
import com.example.roomreservation.DAO.reservationDAO;
import com.example.roomreservation.entity.BookingsEntity;
import com.example.roomreservation.entity.ReservationEntity;
import com.example.roomreservation.entity.UserEntity;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ReservationController implements Initializable {
    ObservableList<BookingsEntity> BList;

    @FXML
    private TableView reservetbl;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        BookingsDAO dao4 = new BookingsDAO();
        BList = dao4.get();
        reservetbl.setItems(BList);

        TableColumn col1 = (TableColumn) reservetbl.getColumns().get(0);
        col1.setCellValueFactory(new PropertyValueFactory("bookingId"));
        TableColumn col2 = (TableColumn) reservetbl.getColumns().get(1);
        col2.setCellValueFactory(new PropertyValueFactory("UserId"));
        TableColumn col3 = (TableColumn) reservetbl.getColumns().get(2);
        col3.setCellValueFactory(new PropertyValueFactory("RoomNoId"));
        TableColumn col4 = (TableColumn) reservetbl.getColumns().get(3);
        col4.setCellValueFactory(new PropertyValueFactory("checkIn"));
        TableColumn col5 = (TableColumn) reservetbl.getColumns().get(4);
        col5.setCellValueFactory(new PropertyValueFactory("checkOut"));
        TableColumn col6 = (TableColumn) reservetbl.getColumns().get(5);
        col6.setCellValueFactory(new PropertyValueFactory("totalDays"));
        TableColumn col7 = (TableColumn) reservetbl.getColumns().get(6);
        col7.setCellValueFactory(new PropertyValueFactory("price"));
    }

    @FXML
    void ConfirmBookingOnAction(ActionEvent event) throws SQLException, IOException {
        reservationDAO dao = new reservationDAO();
        ReservationEntity data = new ReservationEntity();
        Double sum = 0.00;
        String totalrooms = "";

        for (int i = 0; i < BList.size(); i++) {
            String res = String.valueOf(BList.get(i).getRoomNoId());
            String numDays = String.valueOf(BList.get(i).getTotalDays());
            String checkIn = String.valueOf(BList.get(i).getCheckIn());
            String checkOut = String.valueOf(BList.get(i).getCheckOut());
            totalrooms += "RoomNo " + res + " ("+ numDays + " days) \n[CheckIn : " +checkIn+"] [CheckOut : "+checkOut+"]\n\n";

            Double user = BList.get(i).getPrice();
            sum = sum + user;

        }
        String customer_name = HomeController.name;
        UserEntity userId = BList.get(0).getUserId();

        data.setCustomerId(userId);
        data.setCustomerName(customer_name);
        data.setRoomNumbers(totalrooms);
        data.setTotalBill(BigDecimal.valueOf(sum));
        data.setPaidStatus("Not Paid");

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Successfully Updated");
        alert.showAndWait();
        dao.addData(data);

        String win = "bill";
        Closing.closeWindow(event, win);


    }



    @FXML
    void back(ActionEvent event) throws IOException {
        String win = "home";
        Closing.closeWindow(event, win);

    }

}