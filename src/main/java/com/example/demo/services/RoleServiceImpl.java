package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Role;
import com.example.demo.repositories.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();

    }

    @Override
    public Role getById(Integer Id) {
        return roleRepository.findById(Id)
        .orElseThrow(() -> new IllegalArgumentException("Role Tidak Ditemukan"));
    }

    @Override
    public Boolean save(Role role) {
        roleRepository.save(role);
        return roleRepository.findById(role.getId()).isPresent();

    }

    @Override
    public Boolean delete(Integer Id) {
        roleRepository.deleteById(Id);
        return !roleRepository.findById(Id).isPresent();
    }

    @Override
    public Integer getidbylevel() {
        return roleRepository.getidbylevel();
    }

    
    
}
