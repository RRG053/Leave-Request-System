package com.example.demo.services;

import com.example.demo.models.Role;

import java.util.List;

public interface RoleService {

    public List<Role> getAll();
    public Role getById(Integer Id);
    public Boolean save(Role role);
    public Boolean delete(Integer Id);
    public Integer getidbylevel(); //untuk otomatis level tertinggi pada role (4 sebagai intern) dgn query pada ROLEREPO
    
}
