package com.example.demo.services;

import com.example.demo.dto.LoginDTO;
import com.example.demo.models.User;

import java.util.List;


public interface UserService {
    public List<User> getAll();
    public User getById(Integer Id);
    public Boolean save(User user);
    public Boolean delete(Integer Id); 
    public void updatePasswordEmployee(Integer id, String password); //update password employee dgn query dari USERREPO
    public Object logiDTO(LoginDTO loginDTO);
}
