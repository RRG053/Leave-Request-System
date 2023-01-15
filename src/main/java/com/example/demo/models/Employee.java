package com.example.demo.models;
import java.sql.Date;

import javax.persistence.*;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer Id;

    @Column(name = "Fullname") //HARUS SESUAI NAMA TABLE
    private String Fullname;

    @Column(name = "Email")
    private String Email;

    @Column(name = "Birthdate")
    private Date Birthdate;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL)
    // @JoinColumn(name = "Id", referencedColumnName = "Id")
    private User user;
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public Integer getId() {
        return Id;
    }
    
    public void setId(Integer Id) {
        this.Id = Id;
    }

    public String getFullname() {
        return Fullname;
    }

    public void setFullname(String fullname) {
        this.Fullname = fullname;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public Date getBirthdate() {
        return Birthdate;
    }

    public void setBirthdate(Date Birthdate) {
        this.Birthdate = Birthdate;
    }
}
