package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.models.Division;
import com.example.demo.services.DivisionService;
import com.example.demo.services.RegionService;



@Controller
@RequestMapping("division")
public class DivisionController {
    @Autowired
    private DivisionService divisionService;
    
    @Autowired
    private RegionService regionService;

    @GetMapping
    public String index(Model model){
        model.addAttribute("division", divisionService.getAll());
        return "division/index";
    }

    @GetMapping(value = {"form", "form/{Id}"})
    public String create(@PathVariable (required = false) Integer Id, Model model){
        if(Id!=null){
            model.addAttribute("division", divisionService.getById(Id));
            model.addAttribute("region", regionService.getAll());
        }else{
            model.addAttribute("division", new Division());
            model.addAttribute("region", regionService.getAll());
        }
        return "division/form";
    }

    

    @PostMapping("save")
    public String save(@Nullable Division division){
       boolean result;
        if(division.getId() == null ){
            result = divisionService.save(division);
        }else{
            result = divisionService.save(division);
        }
        if(result){
            return "redirect:/division";
        }else{
            return "division/form";
        }
    }

    
    // ? (Bikin method baru dari method di atas, menambah PathVariable regionId, dan disable if)
    @GetMapping("/delete/{Id}")
    public String delete(@PathVariable Integer Id, Division division){
        divisionService.delete(Id);
        return "redirect:/division";
    }
}


