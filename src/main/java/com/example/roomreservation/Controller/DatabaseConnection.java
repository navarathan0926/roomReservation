package com.example.roomreservation.Controller;


import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    public Connection databaseLink;
    private java.lang.String password;
    private java.lang.String username;

    public Connection getConnection(){
        java.lang.String databaseName = "userlogin";
        java.lang.String databaseUser = "root";
        java.lang.String databasePassword= "";
        java.lang.String url = "jdbc:mysql://localhost:3306/"+databaseName;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink= DriverManager.getConnection(url, databaseUser, databasePassword);
//            Statement stmt=databaseLink.createStatement();

        }catch(Exception e){
            e.printStackTrace();
        }

        return databaseLink;
    }

}
