package com.example.springbootproject.controller;

import com.example.springbootproject.Service.EmployeeService;
import com.example.springbootproject.model.Employee;
import com.example.springbootproject.repository.EmployeeDAO;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeController {
   private EmployeeService employeeService;
   private EmployeeDAO empDAO;

    public EmployeeController(EmployeeService employeeService, EmployeeDAO empDAO) {
        this.employeeService = employeeService;
        this.empDAO = empDAO;
    }

    @GetMapping("/ListEmployee")
    public List<Employee> ListAllEmployee() throws Exception {
        return empDAO.FindAllEmp();
    }
    @PostMapping("/insertemployee")
    public Employee insertEmp(@RequestBody Employee insertEmp){
        return employeeService.insert(insertEmp);
    }
    @GetMapping("/Employee/{id_employee}")
    public Optional<Employee> findEmployeeId(@PathVariable int id_employee) throws SQLException {
        return employeeService.findByid(id_employee);
    }
}
