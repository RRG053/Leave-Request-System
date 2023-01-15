package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.models.Region;
import com.example.demo.services.RegionService;

@Controller
@RequestMapping("region")
public class RegionController {
    @Autowired
    private RegionService regionService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("region", regionService.getAll());
        return "region/index";
    }

    @GetMapping(value = {"form", "form/{Id}"})
    public String create(@PathVariable (required = false) Integer Id, Model model){
        //Object data = rrdao.getById(regionId);
        if(Id!=null){
            model.addAttribute("region", regionService.getById(Id));
        }else{
            model.addAttribute("region", new Region());
        }
        return "region/form";
    }

    @PostMapping("save")
    public String save(@Nullable Region region){
        boolean result;
        if(region.getId() == null){
            result = regionService.save(region);
        }else{
            result = regionService.save(region);
        }
        if(result){
            return "redirect:/region";
        }else{
            return "region/form";
        }
    }

    @GetMapping("/delete/{Id}")
    public String delete(@PathVariable Integer Id, Region region){
        regionService.delete(Id);
        return "redirect:/region";
    }

}
