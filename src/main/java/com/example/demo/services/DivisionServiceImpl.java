package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Division;
import com.example.demo.repositories.DivisionRepository;

@Service
public class DivisionServiceImpl implements DivisionService{

    @Autowired
    private DivisionRepository divisionRepository;

    @Override
    public List<Division> getAll() {
        return divisionRepository.findAll();
    }

    @Override
    public Division getById(Integer Id) {
        return divisionRepository.findById(Id).orElseThrow(() -> new IllegalArgumentException("Divisi Tidak Ditemukan"));
    }

    @Override
    public Boolean save(Division division) {
        divisionRepository.save(division);
        return divisionRepository.findById(division.getId()).isPresent();
    }

    @Override
    public Boolean delete(Integer Id) {
        divisionRepository.deleteById(Id);
        return !divisionRepository.findById(Id).isPresent();
    }
    
}
