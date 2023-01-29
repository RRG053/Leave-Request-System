package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.services.EmployeeService;

@Controller
@RequestMapping("employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public String index(Model model){
        model.addAttribute("employee", employeeService.getAll());
        return "employee/index";
    }

}