package com.ttn.bootcamp.controller.day1;

import com.ttn.bootcamp.entity.day1.Employee;
import com.ttn.bootcamp.serivce.day1.EmployeeDaoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/day-1/employee")
@Tag(name = "Day 1 - Employee APIs", description = " CRUD API for employee")

public class EmployeeController {

    @Autowired
    private EmployeeDaoService employeeService;


    // Question 3
    @GetMapping("/all")
    public List<Employee> getAllEmployee(){
        return employeeService.findAll();
    }

    // Question 4
    @GetMapping("/{id}")
    public Employee getEmployeeById(@RequestParam int id){
        return employeeService.findOne(id);
    }

    // Question 5
    @PostMapping("/add")
    public Employee addEmployee(@RequestBody Employee employee){
        return employeeService.save(employee);
    }

    // Question 7
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployeeById(@RequestParam int id){
        employeeService.deleteById(id);
        return ResponseEntity.ok("Employee Deleted successfully");
    }



}
