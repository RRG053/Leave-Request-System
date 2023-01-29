package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.LoginDTO;
import com.example.demo.dto.RegisterDTO;
import com.example.demo.models.Division;
import com.example.demo.models.Employee;
import com.example.demo.models.Role;
import com.example.demo.models.User;
import com.example.demo.services.DivisionService;
import com.example.demo.services.EmployeeService;
import com.example.demo.services.RoleService;
import com.example.demo.services.UserService;

@RestController
@RequestMapping("/api/user")
public class RestUserManagementController {

    private EmployeeService employeeService;
    private RoleService roleService;
    private UserService userService;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private DivisionService divisionService;

    @Autowired
    public RestUserManagementController(
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

    
    // @GetMapping(value = {"register"})
    // public String create(Model model){
    //         model.addAttribute("role_id", roleService.getidbylevel());
    //         model.addAttribute("register", new RegisterDTO());
    //         model.addAttribute("division_list", divisionService.getAll());
    //     return "employee/register";
    // }

    @PostMapping("register")
    public ResponseEntity<?> save(@RequestBody RegisterDTO register){
        boolean result, result2;
        Employee employee = new Employee();
        employee.setEmail(register.getEmail());
        employee.setNik(register.getNik());
        employee.setFullname(register.getFullname());
        employee.setJoinDate(register.getJoinDate());
        employee.setPhoneNumber(register.getPhoneNumber());
        employee.setAddress(register.getAddress());

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
            return ResponseEntity.ok().body("200 Register Sukses");
        }else{
            return ResponseEntity.badRequest().body("400 / 500 Register Gagal (Error Pada Request / Server)");
        }
    }

    @PostMapping("login")
    public ResponseEntity<?> submitLogin(@RequestBody LoginDTO loginDTO){

        org.springframework.security.core.Authentication authentication = authenticationManager
            .authenticate(new UsernamePasswordAuthenticationToken(
            loginDTO.getEmail(), loginDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        if (authentication.isAuthenticated() == true) {
            return ResponseEntity.ok().body("200 Login Sukses");
        } else {
            return ResponseEntity.badRequest().body("400 /500 Login Gagal (Error Pada Request / Server)");
        }
    }

    @PostMapping("change_password")
    public ResponseEntity<?> forgetPasswordSubmitPassword(@RequestBody User u) {
        userService.updatePasswordEmployee(u.getId(), passwordEncoder.encode(u.getPassword()));
        return ResponseEntity.ok().body("200 Password Terganti");
    }

    // @GetMapping("forgetpassword")
    // public String forgetPassword(Model model){
    //     model.addAttribute("employee", new Employee());
    //     return "employee/forgetpassword";
    // }

    // @PostMapping("forgetpassword/submitEmail")
    // public String forgetPasswordSubmitEmail(Employee e){
    //     Integer x = employeeService.getIdEmployeeFromEmail(e.getEmail());
    //     if(x!=null){
    //         return "redirect:/employee/forgetpassword/employeeId/"+x;
    //     }else{
    //         return "employee/forgetpassword";
    //     }
    // }

    // @GetMapping("forgetpassword/employeeId/{Id}")
    // public String forgetPasswordChange(@PathVariable (required=true) Integer Id, Model model){
    //     model.addAttribute("id", Id);
    //     model.addAttribute("user", new User());
    //     return "employee/forgetpassword2";
    // }

    // @PostMapping("forgetpassword/submitPassword")
    // public String forgetPasswordSubmitPassword(User u) {
    //     userService.updatePasswordEmployee(u.getId(), passwordEncoder.encode(u.getPassword()));
    //     return "redirect:/employee/login";
    // }
}
