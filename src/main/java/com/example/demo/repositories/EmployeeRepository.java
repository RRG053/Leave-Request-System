package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    
    @Query(value = 
    "SELECT e.id, e.nik, e.fullname, e.email, e.phone_number, e.join_date, e.remaining_leave, e.address, e.division_id, u.password, r.name FROM tb_tr_employee e JOIN tb_tr_user u ON e.Id = u.Id JOIN tb_m_role r ON u.role_id = r.Id WHERE e.email = ?1", nativeQuery = true)
    Employee findEmployee(String email);

    @Query(value = "SELECT id FROM tb_tr_employee WHERE email = ?", nativeQuery = true)
    Integer getIdByEmail(String email);

    @Query(value = "SELECT fullname FROM tb_tr_employee WHERE id = ?", nativeQuery = true)
    String getNameById(Integer id);
}
