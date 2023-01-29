package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Request;

@Repository
public interface RequestRepository extends JpaRepository<Request, Integer>{
    
    @Query(value = "SELECT r.* FROM tb_tr_status s join tb_tr_request r on s.request_id=r.id join tb_m_leave l on r.leave_id=l.id join tb_tr_employee e on s.employee_id=e.id WHERE s.id = (select max(s.id) from tb_tr_status s join tb_tr_request r on r.id=s.request_id) and s.employee_id= ? and s.status='REQUESTED'", nativeQuery = true)
    List<Request> getApprovalBackupByEmployeeId(Integer id);

    @Query(value="select employee_id_request from tb_tr_status  where request_id = ? and status='REQUESTED'", nativeQuery = true)
    Integer getEmployeeIdRequest(Integer id);

    @Query(value="select r.* from tb_tr_request as r join tb_tr_status as s on r.id=s.request_id join tb_tr_employee as e on s.employee_id=e.id join tb_tr_user as u on e.id=u.id join tb_m_role as rl on u.role_id=rl.id where s.id = (select max(s.id) from tb_tr_status s join tb_tr_request r on r.id=s.request_id where s.employee_id_request= ?)", nativeQuery = true)
    List<Request> getRequestHistoryByEmployeeId(Integer id);
}