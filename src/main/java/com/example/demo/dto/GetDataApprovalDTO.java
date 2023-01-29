package com.example.demo.dto;

import java.sql.Date;

public class GetDataApprovalDTO {
    private Integer IdRequest;

    private String EmployeeRequestName;

    private String Description;   

    private String LeaveType;

    private Date StartDate;

    private Date EndDate;

    private String EmployeeApprovalName;

    public Integer getIdRequest() {
        return IdRequest;
    }

    public void setIdRequest(Integer idRequest) {
        IdRequest = idRequest;
    }

    public String getEmployeeRequestName() {
        return EmployeeRequestName;
    }

    public void setEmployeeRequestName(String employeeRequestName) {
        EmployeeRequestName = employeeRequestName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getLeaveType() {
        return LeaveType;
    }

    public void setLeaveType(String leaveType) {
        LeaveType = leaveType;
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

    public String getEmployeeApprovalName() {
        return EmployeeApprovalName;
    }

    public void setEmployeeApprovalName(String employeeApprovalName) {
        EmployeeApprovalName = employeeApprovalName;
    }

}
