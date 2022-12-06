package com.abistudio.testspringboot.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository <UserInfo, Integer> {
    Optional<UserInfo> findByUsername(String username);
}
