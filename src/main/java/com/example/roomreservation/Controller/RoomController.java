package com.example.roomreservation.Controller;



import com.example.roomreservation.entity.RoomsEntity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import java.time.LocalDate;

public class RoomController {
    public static String username;

    public static LocalDate inDate, outDate;

    @FXML
    private Label no, ac;

    private RoomsEntity room;
    private MyListener myListener;

    @FXML
    private AnchorPane roomPane;


    public void setData(RoomsEntity rooms, MyListener myListener) {
        this.room = rooms;
        this.myListener = myListener;
        no.setText(String.valueOf(room.getRoomNo()));  // for show room number
        ac.setText(room.getAcStatus());
        roomPane.setDisable(orderingBtn(room.getAvailability()));     // for disable the radio button
    }


    // calling the listener class for getting details
    @FXML
    private void click(MouseEvent mouseEvent) {
        myListener.onClickListener(room);
    }

    // check the availability of rooms and return the value
    // if the room is available then the radio button is enabled otherwise the radio button should disabled
    // radio button clicked rooms are only able to book
    public boolean orderingBtn(String availability){
        if(availability.equals("YES")){
            roomPane.setStyle("-fx-background-color: #74b9ff");
            return false;
        }else{
            roomPane.setDisable(true);
            roomPane.setStyle("-fx-background-color: #636e72");
            return true;
        }
    }
}
