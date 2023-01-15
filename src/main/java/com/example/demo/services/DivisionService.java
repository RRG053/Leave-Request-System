package com.example.demo.services;

import com.example.demo.models.Division;
import java.util.List;


public interface DivisionService {
    public List<Division> getAll();
    public Division getById(Integer Id);
    public Boolean save(Division division);
    public Boolean delete(Integer Id); 
}
