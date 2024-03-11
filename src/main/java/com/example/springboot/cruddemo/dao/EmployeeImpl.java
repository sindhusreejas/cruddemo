package com.example.springboot.cruddemo.dao;

import com.example.springboot.cruddemo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeImpl implements EmployeeDAO{

    EntityManager entityManager1;

    public EmployeeImpl(){

    }

    @Autowired
    public EmployeeImpl(EntityManager entityManager) {
        entityManager1 = entityManager;
    }

    @Override
    public List<Employee> findAll() {

        TypedQuery<Employee> emp = entityManager1.createQuery("FROM Employee", Employee.class);

        return emp.getResultList();
    }

    @Override
    public Employee findById(int id) {

        Employee emp = entityManager1.find(Employee.class, id);
        return emp;
    }

    @Override
    public Employee save(Employee emp) {
        Employee emp1 = entityManager1.merge(emp);

        return emp1;
    }

    @Override
    public void deleteById(int id) {
        Employee emp = entityManager1.find(Employee.class, id);
        entityManager1.remove(emp);
    }
}
