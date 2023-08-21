package com.example.springbootproject.connectionBase;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private String url;
    private String user;
    private String password;


    public DatabaseConnection(){
        this.url = "jdbc:postgresql://localhost:5432/"+Settings.DATABASE_NAME;
        this.user = Settings.USER;
        this.password = Settings.PASSWORD;
    }

    public Connection createConnection(){
        try{
            Connection connection = DriverManager.getConnection(
                    this.url, this.user, this.password
            );
            System.out.println("vous etes connecte");
            return connection;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
