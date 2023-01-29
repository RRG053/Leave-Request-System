package com.example.demo.models;

import java.time.LocalDate;

import javax.persistence.*;

@Entity
@Table(name = "tb_tr_status")
public class Status {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer Id;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "status")
    private String Status;

    @Column(name = "employee_id_request")
    private Integer EmpIdRequest;

    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id") 
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "request_id", referencedColumnName = "id") 
    private Request request;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public Integer getEmpIdRequest() {
        return EmpIdRequest;
    }

    public void setEmpIdRequest(Integer empIdRequest) {
        EmpIdRequest = empIdRequest;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }
}
