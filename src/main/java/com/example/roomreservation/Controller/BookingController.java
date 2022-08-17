package com.example.roomreservation.Controller;


import com.example.roomreservation.DAO.BookingsDAO;
import com.example.roomreservation.DAO.RoomsDAO;
import com.example.roomreservation.HelloApplication;
import com.example.roomreservation.entity.BookingsEntity;
import com.example.roomreservation.entity.RoomsEntity;

import com.example.roomreservation.entity.UserEntity;
import com.sun.javafx.binding.DoubleConstant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.util.Callback;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDate.*;
import java.time.Month;
import java.time.chrono.ChronoLocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import java.util.ResourceBundle;

import static java.time.LocalDate.now;


public class BookingController implements Initializable {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label Price, in, out, roomAvailability;

    @FXML
    private Label acStatus,total_amount, total_day, roomNum;

    @FXML
    private GridPane grid;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private Label type, nameLbl2;

    @FXML
    private ComboBox<String> rType;

    public static long dateDuration=0;
    public static Double roomPrice=0.00;


    @FXML
    private DatePicker checkInDate, checkOutDate;

    DatabaseConnection connection= new DatabaseConnection();
    Connection connectDB= connection.getConnection();
    Statement statement = connectDB.createStatement();


    public BookingController() throws SQLException {
    }

    @FXML
    void selectedroom(ActionEvent event) {
        check();
        if (!rType.getSelectionModel().getSelectedItem().equals("")) {
            room.clear();
        }
    }

    @FXML
    void onActionIn(ActionEvent event) {
        check();
        findTotalPrice();
    }

    @FXML
    void onActionOut(ActionEvent event) {
        check();
        findTotalPrice();
    }


    private String insertRoomType() {
        rType.getItems().removeAll(rType.getItems());
        String query = "SELECT DISTINCT roomType FROM rooms";
        try {
            PreparedStatement pst = connectDB.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            ObservableList<String> listacombo= FXCollections.observableArrayList();
            while (rs.next()) {
                String room_type = rs.getString("roomType");
                rType.getItems().add(room_type);
            }
            return String.valueOf(rType.getItems());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // check availability of rooms by checking the checkIn and checkout date
    void check(){
        room.clear();
        LocalDate checkIn = checkInDate.getValue();
        LocalDate checkOut = checkOutDate.getValue();
        RoomController.inDate=checkInDate.getValue();
        RoomController.outDate=checkOutDate.getValue();
        in.setText(String.valueOf(RoomController.inDate));
        out.setText(String.valueOf(RoomController.outDate));

        try {
            PreparedStatement ps1=connectDB.prepareStatement("UPDATE rooms SET availability='No';"); // Default value of availability is NO
            PreparedStatement pst =connectDB.prepareStatement("update rooms as r set availability='YES' WHERE NOT EXISTS(SELECT 1 FROM bookings b WHERE b.roomNo = r.roomNo AND(('"+checkIn+"' >= b.checkIn AND '"+checkIn+"'  <= b.checkOut) OR ('"+checkIn+"' <= b.checkIn AND '"+checkOut+"' >= b.CheckIn)));");

            ps1.executeUpdate();
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            e.getCause();
        }
        Period period = Period.between(checkIn, checkOut);
        dateDuration = Math.abs(period.getDays()+1);
        total_day.setText(String.valueOf(dateDuration));

        shown();
        room.clear();
    }


    //  it will load the home page when clicked the button
    @FXML
    void goHome(ActionEvent event) throws IOException {
        String win = "home";
        Closing.closeWindow(event,win);
    }


    //  insert the room details in arraylist
    private List<RoomsEntity> room = new ArrayList<>();
    private MyListener myListener;
    private List<RoomsEntity> getData() {
        List<RoomsEntity> rooms = new ArrayList<>();
        RoomsEntity room;
        try{
//            insertRoomType();
            String room_type= String.valueOf(rType.getValue());
            String sql = ("SELECT * FROM rooms WHERE roomType='"+room_type+"' ;");
            ResultSet rs = statement.executeQuery(sql);

            //  get data from database
            while(rs.next()) {
                room = new RoomsEntity();
                String roomNo=rs.getString("roomNo");
                room.setRoomNo(Integer.parseInt(roomNo));
                String acStatus = rs.getString("acStatus");
                room.setAcStatus(acStatus);
                String roomType = rs.getString("roomType");
                room.setRoomType(roomType);
                String availability = rs.getString("availability");
                room.setAvailability(availability);
                String price = rs.getString("price");
                room.setPrice(BigDecimal.valueOf(Double.parseDouble(price)));
                rooms.add(room);    // add every detail in rooms
            }
        } catch (SQLException e) {
            e.printStackTrace();
            e.getCause();
        }
        return rooms;
    }

    //  show room details in particular labels
    public void setChosenRoom(RoomsEntity rooms) {
        roomNum.setText(String.valueOf(rooms.getRoomNo()));
        acStatus.setText(rooms.getAcStatus());
        type.setText(rooms.getRoomType());
        roomAvailability.setText(rooms.getAvailability());
        Price.setText(String.valueOf(rooms.getPrice()));
        roomPrice= Double.valueOf(Price.getText());

        findTotalPrice();
    }

    void findTotalPrice(){
        Double price= dateDuration * roomPrice;
        total_amount.setText(String.valueOf(price));
        total_day.setText(String.valueOf(dateDuration));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nameLbl2.setText(HomeController.name);
        checkInDate.setValue(now());
        DatePicker maxDate = new DatePicker();
        maxDate.setValue(now()); // Max date available will be 2015-01-01
        final Callback<DatePicker, DateCell> dayCellFactory;
        dayCellFactory = (final DatePicker datePicker) -> new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);

                if (item.isBefore(now())) { //Disable all dates after required date
                    setDisable(true);
                    setStyle("-fx-background-color: #bdc3c7;"); //To set background on different color
                }
            }
        };

        final Callback<DatePicker, DateCell> dayCellFactory2;
        dayCellFactory2 = (final DatePicker datePicker) -> new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);

                if (item.isBefore(checkInDate.getValue())) { //Disable all dates after required date
                    setDisable(true);
                    setStyle("-fx-background-color: #bdc3c7;"); //To set background on different color
                }
            }
        };
//Finally, we just need to update our DatePicker cell factory as follow:
        checkInDate.setDayCellFactory(dayCellFactory);
        checkOutDate.setDayCellFactory(dayCellFactory2);
        checkOutDate.setValue(now());
        insertRoomType();
    }

    public void shown(){
        nameLbl2.setText(HomeController.name);
        room.addAll(getData());

        //  it will call setChosenRoom function until the room list size greater than zero
        if (room.size() > 0) {
//            setChosenRoom(room.get(0));
            myListener = new MyListener() {
                //  calling listener class when click the component
                @Override
                public void onClickListener(RoomsEntity rooms) {
                    setChosenRoom(rooms);
                }
            };
        }

        int column = 0;
        int row = 1;

        try {
            grid.getChildren().setAll();
            for (int i = 0; i < room.size(); i++) {

//                FXMLLoader fxmlLoader = new FXMLLoader();
                // load all the rooms in Booking page
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("room.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                RoomController roomController = fxmlLoader.getController();
                roomController.setData(room.get(i), myListener);

                //  for define 3 columns
                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); // (child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);
                GridPane.setMargin(anchorPane, new Insets(10)); // for set margin of gridPane
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void clearValue(){
        acStatus.setText("");
        roomNum.setText("");
        type.setText("");
        roomAvailability.setText("");
        Price.setText("");
        total_amount.setText("0.00");
        total_day.setText("0");
    }

    // function when click the reserve button
    @FXML
    void reserveRoom(ActionEvent event) {
        if (roomNum.getText().isBlank() == false) {
            int roomNo = Integer.parseInt(roomNum.getText());

            LocalDate checkIn = checkInDate.getValue();
            LocalDate checkOut = checkOutDate.getValue();

            Period period = Period.between(checkIn, checkOut);
            dateDuration = Math.abs(period.getDays() + 1);
            int totalday = (int) dateDuration;
            Double price = dateDuration * roomPrice;

            try {
                String userName = HomeController.name;
                String user = ("SELECT user_id FROM user WHERE username='" + userName + "'");
                ResultSet rs = statement.executeQuery(user);

                while (rs.next()) {
                    int user_id = rs.getInt(1);
                    PreparedStatement ps = connectDB.prepareStatement("INSERT INTO bookings(user_id,roomNo,checkIn,checkOut,total_days,price)  VALUES (" + user_id + "," + roomNo + ",'" + checkIn + "','" + checkOut + "','" + totalday + "','" + price + "')");
                    int count = ps.executeUpdate();
                    if (count > 0) {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setContentText("Confirm booking?");
                        alert.showAndWait();
                        clearValue();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();
            }
            check();
        } else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Please select the Room!");
            alert.showAndWait();
        }
    }
}