package com.example.demo.models;
import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "Password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "Id") 
    private Role role;

    @OneToOne
    @JoinColumn(name = "Id", referencedColumnName = "Id")
    private Employee employee;

    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public Role getRole() {
        return role;
    }


    public void setRole(Role role) {
        this.role = role;
    }

}
