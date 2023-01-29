package com.example.demo.services;

import java.util.List;

import com.example.demo.models.Request;
import com.example.demo.models.Status;


public interface ApprovalService {
    public List<Request> getAll();
    public Request getById(Integer Id);
    public List<Request> getApprovalBackupByEmployeeId(Integer Id);
    public Integer getEmployeeIdRequest(Integer Id);
    public Boolean saveStatus(Status status);
}
