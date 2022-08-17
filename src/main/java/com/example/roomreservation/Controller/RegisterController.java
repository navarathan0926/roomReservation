package com.example.roomreservation.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterController {
    @FXML
    private Label regilabel, confirmpasswordlabel;

    private static String encPw=null;
    @FXML
    private TextField emailtxt;

    @FXML
    private TextField lnametxt, phonetxt;

    @FXML
    private TextField nametext;

    @FXML
    private TextField fnametxt;

    @FXML
    private PasswordField pswtext, cpswtxt;

    public static boolean isValidUsername(String name)
    {
        String regex = "^[A-Z]\\w{4,29}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(name);
        return m.matches();
    }

    public void registerOnAction(ActionEvent event) throws IOException {
        if (fnametxt.getText().isBlank() == false && lnametxt.getText().isBlank() == false && emailtxt.getText().isBlank() == false && nametext.getText().isBlank() == false && pswtext.getText().isBlank() == false) {
            if (isValidUsername(nametext.getText())) {
                if (pswtext.getText().equals(cpswtxt.getText())) {
                    register(event);
                    HomeController.name = nametext.getText();
                    String win = "home";
                    Closing.closeWindow(event, win);

                    confirmpasswordlabel.setText(" ");
                } else {
                    confirmpasswordlabel.setText("Password does not match");
                }
            } else {
                regilabel.setText("Username should start with capital letter!");
            }
        } else {
            regilabel.setText("Please enter all details");
        }

    }


    void register(ActionEvent event){
        DatabaseConnection connection= new DatabaseConnection();
        Connection connectDB= connection.getConnection();

        String firstname = fnametxt.getText();
        String lastname = lnametxt.getText();
        String email = emailtxt.getText();
        String phone_no = phonetxt.getText();
        String username = nametext.getText();
        String password = pswtext.getText();

        MessageDigest m = null;
        try {
            m = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        m.update(password.getBytes());

        byte[] bytes = m.digest();

        /* The bytes array has bytes in decimal form. Converting it into hexadecimal format. */
        StringBuilder s = new StringBuilder();
        for(int i=0; i< bytes.length ;i++)
        {
            s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        encPw = s.toString();

        String insertFeilds ="INSERT INTO user(firstname,lastname,email,phone_no,username,password)  VALUES ('";
        String insertValues =firstname+"','"+lastname+"','"+ email +"','"+phone_no+"','"+username+"','"+encPw+"')" ;
        String insertTORegister =insertFeilds + insertValues  ;

        try{

            Statement statement =connectDB.createStatement();
            statement.executeUpdate(insertTORegister);
            String win = "home";
            Closing.closeWindow(event,win);
            regilabel.setText("User has been successfully registered");
            HelloController he = new HelloController();
            HomeController.name=nametext.getText();



        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }

    }

}

