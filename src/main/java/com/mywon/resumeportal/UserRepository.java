package com.mywon.resumeportal;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mywon.resumeportal.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUserName(String userName);
}