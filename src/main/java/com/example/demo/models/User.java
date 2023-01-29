package com.example.demo.models;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_tr_user")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer Id;

    @Column(name = "password")
    private String Password;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "role_id", referencedColumnName = "id") 
    private Role role;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "id", referencedColumnName = "id")
    private Employee employee;

    public Employee getEmployee() {
        return employee;
    }


    public void setEmployee(Employee employee) {
        this.employee = employee;
    }


    public Integer getId() {
        return Id;
    }


    public void setId(Integer Id) {
        this.Id = Id;
    }


    public String getPassword() {
        return Password;
    }


    public void setPassword(String Password) {
        this.Password = Password;
    }


    public Role getRole() {
        return role;
    }


    public void setRole(Role role) {
        this.role = role;
    }

}
