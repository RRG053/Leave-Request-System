package com.example.demo.services;

import com.example.demo.models.Employee;

import java.util.List;


public interface EmployeeService {
    public List<Employee> getAll();
    public Employee getById(Integer Id);
    public Boolean save(Employee employee);
    public Boolean delete(Integer Id); 
    public Employee findEmployee(String email); //Digunakan untuk login dengan query dari EmpREPO
    public Integer getIdEmployeeFromEmail(String email); //digunakan untuk update pass employee berdasar id email dgn query pada empREPO
    public String getNameEmployeeFromId(Integer id);
}
