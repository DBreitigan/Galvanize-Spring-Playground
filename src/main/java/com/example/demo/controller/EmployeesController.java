package com.example.demo.controller;

import com.example.demo.database.EmployeeRepository;
import com.example.demo.model.Employee;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeesController {

    private EmployeeRepository repository;

    public EmployeesController(EmployeeRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/employees")
    public String getEmployees() {
        return "Super secret list of employees";
    }

    @GetMapping("/admin/employees")
    public Iterable<Employee> getAdminEmployees() {
        return repository.findAll();
    }

    @GetMapping("/me")
    public Employee getMe(@AuthenticationPrincipal Employee employee) {
        return employee;
    }

}