// package com.example.demo.controllers;

// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;

// public class RegisterController {
    
//     @GetMapping(value = {"form", "form/{Id}"})
//     public String create(@PathVariable (required = false) Integer Id, Model model){
//         if(Id!=null){
//             model.addAttribute("role", roleService.getById(Id));
//             // model.addAttribute("region", regionService.getAll());
//         }else{
//             model.addAttribute("role", new Role());
//             // model.addAttribute("region", regionService.getAll());
//         }
//         return "role/form";
//     }

// }
