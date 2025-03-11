package com.ttn.bootcamp.serivce.day1;


import com.ttn.bootcamp.Dao.Employee;
import com.ttn.bootcamp.exception.ResourceNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class EmployeeDaoService {

    private static List<Employee> employees = new ArrayList<>();

    private static Integer employeesCount= 0;

    static {
        employees.add(new Employee(++employeesCount,"Preeti",23));
        employees.add(new Employee(++employeesCount,"Rahul",22));
        employees.add(new Employee(++employeesCount,"Priya",24));
    }


    public List<Employee> findAll() {
        return employees;
    }

    public Employee save(Employee employee) {
        employee.setId(++employeesCount);
        employees.add(employee);
        return employee;
    }

    public Employee findOne(Integer id) {
        Predicate<? super Employee> predicate = employee -> employee.getId().equals(id);
        return employees.stream().filter(predicate).findFirst().orElseThrow(() ->
                new ResourceNotFoundException("Employee with id '" + id + "' not found"));

    }

    public void deleteById(int id) {
        Predicate<? super Employee> predicate = employee -> employee.getId().equals(id);
        employees.removeIf(predicate);
    }

    // Day 1 - Question 7
    public Employee update(int id, Employee updatedEmployee) {
        Employee employee = findOne(id);
        employee.setName(updatedEmployee.getName());
        employee.setAge(updatedEmployee.getAge());
        return employee;
    }
}
