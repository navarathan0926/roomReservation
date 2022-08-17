package com.example.roomreservation.Controller;


import com.example.roomreservation.DAO.reservationDAO;
import com.example.roomreservation.entity.BookingsEntity;
import com.example.roomreservation.entity.ReservationEntity;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class ReservationBillController implements Initializable {

    @FXML
    private Label l1,l2, l3, l4, l5, l6;

    ObservableList<ReservationEntity> RList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        reservationDAO dav=new reservationDAO();
        RList=dav.getAll();

        String Id = String.valueOf(RList.get(0).getReservedId());
        String CustomerId = String.valueOf(RList.get(0).getCustomerId());
        String name = String.valueOf(RList.get(0).getCustomerName());
        String rooms = String.valueOf(RList.get(0).getRoomNumbers());
        String total_payment= String.valueOf(RList.get(0).getTotalBill());
        String Phone_num= RList.get(0).getCustomerId().getPhoneNo();

        l1.setText(Id);
        l2.setText(CustomerId);
        l3.setText(name);
        l4.setText(rooms);
        l5.setText(total_payment);
        l6.setText(Phone_num);
    }

    @FXML
    void PrintBillOnAction(ActionEvent event) throws IOException, SQLException {
        DatabaseConnection connection = new DatabaseConnection();
        Connection connectDB = connection.getConnection();
        Statement statement = connectDB.createStatement();
        String userName=HomeController.name;

        String user=("SELECT user_id FROM user WHERE username='"+userName+"'");
        ResultSet rs = statement.executeQuery(user);
        while(rs.next()) {
            int userId = rs.getInt(1);
            PreparedStatement ps = connectDB.prepareStatement("UPDATE bookings SET reserved_Status='Booked' WHERE user_id=" + userId);
            PreparedStatement ps1 = connectDB.prepareStatement("UPDATE reservation SET paid_Status='Paid' WHERE customer_id=" + userId);

            ps.executeUpdate();
            ps1.executeUpdate();
        }
        String Id = String.valueOf(RList.get(0).getReservedId());

            String path = "C:\\Users\\navar\\IdeaProjects\\RoomReservation\\bill\\";
            File file = new File(path + "bill" + Id + ".pdf");
            if (file.toString().endsWith(".pdf"))
                Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + file);
            else {
                Desktop desktop = Desktop.getDesktop();
                desktop.open(file);
            }
        CreateBills();
    }


    public void CreateBills() throws IOException {
        String Id = String.valueOf(RList.get(0).getReservedId());
        String CustomerId = String.valueOf(RList.get(0).getCustomerId());
        String name = String.valueOf(RList.get(0).getCustomerName());
        String rooms = String.valueOf(RList.get(0).getRoomNumbers());
        String total_payment= String.valueOf(RList.get(0).getTotalBill());
        String Phone_num= RList.get(0).getCustomerId().getPhoneNo();


        String path = "C:\\Users\\navar\\IdeaProjects\\RoomReservation\\bill\\";
        Document doc = new Document();
        try {
            PdfWriter.getInstance(doc, new FileOutputStream(path + "bill" + Id + ".pdf"));
            doc.open();
            Paragraph paragraph1 = new Paragraph("---------------------------------------------------  Payment List -----------------------------------------------------" +
                    "\n");
            Paragraph paragraph2 = new Paragraph("          Reserved Id: " + Id + "\n\n             Customer Details:\n                         Name: " + name +
                    "\n                         ID Number: " + CustomerId + "\n                         Mobile Number : " + Phone_num +"\n\n\n");
            doc.add(paragraph1);
            doc.add(paragraph2);

            PdfPTable table = new PdfPTable(1);
            table.addCell("Room Numbers: \n\n" + rooms+"\n");
            doc.add(table);

            Paragraph paragraph4 = new Paragraph("                          Total Amount Paid : " +total_payment+
                    "\n\n-------------------------------------------------------   * * *   ----------------------------------------------------------" +
                    "\n");



            doc.add(paragraph4);
        } catch (Exception e) {
            e.printStackTrace();
        }
        doc.close();

        File file = new File(path + "bill" +Id + ".pdf");
        if (file.toString().endsWith(".pdf"))
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + file);
        else {
            Desktop desktop = Desktop.getDesktop();
            desktop.open(file);
        }
    }



    @FXML
    void back(ActionEvent event) throws IOException {
        String win = "home";
        Closing.closeWindow(event, win);

    }

}

