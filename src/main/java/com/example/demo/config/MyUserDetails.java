package com.example.demo.config;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.models.Employee;
import com.example.demo.repositories.EmployeeRepository;

@Service
public class MyUserDetails implements UserDetails, UserDetailsService {
    private EmployeeRepository employeeRepository;
    private String username;
    private String password;
    private GrantedAuthority authority;

    public MyUserDetails(){}

    public MyUserDetails(com.example.demo.models.User user){
        this.username=user.getEmployee().getEmail();
        this.password=user.getPassword();
        this.authority=new SimpleGrantedAuthority(user.getRole().getName());
    }

    @Autowired
    public MyUserDetails(EmployeeRepository employeeRepository){
        this.employeeRepository=employeeRepository;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        Set<GrantedAuthority> grantedAuthority = new HashSet<>();
        grantedAuthority.add(authority);
        return grantedAuthority;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Employee data = employeeRepository.findEmployee(username);
        this.authority = new SimpleGrantedAuthority(data.getUser().getRole().getName());
        this.username=data.getEmail();
        this.password=data.getUser().getPassword();
        return new User(data.getEmail(), data.getUser().getPassword(), getAuthorities());
    }

    @Override
    public String getPassword(){
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
