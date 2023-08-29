package com.example.springbootproject.repository;

import com.example.springbootproject.connectionBase.DatabaseConnection;
import com.example.springbootproject.model.Facture;
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
public class FactureDAO {
    DatabaseConnection dbc = new DatabaseConnection();
    Connection connection = dbc.createConnection();

    public List<Facture> Allfacture() throws Exception{
        List<Facture> array = new ArrayList<>();
        String sql = "SELECT * FROM facture;";

        try (PreparedStatement statement = connection.prepareStatement(sql)){
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                array.add(new Facture(
                        resultSet.getInt("id_facture"),
                        resultSet.getInt("id_commande"),
                        resultSet.getDate("date_facturation").toLocalDate(),
                        resultSet.getBoolean("montant_statut")
                ));
            }
        }catch (Exception e){
            throw new RuntimeException("facture not found"+e);
        }
        return array;
    }

    public void InsertFacture(Facture facture)throws Exception{
        String sql = "INSERT INTO facture(id_facture,id_commande,date_facturation,montant_statut) VALUES(?,?,?,?);";

        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1,facture.getId_facture());
            statement.setInt(2,facture.getId_commande());
            statement.setDate(3, Date.valueOf(facture.getDate_facturation()));
            statement.setBoolean(4,facture.isMontant_statut());

            statement.executeUpdate();
        }
    }

    public void UpdateFacture(Facture facture)throws Exception{
        String sql = "UPDATE facture SET id_commande=?,date_facturation=?,montant_statut=? WHERE id_facture=?;";

        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, facture.getId_commande());
            statement.setDate(2, Date.valueOf(facture.getDate_facturation()));
            statement.setBoolean(3, facture.isMontant_statut());
            statement.setInt(4,facture.getId_facture());

            statement.executeUpdate();
        }
    }

    public Optional<Facture> findFacture(int id_facture)throws Exception{
        String sql = "SELECT * FROM facture WHERE id_facture=?;";

        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1,id_facture);

            try (ResultSet resultSet = statement.executeQuery()){
                if (resultSet.next()){
                    return Optional.of(extract(resultSet));
                }
            }
        }
        return Optional.empty();
    }

    private Facture extract(ResultSet resultSet)throws Exception{
        int id_facture = resultSet.getInt("id_facture");
        int id_commande =resultSet.getInt("id_commande");
        LocalDate date_facturation = resultSet.getDate("date_facturation").toLocalDate();
        boolean montant_statut = resultSet.getBoolean("montant_statut");

        return new Facture(id_facture,id_commande,date_facturation,montant_statut);
    }

    public void DeleteFacture(int id_facture)throws Exception{
        String sql = "DELETE FROM facture WHERE id_facture=?;";

        try(PreparedStatement statement =connection.prepareStatement(sql)){
            statement.setInt(1,id_facture);

            statement.executeUpdate();
        }
    }
}
