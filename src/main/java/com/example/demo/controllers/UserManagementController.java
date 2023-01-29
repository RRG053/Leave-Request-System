package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.models.Division;
import com.example.demo.models.Employee;
import com.example.demo.models.User;
import com.example.demo.models.Role;
import com.example.demo.services.DivisionService;
import com.example.demo.services.EmployeeService;
import com.example.demo.services.RoleService;
import com.example.demo.services.UserService;
import com.example.demo.dto.LoginDTO;
import com.example.demo.dto.RegisterDTO;

@Controller
@RequestMapping("employee")
public class UserManagementController {

    private EmployeeService employeeService;
    private RoleService roleService;
    private UserService userService;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private DivisionService divisionService;

    @Autowired
    public UserManagementController(
            AuthenticationManager authenticationManager,
            UserService userService,
            RoleService roleService,
            EmployeeService employeeService,
            DivisionService divisionService,
            PasswordEncoder passwordEncoder) {
        this.authenticationManager=authenticationManager;
        this.userService=userService;
        this.roleService=roleService;
        this.employeeService=employeeService;
        this.divisionService=divisionService;
        this.passwordEncoder=passwordEncoder;
    }

    
    @GetMapping(value = {"register"})
    public String create(Model model){
            model.addAttribute("role_id", roleService.getidbylevel());
            model.addAttribute("register", new RegisterDTO());
            model.addAttribute("division_list", divisionService.getAll());
        return "employee/register";
    }

    @PostMapping("save")
    public String save(RegisterDTO register){
        boolean result, result2;
        Employee employee = new Employee();
        employee.setEmail(register.getEmail());
        employee.setNik(register.getNik());
        employee.setFullname(register.getFullname());
        employee.setJoinDate(register.getJoinDate());
        employee.setPhoneNumber(register.getPhoneNumber());
        employee.setAddress(register.getAddress());
        // employee.setRemainingLeaves(12);

        User user = new User();
        Role role = new Role();
        Division division = new Division();
        division.setId(register.getDivisionId());
        employee.setDivision(division);
        role.setId(register.getRoleId());
        user.setId(employeeService.getIdEmployeeFromEmail(register.getEmail()));
        user.setPassword(passwordEncoder.encode(register.getPassword()));
        user.setRole(role);
        employee.setUser(user);

        result = employeeService.save(employee);
        result2 = userService.save(user);

        if(result && result2){
            return "redirect:/employee/login";
        }else{
            return "employee/register";
        }
    }

    @GetMapping(value = {"login"})
    public String login(Model model){
            model.addAttribute("login", new LoginDTO());
        return "employee/login";
    }

    @PostMapping("login/submit")
    public String submitLogin(LoginDTO loginDTO){

        org.springframework.security.core.Authentication authentication = authenticationManager
            .authenticate(new UsernamePasswordAuthenticationToken(
            loginDTO.getEmail(), loginDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "redirect:/leave_request";
    }

    // @PostMapping("login/submit")
    // public String submitLogin(Employee e, User u){
    //     Boolean x = employeeService.findEmployee(e.getEmail(), u.getPassword());
    //     if(x){
    //         return "redirect:/employee";
    //     }else{
    //         return "redirect:/employee/login";
    //     }
    // }

    @GetMapping("forgetpassword")
    public String forgetPassword(Model model){
        model.addAttribute("employee", new Employee());
        return "employee/forgetpassword";
    }

    @PostMapping("forgetpassword/submitEmail")
    public String forgetPasswordSubmitEmail(Employee e){
        Integer x = employeeService.getIdEmployeeFromEmail(e.getEmail());
        if(x!=null){
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
        userService.updatePasswordEmployee(u.getId(), passwordEncoder.encode(u.getPassword()));
        return "redirect:/employee/login";
    }
}
