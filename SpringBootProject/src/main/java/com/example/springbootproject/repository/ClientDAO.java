package com.example.springbootproject.repository;

import com.example.springbootproject.connectionBase.DatabaseConnection;
import com.example.springbootproject.model.Client;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ClientDAO {
    DatabaseConnection dbc = new DatabaseConnection();
    Connection connection = dbc.createConnection();
    public List<Client> findAll(){
        List<Client> allClient = new ArrayList<>();
        String sql = "SELECT * FROM client";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()){
                allClient.add(new Client(
                        result.getInt("id_client"),
                        result.getString("nom"),
                        result.getString("prenom"),
                        result.getString("email"),
                        result.getString("address"),
                        result.getString("contacts"),
                        result.getString("genre")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allClient;
    }
    public static Client InsertClient(int id_client, String nom, String prenom, String email, String address, String contacts, String genre){
        DatabaseConnection dbc = new DatabaseConnection();
        Connection connection = dbc.createConnection();
        try {
            String sql = "INSERT INTO Client (id_client,nom,prenom,email,address,contacts,genre) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,id_client);
            statement.setString(2,nom);
            statement.setString(3,prenom);
            statement.setString(4,email);
            statement.setString(5,address);
            statement.setString(6,contacts);
            statement.setString(7,genre);

            int rows = statement.executeUpdate();
            if (rows> 0){
                System.out.println("Insertion avec succes");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
