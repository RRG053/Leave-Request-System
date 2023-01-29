package com.example.demo.repositories;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Modifying;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.models.Status;

public interface StatusRepository extends JpaRepository<Status, Integer> {
    
    // @Modifying
    // @Transactional
    // @Query(value = "INSERT INTO tbl_tr_status(status, date, employee_id_request, employee_id, request_id) VALUES (:status, :date, :employee_id_request, :employee_id, :request_id)", nativeQuery = true)
    // public void insertApprovalStatus(@Param("status") String status, @Param("date") LocalDate date, @Param("employee_id_request") Integer employee_id_request, @Param("employee_id_request") Integer employee_id, @Param("request_id") Integer request_id);

    @Query(value="SELECT * FROM tb_tr_status s WHERE s.id = (SELECT MAX(s.id) FROM tb_tr_status s where s.request_id = ?)", nativeQuery=true)
    Status findStatusByRequest(Integer id);
}
