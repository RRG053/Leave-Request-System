package com.example.demo.services;

import java.util.List;

import com.example.demo.models.Request;

import com.example.demo.models.Status;


public interface LeaveRequestService {
    public List<Request> getRequestHistoryFromEmployeeId(Integer id);
    public Status getStatusByRequestId(Integer id);
    public String getRoleNameByEmployeeId(Integer id);
}
