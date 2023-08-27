package com.example.springbootproject.repository;

import com.example.springbootproject.connectionBase.DatabaseConnection;
import com.example.springbootproject.model.DetailCommande;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class DetailCommandeDAO {
    DatabaseConnection dbc = new DatabaseConnection();
    Connection connection = dbc.createConnection();

    public List<DetailCommande> AllDetailCommande(){
        List<DetailCommande> array = new ArrayList<>();

        String sql = "SELECT * FROM detail_commande;";

        try (PreparedStatement statement = connection.prepareStatement(sql)){
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                array.add(new DetailCommande(
                        resultSet.getInt("id_detail_commande"),
                        resultSet.getInt("id_commande"),
                        resultSet.getInt("id_produit"),
                        resultSet.getInt("quantiter"),
                        resultSet.getString("mode_payment")
                ));
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return array;
    }

    public void InsetDetailCommande(DetailCommande detailCommande)throws Exception{
        String sql = "INSERT INTO detail_commande(id_detail_commande,id_commande,id_produit,quantiter,mode_payment) values(?,?,?,?,?);";

        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1,detailCommande.getId_detail_commande());
            statement.setInt(2,detailCommande.getId_commande());
            statement.setInt(3,detailCommande.getId_produit());
            statement.setInt(4,detailCommande.getQuantite());
            statement.setString(5,detailCommande.getMode_payment());

            statement.executeUpdate();
        }
    }

    public void UpdateDetailCommande(DetailCommande detailCommande)throws Exception{
        String sql = "UPDATE detail_commande SET id_commande=?,id_produit=?,quantiter=?,mode_payment=? WHERE id_detail_commande=?;";

        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1,detailCommande.getId_commande());
            statement.setInt(2,detailCommande.getId_produit());
            statement.setInt(3,detailCommande.getQuantite());
            statement.setString(4,detailCommande.getMode_payment());
            statement.setInt(5,detailCommande.getId_detail_commande());

            statement.executeUpdate();
        }
    }

    public void deleteDetailCommande(int id_detail_commande)throws Exception{
        String sql = "DELETE FROM detail_commande WHERE id_detail_commande=?;";

        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1,id_detail_commande);

            statement.executeUpdate();
        }
    }

    public Optional<DetailCommande> FindById(int id_detail_commande) throws Exception{
        String sql = "SELECT * FROM detail_commande WHERE id_detail_commande=?;";

        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1,id_detail_commande);
            try (ResultSet resultSet = statement.executeQuery()){
                if (resultSet.next()){
                    return Optional.of(extract(resultSet));
                }
            }
        }
        return Optional.empty();
    }

    private DetailCommande extract(ResultSet resultSet)throws Exception{
        int id_detail_commade = resultSet.getInt("id_detail_commande");
        int id_commande = resultSet.getInt("id_commande");
        int id_produit = resultSet.getInt("id_produit");
        int quantiter = resultSet.getInt("quantiter");
        String mode_payment = resultSet.getString("mode_payment");

        return new DetailCommande(id_detail_commade,id_commande,id_produit,quantiter,mode_payment);
    }
}
