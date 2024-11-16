package com.DFS.MasterNode.Services;

import com.DFS.MasterNode.Models.Client;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class SignupService {

    private final  ClientDetailsService clientDetailsService;
    private final JWTService jwtService;

    public SignupService (ClientDetailsService clientDetailsService, JWTService jwtService){
        this.clientDetailsService = clientDetailsService;
        this.jwtService = jwtService;
    }

    public ResponseEntity<?> Signup(String id, String password){
        if(clientDetailsService.isExistingClient(id)){
            return new ResponseEntity<>("User already exists", HttpStatus.CONFLICT);
        }
        Client client = new Client();
        client.password = password;
        client.id = id;
        clientDetailsService.AddClient(client);
        var token = jwtService.buildToken(new HashMap<>(), client.id);
        return new ResponseEntity<>(token, HttpStatus.ACCEPTED);
    }



}
