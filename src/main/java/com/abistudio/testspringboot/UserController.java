package com.abistudio.testspringboot;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Vector;

@Controller
public class UserController {

    @Autowired
    UserServices userServices;

    public MyUser user;

    @GetMapping("/users")
    public ResponseEntity<Vector<MyUser>>getAll(){
        return new ResponseEntity<Vector<MyUser>>(userServices.getAllUsers(), HttpStatus.OK);
    }

    @RequestMapping(value = "/users/add",method = RequestMethod.POST)
    public ResponseEntity<String>addUser(@RequestBody MyUser user){
        UserRepository.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }



}
