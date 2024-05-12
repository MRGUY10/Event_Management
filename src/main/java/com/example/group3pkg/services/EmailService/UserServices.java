package com.example.group3pkg.services.EmailService;



import java.util.Random;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.group3pkg.Dto.RequestDto;
import com.example.group3pkg.Dto.ResponseDto;
import com.example.group3pkg.models.User;
import com.example.group3pkg.repositories.UserRepository;



@Service
public class UserServices {

    @Autowired
    UserRepository userRepo;
    @Autowired
    EmailService emailService;
    @Autowired
    PasswordEncoder passwordEncoder;


    public ResponseDto registerUser(RequestDto request){
        ResponseDto res = new ResponseDto();

        User existingUser = this.userRepo.findByEmail(request.getEmail());
        if(existingUser != null) {
            res.setMessage("UserRepo already registered.");
        } else {
            Random r = new Random();
            String otp = String.format("%06d", r.nextInt(100000));

            User newUser = new User();
            newUser.setUsername(request.getUsername());
            newUser.setEmail(request.getEmail());
            newUser.setRole(request.getRole());
            newUser.setOtp(otp);
            newUser.setVerified(false);

            User savedUser = this.userRepo.save(newUser);
            String subject = "Email Verfication";
            String body = "Your verification OTP is "+savedUser.getOtp();
            //Email Send
            this.emailService.sendEmail(savedUser.getEmail(), subject, body);

            res.setUser_id(savedUser.getUser_id());
            res.setUsername(savedUser.getUsername());
            res.setEmail(savedUser.getEmail());
            res.setRole(savedUser.getRole());
            res.setMessage("OTP sent successfully!");

        }

        return res;

    }


    public String verifyUser(String email, String otp) {
        String response = "";
        User user = this.userRepo.findByEmail(email);

        if(user != null && user.isVerified()) {
            response = "UserRepo is already verified.";
        } else if (user != null && otp.equals(user.getOtp())) {
            user.setVerified(true);
            this.userRepo.save(user);
            response = "UserRepo verified successfully. Please set your password.";
        } else {
            response = "UserRepo not verified.";
        }

        return response;
    }

    public String setPassword(String email, String password) {
        String response = "";
        User user = this.userRepo.findByEmail(email);

        if(user != null && user.isVerified()) {
            String encodedPassword = passwordEncoder.encode(password);
            user.setPassword(encodedPassword);
            this.userRepo.save(user);
            response = "Password set successfully.";
        } else {
            response = "UserRepo not verified.";
        }

        return response;
    }
    public String login(String email, String password) {
        User user = this.userRepo.findByEmail(email);
        if (user != null && user.isVerified()) {
            if (passwordEncoder.matches(password, user.getPassword())) {
                return "Login successful.";
            } else {
                return "Incorrect password.";
            }
        } else {
            return "UserRepo not found or not verified.";
        }
    }
}


