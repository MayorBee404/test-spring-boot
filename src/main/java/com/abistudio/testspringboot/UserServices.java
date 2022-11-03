package com.abistudio.testspringboot;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Vector;


@Service
public class UserServices {

    public Vector<MyUser> getAllUsers() {
        return UserRepository.getAllUsersData();
    }
}
