package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Employee;
// import com.example.demo.models.Role;
import com.example.demo.repositories.EmployeeRepository;
// import com.example.demo.repositories.RoleRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getById(Integer Id) {
        return employeeRepository.findById(Id).orElseThrow(() -> new IllegalArgumentException("Employee Tidak Ditemukan"));
    }

    @Override
    public Boolean save(Employee employee) {
        employeeRepository.save(employee);
        return employeeRepository.findById(employee.getUser().getId()).isPresent();
    }

    @Override
    public Employee findEmployee(String email){
        return employeeRepository.findEmployee(email);
    }

    @Override
    public Integer getIdEmployeeFromEmail(String email){
        return employeeRepository.getIdByEmail(email);
    }

    @Override
    public String getNameEmployeeFromId(Integer id){
        return employeeRepository.getNameById(id);
    }

    @Override
    public Boolean delete(Integer Id) {
        employeeRepository.deleteById(Id);
        return !employeeRepository.findById(Id).isPresent();
    }
    
}
