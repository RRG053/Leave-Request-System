package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    
    @Query(value = "select r.id from Role r where r.level = (select max(r.level) from role r)", nativeQuery = true)
    Integer getidbylevel();
}
