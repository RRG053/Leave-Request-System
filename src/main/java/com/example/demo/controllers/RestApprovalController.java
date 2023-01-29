package com.example.demo.controllers;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.config.MyUserDetails;
import com.example.demo.dto.GetDataApprovalDTO;
import com.example.demo.models.Employee;
import com.example.demo.models.Leave;
import com.example.demo.models.Request;
import com.example.demo.models.Status;
import com.example.demo.services.ApprovalService;
import com.example.demo.services.EmployeeService;


@RestController
@RequestMapping("/api/leave_request/approval")
public class RestApprovalController {

    @Autowired
    MyUserDetails userDetails;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    ApprovalService approvalService;
	
	

    @GetMapping("getdata")
    public List<GetDataApprovalDTO> index(){
        Integer id_employee = employeeService.getIdEmployeeFromEmail(userDetails.getUsername());
        if(userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("Staff"))){
            List<GetDataApprovalDTO> list_g = new ArrayList<GetDataApprovalDTO>();
            List<Request> r_list =  approvalService.getApprovalBackupByEmployeeId(id_employee);
            r_list.forEach((r) -> {
                GetDataApprovalDTO g = new GetDataApprovalDTO();
                g.setIdRequest(r.getId()); //id form request
                g.setDescription(r.getDescription()); //deskripsi cuti dalam request
                g.setStartDate(r.getStartDate()); //tanggal mulai cuti
                g.setEndDate(r.getEndDate()); //tanggal berakhir cuti
                g.setLeaveType(r.getLeave().getName()); //jenis cuti yang diambil
                g.setEmployeeRequestName(employeeService.getNameEmployeeFromId(approvalService.getEmployeeIdRequest(r.getId())));
                g.setEmployeeApprovalName(employeeService.getNameEmployeeFromId(id_employee));
                // return g;
                list_g.add(g);
            });
            return list_g;

            // return approvalService.getApprovalBackupByEmployeeId(id_employee);
        }
        return null;
    }

    @GetMapping("{idRequest}/accept")
    public ResponseEntity<Object> acceptRequest(@PathVariable Integer idRequest){
        Integer id_employee = employeeService.getIdEmployeeFromEmail(userDetails.getUsername());
        if(userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("Staff"))){
            Status status = new Status();
            status.setStatus("APPROVED");
            status.setDate(java.time.LocalDate.now());
            status.setEmpIdRequest(approvalService.getEmployeeIdRequest(idRequest));

            Employee employee = new Employee();
            Request request = new Request();
            employee.setId(id_employee);
            request.setId(idRequest);
            status.setEmployee(employee);
            status.setRequest(request);
            Boolean x = approvalService.saveStatus(status);
            if(x==true){
                return ResponseEntity.ok().body("200 Request Telah Di-Approve");
            }else{
                return ResponseEntity.badRequest().body("400 / 500 Approve Request Gagal (Error Pada Client / Server)");
            } 
        }
        return ResponseEntity.badRequest().body("401 Role tidak cocok");
    }

    @GetMapping("{idRequest}/reject")
    public ResponseEntity<Object> rejectRequest(@PathVariable Integer idRequest){
        Integer id_employee = employeeService.getIdEmployeeFromEmail(userDetails.getUsername());
        if(userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("Staff"))){
            Status status = new Status();
            status.setStatus("REJECTED");
            status.setDate(java.time.LocalDate.now());
            status.setEmpIdRequest(approvalService.getEmployeeIdRequest(idRequest));
            
            Employee employee = new Employee();
            Request request = new Request();
            employee.setId(id_employee);
            request.setId(idRequest);
            status.setEmployee(employee);
            status.setRequest(request);
            Boolean x = approvalService.saveStatus(status);
            if(x==true){
                return ResponseEntity.ok().body("200 Request Telah Di-Reject");
            }else{
                return ResponseEntity.badRequest().body("400 / 500 Reject Request Gagal (Error Pada Client / Server)");
            } 
        }
        return ResponseEntity.badRequest().body("401 Role tidak cocok");
    }

    // @GetMapping("approve_request/{idRequest}")
    // public ResponseEntity<Object> approveRequest(@PathVariable (required = true) Integer idRequest, @RequestBody ApprovalDTO data){
	// 	ApprovalDTO status = approvalService.getApprovalBackupByEmployeeId(idRequest);
    //     status.setDate(java.time.LocalDate.now());
    //     status.setStatus(data.getStatus());
    //     approvalService.save(status);

    //     return new ResponseEntity<>("Updated status", HttpStatus.OK);
	// }

    // @PostMapping("approve_request/submit")
    // public String submitApproveRequest(){
    //     return null;
    // }

    
}
