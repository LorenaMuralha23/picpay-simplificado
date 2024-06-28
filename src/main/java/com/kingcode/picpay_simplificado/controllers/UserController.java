package com.kingcode.picpay_simplificado.controllers;

import com.kingcode.picpay_simplificado.domain.user.User;
import com.kingcode.picpay_simplificado.dtos.UserDTO;
import com.kingcode.picpay_simplificado.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/users")
public class UserController {
    
    @Autowired
    private UserService service;
    
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDTO user){
        User newUser = service.createUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }
    
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = this.service.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    
}
