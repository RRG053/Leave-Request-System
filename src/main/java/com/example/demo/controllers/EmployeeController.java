package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.models.Employee;
import com.example.demo.models.User;
import com.example.demo.services.EmployeeService;
import com.example.demo.services.UserService;
import com.example.demo.services.RoleService;

@Controller
@RequestMapping("employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    
    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String index(Model model){
        model.addAttribute("employee", employeeService.getAll());
        return "employee/index";
    }

    @GetMapping(value = {"register"})
    public String create(Model model){
            model.addAttribute("role_id", roleService.getidbylevel());
            model.addAttribute("employee", new Employee());
            model.addAttribute("user", new User());
        return "employee/register";
    }

    @PostMapping("save")
    public String save(@Nullable Employee employee){
        boolean result;
        // Integer idlevel = roleService.getidbylevel();
        result = employeeService.save(employee);

        if(result){
            return "redirect:/employee";
        }else{
            return "employee/register";
        }
    }

    @GetMapping(value = {"login"})
    public String login(Model model){
            model.addAttribute("employee", new Employee());
            model.addAttribute("user", new User());
        return "employee/login";
    }

    @PostMapping("login/submit")
    public String submitLogin(Employee e, User u){
        Boolean x = employeeService.findEmployee(e.getEmail(), u.getPassword());
        if(x){
            return "redirect:/employee";
        }else{
            return "redirect:/employee/login";
        }
    }

    @GetMapping("forgetpassword")
    public String forgetPassword(Model model){
        model.addAttribute("employee", new Employee());
        return "employee/forgetpassword";
    }

    @PostMapping("forgetpassword/submitEmail")
    public String forgetPasswordSubmitEmail(Employee e){
        Integer x = employeeService.getIdEmployeeFromEmail(e.getEmail());
        if(x!=null){
            // System.out.println(Integer.toString(x));
            return "redirect:/employee/forgetpassword/employeeId/"+x;
        }else{
            return "employee/forgetpassword";
        }
    }

    @GetMapping("forgetpassword/employeeId/{Id}")
    public String forgetPasswordChange(@PathVariable (required=true) Integer Id, Model model){
        model.addAttribute("id", Id);
        model.addAttribute("user", new User());
        return "employee/forgetpassword2";
    }

    @PostMapping("forgetpassword/submitPassword")
    public String forgetPasswordSubmitPassword(User u) {
        userService.updatePasswordEmployee(u.getId(), u.getPassword());
        return "redirect:/employee/login";
    }

    
    // ? (Bikin method baru dari method di atas, menambah PathVariable regionId, dan disable if)
    // @GetMapping("/delete/{Id}")
    // public String delete(@PathVariable Integer Id, Employee employee){
    //     employeeService.delete(Id);
    //     return "redirect:/employee";
    // }
}