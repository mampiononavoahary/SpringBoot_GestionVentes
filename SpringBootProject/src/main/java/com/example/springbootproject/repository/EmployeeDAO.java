package com.example.springbootproject.repository;

import com.example.springbootproject.connectionBase.DatabaseConnection;
import com.example.springbootproject.model.Employee;
import com.example.springbootproject.model.Utilisateur;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class EmployeeDAO {
    DatabaseConnection dbc = new DatabaseConnection();
    Connection connecter = dbc.createConnection();

    public void InsertEmployee(Employee insertEmp) throws Exception{
        DatabaseConnection db = new DatabaseConnection();
        Connection connection = db.createConnection();

        String sql = "INSERT INTO employee(id_employee,nom,prenom,email,poste,contacts,genre,address)"+ "VALUES(?,?,?,?,?,?,?,?);";
        try (PreparedStatement Statement = connection.prepareStatement(sql)){
            Statement.setInt(1,insertEmp.getId_employee());
            Statement.setString(2,insertEmp.getNom());
            Statement.setString(3, insertEmp.getPrenom());
            Statement.setString(4, insertEmp.getEmail());
            Statement.setString(5,insertEmp.getPoste());
            Statement.setString(6,insertEmp.getContacts());
            Statement.setString(7,insertEmp.getGenre());
            Statement.setString(8,insertEmp.getAddress());

          Statement.executeUpdate();
        }
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
    public void UpdateEmployee(int id_employee, String nom, String prenom, String email, String address, String contacts, String poste,String genre){
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
    public Optional<Employee> findEmployeeById(int id_employee) throws SQLException {
            DatabaseConnection db = new DatabaseConnection();
            Connection connection = db.createConnection();

            String sql = "SELECT * FROM Employee WHERE id_employee = ?";
            try(PreparedStatement statement = connection.prepareStatement(sql)){
                statement.setInt(1,id_employee);
                try(ResultSet resultSet = statement.executeQuery()){
                    if (resultSet.next()){
                        return Optional.of(extractEmployeeFromResultSet(resultSet));
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        return Optional.empty();
    }
    private Employee extractEmployeeFromResultSet(ResultSet resultSet) throws Exception{
            int id_employee = resultSet.getInt("id_employee");
            String nom = resultSet.getString("nom");
            String prenom = resultSet.getString("prenom");
            String email = resultSet.getString("email");
            String poste = resultSet.getString("poste");
            String contacts = resultSet.getString("contacts");
            String genre = resultSet.getString("genre");
            String address = resultSet.getString("address");

            return new Employee(id_employee,nom,prenom,email,poste,contacts,genre,address);

    }


}
