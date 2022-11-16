package com.abistudio.testspringboot.user;

import com.abistudio.testspringboot.jwt.JwtTokenUtility;
import com.abistudio.testspringboot.myuser.MyUser;
import com.abistudio.testspringboot.myuser.UserRepository;
import com.abistudio.testspringboot.myuser.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Vector;

@Controller
public class UserController {

    @Autowired
    UserServices userServices;
    JwtTokenUtility jwtTokenUtility;

    AuthenticationManager authenticationManager;

    public MyUser user;

    @GetMapping("/users")
    public ResponseEntity<Vector<MyUser>> getAll() {
        return new ResponseEntity<Vector<MyUser>>(userServices.getAllUsers(), HttpStatus.OK);
    }

    @RequestMapping(value = "/users/add", method = RequestMethod.POST)
    public ResponseEntity<String> addUser(@RequestBody MyUser user) {
        UserRepository.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginInfo loginInfo) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginInfo.getUsername(), loginInfo.getPassword())

        );
        UserInfo userInfo = (UserInfo) authentication.getPrincipal();
        String accessToken = jwtTokenUtility.generateAccessToken(userInfo);
        LoginResponse loginResponse = new LoginResponse(userInfo.getUsername(), accessToken);
        return ResponseEntity.ok(loginResponse);
    }

}
