package com.example.springbootproject.repository;

import com.example.springbootproject.connectionBase.DatabaseConnection;
import com.example.springbootproject.model.Employee;
import org.springframework.stereotype.Repository;

import java.sql.*;
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

    //Find All Employee
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

    //update Employee
    public static void UpdateEmployee(int id_employee, String nom, String prenom, String email, String address, String contacts, String poste,String genre){
        DatabaseConnection db = new DatabaseConnection();
        Connection connection = db.createConnection();
        try {
            String sql = "UPDATE Employee SET nom = ?, prenom = ?, email = ?, poste = ?, contacts = ?, genre = ?, address = ? WHERE id_employee = ?;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, nom);
            statement.setString(2, prenom);
            statement.setString(3, email);
            statement.setString(4, poste);
            statement.setString(5, contacts);
            statement.setString(6, genre);
            statement.setString(7, address);
            statement.setInt(8, id_employee);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("l'employee avec l'id " + id_employee + " est metre a jour.");
            } else {
                System.out.println("l'employee qui porte l'id " + id_employee + " n'existe pas.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //find employee by id
    public static Employee FindEmployeeById(int id_employee){
        DatabaseConnection db = new DatabaseConnection();
        Connection connection = db.createConnection();
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM Employee WHERE id = "+ id_employee;
            ResultSet resultSet = statement.executeQuery(sql);

            if (resultSet.next()){
                return new Employee(
                        resultSet.getInt("id_employee"),
                        resultSet.getString("nom"),
                        resultSet.getString("prenom"),
                        resultSet.getString("email"),
                        resultSet.getString("poste"),
                        resultSet.getString("contacts"),
                        resultSet.getString("genre"),
                        resultSet.getString("address")
                );
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
