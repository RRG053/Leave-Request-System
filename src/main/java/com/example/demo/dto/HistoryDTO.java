package com.example.demo.dto;

import java.time.LocalDate;

public class HistoryDTO {
    private Integer requestId;
    private LocalDate statusDate;
    private String employeeName;
    private String roleName;
    private String status;
    public Integer getRequestId() {
        return requestId;
    }
    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }
    public LocalDate getStatusDate() {
        return statusDate;
    }
    public void setStatusDate(LocalDate statusDate) {
        this.statusDate = statusDate;
    }
    public String getEmployeeName() {
        return employeeName;
    }
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
    public String getRoleName() {
        return roleName;
    }
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
