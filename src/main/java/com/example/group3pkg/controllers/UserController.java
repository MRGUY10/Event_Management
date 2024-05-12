package com.example.group3pkg.controllers;






import com.example.group3pkg.Dto.RequestDto;
import com.example.group3pkg.Dto.ResponseDto;
import com.example.group3pkg.services.EmailService.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.servlet.ModelAndView;


import java.security.Principal;

@Controller
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private  UserDetailsService userDetailsService;
    @Autowired
    UserServices userServices;

    public UserController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }
    @GetMapping("/registration")
    public String getRegistrationPage(@ModelAttribute("user") RequestDto request) {
        return "register";
    }
    @PostMapping("/registration")
    public String saveUser(@ModelAttribute("user") RequestDto userDto, Model model) {
        userServices.registerUser(userDto);
        model.addAttribute("message", "Registered Successfuly!");
        return "register";
    }


    @GetMapping("/user-verify")
    public String getVerificationPage(@ModelAttribute("user") RequestDto request) {
        return "register"; // Return the verification page
    }

    @PostMapping("/user-verify")
    public String verifyUser(@RequestParam String email, @RequestParam String otp, Model model) {
        String res = this.userServices.verifyUser(email, otp);
        model.addAttribute("verificationMessage", res); // Pass verification result to the view
        return "register"; // Return the verification page
    }


    @PostMapping("/set-password")
    public ModelAndView setPassword(@RequestParam String email, @RequestParam String password) {
        String res = this.userServices.setPassword(email, password);
        ModelAndView modelAndView = new ModelAndView("register");
        modelAndView.addObject("response", res);
        return modelAndView;
    }




    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }
    @GetMapping("user-page")
    public String userPage (Model model, Principal principal) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("user", userDetails);
        return "admin";
    }

    @GetMapping("admin-page")
    public String adminPage (Model model, Principal principal) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("user", userDetails);
        return "admin";
    }
}
