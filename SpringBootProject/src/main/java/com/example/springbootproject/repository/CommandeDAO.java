package com.example.springbootproject.repository;

import com.example.springbootproject.connectionBase.DatabaseConnection;
import com.example.springbootproject.model.Commande;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CommandeDAO {

    DatabaseConnection dbc = new DatabaseConnection();
    Connection connection = dbc.createConnection();
    public List<Commande> AllCommande(){
        List<Commande> array = new ArrayList<>();
        String sql = "SELECT * FROM Commande;";

        try (PreparedStatement statement = connection.prepareStatement(sql)){
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                array.add(new Commande(
                        resultSet.getInt("id_commande"),
                        resultSet.getInt("id_client"),
                        resultSet.getInt("id_employee"),
                        resultSet.getDate("date_commande").toLocalDate()
                        ));
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return array;
    }

    public void InsertCommande(Commande commande){
        String requette = "INSERT INTO Commande(id_commande,id_client,id_employee,date_commande) VALUES (?,?,?,?);";

        try (PreparedStatement statement = connection.prepareStatement(requette)){
            statement.setInt(1,commande.getId_commande());
            statement.setInt(2,commande.getId_client());
            statement.setInt(3,commande.getId_employee());
            statement.setDate(4, Date.valueOf(commande.getDate_commande()));

            int rows = statement.executeUpdate();
            if (rows>0){
                System.out.println("Commande inseted");
            }
        }catch (Exception e){
            throw new RuntimeException("Error in inserted :"+e+"");
        }
    }

    public Optional<Commande> finCommande(int id_commande)throws Exception{

        String sql ="SELECT * FROM Commande WHERE id_commande=?;";
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1,id_commande);
           try(ResultSet resultSet = statement.executeQuery()){
               if(resultSet.next()){
                   return Optional.of(extract(resultSet));
               }
           }
        }
        return Optional.empty();
    }

    public void UpdateCommande(Commande update)throws Exception{
        String sql = "UPDATE Commande SET id_client= ?,id_employee=?, date_commande=? WHERE id_commande=?;";

        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1,update.getId_client());
            statement.setInt(2,update.getId_employee());
            statement.setDate(3,Date.valueOf(update.getDate_commande()));
            statement.setInt(4,update.getId_commande());

            int rows = statement.executeUpdate();
            if(rows>0){
                System.out.println("Commande with id: "+update.getId_commande()+" is already up to date");
            }
        }
    }

    private Commande extract(ResultSet resultSet) throws Exception{
        int id_commande = resultSet.getInt("id_commande");
        int id_client = resultSet.getInt("id_client");
        int id_employee = resultSet.getInt("id_employee");
        LocalDate date_commande = resultSet.getDate("date_commande").toLocalDate();

        return new Commande(id_commande,id_client,id_employee,date_commande);
    }

    public void deleteCommande(int id_commande) throws Exception{
        String sql = "DELETE FROM Commande WHERE id_commande=?;";

        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1,id_commande);

            statement.executeUpdate();
        }
    }
}
