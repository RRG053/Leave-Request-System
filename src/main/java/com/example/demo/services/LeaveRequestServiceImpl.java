package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Request;
import com.example.demo.models.Status;
import com.example.demo.repositories.RequestRepository;
import com.example.demo.repositories.StatusRepository;
import com.example.demo.repositories.RoleRepository;


@Service
public class LeaveRequestServiceImpl implements LeaveRequestService{

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Request> getRequestHistoryFromEmployeeId(Integer id){
        return requestRepository.getRequestHistoryByEmployeeId(id);
    }

    @Override
    public Status getStatusByRequestId(Integer id){
        return statusRepository.findStatusByRequest(id);
    }

    @Override
    public String getRoleNameByEmployeeId(Integer id){
        return roleRepository.getRoleNameByEmployeeId(id);
    }
    
}

