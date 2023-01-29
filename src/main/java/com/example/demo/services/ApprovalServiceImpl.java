package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Request;
import com.example.demo.models.Status;
import com.example.demo.repositories.RequestRepository;
import com.example.demo.repositories.StatusRepository;


@Service
public class ApprovalServiceImpl implements ApprovalService{

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Override
    public List<Request> getAll() {
        return requestRepository.findAll();
    }

    @Override
    public Request getById(Integer Id) {
        return requestRepository.findById(Id).orElseThrow(() -> new IllegalArgumentException("Request Tidak Ditemukan"));
    }

    @Override
    public List<Request> getApprovalBackupByEmployeeId(Integer Id) {
        return requestRepository.getApprovalBackupByEmployeeId(Id);
    }

    @Override
    public Integer getEmployeeIdRequest(Integer Id){
        return requestRepository.getEmployeeIdRequest(Id);
    }

    @Override
    public Boolean saveStatus(Status status) {
        statusRepository.save(status);
        return statusRepository.findById(status.getId()).isPresent();
    }
    
}

