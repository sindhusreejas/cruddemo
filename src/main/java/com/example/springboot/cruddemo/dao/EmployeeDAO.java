package com.example.springboot.cruddemo.dao;

import com.example.springboot.cruddemo.entity.Employee;

import java.util.List;

public interface EmployeeDAO {
    List<Employee> findAll();

    Employee findById(int id);

    Employee save(Employee emp);

    void deleteById(int id);

}
