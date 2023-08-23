package com.example.springbootproject.Service;

import com.example.springbootproject.model.Employee;
import com.example.springbootproject.repository.EmployeeDAO;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private EmployeeDAO empDAO;

    public EmployeeService(EmployeeDAO empDAO) {
        this.empDAO = empDAO;
    }

    public Employee insert(Employee insertEmp){
        try {
            this.empDAO.InsertEmployee(insertEmp);

            return insertEmp;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Employee updateEmployee(Employee update){
        this.empDAO.UpdateEmployee(update);

        return update;
    }

    public List<Employee> findAllEmployee() throws Exception{
        return empDAO.FindAllEmp();
    }

    public Optional<Employee> findByid(int id_employee){
        try {
            return empDAO.findEmployeeById(id_employee);
        } catch (SQLException e) {
            throw new RuntimeException("There has been an error when fetching todo with id :"+id_employee);
        }
    }

    public void deleteEmployee(int id_employee){
        try {
            empDAO.deleteEmployee(id_employee);
        }catch (Exception e){
            throw new RuntimeException("There was an error when deleting Employee with ID " + id_employee, e);
        }
    }
}
