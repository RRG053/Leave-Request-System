package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("leave_request")
public class LeaveRequestController {

    @GetMapping
    public String index(){
        return "redirect:/leave_request/dashboard";
    }

    @GetMapping("dashboard")
    public String dashboard(){
        return "leave_request/dashboard";
    }

    @GetMapping("create_request")
    public String createRequest(){
        return "leave_request/form";
    }

    @PostMapping("create_request/submit")
    public String submitRequest(){
        return null;
    }

}