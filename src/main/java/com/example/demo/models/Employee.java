package com.example.demo.models;
import java.sql.Date;

import javax.persistence.*;

@Entity
@Table(name = "tb_tr_employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer Id;

    @Column(name = "nik") //HARUS SESUAI NAMA TABLE
    private String Nik;

    @Column(name = "fullname") //HARUS SESUAI NAMA TABLE
    private String Fullname;

    @Column(name = "email")
    private String Email;

    @Column(name = "phone_number")
    private String PhoneNumber;

    @Column(name = "address")
    private String Address;

    @Column(name = "join_date")
    private Date JoinDate;

    @Column(name = "remaining_leave")
    private Integer RemainingLeaves;
    
    @ManyToOne
    @JoinColumn(name = "division_id", referencedColumnName = "id") 
    private Division division;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL)
    // @JoinColumn(name = "Id", referencedColumnName = "Id")
    private User user;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getNik() {
        return Nik;
    }

    public void setNik(String nik) {
        Nik = nik;
    }
    
    public String getFullname() {
        return Fullname;
    }

    public void setFullname(String fullname) {
        Fullname = fullname;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public Date getJoinDate() {
        return JoinDate;
    }

    public void setJoinDate(Date joinDate) {
        JoinDate = joinDate;
    }

    public Integer getRemainingLeaves() {
        return RemainingLeaves;
    }

    public void setRemainingLeaves(Integer remainingLeaves) {
        RemainingLeaves = remainingLeaves;
    }

    public Division getDivision() {
        return division;
    }

    public void setDivision(Division division) {
        this.division = division;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    

    
}
