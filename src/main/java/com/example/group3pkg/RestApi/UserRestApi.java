package com.example.group3pkg.RestApi;


import com.example.group3pkg.Dto.RequestDto;
import com.example.group3pkg.Dto.ResponseDto;
import com.example.group3pkg.models.User;
import com.example.group3pkg.services.EmailService.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth")
public class UserRestApi {

    @Autowired
    UserServices userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    public UserRestApi(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/user-register")
    public ResponseEntity<ResponseDto> registerUser(@RequestBody RequestDto request) {
        ResponseDto res = this.userService.registerUser(request);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PostMapping("/user-verify")
    public ResponseEntity<?> verifyUser(@RequestParam String email, @RequestParam String otp) {
        String res = this.userService.verifyUser(email, otp);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
    @PostMapping("/set-password")
    public ResponseEntity<?> setPassword(@RequestParam String email, @RequestParam String password) {
        String res = this.userService.setPassword(email, password);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();
        String res = userService.login(email, password);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }


}
