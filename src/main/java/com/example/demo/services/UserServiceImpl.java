package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.LoginDTO;
import com.example.demo.models.Employee;
import com.example.demo.models.User;
import com.example.demo.repositories.EmployeeRepository;
import com.example.demo.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getById(Integer Id) {
        return userRepository.findById(Id)
        .orElseThrow(() -> new IllegalArgumentException("Region Tidak Ditemukan"));
    }

    @Override
    public Boolean save(User user) {
        userRepository.save(user);
        return userRepository.findById(user.getId()).isPresent();
    }

    @Override
    public Boolean delete(Integer Id) {
        userRepository.deleteById(Id);
        return !userRepository.findById(Id).isPresent();
    }
    
    @Override
    public void updatePasswordEmployee(Integer id, String Password){
        userRepository.updatePasswordEmployee(id, Password);
    }

    @Override
    public Object logiDTO(LoginDTO loginDTO) {
        Employee emp = employeeRepository.findEmployee(loginDTO.getEmail());

        if(emp.getEmail() != null && passwordEncoder.matches(loginDTO.getPassword(), emp.getUser().getPassword())){
            return logiDTO(loginDTO);
        }
        return false;
    }
}
