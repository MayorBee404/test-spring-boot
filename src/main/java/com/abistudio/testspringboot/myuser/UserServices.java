package com.abistudio.testspringboot.myuser;

import org.springframework.stereotype.Service;

import java.util.Vector;


@Service
public class UserServices {

    public Vector<MyUser> getAllUsers() {
        return UserRepository.getAllUsersData();
    }
}
