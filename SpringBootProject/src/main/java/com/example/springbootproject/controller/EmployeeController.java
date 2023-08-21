package com.example.springbootproject.controller;

import com.example.springbootproject.model.Employee;
import com.example.springbootproject.repository.EmployeeDAO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {
    private EmployeeDAO empDAO;
    public EmployeeController(EmployeeDAO empDAO){
        this.empDAO = empDAO;
    }

    @GetMapping("/ListEmployee")
    public List<Employee> ListAllEmployee() throws Exception {
        return empDAO.FindAllEmp();
    }
    @PostMapping("/insertemployee")
    public String InsertEmployee(@RequestBody Employee employee) {
        empDAO.InsertEmployee(
                employee.getId_employee(),
                employee.getNom(),
                employee.getPrenom(),
                employee.getEmail(),
                employee.getAddress(),
                employee.getContacts(),
                employee.getPoste(),
                employee.getGenre()
        );

        return "Employee inserted successfully!";
    }

}
