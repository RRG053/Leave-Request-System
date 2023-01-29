package com.example.demo.dto;
import java.sql.Date;

public class RegisterDTO {
    private Integer Id;

    private String Nik;

    private String Fullname;

    private String Email;

    private Date JoinDate;

    private String PhoneNumber;

    private String Address;

    private String Password;

    private Integer DivisionId;

    private Integer RoleId;

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

    public Date getJoinDate() {
        return JoinDate;
    }

    public void setJoinDate(Date joinDate) {
        JoinDate = joinDate;
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

    public void setAddress(String address) {
        Address = address;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public Integer getDivisionId() {
        return DivisionId;
    }

    public void setDivisionId(Integer divisionId) {
        DivisionId = divisionId;
    }

    public Integer getRoleId() {
        return RoleId;
    }

    public void setRoleId(Integer roleId) {
        RoleId = roleId;
    }


    


}
