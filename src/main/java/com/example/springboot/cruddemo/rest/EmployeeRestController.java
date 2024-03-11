package com.example.springboot.cruddemo.rest;

import com.example.springboot.cruddemo.entity.Employee;
import com.example.springboot.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> findAll(){
        return employeeService.findAll();

    }

    @GetMapping("/employees/{employeeid}")
    public Employee getEmployee(@PathVariable int employeeid) {
        return employeeService.findById(employeeid);

    }

    @PostMapping("/employees")
    public Employee postEmployee(@RequestBody Employee emp){
        Employee emp1 =  employeeService.save(emp);

        return emp1;
    }

    @PutMapping("/employees")
    public Employee putEmployee(@RequestBody Employee emp){
        Employee emp1 =  employeeService.save(emp);

        return emp1;
    }
    @DeleteMapping("/employees/{employeeid}")
    public String deleteEmployee(@PathVariable int employeeid){
        //Employee employee = employeeService.findById(employeeid);
        employeeService.deleteById(employeeid);

        return "Employee deleted";
    }

}

