package com.example.demo.services;

import com.example.demo.models.Employee;

import java.util.List;


public interface EmployeeService {
    public List<Employee> getAll();
    public Employee getById(Integer Id);
    public Boolean save(Employee employee);
    public Boolean delete(Integer Id); 
    public Boolean findEmployee(String email,String password);
    public Integer getIdEmployeeFromEmail(String email);
}
