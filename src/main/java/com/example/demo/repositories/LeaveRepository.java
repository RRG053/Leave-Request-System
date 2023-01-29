package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Leave;

public interface LeaveRepository extends JpaRepository<Leave, Integer> {
    
}
