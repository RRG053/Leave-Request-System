package com.example.demo.models;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.*;

@Entity
@Table(name = "tb_tr_request")
public class Request {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer Id;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "start_date")
    private Date StartDate;

    @Column(name = "end_date")
    private Date EndDate;

    @Column(name = "temp_phone_number")
    private String PhoneNumber;

    @Column(name = "temp_address")
    private String Address;

    @Column(name = "description")
    private String Description;

    @ManyToOne
    @JoinColumn(name = "leave_id", referencedColumnName = "id")
    private Leave leave;

    @ManyToOne
    @JoinColumn(name = "employee_backup_id", referencedColumnName = "id")
    private Employee employee;




    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Date getStartDate() {
        return StartDate;
    }

    public void setStartDate(Date startDate) {
        StartDate = startDate;
    }

    public Date getEndDate() {
        return EndDate;
    }

    public void setEndDate(Date endDate) {
        EndDate = endDate;
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

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Leave getLeave() {
        return leave;
    }

    public void setLeave(Leave leave) {
        this.leave = leave;
    }
}
