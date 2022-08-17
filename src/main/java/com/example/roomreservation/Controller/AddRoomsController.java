package com.example.roomreservation.Controller;

import com.example.roomreservation.DAO.RoomsDAO;
import com.example.roomreservation.entity.RoomsEntity;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.math.BigDecimal;

public class AddRoomsController {
    @FXML
    private TextField txt1, txt2, txt3, txt4, txt5;

    @FXML
    private Label lb12, lbl3;
    @FXML
    private TableView table;
    ObservableList<RoomsEntity> rList;

    public void initialize() {
        RoomsDAO dao = new RoomsDAO();
        rList = dao.getAll();
        table.setItems(rList);

        TableColumn col1 = (TableColumn) table.getColumns().get(0);
        col1.setCellValueFactory(new PropertyValueFactory("roomNo"));
        TableColumn col2 = (TableColumn) table.getColumns().get(1);
        col2.setCellValueFactory(new PropertyValueFactory("acStatus"));
        TableColumn col3 = (TableColumn) table.getColumns().get(2);
        col3.setCellValueFactory(new PropertyValueFactory("roomType"));
        TableColumn col4 = (TableColumn) table.getColumns().get(3);
        col4.setCellValueFactory(new PropertyValueFactory("price"));
        TableColumn col5 = (TableColumn) table.getColumns().get(4);
        col5.setCellValueFactory(new PropertyValueFactory("availability"));

        ViewOnAction();
    }

    public void refreshView() {
        RoomsDAO dao = new RoomsDAO();
        rList = dao.getAll();
        table.setItems(rList);
        table.refresh();
    }

    @FXML
    void AddroomsOnAction() {
        RoomsDAO dao = new RoomsDAO();
        RoomsEntity data = new RoomsEntity();
        rList=dao.getAll();

        String roomno=txt1.getText();
        String acStatus = txt2.getText();
        String room_type = txt3.getText();
        double price = Double.parseDouble(txt4.getText());
        String available = txt5.getText();
      for(int i=0;i<=rList.size();i++) {
          if (i<rList.size()) {
              if (Integer.parseInt(roomno) == rList.get(i).getRoomNo()) {
                  lb12.setText("Already exit");
                  break;
              }
          }else{
              lb12.setText(" ");
              data.setRoomNo(Integer.parseInt(roomno));
              data.setAcStatus(acStatus);
              data.setRoomType(room_type);
              data.setPrice(BigDecimal.valueOf(price));
              data.setAvailability(available);
              Alert alert = new Alert(Alert.AlertType.INFORMATION);
              alert.setContentText("Successfully added");
              alert.showAndWait();
              dao.addData(data);

          }
      }

        refreshView();

    }

    @FXML
    void DeleteroomsOnAction() {

        RoomsEntity roomNo = (RoomsEntity) table.getSelectionModel().getSelectedItem();
        RoomsDAO dao = new RoomsDAO();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Confirm deleted");
        alert.showAndWait();
        dao.deleteData(roomNo);
        refreshView();
    }

    @FXML
    void UpdateroomsOnAction() {

        RoomsEntity data = (RoomsEntity) table.getSelectionModel().getSelectedItem();
        RoomsDAO dao = new RoomsDAO();
        data.setRoomNo(Integer.parseInt(txt1.getText()));
        data.setAcStatus(txt2.getText());
        data.setRoomType(txt3.getText());
        data.setPrice(BigDecimal.valueOf(Double.parseDouble(txt4.getText())));
        data.setAvailability(txt5.getText());

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Successfully Updated");
        alert.showAndWait();
        dao.updateData(data);
        refreshView();
    }


    public void ViewOnAction() {
        table.setRowFactory(tv -> {
            TableRow<RoomsEntity> myRow = new TableRow<>();
            myRow.setOnMouseClicked(event ->
            {
                if (event.getClickCount() == 1 && (!myRow.isEmpty())) {
                    RoomsEntity data = (RoomsEntity) table.getSelectionModel().getSelectedItem();
                    txt1.setText(String.valueOf(data.getRoomNo()));
                    txt2.setText(data.getAcStatus());
                    txt3.setText(data.getRoomType());
                    txt4.setText(String.valueOf(data.getPrice()));
                    txt5.setText(data.getAvailability());
                }
            });
            return myRow;
        });
    }

    @FXML
    void reset (ActionEvent event) throws IOException {
        String win = "addrooms";
        Closing.closeWindow(event,win);
    }

    @FXML
    void Back (ActionEvent event) throws IOException {
        String win = "adminDashboard";
        Closing.closeWindow(event,win);
    }

}