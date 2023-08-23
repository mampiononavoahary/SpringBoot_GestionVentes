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
import java.util.Optional;

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
    public void InsertClient(Client InsertClient){
        DatabaseConnection dbc = new DatabaseConnection();
        Connection connection = dbc.createConnection();

        String sql = "INSERT INTO Client (id_client,nom,prenom,email,address,contacts,genre) VALUES (?,?,?,?,?,?,?)";
        try (  PreparedStatement statement = connection.prepareStatement(sql);){
            statement.setInt(1,InsertClient.getId_client());
            statement.setString(2, InsertClient.getNom());
            statement.setString(3, InsertClient.getPrenom());
            statement.setString(4, InsertClient.getEmail());
            statement.setString(5, InsertClient.getAddress());
            statement.setString(6, InsertClient.getContacts());
            statement.setString(7, InsertClient.getGenre());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void UpateClient(Client client) throws Exception{
        DatabaseConnection dbc = new DatabaseConnection();
        Connection connection = dbc.createConnection();
        String requette = "UPDATE Client SET nom=?,prenom=?,email=?,address=?,contacts=?,genre=? WHERE id_client=?;";

        try (PreparedStatement statement = connection.prepareStatement(requette)){
            statement.setString(1, client.getNom());
            statement.setString(2, client.getPrenom());
            statement.setString(3, client.getEmail());
            statement.setString(4, client.getAddress());
            statement.setString(5, client.getContacts());
            statement.setString(6, client.getGenre());
            statement.setInt(7,client.getId_client());

            statement.executeUpdate();
        }
    }

    public Optional<Client> findAClient(int id_client) throws Exception{
        DatabaseConnection dbc = new DatabaseConnection();
        Connection connection = dbc.createConnection();

        String requette = "SELECT * FROM Client WHERE id_client=?;";
        try (PreparedStatement statement = connection.prepareStatement(requette)){
            statement.setInt(1,id_client);

            try (ResultSet resultSet = statement.executeQuery()){
                if(resultSet.next()){
                    return Optional.of(extract(resultSet));
                }
            }
        }
        return Optional.empty();
    }

    private Client extract(ResultSet resultSet) throws Exception{
       int id_client = resultSet.getInt("id_client");
       String nom =  resultSet.getString("nom");
       String prenom = resultSet.getString("prenom");
       String email = resultSet.getString("email");
       String address = resultSet.getString("address");
       String contacts = resultSet.getString("contacts");
       String genre = resultSet.getString("genre");

        return new Client(id_client,nom,prenom,email,address,contacts,genre);
    }

    public void deleteClient(int id_client)throws Exception{
        DatabaseConnection dbc = new DatabaseConnection();
        Connection connection = dbc.createConnection();
        String requette = "DELETE FROM Client WHERE id_client=?;";

        try(PreparedStatement statement = connection.prepareStatement(requette)){
            statement.setInt(1,id_client);

            statement.executeUpdate();
        }
    }
}
