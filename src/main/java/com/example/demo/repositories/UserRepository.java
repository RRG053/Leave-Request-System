package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;

import com.example.demo.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    
    @Transactional
    @Modifying
    @Query(value = "UPDATE user SET password = ?2 WHERE id = ?1",nativeQuery = true)
    void updatePasswordEmployee(Integer id, String password);

}