package com.example.springbootproject.repository;

import com.example.springbootproject.connectionBase.DatabaseConnection;
import com.example.springbootproject.model.Produit;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProduitDAO {
    DatabaseConnection dbc = new DatabaseConnection();
    Connection connection = dbc.createConnection();

    public List<Produit> AllProduct() throws Exception{
        List<Produit> array = new ArrayList<>();
        String sql = "SELECT * FROM produit;";

        try (PreparedStatement statement = connection.prepareStatement(sql)){
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                array.add(new Produit(
                        resultSet.getInt("id_produit"),
                        resultSet.getString("nom_produit"),
                        resultSet.getString("description"),
                        resultSet.getInt("quantiter_stock"),
                        resultSet.getDouble("prix"),
                        resultSet.getString("type")
                ));
            }
        }catch (Exception e){
            throw new RuntimeException("Product not found"+e);
        }
        return array;
    }

    public void InsertProduit(Produit produit)throws Exception{
        String sql = "INSERT INTO produit(id_produit,nom_produit,description,quantiter_stock,prix,type) VALUES(?,?,?,?,?,?);";

        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1,produit.getId_produit());
            statement.setString(2, produit.getNom_produit());
            statement.setString(3, produit.getDescription());
            statement.setInt(4,produit.getQuantiter_stock());
            statement.setDouble(5,produit.getPrix());
            statement.setString(6, produit.getType());

            statement.executeUpdate();
        }
    }

    public void UpdateProduct(Produit produit)throws Exception{
        String sql = "UPDATE produit SET nom_produit=?,description=?,quantiter_stock=?,prix=?,type=? WHERE id_produit=?;";

        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, produit.getNom_produit());
            statement.setString(2, produit.getDescription());
            statement.setInt(3,produit.getQuantiter_stock());
            statement.setDouble(4,produit.getPrix());
            statement.setString(5, produit.getType());
            statement.setInt(6,produit.getId_produit());

            statement.executeUpdate();
     }
    }

    public Optional<Produit> findProduct(int id_produit)throws Exception{
        String sql = "SELECT * FROM produit WHERE id_produit=?;";

        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1,id_produit);

            try (ResultSet resultSet = statement.executeQuery()){
                if (resultSet.next()){
                return Optional.of(extract(resultSet));
                }
            }
        }
        return Optional.empty();
    }

    private Produit extract(ResultSet resultSet)throws Exception{
        int id_produit = resultSet.getInt("id_produit");
        String nom_produit = resultSet.getString("nom_produit");
        String description = resultSet.getString("description");
        int quantiter_stock = resultSet.getInt("quantiter_stock");
        Double prix = resultSet.getDouble("prix");
        String type = resultSet.getString("type");

        return new Produit(id_produit,nom_produit,description,quantiter_stock,prix,type);
    }

    public void DeleteProduct(int id_produit)throws Exception{
        String sql = "DELETE FROM produit WHERE id_produit=?;";

        try(PreparedStatement statement =connection.prepareStatement(sql)){
            statement.setInt(1,id_produit);

            statement.executeUpdate();
        }
    }
}
