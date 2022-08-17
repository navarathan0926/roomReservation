package com.example.roomreservation.Controller;



import com.example.roomreservation.DAO.reservationDAO;
import com.example.roomreservation.entity.ReservationEntity;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ShowReservationController implements  Initializable{
    ObservableList<ReservationEntity> ReList;
    @FXML
    private TableView tableReservation;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        reservationDAO dao=new reservationDAO();
        ReList=dao.get();
        tableReservation.setItems(ReList);

        TableColumn col1 = (TableColumn) tableReservation.getColumns().get(0);
        col1.setCellValueFactory(new PropertyValueFactory("reservedId"));
        TableColumn col2 = (TableColumn) tableReservation.getColumns().get(1);
        col2.setCellValueFactory(new PropertyValueFactory("CustomerId"));
        TableColumn col3 = (TableColumn) tableReservation.getColumns().get(2);
        col3.setCellValueFactory(new PropertyValueFactory("roomNumbers"));
        TableColumn col4 = (TableColumn) tableReservation.getColumns().get(3);
        col4.setCellValueFactory(new PropertyValueFactory("totalBill"));
        TableColumn col5 = (TableColumn) tableReservation.getColumns().get(4);
        col5.setCellValueFactory(new PropertyValueFactory("paidStatus"));
    }


    @FXML
    void back(ActionEvent event) throws IOException {
        String win = "adminDashboard";
        Closing.closeWindow(event, win);
    }



}
