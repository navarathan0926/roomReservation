package com.example.roomreservation.Controller;


import com.example.roomreservation.DAO.usersDAO;
import com.example.roomreservation.entity.UserEntity;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

    public class ShowGuestController implements Initializable {
        ObservableList<UserEntity> uList;

        @FXML
        private TableView table1;

        public ShowGuestController() throws SQLException {

        }

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {

           usersDAO dao3=new usersDAO();
            uList=dao3.getAll();
            table1.setItems(uList);

            TableColumn col1 = (TableColumn) table1.getColumns().get(0);
            col1.setCellValueFactory(new PropertyValueFactory("userId"));
            TableColumn col2 = (TableColumn) table1.getColumns().get(1);
            col2.setCellValueFactory(new PropertyValueFactory("firstname"));
            TableColumn col3 = (TableColumn) table1.getColumns().get(2);
            col3.setCellValueFactory(new PropertyValueFactory("lastname"));
            TableColumn col4 = (TableColumn) table1.getColumns().get(3);
            col4.setCellValueFactory(new PropertyValueFactory("email"));
            TableColumn col5 = (TableColumn) table1.getColumns().get(4);
            col5.setCellValueFactory(new PropertyValueFactory("phoneNo"));
        }


        @FXML
        void cancelBooking() {
            UserEntity user_id = (UserEntity) table1.getSelectionModel().getSelectedItem();
            usersDAO dao = new usersDAO();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Confirm cancelled");
            alert.showAndWait();
            dao.deleteData(user_id);
            refreshView();
        }
        @FXML
        void back(ActionEvent event) throws IOException {
            String win = "adminDashboard";
            Closing.closeWindow(event, win);
        }

        @FXML
        void deleteUserOnAction(ActionEvent event) {

        }


        public void refreshView() {
            table1.setItems(uList);
            table1.refresh();
        }
    }

