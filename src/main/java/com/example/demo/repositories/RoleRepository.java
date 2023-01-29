package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    
    @Query(value = "select r.id from tb_m_role r where r.level = (select max(r.level) from tb_m_role r)", nativeQuery = true)
    Integer getidbylevel();

    @Query(value="SELECT r.name FROM tb_m_role r JOIN tb_tr_user u on r.id=u.role_id join tb_tr_employee e on e.id=u.id WHERE e.id = ?", nativeQuery = true)
    String getRoleNameByEmployeeId(Integer id);
}
