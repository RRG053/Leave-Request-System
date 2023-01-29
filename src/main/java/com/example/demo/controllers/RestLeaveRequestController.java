package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.config.MyUserDetails;
import com.example.demo.dto.HistoryDTO;
import com.example.demo.models.Request;
import com.example.demo.models.Status;
import com.example.demo.services.EmployeeService;
import com.example.demo.services.LeaveRequestService;

@RestController
@RequestMapping("api/leave_request")
public class RestLeaveRequestController {

    @Autowired
    LeaveRequestService leaveRequestService;

    @Autowired
    MyUserDetails userDetails;

    @Autowired
    EmployeeService employeeService;
    
    @GetMapping("history")
    public List<HistoryDTO> getHistory() {
        List<HistoryDTO> list_h = new ArrayList<HistoryDTO>();
        Integer id_employee = employeeService.getIdEmployeeFromEmail(userDetails.getUsername());
        if(userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("Staff"))){
            List<Request> r_list = leaveRequestService.getRequestHistoryFromEmployeeId(id_employee);
            r_list.forEach((r) -> {
                HistoryDTO h = new HistoryDTO();
                Status s = leaveRequestService.getStatusByRequestId(r.getId());
                h.setRequestId(r.getId());
                h.setStatusDate(s.getDate());
                h.setEmployeeName(employeeService.getNameEmployeeFromId(s.getEmployee().getId()));
                h.setRoleName(leaveRequestService.getRoleNameByEmployeeId(s.getEmployee().getId()));
                h.setStatus(s.getStatus());
                list_h.add(h);
            });
            return list_h;

            // return approvalService.getApprovalBackupByEmployeeId(id_employee);
        }
        return null;
    }
    
}