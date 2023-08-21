package com.example.springbootproject.repository;

import com.example.springbootproject.connectionBase.DatabaseConnection;
import com.example.springbootproject.model.Employee;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeDAO {
    DatabaseConnection dbc = new DatabaseConnection();
    Connection connecter = dbc.createConnection();

    public Employee InsertEmployee(int id_employee, String nom, String prenom, String email, String address, String contacts, String poste,String genre){
        DatabaseConnection db = new DatabaseConnection();
        Connection connection = db.createConnection();
        try {
            String sql = "INSERT INTO employee(id_employee,nom,prenom,email,poste,contacts,genre,address) VALUES(?,?,?,?,?,?,?,?);";
            PreparedStatement Statement = connection.prepareStatement(sql);
            Statement.setInt(1,id_employee);
            Statement.setString(2,nom);
            Statement.setString(3,prenom);
            Statement.setString(4,email);
            Statement.setString(5,poste);
            Statement.setString(6,contacts);
            Statement.setString(7,genre);
            Statement.setString(8,address);

            int rows = Statement.executeUpdate();
            if(rows>0){
                System.out.println("Insertion employee avec success");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public List<Employee> FindAllEmp(){
        List<Employee> AllEmployee = new ArrayList<>();
        String requete = "SELECT * FROM employee;";
     try (PreparedStatement preparedStatement = connecter.prepareStatement(requete)){
         ResultSet resultat = preparedStatement.executeQuery();
         while (resultat.next()){
             AllEmployee.add(new Employee(
                     resultat.getInt("id_employee"),
                     resultat.getString("nom"),
                     resultat.getString("prenom"),
                     resultat.getString("email"),
                     resultat.getString("poste"),
                     resultat.getString("contacts"),
                     resultat.getString("genre"),
                     resultat.getString("address")
             ));
         }

     } catch (SQLException e) {
         throw new RuntimeException(e);
     }
        return AllEmployee;
    }

}
