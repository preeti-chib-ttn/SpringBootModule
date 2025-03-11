package com.ttn.bootcamp.controller.day1;

import com.ttn.bootcamp.Dao.Employee;
import com.ttn.bootcamp.serivce.day1.EmployeeDaoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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
    public Employee getEmployeeById(@RequestParam Integer id){
        return employeeService.findOne(id);
    }

    // Question 5
    @PostMapping("/add")
    public Employee addEmployee(@RequestBody @Valid Employee employee){
        return employeeService.save(employee);
    }

    // Question 7
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployeeById(@RequestParam int id){
        employeeService.deleteById(id);
        return ResponseEntity.ok("Employee Deleted successfully");
    }

    // Day 1 - Question 7
    @PutMapping("/{id}")
    public Employee updateEmployee(@RequestParam int id,@RequestBody @Valid Employee employee){
        return employeeService.update(id,employee);
    }
}
