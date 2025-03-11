package com.ttn.bootcamp.serivce.day1;


import com.ttn.bootcamp.entity.day1.Employee;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class EmployeeDaoService {

    private static List<Employee> employees = new ArrayList<>();

    private static int employeesCount = 0;

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

    public Employee findOne(int id) {
        Predicate<? super Employee> predicate = employee -> employee.getId().equals(id);
        return employees.stream().filter(predicate).findFirst().orElse(null);
    }

    public void deleteById(int id) {
        Predicate<? super Employee> predicate = employee -> employee.getId().equals(id);
        employees.removeIf(predicate);
    }
}
