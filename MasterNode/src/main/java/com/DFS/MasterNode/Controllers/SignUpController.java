package com.DFS.MasterNode.Controllers;

import com.DFS.MasterNode.Services.ClientDetailsService;
import com.DFS.MasterNode.Services.SignupService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/signup")
public class SignUpController {

    private final SignupService signupService;

    public SignUpController(SignupService signupService){
        this.signupService = signupService;
    }

    @PostMapping 
    public ResponseEntity<?> AddClient(@RequestParam("id") String id, @RequestParam("password") String password){
        return signupService.Signup(id, password);
    }

}
