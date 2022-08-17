package com.example.roomreservation.Controller;


import com.example.roomreservation.DAO.BookingsDAO;
import com.example.roomreservation.entity.BookingsEntity;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.IOException;
import java.sql.*;
import java.net.URL;
import java.util.ResourceBundle;

public class CancelBookingController implements Initializable {

    ObservableList<BookingsEntity> bList;
    @FXML
    private TableView tables;

    public CancelBookingController() throws SQLException {
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        BookingsDAO dao2=new BookingsDAO();
        bList=dao2.get();
        tables.setItems(bList);

            TableColumn col1 = (TableColumn) tables.getColumns().get(0);
            col1.setCellValueFactory(new PropertyValueFactory("bookingId"));
            TableColumn col2 = (TableColumn) tables.getColumns().get(1);
            col2.setCellValueFactory(new PropertyValueFactory("UserId"));
            TableColumn col3 = (TableColumn) tables.getColumns().get(2);
            col3.setCellValueFactory(new PropertyValueFactory("RoomNoId"));
            TableColumn col4 = (TableColumn) tables.getColumns().get(3);
            col4.setCellValueFactory(new PropertyValueFactory("checkIn"));
            TableColumn col5 = (TableColumn) tables.getColumns().get(4);
            col5.setCellValueFactory(new PropertyValueFactory("checkOut"));
            TableColumn col6 = (TableColumn) tables.getColumns().get(5);
            col6.setCellValueFactory(new PropertyValueFactory("reservedStatus"));
    }

    @FXML
    void cancelBooking() {
        BookingsEntity booking_id = (BookingsEntity) tables.getSelectionModel().getSelectedItem();
        BookingsDAO dao = new BookingsDAO();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Confirm cancelled");
        alert.showAndWait();
        dao.deleteData(booking_id);
        refreshView();
    }

    public void refreshView() {
        BookingsDAO dao=new BookingsDAO();
        bList=dao.get();
        tables.setItems(bList);
        tables.refresh();
    }
    @FXML
    void back(ActionEvent event) throws IOException {
        String win = "home";
        Closing.closeWindow(event, win);
    }

}