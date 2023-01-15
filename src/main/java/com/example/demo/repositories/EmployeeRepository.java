package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    
    @Query(value = "SELECT * FROM employee e, user u WHERE e.Id = u.Id AND e.email = ?1 AND u.password = ?2", nativeQuery = true)
    Employee findEmployee(String email, String password);

    @Query(value = "SELECT id FROM employee WHERE email = ?", nativeQuery = true)
    Integer getIdByEmail(String email);
}
